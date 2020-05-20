package xyz.ylx.crawler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ylx.crawler.mapper.CountryMapper;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.service.crawler.CountryService;
import xyz.ylx.crawler.utils.validator.ValidList;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
public class CountryTest {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private CountryMapper countryMapper;

    @Resource
    private Validator globalValidator;

    @Test
    void testCountryMybatisXml() {
        List<Country> countryList = countryMapper.getCountry();

        LambdaUpdateChainWrapper<Country> countryUpdateWrapper = new LambdaUpdateChainWrapper<>(countryMapper);
    }

    @Resource
    private CountryService countryService;

    @SneakyThrows
    @Test
    void getCountryList() {
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(
                        HttpRequest
                                .newBuilder()
                                .uri(URI.create("https://ncov.dxy.cn/ncovh5/view/pneumonia"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );

        String reg= "window.getListByCountryTypeService2true = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(response.body());

        LambdaQueryWrapper<Country> countryQuery = new LambdaQueryWrapper<>();

        if (totalMatcher.find()) {
            List<Country> countryList = objectMapper.readValue(
                    totalMatcher.group(1),
                    new TypeReference<>(){}
            );

            ValidList<Country> countryValidList = new ValidList<>(countryList);

            Country c = countryList.get(0);

            Set<ConstraintViolation<List<Country>>> result = globalValidator.validate(countryValidList);
            System.out.println(result);

            Optional.ofNullable(
                    countryService
                            .getOne(countryQuery.eq(Country::getProvinceName, "美国"))
            )
                    .ifPresentOrElse(country -> {
                                if (countryList.get(0).getModifyTime().equals(country.getModifyTime())) {
                                    log.info("该时段国家疫情数据已存在");
                                } else {
                                    countryMapper.insertForeachCountry(countryList);
                                }
                            },
                            () -> //countryService.saveBatch(countryList));
                                     countryMapper.insertForeachCountry(countryList));
        }
    }
}
