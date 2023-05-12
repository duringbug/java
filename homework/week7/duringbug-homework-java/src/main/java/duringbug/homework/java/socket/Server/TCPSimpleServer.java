/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-14 11:05:53
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 19:34:06
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duringbug.homework.java.socket.Server.Interface.TCPServer;
import duringbug.homework.java.utils.UpperUtil;

public class TCPSimpleServer implements TCPServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TCPSimpleServer.class);
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        LOGGER.info("Server listening on port " + port);
        // 1. 创建一个服务器端Socket，即ServerSocket，监听指定端口
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (serverSocket == null) {
            LOGGER.error("端口被占用无法启动");
            return;
        }
        // 2. 调用accept()方法开始监听，阻塞等待客户端的连接
        LOGGER.info("Waiting for client connection...");
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Client connected");
        // 3. 获取Socket的字节输出流
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),
                    StandardCharsets.UTF_8), true);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        // 4. 获取Socket的字节输入流，并准备读取客户端发送的信息
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),
                    StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        // 5. 阻塞读取客户端发送的信息
        String str;
        try {
            while ((str = in.readLine()) != null) {
                LOGGER.info("我是服务端，客户端说: " + str);
                // 消息回写
                if (str.equals("stop")) {
                    break;
                }
                out.println("服务端已收到消息" + UpperUtil.upperWords(str));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        this.stop();
    }

    public void stop() {
        LOGGER.info("Closing server and client sockets");
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}