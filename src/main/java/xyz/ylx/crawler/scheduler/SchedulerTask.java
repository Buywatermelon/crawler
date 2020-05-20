package xyz.ylx.crawler.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.ylx.crawler.constant.Mail;
import xyz.ylx.crawler.service.crawler.*;
import xyz.ylx.crawler.service.mail.MailService;
import javax.annotation.Resource;

/**
 * 任务调度
 */
@Slf4j
@Component
public class SchedulerTask {

    @Resource
    private RumorService rumorService;

    @Resource
    private NewsService newsService;

    @Resource
    private RecommendService recommendService;

    @Resource
    private OverallService overallService;

    @Resource
    private CountryService countryService;

    @Resource
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void scheduledRumor() {
        log.info("疫情谣言爬虫开始运行");
        try {
            rumorService.crawlerRumor();
        } catch (Exception e) {
            log.error(e.getMessage());
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情谣言"));
        }
        log.info("疫情谣言爬虫结束运行");
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void scheduleNews() {
        log.info("疫情新闻爬虫开始运行");
        try {
            newsService.crawlerNews();
        } catch (Exception e) {
            log.error(e.getMessage());
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情新闻"));
        }
        log.info("疫情新闻爬虫结束运行");
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void scheduleRecommend() {
        log.info("疫情防护爬虫开始运行");
        try {
            recommendService.recommend();
        } catch (Exception e) {
            log.error(e.getMessage());
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情防护"));
        }
        log.info("疫情防护爬虫结束运行");
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduleOverall() {
        log.info("疫情总体爬虫开始运行");
        try {
            overallService.crawlerOverall();
        } catch (Exception e) {
            log.error(e.getMessage());
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT, getContent("疫情总体"));
        }
        log.info("疫情总体爬虫结束运行");
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduledCountry() {
        log.info("国家疫情信息爬虫开始运行");
        try {
            countryService.crawlerCountry();
        } catch (Exception e) {
            log.error(e.getMessage());
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT, getContent("国家疫情信息"));
        }
        log.info("国家疫情信息爬虫结束运行");
    }

    @SneakyThrows
    private String getContent(String crawlerJob) {
        Context context = new Context();
        context.setVariable("crawlerJob", crawlerJob);

        return templateEngine.process("mail.html", context);
    }
}
