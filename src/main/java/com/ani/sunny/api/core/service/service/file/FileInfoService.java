package com.ani.sunny.api.core.service.service.file;

/**
 * Created by zhanglina on 18-1-30.
 */
public interface FileInfoService {
    public String readFromFile(String filePath);
    public String writeToFile(String value);
}
