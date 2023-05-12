/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 21:17:57
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-05 10:09:59
 */
package duringbug.cloud.socket.Start;
import duringbug.cloud.socket.Client.TCPClient;
import java.io.IOException;

public class OpenClient extends Thread {
    int port;
    String url;
    public  OpenClient(int port,String url){
        this.port=port;
        this.url=url;
    }
    @Override
    public void run() {
        TCPClient client = new TCPClient();
        try {
            client.startConnection(url, port);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            client.stopConnection();
        }
    }
}
