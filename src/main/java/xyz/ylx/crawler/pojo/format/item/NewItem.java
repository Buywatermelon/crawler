package xyz.ylx.crawler.pojo.format.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Builder
@Accessors(chain = true)
public class NewItem {

	@JsonProperty("sourceUrl")
	private String sourceUrl;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("id")
	private int id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("provinceId")
	private String provinceId;

	@JsonProperty("pubDate")
	private Timestamp pubDate;

	@JsonProperty("pubDateStr")
	private String pubDateStr;

	@JsonProperty("infoSource")
	private String infoSource;
}