/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 19:12:51
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-05 11:44:13
 */
package duringbug.cloud.socket;

import duringbug.cloud.socket.Client.NIOClient;
import duringbug.cloud.socket.Client.TCPClient;
import duringbug.cloud.socket.Server.NIOServer;
import duringbug.cloud.socket.Server.NIOServer2;
import duringbug.cloud.socket.Server.TCPServer;
import duringbug.cloud.socket.Start.OpenClient;
import java.io.IOException;
import org.junit.jupiter.api.Test;




public class MyTest {
    @Test
    public void ServerOpen(){
        int port = 9091;
        TCPServer server=new TCPServer();
        try {
            server.start(port);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            server.stop();
        }
    }
    @Test
    public void ClientOpen(){
        int port = 9091;
        TCPClient client = new TCPClient();
        try {
            client.startConnection("127.0.0.1", port);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            client.stopConnection();
        }
    }

    @Test
    public void Client2Open(){
        int port = 9091;
        TCPClient client = new TCPClient();
        try {
            client.startConnection("127.0.0.1", port);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            client.stopConnection();
        }
    }
    @Test
    public void ManyClientOpen(){
        for(int i=0;i<614;i++)
        {
            OpenClient openClient=new OpenClient(9091,"127.0.0.1");
            openClient.start();
        }
    }
    @Test
    public void NIOServerOpen() throws IOException{
        NIOServer nioServer=new NIOServer(9091);
        nioServer.openNIOServer();
    }
    @Test
    public void ManyClient2Open(){
        for(int i=0;i<614;i++)
        {
            OpenClient openClient=new OpenClient(9091,"127.0.0.1");
            openClient.start();
        }
    }
    @Test 
    public void openNIOServer2() throws IOException{
        NIOServer2 nioServer2= new NIOServer2();
        nioServer2.startServer();
    }
    @Test
    public void openNIOClient() throws IOException{
        NIOClient nioClient=new NIOClient();
        nioClient.startClient();
    }
    @Test
    public void openNIOClient2() throws IOException{
        NIOClient nioClient=new NIOClient();
        nioClient.startClient();
    }
}
