/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-16 20:25:22
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 21:18:28
 */
package duringbug.homework.java.utils;

import java.io.IOException;

import duringbug.homework.java.socket.Config.ClinetType;
import duringbug.homework.java.socket.Config.Config;
import duringbug.homework.java.socket.Config.ServerType;
import duringbug.homework.java.socket.Server.TCPMultiClientServer;
import duringbug.homework.java.socket.Server.TCPSimpleServer;
import duringbug.homework.java.socket.Start.OpenClinet;
import duringbug.homework.java.socket.Start.OpenServer;
import duringbug.homework.java.socket.Start.Interface.StartClinet;
import duringbug.homework.java.socket.Start.Interface.StartServer;

public class TCP {
    private Config config;

    public TCP(Config config) {
        this.config = config;
    }

    public void runServer() throws InterruptedException, IOException {
        if (config.serverType != null && config.serverType.equals(ServerType.MULTI)) {
            StartServer startServer = new OpenServer();
            startServer.setPort(config.port);
            startServer.setTCPServer(new TCPMultiClientServer());
            Thread thread = new Thread(startServer);
            thread.start();
            thread.join();
        } else if (config.serverType != null && config.serverType.equals(ServerType.SIMPLE)) {
            StartServer startServer = new OpenServer();
            startServer.setPort(config.port);
            startServer.setTCPServer(new TCPSimpleServer());
            Thread thread = new Thread(startServer);
            thread.start();
            thread.join();
        } else if (config.clinetTyle != null && config.clinetTyle.equals(ClinetType.SIMPLE)) {
            StartClinet startClinet = new OpenClinet(config.port, config.host);
            Thread thread2 = new Thread(startClinet);
            thread2.start();
            thread2.join();
            for (String msg : config.msg) {
                startClinet.sendMsg(msg);
            }
        }
    }
}
