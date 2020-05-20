package xyz.ylx.crawler.utils.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.entity.Wiki;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class WikiResponse {

    @Builder.Default
    private Integer Code = 200;

    @Builder.Default
    private String msg = "success";

    private List<Wiki> wikiList;
}
