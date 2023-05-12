/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 17:51:53
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-05 11:22:54
 */
package duringbug.cloud.socket.Client;

import duringbug.cloud.socket.handler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class TCPClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void startConnection(String ip, int port) throws IOException { 
        // 1. 创建客户端Socket，指定服务器地址，端口
        clientSocket = new Socket(ip, port);
        // 2. 获取输出流，向服务器端发送信息
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        // 3. 获取输入流，并读取服务器端的响应信息
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // 4. 启动线程持续读取并打印服务器端消息
        ClientHandler clientHandler=new ClientHandler(clientSocket);
        clientHandler.start();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = console.readLine()) != null) {
            out.println(input);
        }
    }
     
    public void stopConnection() { 
        // 关闭相关资源
        try {
            if(in!=null) in.close();
            if(out!=null) out.close();
            if(clientSocket!=null)
            { 
                clientSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
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
}