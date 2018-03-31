package service;

import csv.Generator;
import csv.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Configuration
public class Initialisation {
    @Value("${csv.testfile}")
    private String csvFileDefault;
    @Value("${email.isMocked}")
    private boolean isMocked;
    @Value("${email.smtp.host}")
    private String smtpHost;
    @Value("${email.smtp.port}")
    private int smtpPort;
    @Value("${email.smtp.useSsl}")
    private boolean smtpSsl;
    @Value("${email.smtp.user}")
    private String user;
    @Value("${email.smtp.password}")
    private String password;

    @Value("${email.from}")
    private String emailFrom;

    @RequestMapping("/")
    @ResponseBody
    public HttpEntity<PageTemplate> landingPage() {
        PageTemplate page = new PageTemplate("landing page. list of available endpoints:");
        page.add(linkTo(methodOn(Initialisation.class).landingPage()).withSelfRel());
        page.add(linkTo(methodOn(Initialisation.class).start("csvFile")).withRel("start processing csv"));
        page.add(linkTo(methodOn(Initialisation.class).generateCsv()).withRel("generate a big csv"));

        return new ResponseEntity<>(page, HttpStatus.OK);

    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity<PageTemplate> start(@RequestParam(value = "csvFile", required = false) String csvFile) {
        PageTemplate page = new PageTemplate("csv file processed ");
        page.add(linkTo(methodOn(Initialisation.class).landingPage()).withRel("landing page"));
        page.add(linkTo(methodOn(Initialisation.class).start("csvFile")).withSelfRel());
        Application.initializeEmailService(isMocked, smtpHost, smtpPort, smtpSsl, user, password, emailFrom);
        if (new Processor(csvFile == null || csvFile.equals("") ? csvFileDefault : csvFile).startProcessing()) {
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(page, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/generateCsv")
    @ResponseBody
    public HttpEntity<PageTemplate> generateCsv() {
        PageTemplate page = new PageTemplate("csv file generated ");
        page.add(linkTo(methodOn(Initialisation.class).landingPage()).withRel("landing page"));
        page.add(linkTo(methodOn(Initialisation.class).generateCsv()).withSelfRel());

        csvFileDefault = new Generator().generateCsvFile();

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    public String getCsvFileDefault() {
        return csvFileDefault;
    }

    public boolean isMocked() {
        return isMocked;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public boolean isSmtpSsl() {
        return smtpSsl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailFrom() {
        return emailFrom;
    }
}
