package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.mapper.NewsMapper;
import xyz.ylx.crawler.pojo.entity.News;
import xyz.ylx.crawler.pojo.format.NewsFormat;
import xyz.ylx.crawler.service.crawler.NewsService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient;

    private final HttpRequest dxyNewsHttpRequest;

    public NewsServiceImpl(ObjectMapper objectMapper, HttpClient httpClient, HttpRequest dxyNewsHttpRequest) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.dxyNewsHttpRequest = dxyNewsHttpRequest;
    }

    @Override
    public void crawlerNews() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient
                .send(
                        dxyNewsHttpRequest,
                        HttpResponse.BodyHandlers.ofString());

        NewsFormat newsFormat = objectMapper.readValue(response.body(), NewsFormat.class);

        List<News> newsList = newsFormat.getData()
                .stream()
                .takeWhile(n -> ObjectUtils.isEmpty(this.getById(n.getId())) )
                .collect(toList());

        try {
            this.saveBatch(newsList);
        } catch(RuntimeException e) {
            throw new RuntimeException();
        }
    }
}
