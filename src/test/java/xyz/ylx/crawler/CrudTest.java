package xyz.ylx.crawler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.service.crawler.CountryService;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class CrudTest {

    @Resource
    private CountryService countryService;

    @Test
    public void testCurdCountry() {
        LambdaQueryWrapper<Country> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Country::getProvinceName, "美国").orderByDesc(Country::getModifyTime);

        Country country = countryService.getOne(queryWrapper);

        System.out.println(country);
    }
}
