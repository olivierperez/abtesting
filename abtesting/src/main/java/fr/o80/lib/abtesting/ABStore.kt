package fr.o80.lib.abtesting

import android.content.SharedPreferences

/**
 * @author Olivier Perez
 */
class ABStore(private val sharedPref: SharedPreferences) {

    fun getInt(name: String, defaultValue: Int): Int =
            sharedPref.getInt(name, defaultValue)

    operator fun set(name: String, value: Int) {
        sharedPref.edit()
                .putInt(name, value)
                .apply()
    }
}
