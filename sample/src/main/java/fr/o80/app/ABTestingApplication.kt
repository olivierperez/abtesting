package fr.o80.app

import android.app.Application
import android.util.Log
import fr.o80.lib.abtesting.ABPercentConfig.DefaultCase
import fr.o80.lib.abtesting.ABTesting
import fr.o80.lib.abtesting.abConfiguration

/**
 * @author Olivier Perez
 */
class ABTestingApplication : Application() {

    lateinit var abTesting: ABTesting
        private set

    override fun onCreate() {
        super.onCreate()

        // Configure ABTesting
        abTesting = abConfiguration(this) {
            percentage(ABTestingConst.ABTESTING_EXAMPLE) {
                "A" isIn 1..33
                "B" isIn 34..66
                "default" isFor DefaultCase
            }
        }

        Log.d("", "")
    }
}
