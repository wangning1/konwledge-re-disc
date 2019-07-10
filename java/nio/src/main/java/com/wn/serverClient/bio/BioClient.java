package com.wn.serverClient.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wangning on 2017/11/7.
 */
public class BioClient {

    public static void main(String[] args)throws Exception {
        System.out.println("客户端启动~~~");
        Socket socket = new Socket("localhost", 8088);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.write("EOF");
        writer.flush();
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String serverInfo = null;
        while ((serverInfo = reader.readLine()) != null){
            System.out.println("我是客户端，接收到服务端的信息：" + serverInfo);
        }

        reader.close();
        inputStream.close();
        writer.close();
        outputStream.close();
        socket.close();

    }

}
