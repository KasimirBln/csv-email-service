package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import utilities.Contact;

@Component
public class EmailService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private boolean isMocked;
    private String smtpHost;
    private int smtpPort;
    private boolean smtpSsl;
    private String user;
    private String password;
    private String emailFrom;

    public EmailService(boolean isMocked, String smtpHost, int smtpPort, boolean smtpSsl, String user, String password, String emailFrom) {
        this.isMocked = isMocked;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpSsl = smtpSsl;
        this.user = user;
        this.password = password;
        this.emailFrom = emailFrom;
    }

    @Bean
    public void send(Contact contact) {
        String logMessage = "Sending: ";
        if (contact.isHeader()) {
            return;
        }
        logMessage += (contact.toString());
        if (!contact.isValid()) {
            logMessage += (" ... (failed) ...");
        } else {
            if (isMocked) {
                logMessage += (" ... (mocked) ...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(e.getLocalizedMessage());
                    Thread.currentThread().interrupt();
                }
            } else {
                SimpleEmail email = new SimpleEmail();
                try {
                    email.setHostName(smtpHost);
                    email.setSmtpPort(smtpPort);
                    email.setAuthenticator(new DefaultAuthenticator(user, password));
                    email.setSSLOnConnect(smtpSsl);
                    email.setFrom(emailFrom);
                    email.setSubject("TestMail");
                    email.setMsg("Dear " + contact.getFirstName() + " " + contact.getLastName() + ",\nThis is a test mail ... :-)");
                    email.addTo(contact.getEmailAddress());
                    email.send();
                    logMessage += (" ... (sent) ...");
                } catch (EmailException e) {
                    logMessage += (" ... (failed) ...");
                    log.error(e.getLocalizedMessage());
                }
            }
        }
        log.info("{} done.", logMessage);
    }
}
