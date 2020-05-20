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
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public void crawlerCountry() throws IOException {
        String reg = "window.getListByCountryTypeService2true = (.*?)\\}(?=catch)";

        List<Country> countryList = HttpConfig.parseDxyHtmlToList(reg);

        LambdaQueryWrapper<Country> countryQuery = new LambdaQueryWrapper<>();

        Optional.ofNullable(
                this.getOne(countryQuery.eq(Country::getProvinceName, "美国").orderByDesc(Country::getModifyTime))
        )
                .ifPresentOrElse(country -> {
                    if (countryList.get(0).getModifyTime().equals(country.getModifyTime()))
                        log.info("该时段国家疫情数据已存在");
                    else
                        countryMapper.insertForeachCountry(countryList);
                    },
                        () -> countryMapper.insertForeachCountry(countryList)
                );
    }
}
