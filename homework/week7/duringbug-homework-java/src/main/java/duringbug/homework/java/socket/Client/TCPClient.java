package duringbug.homework.java.socket.Client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String host, int port) throws IOException { // 1. 创建客户端Socket，指定服务器地址，端口
        clientSocket = new Socket(host, port);
        // 2. 获取输入输出流
        out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),
                StandardCharsets.UTF_8), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),
                StandardCharsets.UTF_8));
    }

    public String sendMessage(String msg) throws IOException { // 3. 向服务端发送消息
        out.println(msg);
        // 4. 接收服务端回写信息
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() { // 关闭相关资源
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (clientSocket != null)
                clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}