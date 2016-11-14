# Android-ABTesting

Android-ABTesting simplify the management of AB tests in your Android application.
It is useful, for example, when you want to check if a new feature is appreciated by your users, you can give access to this new feature to a little part of users, the others will not see anything new.
It can also be used when you can to propose 2 ways to access a new feature and you don't know what way will encourage more your user to use the feature.

## Usage

### Configure ABTesting cases

```java
public class ABTestingApplication extends Application {

    private ABTesting abTesting;

    @Override
    public void onCreate() {
        super.onCreate();

        // Configure ABTesting
        abTesting = new ABTesting(this);

        ABPercentConfig percent = abTesting.percent("example", "default");
        percent.addCase(0, 20, "A");
        percent.addCase(21, 40, "B");

        // Init values
        abTesting.init();
    }

    public ABTesting getABTesting() {
        return abTesting;
    }
}
```

### Take care of ABTesting case

```java
public class ABTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab_testing);

        TextView textView = (TextView) findViewById(R.id.textView);

        // Retrieve ABTest config
        ABTestingApplication application = (ABTestingApplication) getApplication();
        ABTesting abTesting = application.getABTesting();

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
