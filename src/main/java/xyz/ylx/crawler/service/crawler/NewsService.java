package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.bean.News;

import java.io.IOException;

public interface NewsService extends IService<News> {

    void news() throws IOException, InterruptedException;
}
