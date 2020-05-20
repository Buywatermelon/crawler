package xyz.ylx.crawler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.service.crawler.CountryService;
import xyz.ylx.crawler.utils.response.ChinaSeriesResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class CountryController {

    private final ObjectMapper objectMapper;

    private final CountryService countryService;

    public CountryController(CountryService countryService, ObjectMapper objectMapper) {
        this.countryService = countryService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @GetMapping("/chinaSeries/index")
    public ChinaSeriesResponse getChinaSeriesData() {
        LambdaQueryWrapper<Country> countryQuery = new LambdaQueryWrapper<>();

        String chinaSeriesUri = countryService.getOne(
                countryQuery
                        .eq(Country::getProvinceName, "中国")
                        .orderByDesc(Country::getModifyTime)
        ).getStatisticsData();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create(chinaSeriesUri))
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), ChinaSeriesResponse.class);
    }
}
