# Android-ABTesting

Android-ABTesting simplify the management of AB tests in your Android application.
It is useful, for example, when you want to check if a new feature is appreciated by your users, you can give access to this new feature to a little part of users, the others will not see anything new.
It can also be used when you can to propose 2 ways to access a new feature and you don't know what way will encourage more your user to use the feature.

## Usage

### Configure ABTesting cases

```kotlin
class ABTestingApplication : Application() {

    lateinit var abTesting: ABTesting
        private set

    override fun onCreate() {
        super.onCreate()

        // Configure ABTesting
        abTesting = abConfiguration(this) {
            percentage("example") {
                "A" isIn 1..20
                "B" isIn 21..40
                "default" isFor DefaultCase
            }
        }
    }
}
```

### Take care of ABTesting case

```kotlin
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
}
```

## Download

Android-ABTesting is available on [JitPack](https://jitpack.io/#olivierperez/android-abtesting),
add the JitPack repository in your top level `build.gradle`:
```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
```
and the dependency in the `build.gradle` of the module:

```gradle
dependencies {
    compile 'com.github.olivierperez:abtesting:0.1'
}
```

## License

    Copyright 2016 Olivier Perez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
