package xyz.ylx.crawler.pojo.format;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.format.item.CitiesItem;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class AreaFormat {
    /**
     * 当前确诊人数
     */
    private int currentConfirmedCount;

    /**
     * 累计确诊人数
     */
    private int confirmedCount;

    /**
     * 治愈人数
     */
    private int curedCount;

    /**
     * 统计数据
     */
    private String statisticsData;

    /**
     * 城市数据
     */
    private List<CitiesItem> cities;

    /**
     * 地区编码
     */
    private int locationId;

    /**
     * 省份简称
     */
    private String provinceShortName;

    /**
     * 评论
     */
    private String comment;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 死亡人数
     */
    private int deadCount;

    /**
     * 疑似人数
     */
    private int suspectedCount;
}