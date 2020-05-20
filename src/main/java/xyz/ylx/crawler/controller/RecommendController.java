package xyz.ylx.crawler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.pojo.entity.Recommend;
import xyz.ylx.crawler.service.crawler.RecommendService;
import xyz.ylx.crawler.utils.response.RecommendResponse;

@RestController
public class RecommendController {

    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("recommend/index")
    public RecommendResponse getRecommend(
            @RequestParam(defaultValue = "10") int num,
            @RequestParam(defaultValue = "1") int page) {

        LambdaQueryWrapper<Recommend> recommendWrapper = new LambdaQueryWrapper<>();
        Page<Recommend> recommendPage = new Page<>(page, num);

        recommendWrapper.orderByDesc(Recommend::getCreateTime);

        recommendService.page(recommendPage, recommendWrapper).getRecords();

        return RecommendResponse.builder()
                .recommendList(
                        recommendService.page(recommendPage, recommendWrapper).getRecords()
                )
                .build();
    }
}
