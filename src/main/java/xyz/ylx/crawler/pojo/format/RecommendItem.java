package xyz.ylx.crawler.pojo.format;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import java.sql.Timestamp;

@Data
@Builder
@Accessors(chain = true)
public class RecommendItem {

	@JsonProperty("imgUrl")
	private String imgUrl;

	@JsonProperty("deleted")
	private boolean deleted;

	@JsonProperty("modifyTime")
	private Timestamp modifyTime;

	@JsonProperty("recordStatus")
	private int recordStatus;

	@JsonProperty("createTime")
	private Timestamp createTime;

	@JsonProperty("linkUrl")
	private String linkUrl;

	@JsonProperty("id")
	private int id;

	@JsonProperty("sort")
	private int sort;

	@JsonProperty("title")
	private String title;

	@JsonProperty("contentType")
	private int contentType;

	@JsonProperty("countryType")
	private int countryType;

	@JsonProperty("operator")
	private String operator;
}