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
public class Recommend {

    private String imgUrl;

    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime modifyTime;

    private int recordStatus;

    @JsonDeserialize(using = LocalDateTimeToLongDeserialize.class)
    private LocalDateTime createTime;

    private String linkUrl;

    private int id;

    private int sort;

    private String title;

    private int contentType;

    private int countryType;

    private String operator;
}
