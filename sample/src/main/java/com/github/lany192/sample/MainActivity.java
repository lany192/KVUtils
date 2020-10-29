package com.github.lany192.sample;

import android.os.Bundle;
import android.view.View;
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
        mEditText = findViewById(R.id.show1);
        showText = findViewById(R.id.show2);
        findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.clear();
                long time = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    SPUtils.putBoolean("test" + i, true);
                    SPUtils.putString("test_str_" + i, "haha" + i);
                    System.out.println("1循环" + i);
                }
                showText.setText(String.format(Locale.getDefault(), "SPUtils运行时间：%d毫秒", System.currentTimeMillis() - time));
            }
        });
        findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KVUtils.get().clear();
                long time = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    KVUtils.get().putBoolean("test" + i, true);
                    KVUtils.get().putString("test_str_" + i, "haha" + i);
                    System.out.println("2循环" + i);
                }
                showText.setText(String.format(Locale.getDefault(), "KVUtils运行时间：%d毫秒", System.currentTimeMillis() - time));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = KVUtils.get().getString("my_key");
                showText.setText("" + value);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KVUtils.get().clear();
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mEditText.getText().toString();
                KVUtils.get().putString("my_key", value);
            }
        });
    }
}
