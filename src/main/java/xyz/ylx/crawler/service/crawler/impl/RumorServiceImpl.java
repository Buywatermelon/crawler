package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.ylx.crawler.mapper.RumorMapper;
import xyz.ylx.crawler.pojo.bean.Rumor;
import xyz.ylx.crawler.pojo.format.RumorFormat;
import xyz.ylx.crawler.constant.ApiUri;
import xyz.ylx.crawler.service.crawler.RumorService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class RumorServiceImpl extends ServiceImpl<RumorMapper, Rumor> implements RumorService {

    private final ObjectMapper objectMapper;

    public RumorServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void rumor() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(ApiUri.TX_HOST + ApiUri.RUMOR + "?page=0"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        RumorFormat rumorFormat = objectMapper.readValue(response.body(), RumorFormat.class);

        List<Rumor> rumorList = rumorFormat.getContent()
                .stream()
                .map(rumorItem ->
                        Rumor.builder()
                                .id(rumorItem.getId())
                                .date(rumorItem.getDate())
                                .title(rumorItem.getTitle())
                                .explain(rumorItem.getExplain())
                                .imgSrc(rumorItem.getCover())
                                .markStyle(rumorItem.getMarkstyle())
                                .desc(rumorItem.getJsonMemberAbstract())
                                .author(rumorItem.getAuthor())
                                .build())
                .takeWhile(r -> ObjectUtils.isEmpty(this.getById(r.getId())) )
                .collect(toList());

        try {
            this.saveBatch(rumorList);
        } catch(RuntimeException e) {
            throw new RuntimeException();
        }
    }
}
