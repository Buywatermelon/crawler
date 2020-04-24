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
public class News {

    private int id;

    private String title;

    private String summary;

    private String infoSource;

    private String sourceUrl;

    private LocalDateTime pubDate;

    private String provinceName;

    private String provinceId;

    private LocalDateTime crawlTime;
}
