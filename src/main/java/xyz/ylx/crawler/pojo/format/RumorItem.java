package xyz.ylx.crawler.pojo.format;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class RumorItem {

    @JsonProperty("date")
    private String date;

    @JsonProperty("explain")
    private String explain;

    @JsonProperty("arttype")
    private String arttype;

    @JsonProperty("author")
    private String author;

    @JsonProperty("section")
    private String section;

    @JsonProperty("abstract")
    private String jsonMemberAbstract;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private int type;

    @JsonProperty("coversqual")
    private String coversqual;

    @JsonProperty("result")
    private String result;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("iscolled")
    private boolean iscolled;

    @JsonProperty("videourl")
    private String videourl;

    @JsonProperty("authordesc")
    private String authordesc;

    @JsonProperty("coverrect")
    private String coverrect;

    @JsonProperty("markstyle")
    private String markstyle;

    @JsonProperty("id")
    private String id;

    @JsonProperty("tag")
    private List<String> tag;
}
