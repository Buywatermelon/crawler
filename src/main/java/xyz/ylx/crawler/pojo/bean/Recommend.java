package xyz.ylx.crawler.pojo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Recommend {

    private String imgUrl;

    private LocalDateTime modifyTime;

    private int recordStatus;

    private LocalDateTime createTime;

    private String linkUrl;

    private int id;

    private int sort;

    private String title;

    private int contentType;

    private int countryType;

    private String operator;
}
