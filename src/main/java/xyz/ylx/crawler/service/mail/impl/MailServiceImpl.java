package xyz.ylx.crawler.service.mail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xyz.ylx.crawler.service.mail.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMail(Object to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        if (to instanceof String) {
            message.setTo((String) to);
        } else if (to instanceof String[]) {
            message.setTo((String[]) to);
        }
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendHtmlMail(Object to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            if (to instanceof String) {
                messageHelper.setTo((String) to);
            } else if (to instanceof String[]) {
                messageHelper.setTo((String[]) to);
            }
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            mailSender.send(message);

            log.info("邮件已发送");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常");
        }
    }

    @Override
    public void sentAttachmentMail(Object to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));

            if (to instanceof String) {
                messageHelper.setTo((String) to);
            } else if (to instanceof String[]) {
                messageHelper.setTo((String[]) to);
            }
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            messageHelper.addAttachment(fileName, file);
            mailSender.send(message);

            log.info("邮件已发送");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常");
        }
    }
}
