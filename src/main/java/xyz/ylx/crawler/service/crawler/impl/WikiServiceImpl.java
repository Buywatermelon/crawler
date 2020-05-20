package xyz.ylx.crawler.service.crawler.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ylx.crawler.config.HttpConfig;
import xyz.ylx.crawler.mapper.WikiMapper;
import xyz.ylx.crawler.pojo.entity.Wiki;
import xyz.ylx.crawler.pojo.format.WikiFormat;
import xyz.ylx.crawler.service.crawler.WikiService;
import java.util.List;

@Service
@Transactional
public class WikiServiceImpl extends ServiceImpl<WikiMapper, Wiki> implements WikiService {

    @SneakyThrows
    @Override
    public void crawlerWiki() {
        String reg = "window.getWikiList = (.*?)\\}(?=catch)";

        WikiFormat wikiFormat = HttpConfig.parseDxyHtmlToObject(reg, WikiFormat.class);

        List<Wiki> wikiList = wikiFormat.getResult();

        this.saveBatch(wikiList);
    }
}
