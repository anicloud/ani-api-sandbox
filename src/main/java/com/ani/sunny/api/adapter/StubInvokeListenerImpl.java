package com.ani.sunny.api.adapter;

import com.ani.agent.service.service.websocket.StubInvokeListener;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.util.SessionManager;
import com.ani.sunny.api.core.service.service.file.FileInfoService;
import com.ani.sunny.api.core.service.service.file.FileInfoServiceImpl;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Vector;

/**
 * Created by zhanglina on 17-7-28.
 */
public class StubInvokeListenerImpl implements StubInvokeListener {


    @Override
    public void receiveInvokeResult(AniStub aniStub) {
        switch (aniStub.stubId){
            case 301:handByPlatForm(aniStub);break;
            case 305:handleByTcp(aniStub);break;
            case 307:handByUdp(aniStub);break;
            case 309:handByUdpThread(aniStub);break;
            case 311:handByTcpThread(aniStub);break;
            case 3: writeToLocal(aniStub);

        }

//            // 4.向服务器端发送数据报


    }
    public void writeToLocal(AniStub aniStub){
        StubArgumentDto argumentDto=aniStub.outputArguments.get(0);
        String value=(String) argumentDto.getValue();
        SunnyConstants.stubValueDto.setName("test");
        SunnyConstants.stubValueDto.setValue(value);
        synchronized(SunnyConstants.stubValueDto){
            SunnyConstants.stubValueDto.notify();
        }

       
    }
        
     private void handByUdpThread(AniStub aniStub){
         System.out.println(aniStub);
         StubArgumentDto argumentDto1 = aniStub.outputArguments.get(0);
         String imgName = (String) argumentDto1.value;
         byte[] data = imgName.getBytes();
//            // 2.创建数据报，包含发送的数据信息
         DatagramPacket packet = new DatagramPacket(data, data.length, Constants.address, Constants.port);
//
//            // 3.创建DatagramSocket对象
         try {
//              DatagramSocket datagramSocket=new DatagramSocket(8887);
//              datagramSocket.send(packet);
             Constants.datagramSocket.send(packet);

         }catch (Exception e){
             e.printStackTrace();
         }

     }
     private void handByTcpThread(AniStub aniStub){
         System.out.println(aniStub);
         StubArgumentDto argumentDto1 = aniStub.outputArguments.get(0);
         String imgName = (String) argumentDto1.value;
         Socket socket=Constants.socket;
         try{
             OutputStream os=socket.getOutputStream();//字节输出流
             PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
             pw.write(imgName);
             pw.flush();
             System.out.println("消息已发送"+imgName+"*****");
         }catch (IOException e){
             e.printStackTrace();
         }


     }
     private void handByPlatForm(AniStub aniStub){
         StubArgumentDto argumentDto1 = aniStub.outputArguments.get(0);
         byte imgtype=(byte)argumentDto1.getValue();
         StubArgumentDto argumentDto2 = aniStub.outputArguments.get(1);
         Byte[] imgBytes=(Byte[]) argumentDto2.getValue();
         synchronized(SunnyConstants.bytes){
             SunnyConstants.bytes.notify();
         }
//         System.out.println(imgBytes.length+"@@@@@@@@@@@@@@@");
         byte[] imgb =new byte[imgBytes.length];
         
         for (int i=0;i<imgBytes.length;i++){
             imgb[i]=(byte)imgBytes[i];
         }
         String type="";
         if (imgtype==1){
             type="jpg";
         }else if (imgtype==2){
             type="bmp";
         }
         String path="/home/anicloud/third/jpg/ani."+type;
         System.out.println(path);
         byte2image(imgb,path);

     }
     private void  handleByTcp(AniStub aniStub){
         System.out.println("welcome handleByTcp******");
        StubArgumentDto argumentDto1 = aniStub.outputArguments.get(0);
        String imgName = (String) argumentDto1.value;
        StubArgumentDto argumentDto2 = aniStub.outputArguments.get(1);
        String IP = (String) argumentDto2.value;
        System.out.println(IP+"______________________________________");
        StubArgumentDto argumentDto3 = aniStub.outputArguments.get(2);
        short port = (short) argumentDto3.value;
                 try {
             Socket socket=new Socket(IP,(int)port);
             OutputStream os=socket.getOutputStream();//字节输出流
             PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
             pw.write(imgName);
             pw.flush();
             socket.shutdownOutput();
             //关闭输出流
             //3.获取输入流，并读取务器端的响应信息
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
             File img = new File("/home/anicloud/third/jpg/"+imgName);
             FileOutputStream fos=new FileOutputStream(img);
             while ((length=dis.read(bytes,0,bytes.length))!=-1){
                 fos.write(bytes,0,length);
                 fos.flush();
             }

         }catch (IOException e){
             e.printStackTrace();
         }

     }
     private void handByUdp(AniStub aniStub){
         System.out.println("welcome handByUdp******");
         StubArgumentDto argumentDto1 = aniStub.outputArguments.get(0);
         String imgName = (String) argumentDto1.value;
         System.out.println(imgName);
         StubArgumentDto argumentDto2 = aniStub.outputArguments.get(1);
         String IP = (String) argumentDto2.value;
         System.out.println(IP+"____________________________________");
         StubArgumentDto argumentDto3 = aniStub.outputArguments.get(2);
         short port = (short) argumentDto3.value;
         System.out.println(port);
         DatagramSocket socket=null;
         try {
            socket = new DatagramSocket(8888);
             socket.setSoTimeout(20*1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            InetAddress address = InetAddress.getByName(IP);
            byte[] data = imgName.getBytes();
            // 2.创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address,port);

            // 3.创建DatagramSocket对象

            // 4.向服务器端发送数据报
            socket.send(packet);
              /* 接收服务器端响应的数据
                    */

            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("/home/anicloud/third/jpg/"+imgName))));
            // 1.创建数据报，用于接收服务器端响应的数据
            int i = 0;
            byte[] receiveByte=null;
            DatagramPacket dataPacket=null;
            byte[] isSuccess=new byte[4];
            DatagramPacket dataPackets=new DatagramPacket(isSuccess,4);
            socket.receive(dataPackets);
            int s=0;
            while(i == 0){//无数据，则循环

                receiveByte = new byte[65500];
                dataPacket = new DatagramPacket(receiveByte, receiveByte.length);

                socket.receive(dataPacket);
                i = dataPacket.getLength();
                if(i > 0){
                    if (s==0){
                        fileOut.write(receiveByte,1,i-1);
                    }else {
                        fileOut.write(receiveByte,0,i);
                    }

                    fileOut.flush();
                    s=s+1;
                    if (i<65500){
                       // socket.close();
                        break;
                    }
                    i = 0;//循环接收
                }
            }

        // 4.关闭资源
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }


     }
    public void byte2image(byte[] data,String path){
             if(data.length<3||path.equals("")) return;
             try{
                 FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
                 imageOutput.write(data, 0, data.length);
                 imageOutput.close();
                 System.out.println("Make Picture success,Please find image in " + path);
                 } catch(Exception ex) {
                  System.out.println("Exception: " + ex);
                   ex.printStackTrace();
                 }
    }
}
