package com.ani.sunny.api.thread;

import com.ani.sunny.api.commons.constants.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhanglina on 18-1-5.
 */
public class PortTcpLisenter implements Runnable {
    int port;
    public PortTcpLisenter(int port) {
        this.port = port;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            System.out.println("服务端已启动，等待客户端连接..");
            Socket socket=serverSocket.accept();
            System.out.println("客户端连接..");
            Constants.socket=socket;
            InputStream is=socket.getInputStream();
            DataInputStream dis= new DataInputStream(socket.getInputStream());

            byte[] bytes=new byte[1024];
            int length=0;
            byte[] bytes1=new byte[1];
            dis.read(bytes1,0,1);
            if (bytes1[0]==0){
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                String info=bufferedReader.readLine();
                return;
            }
            File img = new File("/home/anicloud/third/jpg/anicloud.jpg");
            FileOutputStream fos=new FileOutputStream(img);
            while ((length=dis.read(bytes,0,bytes.length))!=-1){
                fos.write(bytes,0,length);
                fos.flush();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
