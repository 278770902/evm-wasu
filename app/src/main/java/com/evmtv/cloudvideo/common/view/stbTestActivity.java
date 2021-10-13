package com.evmtv.cloudvideo.common.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;

public class stbTestActivity extends AppCompatActivity {
    private static int BROADCAST_PORT = 22222;
    private TextView view;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stb_test);
        BROADCAST_PORT = (int) SharedPreferencesUtil.getInstance().getData(SocketMainActivity.PORT_STRING, BROADCAST_PORT);
        view = findViewById(R.id.textViewID);
        stringBuffer = new StringBuffer("开始:");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    xpServerSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void WriteText(String text) {
        stringBuffer.append(text);
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                view.setText(stringBuffer.toString());
            }
        });
    }


    private void xpServerSocket() throws IOException {
        ServerSocket xpServerSocket = new ServerSocket(BROADCAST_PORT);
        while (xpServerSocket != null) {
            WriteText("等待连接");
            //等待客户端连接
            Socket clientSocket = xpServerSocket.accept();
            WriteText("连接成功");
            //获取客户端输入流，
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            //读取客户端数据
            StringBuffer sb = new StringBuffer();
            while ((data = br.readLine()) != null) {
                System.out.println("服务器接收到客户端的数据：" + clientSocket.toString() + data);
                sb.append(data);
                WriteText("服务器接收到客户端的数据：" + clientSocket.toString() + data);
            }

            WriteText(sb.toString());
            //关闭输入流
            clientSocket.shutdownInput();
            if (!sb.toString().isEmpty())
                if (sb.toString().contains("Send")) {
                    com.alibaba.fastjson.JSONObject object = JSON.parseObject(sb.toString());
                    String type = object.getString("Send");
                    switch (type) {
                        case "GetChannelNum":
                            OutputStream os = clientSocket.getOutputStream();
                            System.out.println("正在给小屏发送消息。。。。。。。。");
                            WriteText("正在给小屏发送消息。。。。。。。。");
                            os.write(("{\"ChannelNum\":1234}" + "\n").getBytes("utf-8"));
                            //关闭输出流
                            clientSocket.shutdownOutput();
                            break;
                    }

                } else if (sb.toString().contains("startActivity")) {
                    com.alibaba.fastjson.JSONObject object = JSON.parseObject(sb.toString());
                    String url = object.getString("url");
                    String TIME = object.getString("time");
                    System.out.println("url=" + url + "TIME=" + TIME);
                    WriteText("url=" + url + "TIME=" + TIME);
                }
        }
    }
}
