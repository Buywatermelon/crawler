package xyz.ylx.crawler.pojo.format;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.entity.Wiki;

@Data
@Builder
@Accessors(chain = true)
public class WikiFormat{

    @JsonProperty("result")
    private List<Wiki> result;

    @JsonProperty("total")
    private int total;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("pageNum")
    private int pageNum;
}