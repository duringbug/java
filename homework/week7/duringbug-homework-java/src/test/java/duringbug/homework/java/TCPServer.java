/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-16 20:49:57
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 21:36:28
 */
package duringbug.homework.java;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static int BYTE_LENGTH = 64;
    public int n;

    public void start(int port, int n) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("阻塞等待客户端连接中...");
        clientSocket = serverSocket.accept();
        InputStream is = clientSocket.getInputStream();
        for (int i = 0; i < n; i++) {
            byte[] header = new byte[4];
            is.read(header);
            int length = ByteBuffer.wrap(header).getInt();
            byte[] data = new byte[length];
            // 读取客户端发送的信息
            int cnt = is.read(data, 0, length);
            if (cnt > 0)
                System.out.println("服务端已收到消息: " + new String(data).trim());
        }
        this.stop();
    }

    public void stop() { // 关闭相关资源
        try {
            if (clientSocket != null)
                clientSocket.close();
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
