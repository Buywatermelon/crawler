package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.bean.Rumor;

import java.io.IOException;

public interface RumorService extends IService<Rumor> {

    void rumor() throws IOException, InterruptedException;
}
