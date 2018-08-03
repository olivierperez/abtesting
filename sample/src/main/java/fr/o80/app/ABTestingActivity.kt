package fr.o80.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class ABTestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ab_testing)

        val textView = findViewById<TextView>(R.id.textView)

        // Apply changes for there current AB testing case
        val app = application as ABTestingApplication
        val abValue = app.abTesting.result(ABTestingConst.ABTESTING_EXAMPLE)
        when (abValue) {
            "A" -> {
                textView.text = "(A) case"
            }
            "B" -> {
                textView.text = "(B) case"
            }
            else -> {
                textView.text = "Default case"
            }
        }
    }
}
