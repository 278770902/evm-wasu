package com.evmtv.cloudvideo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.json.JSONException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//        System.out.println("12343234");
        try {
            System.out.println("start-----------");
            //创建服务器端 Socket，指定监听端口
            ServerSocket serverSocket = new ServerSocket(11111);
            while (true) {
                //等待客户端连接
                Socket clientSocket = serverSocket.accept();
                //获取客户端输入流，
                InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String data = null;
//                System.out.println("服务器接收到客户端的数据：" + br.readLine());
                //读取客户端数据
                StringBuffer sb = new StringBuffer();
                while ((data = br.readLine()) != null) {
                    System.out.println("服务器接收到客户端的数据：" + clientSocket.toString() + data);
                    sb.append(data);
                }
//                //关闭输入流
//                clientSocket.shutdownInput();
//                //获取客户端输出流
//                OutputStream os = clientSocket.getOutputStream();
//                PrintWriter pw = new PrintWriter(os);
//                //向客户端发送数据
//                pw.print("服务器给客户端回应的数据");
//                pw.print("{\"ChannelNum\": \"1234323\",\"socket\":\"" + clientSocket.toString() + "\"}");
//                pw.flush();
//                //关闭输出流
//                clientSocket.shutdownOutput();
                if (sb.toString().length() <= 0) {
                    System.out.println("data==null");
                    continue;
                }
                clientSocket.shutdownInput();
                System.out.println("data==" + data);
                if (sb.toString().contains("Send")) {
                    JSONObject jsonObject = JSON.parseObject(sb.toString());
                    String type = jsonObject.getString("Send");
                    System.out.println("type==" + type);
                    switch (type) {
                        case "GetChannelNum":
                            OutputStream os = clientSocket.getOutputStream();
//                                        PrintWriter pw = new PrintWriter(os);
                            System.out.println("正在给小屏发送消息。。。。。。。。");
//                                        pw.print(("{\"ChannelNum\":" + SharePerfenceUtils.getInstance(TvService.this).getChannelNum() + "}" + "\n").getBytes("utf-8"));
//                                        pw.flush();
                            os.write(("{\"ChannelNum\":\"1123\"}" + "\n").getBytes("utf-8"));
                            //关闭输出流
                            clientSocket.shutdownOutput();
                            break;
                    }
                }
            }

            //关闭资源
//            pw.checkError();
//            os.close();
//            br.close();
//            isr.close();
//            is.close();
//            clientSocket.close();
//            serverSocket.close();
//            System.out.println("end-----------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (true) {
        }

    }


    @Test
    public void textNum() {
//        long num=20210414235100L;
//        System.out.println(""+num/1000000);

//        String num = "20210414235100";
//        String aa=num.substring(8, num.length());
//        String h = num.substring(8, 10);
//        String m = num.substring(10, 12);
//        String s = num.substring(12, 14);
//        System.out.println(h + ":" + m + ":" + s);
        String url="http%3A%2F%2F172.30.10.154%3A20010%2Flive%2Fshiyi000140123000100.m3u8%3Fstarttime%3D1618761600%26endtime%3D1618764600%26ckey%3D30f7958773e37b7747e704e4895559ff%26AuthInfo%3D%26userid%3D1059845255";
        try {
            ;
            System.out.println("URLEncoder="+URLDecoder.decode(url, "utf-8")); ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
           System.out.println("URLEncoder="+URLEncoder.encode(url, "utf-8")); ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void test() throws IOException {
        ServerSocket xpServerSocket = new ServerSocket(11111);
        while (xpServerSocket != null) {
            System.out.println("3333start-----------");
            //等待客户端连接
            Socket clientSocket = xpServerSocket.accept();
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
            }

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
                }
        }
    }
}