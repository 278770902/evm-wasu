package com.evmtv.cloudvideo.common.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView view;
    private Button testCreateViewID, testGetCViewID, testSendVideoViewID;
    private Socket socket;
    private static int BROADCAST_PORT = 11111;
        private String IP = "192.168.88.2";
//    private String IP = "192.168.1.121";
//    private String IP = "192.168.1.133";
    String GET_CHANEL_NUM_TYPE = "{\n" +
            "\t\"Send\": \"GetChannelNum\"\n" +
            "}\n";
    private StringBuffer sb;
    private Boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BROADCAST_PORT= (int) SharedPreferencesUtil.getInstance().getData(SocketMainActivity.PORT_STRING,BROADCAST_PORT);
        findViewById(R.id.testSendVideoViewID).setOnClickListener(this);
        findViewById(R.id.testCreateViewID).setOnClickListener(this);
        findViewById(R.id.testGetCViewID).setOnClickListener(this);
        view = findViewById(R.id.testTextViewID);
        sb = new StringBuffer("TEXT:");
        view.setText(sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testCreateViewID:
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        isStart = !isStart;
                        isPingSuccess(IP);
                    }
                });
                break;
            case R.id.testSendVideoViewID:
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        String context = "{" +
                                "\"startActivity\": \"PlayerUrl.class\"," +
                                "\"url\": \"https://media.w3.org/2010/05/sintel/trailer.mp4\"," +
                                "\"time\": \"0\"" +
                                "}\n";
                        sendSocket(context, false);
                    }
                });
                break;
            case R.id.testGetCViewID:
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        sendSocket(GET_CHANEL_NUM_TYPE, true);
                    }
                });
                break;
        }
    }


    private void Text(String context) {
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                view.setText(context);
            }
        });
    }

    private void sendSocket(String value, boolean isRead) {
        try {
            if (socket == null || (!socket.isConnected())) {
                socket = new Socket(IP, BROADCAST_PORT);
            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(value.getBytes("utf-8"));
            outputStream.flush();
            socket.shutdownOutput();
            Text(sb.append("Writer" + value).toString());
            if (isRead) {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String json = br.readLine();
                Log.i("socket", "json=" + json);
                Text(sb.append("read json=" + json).toString());
//                socket.shutdownInput();
                //关闭资源
                br.close();
                isr.close();
                is.close();
            }
//            pw.close();
            outputStream.close();
            socket.close();
            socket = null;
        } catch (IOException e) {
            Text(sb.append("socket create error "+e.getMessage()).toString());
            Log.e("XpActivity", "socket create error" + e.getMessage());
        }
    }

    private void isPingSuccess(String m_strForNetAddress) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!isStart)
                    return;
                String delay = new String();
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec("/system/bin/ping -c 4 " + m_strForNetAddress);
                    int status = p.waitFor();
                    if (status == 0)
                        sb.append("成功");
                    else
                        sb.append("失败");
                    BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String str = new String();
                    while ((str = buf.readLine()) != null) {
                        if (str.contains("avg")) {
                            int i = str.indexOf("/", 20);
                            int j = str.indexOf(".", i);
                            System.out.println("延迟:" + str.substring(i + 1, j));
                            delay = str.substring(i + 1, j);
                        }
                    }
//                    if (delay.equals("")) {
//                        EventBusUtils.post(newNetEvent((long) 1000));
//                    } else {
//                        EventBusUtils.post(newNetEvent(Long.parseLong(delay)));
//                    }
                    sb.append(delay);
                    Text(sb.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isPingSuccess(IP);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
