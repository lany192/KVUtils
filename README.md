# SharedPreferences
This is a tool library that encapsulates the commonly used methods of Android SharedPreferences for simplicity.

support minSdkVersion 9
# setup1 Gradle
    dependencies {
        compile 'com.lany:SharedPreferences:1.0.3'
    }
#  setup2 init
    public class MyApp extends Application {
    
        @Override
        public void onCreate() {
            super.onCreate();
            SPHelper.getInstance().init(this, BuildConfig.DEBUG);
            //If you need to encrypt
            SPHelper.getInstance().setEncrypt(true, "user custom password");
        }
    }

or first activity

    public class MainActivity extends AppCompatActivity {
    
        @Override
        public void onCreate() {
            super.onCreate();
            setContentView(R.layout.activity_main);
            SPHelper.getInstance().init(this, BuildConfig.DEBUG);
            //If you need to encrypt
            SPHelper.getInstance().setEncrypt(true, "user custom password");
        }
    }
# setup3
     SPHelper.getInstance().putString("my_key", value);
     boolean result = SPHelper.getInstance().putStringForResult("my_key", value);
     String value = SPHelper.getInstance().getString("my_key");
     ......