package xyz.ylx.crawler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.pojo.entity.Overall;
import xyz.ylx.crawler.service.crawler.OverallService;
import xyz.ylx.crawler.utils.response.OverallResponse;
import javax.validation.constraints.Min;
import static java.util.stream.Collectors.toList;

@Validated
@RestController
public class OverallController {

    private final OverallService overallService;

    public OverallController(OverallService overallService) {
        this.overallService = overallService;
    }

    @GetMapping("/overall/index")
    @JsonView(Overall.OverallSimpleView.class)
    public OverallResponse getOverall(
            @Min(0) @RequestParam(defaultValue = "0") int latest) {

        LambdaQueryWrapper<Overall> overallWrapper = new LambdaQueryWrapper<>();
        overallWrapper.orderByDesc(Overall::getUpdateTime);

        return OverallResponse.builder()
                .results(overallService.list(overallWrapper).stream()
                        .limit(latest + 1)
                        .collect(toList()))
                .build();
    }
}
