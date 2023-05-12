/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-14 15:06:54
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 21:30:44
 */
package duringbug.homework.java;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duringbug.homework.java.socket.Server.TCPMultiClientServer;
import duringbug.homework.java.socket.Server.TCPSimpleServer;
import duringbug.homework.java.socket.Start.OpenClinet;
import duringbug.homework.java.socket.Start.OpenServer;
import duringbug.homework.java.socket.Start.Interface.StartClinet;
import duringbug.homework.java.socket.Start.Interface.StartServer;

public class AppTest {
    @Test
    public void simpleServerOpen() throws IOException, InterruptedException {
        StartServer startServer = new OpenServer();
        startServer.setPort(9091);
        startServer.setTCPServer(new TCPSimpleServer());
        Thread thread = new Thread(startServer);
        thread.start();
        thread.join();
    }

    @Test
    public void multiServerOpen() throws IOException, InterruptedException {
        StartServer startServer = new OpenServer();
        startServer.setPort(9091);
        startServer.setTCPServer(new TCPMultiClientServer());
        Thread thread = new Thread(startServer);
        thread.start();
        thread.join();
    }

    @Test
    public void clientOpen1() throws InterruptedException, IOException {
        StartClinet startClinet = new OpenClinet(9091, "127.0.0.1");
        Thread thread2 = new Thread(startClinet);
        thread2.start();
        thread2.join();
        startClinet.sendMsg("test");
        startClinet.sendMsg("xiaoxiezimu");
    }

    @Test
    public void clientOpen2() throws InterruptedException, IOException {
        StartClinet startClinet = new OpenClinet(9091, "127.0.0.1");
        Thread thread2 = new Thread(startClinet);
        thread2.start();
        thread2.join();
        startClinet.sendMsg("test");
        startClinet.sendMsg("testtwo");
    }

    @Test
    public void stopServer() throws IOException, InterruptedException {
        StartClinet startClinet = new OpenClinet(9091, "127.0.0.1");
        Thread thread2 = new Thread(startClinet);
        thread2.start();
        thread2.join();
        startClinet.sendMsg("stop");
    }

    /**
     * 半包粘包
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    void error() throws InterruptedException, IOException {
        StartClinet startClinet = new OpenClinet(9091, "127.0.0.1");
        Thread thread2 = new Thread(startClinet);
        thread2.start();
        thread2.join();
        for (int i = 0; i < 10000; i++) {
            startClinet.sendMsg("testone");
        }
        startClinet.sendMsg("stop");
    }

    /**
     * 半包粘包
     * 
     */
    @Test
    public void testTCPServer() {
        int port = 9091;
        TCPServer server = new TCPServer();
        try {
            server.start(port, 100);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }

    @Test
    public void testTCPClient() {
        int port = 9091;
        TCPClient client = new TCPClient();
        try {
            client.startConnection("127.0.0.1", port);
            String message = "NETWORK PRINCIPLE";
            client.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.stopConnection();
        }
    }
}
