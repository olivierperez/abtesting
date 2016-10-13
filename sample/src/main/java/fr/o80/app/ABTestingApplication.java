package fr.o80.app;

import android.app.Application;

import fr.o80.lib.abtesting.ABPercentConfig;
import fr.o80.lib.abtesting.ABTesting;

/**
 * @author Olivier Perez
 */
public class ABTestingApplication extends Application {

    private ABTesting abTesting;

    @Override
    public void onCreate() {
        super.onCreate();

        // Configure ABTesting
        abTesting = new ABTesting(this);

        ABPercentConfig percent = abTesting.percent(ABTestingConst.ABTESTING_EXAMPLE, ABTestingConst.ABTESTING_EXAMPLE_DEFAULT);
        percent.addCase(0, 33, "A");
        percent.addCase(34, 66, "B");

        // Init values
        abTesting.init();
    }

    public ABTesting getABTesting() {
        return abTesting;
    }
}
