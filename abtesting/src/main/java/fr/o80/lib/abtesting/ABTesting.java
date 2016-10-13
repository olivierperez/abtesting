package fr.o80.lib.abtesting;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class of ABTesting library, it is used to configure AB tests and retrieve the AB case of the user.
 *
 * @author Olivier Perez
 */
public class ABTesting {

    private Map<String, ABPercentConfig> configs;

    private ABStore store;

    public ABTesting(Context context) {
        this.configs = new HashMap<>();

        SharedPreferences sharedPref = context.getSharedPreferences("ABTestingSharedPref", Context.MODE_PRIVATE);
        this.store = new ABStore(sharedPref);
    }

    /**
     * Configure a AB test by percent.
     *
     * @param configName   Name of the test
     * @param defaultValue Default value if no cases match
     * @return The objet to configure the case
     */
    public ABPercentConfig percent(String configName, String defaultValue) {
        ABPercentConfig config = new ABPercentConfig(configName, defaultValue);
        configs.put(configName, config);
        return config;
    }

    /**
     * Retrieve the value of the given test for the current user.
     *
     * @param configName Name of the test
     * @return The result of the test for the current user
     */
    public String result(String configName) {
        return configs
                .get(configName)
                .result(store);
    }

    /**
     * Init values, and set the user in a specific AB testing case.
     * Call this method just after the AB testing is configured.
     */
    public void init() {
        for (Map.Entry<String, ABPercentConfig> config : configs.entrySet()) {
            config.getValue().init(store);
        }
    }
}
