package csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Contact;
import utilities.Emails;
import utilities.FirstNames;
import utilities.LastNames;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Generator {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private int entries = 1000000;

    public String generateCsvFile() {
        File file = new File("test-file-big.csv");
        try {
            Files.delete(Paths.get("test-file-big.csv")); // delete old file
            if (file.createNewFile()) {
                try (FileWriter writer = new FileWriter(file, false)) {
                    for (int i = 0; i < entries; i++) {
                        writer.write(generateContactLine());
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        if (log.isInfoEnabled()) {
            log.info("filled csv file '" + file.getAbsolutePath() + "' with " + entries + " lines");
        }
        return file.getAbsolutePath();
    }

    private String generateContactLine() {
        return new Contact(Emails.random(), FirstNames.random(), LastNames.random()).toCsv() + "\n";
    }
}

