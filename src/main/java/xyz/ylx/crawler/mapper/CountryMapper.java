package xyz.ylx.crawler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.ylx.crawler.pojo.entity.Country;
import java.util.List;

@Mapper
public interface CountryMapper extends BaseMapper<Country> {

    List<Country> getCountry();
    
    int insertForeachCountry(List<Country> list);
}
