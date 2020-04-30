package xyz.ylx.crawler.scheduler;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.ylx.crawler.constant.Mail;
import xyz.ylx.crawler.service.crawler.NewsService;
import xyz.ylx.crawler.service.crawler.RecommendService;
import xyz.ylx.crawler.service.crawler.RumorService;
import xyz.ylx.crawler.service.mail.MailService;
import javax.annotation.Resource;

@Component
public class SchedulerTask {

    @Resource
    private RumorService rumorService;

    @Resource
    private NewsService newsService;

    @Resource
    private RecommendService recommendService;

    @Resource
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void scheduledRumor() {
        System.out.println("疫情谣言爬虫开始运行");
        try {
            rumorService.rumor();
        } catch (Exception e) {
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情谣言"));
        }
        System.out.println("疫情谣言爬虫结束运行");
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void scheduleNews() {
        System.out.println("疫情新闻爬虫开始运行");
        try {
            newsService.news();
        } catch (Exception e) {
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情新闻"));
        }
        System.out.println("疫情新闻爬虫结束运行");
    }

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void scheduleRecommend() {
        System.out.println("疫情防护爬虫开始运行");
        try {
            recommendService.recommend();
        } catch (Exception e) {
            mailService.sendHtmlMail(Mail.MAIL_TO_0, Mail.MAIL_SUBJECT , getContent("疫情防护"));
        }
        System.out.println("疫情防护爬虫结束运行");
    }

    @SneakyThrows
    private String getContent(String crawlerJob) {
        Context context = new Context();
        context.setVariable("crawlerJob", crawlerJob);

        return templateEngine.process("mail.html", context);
    }
}
