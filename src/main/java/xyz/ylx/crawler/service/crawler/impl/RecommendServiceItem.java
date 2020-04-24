package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.mapper.RecommendMapper;
import xyz.ylx.crawler.pojo.bean.Recommend;
import xyz.ylx.crawler.pojo.format.RecommendFormat;
import xyz.ylx.crawler.service.crawler.RecommendService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class RecommendServiceItem extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    private final ObjectMapper objectMapper;

    public RecommendServiceItem(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public void recommend() {
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

        this.saveBatch(recommendList);
    }
}
