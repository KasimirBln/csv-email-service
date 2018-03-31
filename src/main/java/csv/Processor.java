package csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Application;
import utilities.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Processor {
    private static final Logger log = LoggerFactory.getLogger(Processor.class);
    private Path file;

    public Processor(String filename) {
        try {
            file = Paths.get(filename);
        } catch (InvalidPathException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public boolean startProcessing() {
        log.info("Filepath: {0}", file);
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(Processor::splitAndSend);
            return true;
        } catch (IOException | SecurityException e) {
            log.error(e.toString() + " " + 32);
            return false;
        }
    }

    private static void splitAndSend(String line) {
        String[] fields = line.replaceAll("\"", "").split(";");
        if (fields.length == 3) {
            Contact contact = new Contact(fields[0], fields[1], fields[2]);
            if (contact.isHeader()) {
                if (log.isInfoEnabled()) {
                    log.info("CSV-Columns:\n (0) " + fields[0] + "\n (1) " + fields[1] + "\n (2) " + fields[2]);
                }
            } else if (contact.isValid()) {
                Application.getEmailService().send(contact);
            } else {
                log.warn("Invalid contact in csv: '{0}'", line);
            }
        } else {
            log.warn("Invalid line in csv: '{0}'", line);
        }
    }
}
