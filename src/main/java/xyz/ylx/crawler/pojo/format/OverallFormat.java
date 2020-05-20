package xyz.ylx.crawler.pojo.format;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.format.item.overall.*;
import xyz.ylx.crawler.utils.jackson.TimestampToLongDeserialize;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(autoResultMap = true) // 开启注解映射
public class OverallFormat {
    /**
     * 每日图片
     */
    private String dailyPic;

    /**
     * 每日图片列表
     */
    private List<String> dailyPics;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 计数备注
     */
    private String countRemark;

    /**
     * 当前确诊人数
     */
    private int currentConfirmedCount;

    /**
     * 累计确诊人数
     */
    private int confirmedCount;

    /**
     * 可疑人数
     */
    private int suspectedCount;

    /**
     * 治愈愈人数
     */
    private int curedCount;

    /**
     * 死亡人数
     */
    private int deadCount;

    /**
     * 重症病例人数
     */
    private int seriousCount;

    /**
     * 疑似增长
     */
    private int suspectedIncr;

    /**
     * 当前确诊增长
     */
    private int currentConfirmedIncr;

    /**
     * 累计确诊增长
     */
    private int confirmedIncr;

    /**
     * 治愈增长
     */
    private int curedIncr;

    /**
     * 死亡增长
     */
    private int deadIncr;

    /**
     * 重症病例确诊增长
     */
    private int seriousIncr;

    /**
     * 备注一
     */
    private String remark1;

    /**
     * 备注二
     */
    private String remark2;

    /**
     * 备注三
     */
    private String remark3;

    /**
     * 备注四
     */
    private String remark4;

    /**
     * 备注五
     */
    private String remark5;

    /**
     * 注意一
     */
    private String note1;

    /**
     * 注意2
     */
    private String note2;

    /**
     * 注意三
     */
    private String note3;

    /**
     * 一般说明
     */
    private String generalRemark;

    /**
     * 国外备注
     */
    private String abroadRemark;

    /**
     * 选框
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<MarqueeItem> marquee;

    /**
     * 全国趋势图表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<QuanguoTrendChartItem> quanguoTrendChart;

    /**
     * 湖北趋势图表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<HbFeiHbTrendChartItem> hbFeiHbTrendChart;

    /**
     * 国外趋势图表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ForeignTrendChartItem> foreignTrendChart;

    /**
     * 重点国家趋势图表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ImportantForeignTrendChartItem> importantForeignTrendChart;

    /**
     * 国外趋势图全球
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ForeignTrendChartGlobalItem> foreignTrendChartGlobal;

    /**
     * 重要的国外趋势图全球
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ImportantForeignTrendChartGlobalItem> importantForeignTrendChartGlobal;

    /**
     * 国外统计
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ForeignStatistics foreignStatistics;

    /**
     * 全球统计
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private GlobalStatistics globalStatistics;

    /**
     * 全球其他趋势图数据
     */
    private String globalOtherTrendChartData;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = TimestampToLongDeserialize.class)
    @JsonProperty("modifyTime")
    private Timestamp updateTime;
}