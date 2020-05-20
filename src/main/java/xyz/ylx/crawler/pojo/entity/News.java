package xyz.ylx.crawler.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.utils.jackson.LocalDateTimeToLongDeserialize;

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

    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime pubDate;

    private String provinceName;

    private String provinceId;

    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime crawlTime;
}
