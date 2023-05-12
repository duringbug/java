/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-14 14:39:25
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-14 16:10:19
 */
package duringbug.homework.java.socket.Start;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duringbug.homework.java.socket.Client.TCPClient;
import duringbug.homework.java.socket.Start.Interface.StartClinet;

public class OpenClinet implements StartClinet {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenClinet.class);

    TCPClient tcpClient;
    int port;
    String host;

    public OpenClinet(int port, String host) {
        tcpClient = new TCPClient();
        this.port = port;
        this.host = host;
    }

    public void sendMsg(String msg) throws IOException {
        String result = tcpClient.sendMessage(msg);
        LOGGER.info(result);
    }

    public void stopConnection() {
        tcpClient.stopConnection();
    }

    public void run() {
        try {
            tcpClient.startConnection(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}