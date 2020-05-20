package xyz.ylx.crawler.pojo.format;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ylx.crawler.pojo.format.item.RumorItem;

@Data
@Builder
@Accessors(chain = true)
public class RumorFormat{

	@JsonProperty("code")
	private int code;

	@JsonProperty("content")
	private List<RumorItem> content;
}