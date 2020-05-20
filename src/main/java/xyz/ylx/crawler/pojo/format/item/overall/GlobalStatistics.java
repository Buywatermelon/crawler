package xyz.ylx.crawler.pojo.format.item.overall;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import xyz.ylx.crawler.pojo.entity.Overall;

@Data
public class GlobalStatistics{

    @JsonView(Overall.OverallSimpleView.class)
    private int currentConfirmedCount;

    @JsonView(Overall.OverallSimpleView.class)
    private int confirmedCount;

    @JsonView(Overall.OverallSimpleView.class)
    private int curedCount;

    @JsonView(Overall.OverallSimpleView.class)
    private int currentConfirmedIncr;

    @JsonView(Overall.OverallSimpleView.class)
    private int confirmedIncr;

    @JsonView(Overall.OverallSimpleView.class)
    private int curedIncr;

    @JsonView(Overall.OverallSimpleView.class)
    private int deadCount;

    @JsonView(Overall.OverallSimpleView.class)
    private int deadIncr;
}
