/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-12 10:17:59
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 11:24:28
 */
package cloud.duringbug.TCP.Searcher;

import cloud.duringbug.UDP.utils.MD5Util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;


public class TCPFileSender {
    public void sendFile() {
        // 生成并写入发送文件
        try {
            try (FileWriter fileWriter = new FileWriter("checksum.txt")) {
                Random r = new Random(2023);
                // 尝试 1e3 and 1e8
                for (int i = 0; i < 1e3; i++) {
                    fileWriter.write(r.nextInt());
                }
            }
            File file = new File("checksum.txt");
            if (!file.exists() || !file.isFile()) {
                System.out.println("文件不存在或不是文件。");
                return;
            }
            Socket socket = new Socket("127.0.0.1", 9091);
            System.out.println("连接成功");
            OutputStream os = socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            bis.close();
            os.flush();
            socket.shutdownOutput(); // 发送一个回复表示文件传输完毕
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = br.readLine();
            System.out.println(response);
            br.close();
            os.close();
            socket.close();
            System.out.println("发送文件的名字: " + file.getName());
            System.out.println("发送文件的大小: " + file.length() / 1024 / 1024 + " MB");
            System.out.println("发送文件的MD5: " + MD5Util.getMD5(file));
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
