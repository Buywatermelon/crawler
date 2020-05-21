package xyz.ylx.crawler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ylx.crawler.config.HttpConfig;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.pojo.entity.Overall;
import xyz.ylx.crawler.pojo.entity.Recommend;
import xyz.ylx.crawler.pojo.format.RecommendFormat;
import xyz.ylx.crawler.pojo.format.WikiFormat;
import xyz.ylx.crawler.pojo.format.item.RecommendItem;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@SpringBootTest
public class BeanTest {

    @Resource
    private HttpClient httpClient;

    @Test
    @Resource
    void rumorHttpClientTest(HttpRequest txRumorHttpRequest) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(
                txRumorHttpRequest,
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println(response);
    }

    @Test
    @Resource
    void newsHttpClientTest(HttpRequest dxyNewsHttpRequest) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(
                dxyNewsHttpRequest,
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println(response);
    }

    @Test
    @Resource
    void dxyHtmlText(String dxyHtml) {
        System.out.println(dxyHtml);
    }

    @Test
    void parseDxyHtmlTestToCountry() throws JsonProcessingException {
        List<Country> countryList = HttpConfig.parseDxyHtmlToCountryList("window.getListByCountryTypeService2true = (.*?)\\}(?=catch)");
        System.out.println(countryList);
    }

    @Test
    void parseDxyHtmlToOverall() throws JsonProcessingException {
        Overall overall = HttpConfig.parseDxyHtmlToObject("window.getStatisticsService = (.*?)\\}(?=catch)", Overall.class);
        System.out.println(overall);
    }

    @Test
    void parseDxtHtmlToWiki() throws JsonProcessingException {
        WikiFormat wikiFormat = HttpConfig.parseDxyHtmlToObject("window.getWikiList = (.*?)\\}(?=catch)", WikiFormat.class);
        System.out.println(wikiFormat.getResult());
    }

    @Test
    void parseDxyHtmlToRecommend() throws JsonProcessingException {
        List<Recommend> recommendFormat = HttpConfig.parseDxyHtmlToRecommendList("window.getIndexRecommendListundefined = (.*?)\\}(?=catch)");
        System.out.println(recommendFormat);
    }
}
