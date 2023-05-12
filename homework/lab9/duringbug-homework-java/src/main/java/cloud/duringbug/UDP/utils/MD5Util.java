/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-12 09:48:32
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 09:49:39
 */
package cloud.duringbug.UDP.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5Util {
    public static String getMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
             byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
            MD5.update(buffer, 0, length);
            }
            return new String(byte2hex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        try {
            if (fileInputStream != null){
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
private static String byte2hex(byte[] b){
        String hs="";
        String stmp="";
        for (int n=0; n<b.length; n++){
            stmp=(java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
        }
        return hs; 
    }
}
