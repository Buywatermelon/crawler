package xyz.ylx.crawler.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.format.item.overall.*;
import xyz.ylx.crawler.utils.jackson.TimestampToLongDeserialize;
import xyz.ylx.crawler.utils.mybatis.handler.GenericTypeHandler;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Overall {
  /**
   * 疫情总体信息简单视图
   */
  public interface OverallSimpleView{};

  /**
   * 每日图片
   */
  private String dailyPic;

  /**
   * 每日图片列表
   */
  @TableField(typeHandler = JacksonTypeHandler.class)
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
  @JsonView(OverallSimpleView.class)
  private int currentConfirmedCount;

  /**
   * 累计确诊人数
   */
  @JsonView(OverallSimpleView.class)
  private int confirmedCount;

  /**
   * 可疑人数
   */
  @JsonView(OverallSimpleView.class)
  private int suspectedCount;

  /**
   * 治愈愈人数
   */
  @JsonView(OverallSimpleView.class)
  private int curedCount;

  /**
   * 死亡人数
   */
  @JsonView(OverallSimpleView.class)
  private int deadCount;

  /**
   * 重症病例人数
   */
  @JsonView(OverallSimpleView.class)
  private int seriousCount;

  /**
   * 疑似增长
   */
  @JsonView(OverallSimpleView.class)
  private int suspectedIncr;

  /**
   * 当前确诊增长
   */
  @JsonView(OverallSimpleView.class)
  private int currentConfirmedIncr;

  /**
   * 累计确诊增长
   */
  @JsonView(OverallSimpleView.class)
  private int confirmedIncr;

  /**
   * 治愈增长
   */
  @JsonView(OverallSimpleView.class)
  private int curedIncr;

  /**
   * 死亡增长
   */
  @JsonView(OverallSimpleView.class)
  private int deadIncr;

  /**
   * 重症病例确诊增长
   */
  @JsonView(OverallSimpleView.class)
  private int seriousIncr;

  /**
   * 备注一
   */
  @JsonView(OverallSimpleView.class)
  private String remark1;

  /**
   * 备注二
   */
  @JsonView(OverallSimpleView.class)
  private String remark2;

  /**
   * 备注三
   */
  @JsonView(OverallSimpleView.class)
  private String remark3;

  /**
   * 备注四
   */
  @JsonView(OverallSimpleView.class)
  private String remark4;

  /**
   * 备注五
   */
  @JsonView(OverallSimpleView.class)
  private String remark5;

  /**
   * 注意一
   */
  @JsonView(OverallSimpleView.class)
  private String note1;

  /**
   * 注意2
   */
  @JsonView(OverallSimpleView.class)
  private String note2;

  /**
   * 注意三
   */
  @JsonView(OverallSimpleView.class)
  private String note3;

  /**
   * 一般说明
   */
  @JsonView(OverallSimpleView.class)
  private String generalRemark;

  /**
   * 国外备注
   */
  private String abroadRemark;

  /**
   * 选框
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<MarqueeItem> marquee;

  /**
   * 全国趋势图表
   */

  @TableField(typeHandler = GenericTypeHandler.class)
  private List<QuanguoTrendChartItem> quanguoTrendChart;

  /**
   * 湖北趋势图表
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<HbFeiHbTrendChartItem> hbFeiHbTrendChart;

  /**
   * 国外趋势图表
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<ForeignTrendChartItem> foreignTrendChart;

  /**
   * 重点国家趋势图表
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<ImportantForeignTrendChartItem> importantForeignTrendChart;

  /**
   * 国外趋势图全球
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<ForeignTrendChartGlobalItem> foreignTrendChartGlobal;

  /**
   * 重要的国外趋势图全球
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private List<ImportantForeignTrendChartGlobalItem> importantForeignTrendChartGlobal;

  /**
   * 国外统计
   */
  @TableField(typeHandler = GenericTypeHandler.class)
  private ForeignStatistics foreignStatistics;

  /**
   * 全球统计
   */
  @JsonView(OverallSimpleView.class)
  @TableField(typeHandler = GenericTypeHandler.class)
  private GlobalStatistics globalStatistics;

  /**
   * 全球其他趋势图数据
   */
  private String globalOtherTrendChartData;

  /**
   * 更新时间
   */
  @TableId
  @JsonProperty("modifyTime")
  @JsonView(OverallSimpleView.class)
  @JsonDeserialize(using = TimestampToLongDeserialize.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private Timestamp updateTime;
}
