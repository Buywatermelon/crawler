package xyz.ylx.crawler.pojo.format.item.overall;

import lombok.Data;

@Data
public class ForeignStatistics{
    private int currentConfirmedCount;
    private int confirmedCount;
    private int curedCount;
    private int currentConfirmedIncr;
    private int suspectedIncr;
    private int confirmedIncr;
    private int curedIncr;
    private int deadCount;
    private int deadIncr;
    private int suspectedCount;
}
