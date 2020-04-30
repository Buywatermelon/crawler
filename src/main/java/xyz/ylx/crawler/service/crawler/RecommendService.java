package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.bean.Recommend;

import java.io.IOException;

public interface RecommendService extends IService<Recommend> {

    void recommend() throws IOException, InterruptedException;
}
