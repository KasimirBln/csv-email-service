package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class FirstNames {
    private static ArrayList<String> names = new ArrayList<>(
            Arrays.asList("Amadeus", "Berta", "Camille", "David", "Emilla", "Fridolin", "Gustav", "Hanna", "Ide", "Jan", "Kim"));

    private FirstNames() {}

    public static String random() {
        return names.get((new Random().nextInt(Integer.MAX_VALUE) % names.size()));
    }
}
