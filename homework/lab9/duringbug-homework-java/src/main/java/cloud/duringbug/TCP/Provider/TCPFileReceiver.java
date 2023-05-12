/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-12 10:22:21
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 10:53:18
 */
package cloud.duringbug.TCP.Provider;

import cloud.duringbug.UDP.utils.MD5Util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPFileReceiver {
    public void receiveFile(String filePath) {
        try {
            ServerSocket serverSocket = new ServerSocket(9091);
            System.out.println("等待客户端连接......");
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功，开始接收文件......");
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            socket.shutdownInput(); // 发送一个回复表示文件接收完成
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println("文件已经接收完成。");
            pw.flush();
            pw.close();
            socket.close();
            serverSocket.close();
            System.out.println("接收文件的名字: " + file.getName());
            System.out.println("接收文件的大小: " + file.length() / 1024 / 1024 + " MB");
            System.out.println("接收文件的MD5: " + MD5Util.getMD5(file));
            fos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
