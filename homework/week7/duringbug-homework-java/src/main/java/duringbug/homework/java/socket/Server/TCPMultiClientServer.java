/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-14 16:45:00
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 19:32:30
 */
package duringbug.homework.java.socket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duringbug.homework.java.socket.Server.Interface.TCPServer;

public class TCPMultiClientServer implements TCPServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TCPMultiClientServer.class);
    private ServerSocket serverSocket;
    private HashMap<String, Socket> clientSockets;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        clientSockets = new HashMap<String, Socket>();
        Boolean stop = false;
        // 1. 创建一个服务器端Socket，即ServerSocket，监听指定端口 serverSocket = new ServerSocket(port);
        // 2. 调用accept()方法开始监听，阻塞等待客户端的连接
        if (serverSocket == null) {
            LOGGER.error("端口被占用无法启动");
            return;
        }
        for (; !stop;) {
            LOGGER.info("阻塞等待客户端连接中...");
            try {
                clientSockets.put(UUID.randomUUID().toString(), serverSocket.accept());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            LOGGER.info("用户连接成功");
            for (Map.Entry<String, Socket> clientSocket : clientSockets.entrySet()) {
                try {
                    out = new PrintWriter(new OutputStreamWriter(clientSocket.getValue().getOutputStream(),
                            StandardCharsets.UTF_8), true);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getValue().getInputStream(),
                            StandardCharsets.UTF_8));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                String str;
                try {
                    while ((str = in.readLine()) != null) {
                        LOGGER.info("我是服务端，客户端说: " + str);
                        // 消息回写
                        out.println("服务端已收到消息,您的id为:" + clientSocket.getKey());
                        if (str.equals("stop")) {
                            stop = true;
                            break;
                        }
                    }
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        this.stop();
    }

    public void stop() { // 关闭相关资源
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
