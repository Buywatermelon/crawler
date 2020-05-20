package xyz.ylx.crawler.utils.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class ChinaSeriesItem {
    private int confirmedCount;
    private int currentConfirmedCount;
    private int curedCount;
    private int currentConfirmedIncr;
    private int confirmedIncr;
    private int curedIncr;
    private int dateId;
    private int suspectedCountIncr;
    private int deadCount;
    private int deadIncr;
    private int suspectedCount;
}
