package xyz.ylx.crawler.utils.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.entity.News;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class NewsResponse {

    @Builder.Default
    private boolean success = true;

    private List<News> results;
}
