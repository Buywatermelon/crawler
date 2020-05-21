package xyz.ylx.crawler.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.pojo.entity.Recommend;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 配置单例HttpClient
 * 配置单例HttpRequest
 * 注入原型dxyHtml对象
 * 泛型静态方法，为较为通用的请求——解析流程
 */
@Component
public class HttpConfig {

    private static final HttpConfig HTTP_PARSE_INSTANCE = new HttpConfig();

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private String dxyHtml;

    @PostConstruct
    public void init() {
        HTTP_PARSE_INSTANCE.objectMapper = objectMapper;
        HTTP_PARSE_INSTANCE.dxyHtml = dxyHtml;
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(5000))
                .executor(Executors.newFixedThreadPool(5))
                .build();
    }

    @Bean
    public HttpRequest txRumorHttpRequest() {
        return HttpRequest
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .uri(URI.create(ApiUri.TX_HOST + ApiUri.RUMOR + "?page=0"))
                .timeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public HttpRequest dxyNewsHttpRequest() {
        return HttpRequest
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .uri(URI.create(ApiUri.DXY_FILE + ApiUri.NEWS))
                .timeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public HttpRequest dxyHtmlHttpRequest() {
        return HttpRequest
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .uri(URI.create(ApiUri.DXY))
                .timeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    @Lazy
    @DependsOn(value = {"httpClient", "dxyHtmlHttpRequest"})
    @Scope(value = "prototype")
    public String dxyHtml(HttpClient httpClient, HttpRequest dxyHtmlHttpRequest) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(
                dxyHtmlHttpRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    @SneakyThrows
    public static List<Country> parseDxyHtmlToCountryList(String reg) {
        Matcher matcher = Pattern.compile(reg).matcher(HTTP_PARSE_INSTANCE.dxyHtml);
        if (matcher.find()) {
            return HTTP_PARSE_INSTANCE.objectMapper.readValue(
                    matcher.group(1),
                    new TypeReference<List<Country>>() {}
            );
        }
        return null;
    }

    @SneakyThrows
    public static List<Recommend> parseDxyHtmlToRecommendList(String reg) {
        Matcher matcher = Pattern.compile(reg).matcher(HTTP_PARSE_INSTANCE.dxyHtml);
        if (matcher.find()) {
            return HTTP_PARSE_INSTANCE.objectMapper.readValue(
                    matcher.group(1),
                    new TypeReference<List<Recommend>>() {}
            );
        }
        return null;
    }

    /**
     * 通用解析，通过正则解析从丁香园疫情页面中获取所需Json，将Json解析为T
     * @param reg 正则语句
     * @param clazz Object类型
     * @param <T> Object泛型
     * @return T
     * @throws JsonProcessingException json解析异常
     */
    public static <T> T parseDxyHtmlToObject(String reg, Class<T> clazz) throws JsonProcessingException {
        Matcher matcher = Pattern.compile(reg).matcher(HTTP_PARSE_INSTANCE.dxyHtml);
        T t = null;
        if (matcher.find()) {
            t = HTTP_PARSE_INSTANCE.objectMapper.readValue(
                    matcher.group(1),
                    clazz
            );
        }
        return t;
    }
}
