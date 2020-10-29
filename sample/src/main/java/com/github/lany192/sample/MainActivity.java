package com.github.lany192.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.lany192.KVUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtils.init(getBaseContext());
        KVUtils.get().setEncrypt(true, "sdfsdfsdfs");
        mEditText = findViewById(R.id.show1);
        showText = findViewById(R.id.show2);
        findViewById(R.id.test1).setOnClickListener(v -> {
            SPUtils.clear();
            long time = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                SPUtils.putBoolean("test" + i, true);
                SPUtils.putString("test_str_" + i, "测试内容" + i);
                Log.i("1循环", "" + SPUtils.getBoolean("test" + i));
                Log.i("1循环", "" + SPUtils.getString("test_str_" + i));
            }
            showText.setText(String.format(Locale.getDefault(), "SPUtils运行时间：%d毫秒", System.currentTimeMillis() - time));
        });
        findViewById(R.id.test2).setOnClickListener(v -> {
            KVUtils.get().clear();
            long time = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                KVUtils.get().putBoolean("test" + i, true);
                KVUtils.get().putString("test_str_" + i, "测试内容" + i);
                Log.i("2循环", "" + KVUtils.get().getBoolean("test" + i));
                Log.i("2循环", "" + KVUtils.get().getString("test_str_" + i));
            }
            showText.setText(String.format(Locale.getDefault(), "KVUtils运行时间：%d毫秒", System.currentTimeMillis() - time));
        });
        findViewById(R.id.button2).setOnClickListener(v -> {
            String value = KVUtils.get().getString("my_key");
            showText.setText("" + value);
        });
        findViewById(R.id.button3).setOnClickListener(v -> KVUtils.get().clear());
        findViewById(R.id.button1).setOnClickListener(v -> {
            String value = mEditText.getText().toString();
            KVUtils.get().putString("my_key", value);
        });
    }
}
