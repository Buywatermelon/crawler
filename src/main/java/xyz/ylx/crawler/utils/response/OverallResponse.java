package xyz.ylx.crawler.utils.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.entity.Overall;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class OverallResponse {

    @Builder.Default
    @JsonView(Overall.OverallSimpleView.class)
    private boolean success = true;

    @JsonView(Overall.OverallSimpleView.class)
    private List<Overall> results;
}
