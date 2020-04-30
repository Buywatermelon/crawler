package xyz.ylx.crawler.pojo.bean;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Rumor {

    private String id;

    private String date;

    private String title;

    @TableField("`explain`")
    private String explain;

    private String imgSrc;

    private String markStyle;

    @TableField("`desc`")
    private String desc;

    private String author;
}
