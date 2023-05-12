/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 17:56:54
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-04 20:40:04
 */
/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 17:56:54
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-04 18:02:23
 */
package duringbug.cloud.socket.Server;

import duringbug.cloud.socket.entity.User;
import duringbug.cloud.socket.handler.ClientHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private User user;
    public void start(int port) throws IOException {
        // 1. 创建一个服务器端Socket，即ServerSocket，监听指定端口
        serverSocket = new ServerSocket(port);
        user=new User();
        // 4. 启动线程持续读取并打印服务器端消息
        for (;;) {
            Socket socket = serverSocket.accept();
            ClientHandler ch = new ClientHandler(socket,user);
            ch.start();
            user.addClient(ch);
            user.broadcastMessage("用户 " + socket.getRemoteSocketAddress() + " 加入聊天室！");
        }
    }
    public void stop(){ // 关闭相关资源
            try {
                if(clientSocket!=null) clientSocket.close();
                if(serverSocket!=null) serverSocket.close();
            }catch (IOException e){
                e.printStackTrace();
        } 
    }
    public static void main(String[] args) {
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
}
