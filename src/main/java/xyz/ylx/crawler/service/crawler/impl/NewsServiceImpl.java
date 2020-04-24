package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.mapper.NewsMapper;
import xyz.ylx.crawler.pojo.bean.News;
import xyz.ylx.crawler.pojo.format.NewsFormat;
import xyz.ylx.crawler.service.crawler.NewsService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    private final ObjectMapper objectMapper;

    public NewsServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public void news() {
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
                .takeWhile(n -> ObjectUtils.isEmpty(this.getById(n.getId())) )
                .collect(toList());

        this.saveBatch(newsList);
    }
}
