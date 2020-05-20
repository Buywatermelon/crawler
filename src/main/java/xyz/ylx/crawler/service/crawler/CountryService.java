package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.entity.Country;

import java.io.IOException;

public interface CountryService extends IService<Country> {

    void crawlerCountry() throws IOException, InterruptedException;
}
