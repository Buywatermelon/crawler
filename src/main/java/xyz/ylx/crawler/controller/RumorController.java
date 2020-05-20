package xyz.ylx.crawler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.pojo.entity.Rumor;
import xyz.ylx.crawler.service.crawler.RumorService;
import xyz.ylx.crawler.utils.response.RumorResponse;
import java.util.Optional;

@RestController
public class RumorController {

    private final RumorService rumorService;

    public RumorController(RumorService rumorService) {
        this.rumorService = rumorService;
    }

    @GetMapping("/rumour/index")
    public RumorResponse getRumor(
            @RequestParam(required = false) String world,
            @RequestParam(defaultValue = "10") int num,
            @RequestParam(defaultValue = "0") int page) {

        LambdaQueryWrapper<Rumor> rumorWrapper = new LambdaQueryWrapper<>();
        Page<Rumor> rumorPage = new Page<>(page, num);

        rumorWrapper.orderByDesc(Rumor::getDate);
        Optional.ofNullable(world)
                .ifPresent(w -> rumorWrapper.like(Rumor::getTitle, w));

        return RumorResponse.builder()
                .newslist(rumorService.page(rumorPage, rumorWrapper).getRecords())
                .build();
    }
}
