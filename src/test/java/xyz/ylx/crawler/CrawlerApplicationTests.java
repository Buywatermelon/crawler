package xyz.ylx.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.pojo.bean.News;
import xyz.ylx.crawler.pojo.bean.Recommend;
import xyz.ylx.crawler.pojo.format.NewsFormat;
import xyz.ylx.crawler.pojo.format.RecommendFormat;
import xyz.ylx.crawler.service.crawler.NewsService;
import xyz.ylx.crawler.service.crawler.RecommendService;
import xyz.ylx.crawler.service.crawler.RumorService;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class CrawlerApplicationTests {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RumorService rumorService;

    @Test
    void testRumor() {
        rumorService.rumor();
    }

    @Resource
    private NewsService newsService;

    @SneakyThrows
    @Test
    void testNews() {
        newsService.news();
    }

    @Test
    void testNewsApi () throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(ApiUri.DXY + ApiUri.NEWS))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        NewsFormat newsFormat = objectMapper.readValue(response.body(), NewsFormat.class);
        
         List<News> newsList = newsFormat.getData()
                .stream()
                .map(newItem ->
                        News.builder()
                                .id(newItem.getId())
                                .title(newItem.getTitle())
                                .summary(newItem.getSummary())
                                .infoSource(newItem.getInfoSource())
                                .sourceUrl(newItem.getSourceUrl())
                                .pubDate(LocalDateTime.ofInstant(newItem.getPubDate().toInstant(), ZoneId.of("Asia/Shanghai")))
                                .provinceId(newItem.getProvinceId())
                                .crawlTime(LocalDateTime.now())
                                .build())
                .takeWhile(n -> ObjectUtils.isEmpty(newsService.getById(n.getId())) )
                .collect(toList());

        System.out.println(newsList);

        newsService.saveBatch(newsList);
    }

    @Resource
    private RecommendService recommendService;

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

}
