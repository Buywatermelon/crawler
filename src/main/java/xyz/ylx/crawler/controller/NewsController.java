package xyz.ylx.crawler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.pojo.bean.News;
import xyz.ylx.crawler.service.crawler.NewsService;
import xyz.ylx.crawler.utils.NewsResponse;
import java.util.Optional;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/nCoV/api/news")
    public NewsResponse getNews(
            @RequestParam(required = false) String province,
            @RequestParam(defaultValue = "10") int num,
            @RequestParam(defaultValue = "1") int page) {

        LambdaQueryWrapper<News> newsWrapper = new LambdaQueryWrapper<>();
        Page<News> newsPage = new Page<>(page, num);

        newsWrapper.orderByDesc(News::getPubDate);
        Optional.ofNullable(province)
                .ifPresent(p -> newsWrapper.like(News::getProvinceName, p));

        return NewsResponse.builder()
                .results(newsService.page(newsPage, newsWrapper).getRecords())
                .build();
    }
}
