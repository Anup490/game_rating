package com.anup.gamewebservice.utils;

import com.anup.gamecore.utils.StringUtils;

import java.io.InputStream;

public class FileUtils {

    public static byte[] toBytes(InputStream inputStream) {
        try{
            byte[] buffer = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while (inputStream.read(buffer) > -1){
                stringBuilder.append(new String(buffer));
            }
            if(StringUtils.isBlank(stringBuilder.toString())){
                return new byte[0];
            }
            return stringBuilder.toString().getBytes();
        }catch (Exception e){
            return new byte[0];
        }
    }

}
