package fr.o80.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.o80.lib.abtesting.ABTesting;

public class ABTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab_testing);

        TextView textView = (TextView) findViewById(R.id.textView);

        // Retrieve ABTest config
        ABTestingApplication application = (ABTestingApplication) getApplication();
        ABTesting abTesting = application.getAbTesting();

        // Apply changes for there current AB testing case
        switch (abTesting.result(ABTestingConst.ABTESTING_EXAMPLE)) {
            case "A":
                textView.setText("(A) case");
                break;
            case "B":
                textView.setText("(B) case");
                break;
            default:
                textView.setText("Default case");
                break;
        }
    }
}
