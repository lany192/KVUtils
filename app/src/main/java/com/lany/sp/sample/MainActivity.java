package com.lany.sp.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lany.sp.SPHelper;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.show1);
        showText = (TextView) findViewById(R.id.show2);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mEditText.getText().toString();
                boolean result = SPHelper.getInstance().putStringForResult("my_key", value);
                Toast.makeText(MainActivity.this, result ? "success" : "fail", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = SPHelper.getInstance().getString("my_key");
                showText.setText("" + value);
            }
        });

    }
}
