package xyz.ylx.crawler.pojo.format.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncrVo{
    /**
     * 相比昨天现存确诊人数
     */
    private int currentConfirmedIncr;

    /**
     * 相比昨天累计确诊人数
     */
    private int confirmedIncr;

    /**
     * 相比昨天累计治愈人数
     */
    private int curedIncr;

    /**
     * 相比昨天累计死亡人数
     */
    private int deadIncr;
}
