package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.entity.Overall;

import javax.annotation.Resource;
import java.io.IOException;

public interface OverallService extends IService<Overall> {

    @Resource
    void crawlerOverall() throws IOException;
}
