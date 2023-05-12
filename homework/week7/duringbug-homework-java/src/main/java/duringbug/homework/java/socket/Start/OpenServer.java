/*
 * @Description: 单客户端
 * @Author: 唐健峰
 * @Date: 2023-04-14 14:22:58
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 19:17:22
 */
package duringbug.homework.java.socket.Start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duringbug.homework.java.socket.Server.Interface.TCPServer;
import duringbug.homework.java.socket.Start.Interface.StartServer;

public class OpenServer implements StartServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenServer.class);

    TCPServer tcpServer;
    int port;

    public void setPort(int port) {

        this.port = port;
    }

    public void setTCPServer(TCPServer tcpServer) {
        this.tcpServer = tcpServer;
    }

    public TCPServer getTCPServer() {
        return this.tcpServer;
    }

    public void run() {
        LOGGER.info("服务器端口尝试开放");
        this.tcpServer.start(port);
    }
}
