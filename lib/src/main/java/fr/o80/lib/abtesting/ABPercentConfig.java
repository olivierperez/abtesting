package fr.o80.lib.abtesting;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Perez
 */
public class ABPercentConfig {

    private final String name;
    private final String defaultValue;

    private final List<ABPercentCase> cases;

    ABPercentConfig(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.cases = new ArrayList<>();
    }

    public void addCase(int lower, int upper, String value) {
        cases.add(new ABPercentCase(lower, upper, value));
    }

    String result(ABStore store) {
        int value = store.getInt(name, -1);
        for (ABPercentCase aCase : cases) {
            if (aCase.matches(value)) {
                return aCase.value();
            }
        }
        return defaultValue;
    }

    public void init(ABStore store) {
        if (store.getInt(name, -1) == -1) {
            SecureRandom random = new SecureRandom();
            int value = random.nextInt(99);

            store.set(name, value);
        }
    }
}
