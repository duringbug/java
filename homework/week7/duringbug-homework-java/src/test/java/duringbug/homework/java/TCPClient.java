/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-16 20:50:50
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 21:32:58
 */
package duringbug.homework.java;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPClient {
    private Socket clientSocket;
    private OutputStream out;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = clientSocket.getOutputStream();
    }

    public void sendMessage(String msg) throws IOException { // 重复发送十次
        for (int i = 0; i < 10; i++) {
            int length = msg.length();
            out.write(ByteBuffer.allocate(4).putInt(length).array());
            out.write(msg.getBytes());
        }
    }

    public void stopConnection() { // 关闭相关资源
        try {
            if (out != null)
                out.close();
            if (clientSocket != null)
                clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
