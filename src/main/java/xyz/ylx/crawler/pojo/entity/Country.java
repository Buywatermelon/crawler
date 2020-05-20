package xyz.ylx.crawler.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.format.item.IncrVo;
import xyz.ylx.crawler.utils.jackson.LocalDateTimeToLongDeserialize;
import xyz.ylx.crawler.utils.validator.CannotHaveBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(resultMap = "countryMap")
public class Country {
    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime createTime;

    /**
     * 更改时间
     */
    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime modifyTime;

    /**
     * 标签
     */
    private String tags;

    /**
     * 国家类型
     */
    private int countryType;

    /**
     * 大洲
     */
    private String continents;

    /**
     * 省份
     */
    private String provinceId;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 省份简称
     */
    private String provinceShortName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 当前确诊人数
     */
    private int currentConfirmedCount;

    /**
     * 累计确诊人数
     */
    private int confirmedCount;

    /**
     * 确认计数等级
     */
    private int confirmedCountRank;

    /**
     * 疑似确证人数
     */
    private int suspectedCount;

    /**
     * 累计治愈人数
     */
    private int curedCount;

    /**
     * 累计死亡人数
     */
    private int deadCount;

    /**
     * 死亡人数排名
     */
    private int deadCountRank;

    /**
     * 死亡比例
     */
    private String deadRate;

    /**
     * 死亡率排名
     */
    private int deadRateRank;

    /**
     * 评论
     */
    private String comment;

    /**
     * 分类
     */
    private int sort;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 位置编码
     */
    private int locationId;

    /**
     * 国家简写
     */
    private String countryShortCode;

    /**
     * 国家全称
     */
    @CannotHaveBlank()
    private String countryFullName;

    /**
     * 统计数据
     */
    private String statisticsData;

    /**
     * 增长对象
     */
    @TableField(exist = false)
    private IncrVo incrVo;

    /**
     * 显示排名
     */
    private boolean showRank;
}
