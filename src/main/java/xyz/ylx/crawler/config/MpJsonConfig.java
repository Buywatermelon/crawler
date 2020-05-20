package xyz.ylx.crawler.config;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.ylx.crawler.utils.mybatis.handler.GenericTypeHandler;

/**
 * 数据库与JavaBean序列化与反序列化时的配置
 */
@Component
public class MpJsonConfig implements CommandLineRunner {

    @Override
    public void run(String... args) {
        JacksonTypeHandler.setObjectMapper(new ObjectMapper());
        GenericTypeHandler.setObjectMapper(new ObjectMapper());
    }
}
