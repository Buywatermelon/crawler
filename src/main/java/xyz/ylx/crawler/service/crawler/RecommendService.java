package xyz.ylx.crawler.service.crawler;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ylx.crawler.pojo.bean.Recommend;

public interface RecommendService extends IService<Recommend> {

    void recommend();
}
