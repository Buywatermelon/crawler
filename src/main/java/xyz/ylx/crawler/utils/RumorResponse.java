package xyz.ylx.crawler.utils;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.bean.Rumor;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class RumorResponse {

    @Builder.Default
    private Integer Code = 200;

    @Builder.Default
    private String msg = "success";

    private List<Rumor> newslist;
}
