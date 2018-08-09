package fr.o80.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ab_testing.*

class ABTestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ab_testing)

        val app = application as ABTestingApplication

        loadABResult(app)

        resetButton.setOnClickListener {
            app.abTesting.reset(ABTestingConst.ABTESTING_EXAMPLE)
            loadABResult(app)
        }
    }

    fun loadABResult(app: ABTestingApplication) {
        // Apply changes for there current AB testing case
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
