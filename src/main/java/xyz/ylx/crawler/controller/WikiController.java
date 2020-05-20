package xyz.ylx.crawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ylx.crawler.service.crawler.WikiService;
import xyz.ylx.crawler.utils.response.WikiResponse;

@RestController
public class WikiController {

    private final WikiService wikiService;

    public WikiController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @GetMapping("/wiki/index")
    public WikiResponse getWiki() {
        return WikiResponse.builder()
                .wikiList(wikiService.list())
                .build();
    }
}
