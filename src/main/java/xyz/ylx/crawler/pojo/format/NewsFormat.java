package xyz.ylx.crawler.pojo.format;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class NewsFormat {

	@JsonProperty("code")
	private String code;

	@JsonProperty("data")
	private List<NewItem> data;

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("successAndNotNull")
	private boolean successAndNotNull;

	@JsonProperty("message")
	private String message;
}