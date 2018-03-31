package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class LastNames {
        private static ArrayList<String> names = new ArrayList<>(
                Arrays.asList("Müller", "Zimmermann", "Richter", "Fischer", "Kaufmann", "Steuer", "Reif", "Alt", "Klein", "Groß"));

        private LastNames() {}

        public static String random() {
            return names.get((new Random().nextInt(Integer.MAX_VALUE) % names.size()));
        }
    }