package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class Emails {
    private static ArrayList<String> domains = new ArrayList<>(
            Arrays.asList("gmail", "msn", "yahoo", "web", "gmx", "1und1", "t-online", "vodafone", "kabeldeutschland"));

    private Emails() {}

    public static String random() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE) + "@" + domains.get((random.nextInt(Integer.MAX_VALUE) % domains.size())) + ".com";
    }
}
