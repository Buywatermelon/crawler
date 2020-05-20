package xyz.ylx.crawler.utils.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class ChinaSeriesResponse {
    private String code;
    private List<ChinaSeriesItem> data;
    private boolean success;
    private boolean successAndNotNull;
    private String message;
}