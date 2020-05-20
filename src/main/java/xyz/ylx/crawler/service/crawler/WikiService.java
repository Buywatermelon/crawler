package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.entity.Wiki;

public interface WikiService extends IService<Wiki> {

    void crawlerWiki();
}
