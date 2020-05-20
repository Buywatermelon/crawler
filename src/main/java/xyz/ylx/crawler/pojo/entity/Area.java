package xyz.ylx.crawler.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Area {
    /**
     * 大洲名称
     */
    private String continentName;

    /**
     * 大洲英文名称
     */
    private String continentEnglishName;

    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 国家英文名称
     */
    private String countryEnglishName;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 省份英文名称
     */
    private String provinceEnglishName;

    /**
     * 省份编码
     */
    private String provinceZipCode;

    /**
     * 省份确诊人数
     */
    private String provinceConfirmedCount;

    /**
     * 省份可疑人数
     */
    private String provinceSuspectedCount;

    /**
     * 死亡人数
     */
    private String provinceDeadCount;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市英文名称
     */
    private String cityEnglishName;

    /**
     * 城市编码
     */
    private String cityZipCode;

    /**
     * 城市确诊人数
     */
    private String cityConfirmedCount;

    /**
     * 城市疑似人数
     */
    private String citySuspectedCount;

    /**
     * 城市治愈人数
     */
    private String cityCuredCount;

    /**
     * 城市死亡人数
     */
    private String cityDeadCount;
}
