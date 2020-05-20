package xyz.ylx.crawler.utils.exception.wrapper;

import lombok.extern.slf4j.Slf4j;
import java.util.function.Consumer;

/**
 * 对lambda进行异常处理的包装
 */
@Slf4j
public class HandlingConsumerWrapper {

    public static <T> Consumer<T> handlingConsumerWrapper(Consumer<T> throwingConsumer) {
        return consumer -> {
            try {
                throwingConsumer.accept(consumer);
            } catch (Exception e) {
                log.warn("该时间更新的疫情总体信息已存在");
            }
        };
    }
}
