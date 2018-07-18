package fr.o80.lib.abtesting

import android.content.Context

import java.util.HashMap

/**
 * Base class of ABTesting library, it is used to configure AB tests and retrieve the AB case of the user.
 *
 * @author Olivier Perez
 */
class ABTesting(context: Context) {

    private val configs: MutableMap<String, ABPercentConfig> = HashMap()

    private val store: ABStore by lazy {
        val sharedPref = context.getSharedPreferences("ABTestingSharedPref", Context.MODE_PRIVATE)
        ABStore(sharedPref)
    }

    @AbTestingDsl
    fun percentage(name: String, block: ABPercentConfig.() -> Unit) {
        configs[name] = ABPercentConfig(name).apply(block)
    }

    /**
     * Retrieve the value of the given test for the current user.
     *
     * @param configName Name of the test
     * @return The result of the test for the current user
     */
    fun result(configName: String): String {
        return configs[configName]?.result(store)
                ?: throw IllegalArgumentException("AB configuration not found")
    }

    /**
     * Init values, and set the user in a specific AB testing case.
     * Call this method just after the AB testing is configured.
     */
    fun init() {
        for ((_, value) in configs) {
            value.init(store)
        }
    }
}
