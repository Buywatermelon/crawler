package xyz.ylx.crawler.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * 时间戳转换为Timestamp
 */
public class TimestampToLongDeserialize extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        long longTimestamp = jsonParser.getLongValue();
        return Timestamp.from(Instant.ofEpochMilli(longTimestamp));
    }
}
