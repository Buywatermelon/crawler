package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import xyz.ylx.crawler.mapper.RumorMapper;
import xyz.ylx.crawler.pojo.bean.Rumor;
import xyz.ylx.crawler.pojo.format.RumorFormat;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.service.crawler.RumorService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static java.util.stream.Collectors.toList;

@Service
public class RumorServiceImpl extends ServiceImpl<RumorMapper, Rumor> implements RumorService {

    private final ObjectMapper objectMapper;

    public RumorServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void rumor() {
        HttpClient.newHttpClient()
                .sendAsync(
                        rumorRequest(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(response ->
                        jsonToPojo(response.body(), RumorFormat.class).getContent()
                                .stream()
                                .map(rumorItem ->
                                        Rumor.builder()
                                                .id(rumorItem.getId())
                                                .date(rumorItem.getDate())
                                                .title(rumorItem.getTitle())
                                                .explain(rumorItem.getExplain())
                                                .imgsrc(rumorItem.getCover())
                                                .markstyle(rumorItem.getMarkstyle())
                                                .desc(rumorItem.getJsonMemberAbstract())
                                                .author(rumorItem.getAuthor())
                                                .build()
                                ).collect(toList()))
                .thenAccept(this::saveOrUpdateBatch);
    }

    private HttpRequest rumorRequest() {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(ApiUri.TX_HOST + ApiUri.RUMOR + "?page=0"))
                .build();
    }

    @SneakyThrows
    private <T> T jsonToPojo(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}
