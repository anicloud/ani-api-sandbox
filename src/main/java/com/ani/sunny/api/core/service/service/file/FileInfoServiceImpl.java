package com.ani.sunny.api.core.service.service.file;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by zhanglina on 18-1-30.
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Override
    public String readFromFile(String filePath) {
        File myFile=new File(filePath);
        String resultStr="";
        if(!myFile.exists())
        {
            System.err.println("Can't Find " + filePath);
        }

        try
        {
            char[] buf=new char[1024];
            FileReader fileReader=new FileReader(myFile);
            //fileReader.read(buf);
            int num =0;
            while((num=fileReader.read(buf))!=-1){
                String str=new String(buf,0,num);
                resultStr=resultStr+str;
            }
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        return resultStr;
    }

    @Override
    public String writeToFile(String value) {
        FileWriter writer;
        try {
            writer = new FileWriter("/home/zhanglina/remote1.sdp");
            writer.write(value);
            writer.flush();
            writer.close();
            File oldFile=new File("/home/zhanglina/remote1.sdp");
            File newFile=new File("/home/zhanglina/remote.sdp");
            oldFile.renameTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){

    }
}
