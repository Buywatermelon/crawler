package xyz.ylx.crawler.pojo.format;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.entity.News;
import xyz.ylx.crawler.pojo.format.item.NewItem;

@Data
@Builder
@Accessors(chain = true)
public class NewsFormat {

	@JsonProperty("code")
	private String code;

	@JsonProperty("data")
		private List<News> data;

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("successAndNotNull")
	private boolean successAndNotNull;

	@JsonProperty("message")
	private String message;
}