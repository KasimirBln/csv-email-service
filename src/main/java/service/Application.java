package service;

import email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static void initializeEmailService(boolean isMocked, String smtpHost, int smtpPort, boolean smtpSsl, String user, String password, String emailFrom) {
        emailService = new EmailService(isMocked, smtpHost, smtpPort, smtpSsl, user, password, emailFrom);
    }

    public static EmailService getEmailService() {
        return emailService;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.info(beanName);
            }

        };
    }
}
