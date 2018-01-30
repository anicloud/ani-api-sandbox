package com.ani.sunny.api.thread;

import com.ani.sunny.api.commons.constants.Constants;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by zhanglina on 17-12-28.
 */
public class PortLisenter implements Runnable {
     int port;

    public PortLisenter(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        DatagramSocket socket;

        try{
            int num=0;
            socket=new DatagramSocket(port);
            socket.setSoTimeout(20*1000);
            Constants.datagramSocket=socket;
            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("/home/anicloud/third/jpg/"+num+".jpg"))));
            // 1.创建数据报，用于接收服务器端响应的数据
            int i = 0;
            byte[] receiveByte=null;

            byte[] isSuccess=new byte[4];
            DatagramPacket dataPackets=new DatagramPacket(isSuccess,4);
            socket.receive(dataPackets);
            InetAddress inetAddress=dataPackets.getAddress();
            Constants.address=inetAddress;
            System.out.println(inetAddress+"______");
            int port=dataPackets.getPort();
            Constants.port=port;
            System.out.println(port+"++++++++");

            byte[] message=new byte[100];
            DatagramPacket dataPackets1=new DatagramPacket(message,100);
            socket.receive(dataPackets1);

            boolean b=false;
            DatagramPacket dataPacket=null;

            int s=0;
            while(i == 0){
                receiveByte = new byte[65500];
                dataPacket = new DatagramPacket(receiveByte, receiveByte.length);
                dataPacket.getPort();
                socket.receive(dataPacket);
                i = dataPacket.getLength();
                if (b==false){
                    b=true;
                }else if (b){
                    if(i > 0){
                        if (s==0){
                            fileOut.write(receiveByte,1,i-1);
                        }else {
                            fileOut.write(receiveByte,0,i);
                        }

                        fileOut.flush();
                        s=s+1;
                        if (i<65500){
                            num++;
                        }


                    }

                }
                i=0;

            }
        }catch (Exception e){
            e.printStackTrace();

        }


    }
}
