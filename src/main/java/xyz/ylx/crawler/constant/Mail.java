package xyz.ylx.crawler.constant;

public class Mail {

    /**
     * 邮件发送主题
     */
    public static final String MAIL_SUBJECT = "爬虫任务异常邮件通知";

    /**
     * 邮件发送地址
     */
    public static final String MAIL_TO_0 = "15849273091@163.com";

    /**
     * 邮件发送地址
     */
    public static String MAIL_TO_1 = "ylx150512@163.com";

    /**
     * 若需要发送多个地址，在此处定义
     */
    public static final String[] MAIL_TO_LIST = {MAIL_TO_0, MAIL_TO_1};
}