package xyz.ylx.crawler.utils.mybatis.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * 将mysql中的json格式的字段转化为java中的对象类型T
 */
@MappedTypes({Object.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class GenericTypeHandler extends AbstractJsonTypeHandler<Object> {

    private static ObjectMapper objectMapper;
    private final Class<Object> type;

    public GenericTypeHandler(Class<Object> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }

    @SneakyThrows
    @Override
    protected Object parse(String json) {
        return objectMapper.readValue(json, this.type);
    }

    @SneakyThrows
    @Override
    protected String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        GenericTypeHandler.objectMapper = objectMapper;
    }
}
