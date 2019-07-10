package com.wn.serverClient.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangning on 2017/11/7.
 */
public class BioServer {

    public static void main(String[] args) throws Exception {
        System.out.println("服务端启动~~~");
        ServerSocket serverSocket = new ServerSocket(8086);
        Socket socket = null;
        String info = null;
        while (true){
            socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            info = reader.readLine();
            System.out.println("--我是服务端，客服端传递的信息：" + info);
            if(info.equals("EOF")){
                break;
            }


            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            System.out.println("你好，这是服务端的信息");
            for(int i= 0 ;i < 5;i++){
                printWriter.write("你好，这是服务端的信息");
                printWriter.flush();
            }
//            outputStream.flush();
//            outputStream.close();
            System.out.println("socket is conneted ? "+socket.isConnected());
//            socket.shutdownOutput();
        }
//        socket.shutdownInput();

        socket.shutdownOutput();

//        printWriter.close();
//        outputStream.close();
//        reader.close();
//        inputStream.close();
//        socket.close();
//        serverSocket.close();

    }



}
