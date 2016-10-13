package fr.o80.lib.abtesting;

import android.content.SharedPreferences;

/**
 * @author Olivier Perez
 */
class ABStore {

    private SharedPreferences sharedPref;

    ABStore(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    int getInt(String name, int defaultValue) {
        return sharedPref.getInt(name, defaultValue);
    }

    void set(String name, int value) {
        sharedPref.edit()
                .putInt(name, value)
                .apply();
    }
}
