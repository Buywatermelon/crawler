package xyz.ylx.crawler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.constant.Continent;
import xyz.ylx.crawler.pojo.entity.*;
import xyz.ylx.crawler.pojo.format.*;
import xyz.ylx.crawler.service.crawler.*;
import xyz.ylx.crawler.utils.exception.wrapper.HandlingConsumerWrapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

@Slf4j
@SpringBootTest
class CrawlerApplicationTests {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RumorService rumorService;

    @SneakyThrows
    @Test
    void testRumor() {
        rumorService.crawlerRumor();
    }

    @Resource
    private NewsService newsService;

    @SneakyThrows
    @Test
    void testNews() {
        newsService.crawlerNews();
    }

    @Test
    void testNewsApi () throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(ApiUri.DXY_FILE + ApiUri.NEWS))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        NewsFormat newsFormat = objectMapper.readValue(response.body(), NewsFormat.class);
        
         List<News> newsList = newsFormat.getData()
                .stream()
                .takeWhile(n -> ObjectUtils.isEmpty(newsService.getById(n.getId())) )
                .collect(toList());

        System.out.println(newsList);

        newsService.saveBatch(newsList);
    }

    @Resource
    private RecommendService recommendService;

    @SneakyThrows
    @Test
    void testRecommend() {
        recommendService.recommend();
    }

    @SneakyThrows
    @Test
    void testRecommendApi() {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(ApiUri.DXY + ApiUri.RECOMMEND))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        RecommendFormat recommendFormat = objectMapper.readValue(response.body(), RecommendFormat.class);

        List<Recommend> recommendList = recommendFormat.getData()
                .stream()
                .map(recommendItem ->
                        Recommend.builder()
                                .imgUrl(recommendItem.getImgUrl())
                                .modifyTime(LocalDateTime.ofInstant(recommendItem.getModifyTime().toInstant(), ZoneId.of("Asia/Shanghai")))
                                .recordStatus(recommendItem.getRecordStatus())
                                .createTime(LocalDateTime.ofInstant(recommendItem.getCreateTime().toInstant(), ZoneId.of("Asia/Shanghai")))
                                .linkUrl(recommendItem.getLinkUrl())
                                .id(recommendItem.getId())
                                .sort(recommendItem.getSort())
                                .title(recommendItem.getTitle())
                                .contentType(recommendItem.getContentType())
                                .countryType(recommendItem.getCountryType())
                                .operator(recommendItem.getOperator())
                                .build())
                .takeWhile(r -> ObjectUtils.isEmpty(recommendService.getById(r.getId())) )
                .collect(toList());

        recommendService.saveBatch(recommendList);
    }

    /**
     * 将wiki数据落入本地数据库
     * 无增量数据，因此只需运行一遍，无需使用定时任务运行
     */
    @Resource
    private WikiService wikiService;

    @SneakyThrows
    @Test
    void getWikiList() {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create("http://49.232.173.220:3001/data/getWikiList"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );

        WikiFormat wikiFormat = objectMapper.readValue(response.body(), WikiFormat.class);

        List<Wiki> wikiList = wikiFormat.getResult();

        wikiService.saveBatch(wikiList);
    }

        /**
         * 通过解析html获取国内疫情数据
         */
    @SneakyThrows
    @Test
    void getAreaList() {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create("https://ncov.dxy.cn/ncovh5/view/pneumonia"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );

        String reg= "window.getAreaStat = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(response.body());

        if (totalMatcher.find()){
            List<AreaFormat> areaFormatList = objectMapper.readValue(totalMatcher.group(1), List.class);
            System.out.println(areaFormatList);
        }
    }

    @Test
    void testConvertContinentToEn() {
        String continent = "亚洲";
        String cotinentCn = Continent.convertEn(continent);
    }

    /**
     * 通过解析html获取汇总数据
     */
    @Resource
    private OverallService overallService;

    @SneakyThrows
    @Test
    void getOverall() {

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create("https://ncov.dxy.cn/ncovh5/view/pneumonia"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );

        String reg= "window.getStatisticsService = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(response.body());

        if (totalMatcher.find()){
            LambdaQueryWrapper<Overall> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByDesc(Overall::getUpdateTime);

            Optional.ofNullable(
                    objectMapper.readValue(totalMatcher.group(1), Overall.class)
            ).ifPresentOrElse(
                    HandlingConsumerWrapper.handlingConsumerWrapper((overall) -> {
                        overallService.save(overall);
                    }),
                    () -> log.error("xxx")
            );
        }
    }
}
