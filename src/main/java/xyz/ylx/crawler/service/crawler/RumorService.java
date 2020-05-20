package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.entity.Rumor;

import java.io.IOException;

public interface RumorService extends IService<Rumor> {

    void crawlerRumor() throws IOException, InterruptedException;
}
