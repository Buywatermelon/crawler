package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.entity.News;

import java.io.IOException;

public interface NewsService extends IService<News> {

    void crawlerNews() throws IOException, InterruptedException;
}
