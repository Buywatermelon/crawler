package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ylx.crawler.config.HttpConfig;
import xyz.ylx.crawler.mapper.CountryMapper;
import xyz.ylx.crawler.pojo.entity.Country;
import xyz.ylx.crawler.service.crawler.CountryService;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;


@Slf4j
@Service
@Transactional
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public void crawlerCountry() {
        String reg = "window.getListByCountryTypeService2true = (.*?)\\}(?=catch)";

        List<Country> countryList = HttpConfig.parseDxyHtmlToCountryList(reg);

        LambdaQueryWrapper<Country> countryQuery = new LambdaQueryWrapper<>();

        Optional.ofNullable(
                this.getOne(countryQuery.eq(Country::getProvinceName, "美国").orderByDesc(Country::getModifyTime))
        )
                .ifPresentOrElse(country -> {
                            Country American = countryList.stream()
                                    .filter(c -> c.getProvinceName().equals("美国"))
                                    .collect(toList())
                                    .get(0);
                            if (American.getModifyTime().equals(country.getModifyTime()))
                        log.info("该时段国家疫情数据已存在");
                    else
                        countryMapper.insertForeachCountry(countryList);
                    },
                        () -> countryMapper.insertForeachCountry(countryList)
                );
    }
}
