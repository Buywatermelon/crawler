package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ylx.crawler.config.HttpConfig;
import xyz.ylx.crawler.pojo.entity.Overall;
import xyz.ylx.crawler.mapper.OverallMapper;
import xyz.ylx.crawler.service.crawler.OverallService;
import xyz.ylx.crawler.utils.exception.wrapper.HandlingConsumerWrapper;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class OverallServiceImpl extends ServiceImpl<OverallMapper, Overall> implements OverallService {

    @Override
    public void crawlerOverall() throws IOException {

        String reg = "window.getStatisticsService = (.*?)\\}(?=catch)";

        Overall overall = HttpConfig.parseDxyHtmlToObject(reg, Overall.class);

        LambdaQueryWrapper<Overall> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Overall::getUpdateTime);

        Optional.ofNullable(overall)
                .ifPresent(HandlingConsumerWrapper.handlingConsumerWrapper(this::save));
    }
}
