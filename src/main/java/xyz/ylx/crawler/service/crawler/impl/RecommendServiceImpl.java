package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final ObjectMapper objectMapper;

    public RecommendServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void recommend() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(ApiUri.DXY + ApiUri.RECOMMEND))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        RecommendFormat recommendFormat = objectMapper.readValue(response.body(), RecommendFormat.class);

        List<Recommend> recommendList = recommendFormat.getData()
                .stream()
                .map(recommendItem ->
                        Recommend.builder()
                                .imgUrl(recommendItem.getImgUrl())
                                .modifyTime(LocalDateTime.ofInstant(recommendItem.getModifyTime().toInstant(), ZoneId.of("Asia/Shanghai")))
                                .recordStatus(recommendItem.getRecordStatus())
                                .createTime(LocalDateTime.ofInstant(recommendItem.getCreateTime().toInstant(), ZoneId.of("Asia/Shanghai")))
                                .linkUrl(recommendItem.getLinkUrl())
                                .id(recommendItem.getId())
                                .sort(recommendItem.getSort())
                                .title(recommendItem.getTitle())
                                .contentType(recommendItem.getContentType())
                                .countryType(recommendItem.getCountryType())
                                .operator(recommendItem.getOperator())
                                .build())
                .takeWhile(r -> ObjectUtils.isEmpty(this.getById(r.getId())) )
                .collect(toList());

        try {
            this.saveBatch(recommendList);
        } catch(RuntimeException e) {
            throw new RuntimeException();
        }
    }
}
