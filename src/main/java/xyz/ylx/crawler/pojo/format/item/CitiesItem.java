package xyz.ylx.crawler.pojo.format.item;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class CitiesItem{
    /**
     * 当前确诊人数
     */
    private int currentConfirmedCount;

    /**
     * 累计确证人数
     */
    private int confirmedCount;

    /**
     * 治愈人数
     */
    private int curedCount;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市编码
     */
    private int locationId;

    /**
     * 死亡人数
     */
    private int deadCount;

    /**
     * 疑似人数
     */
    private int suspectedCount;
}
