package com.evmtv.cloudvideo.common.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class SocketMainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText editText;
    public final static String PORT_STRING = "port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_main);
        findViewById(R.id.Text1ViewID).setOnClickListener(this);
        findViewById(R.id.Text2ViewID).setOnClickListener(this);
        editText = findViewById(R.id.textPortViewID);
        editText.setText("11111");
    }


    @Override
    public void onClick(View v) {
        SharedPreferencesUtil.getInstance().saveData(SocketMainActivity.PORT_STRING, editText.getText().toString());
        switch (v.getId()) {
            case R.id.Text1ViewID:
                startActivity(new Intent(SocketMainActivity.this, TestActivity.class));
                break;
            case R.id.Text2ViewID:
                startActivity(new Intent(SocketMainActivity.this, stbTestActivity.class));
                break;
        }
    }
}
