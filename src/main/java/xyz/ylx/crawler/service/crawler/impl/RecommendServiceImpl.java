package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ylx.crawler.config.HttpConfig;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.mapper.RecommendMapper;
import xyz.ylx.crawler.pojo.entity.Recommend;
import xyz.ylx.crawler.pojo.format.RecommendFormat;
import xyz.ylx.crawler.service.crawler.RecommendService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Override
    public void recommend() throws IOException, InterruptedException {
        String reg = "window.getIndexRecommendListundefined = (.*?)\\}(?=catch)";

        List<Recommend> recommendList = HttpConfig.parseDxyHtmlToRecommendList(reg);

        List<Recommend> recommendSaveList = recommendList.stream()
                .takeWhile(r -> ObjectUtils.isEmpty(this.getById(r.getId())))
                .collect(toList());

        this.saveBatch(recommendSaveList);
    }
}
