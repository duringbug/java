/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 17:44:58
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-05 10:50:49
 */
package duringbug.cloud.socket.handler;

import duringbug.cloud.socket.entity.User;
import java.io.IOException;
import java.net.Socket;


/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/4/28 10:11
 * @description:
 */
public class ClientHandler extends Thread{
    private Socket socket;
    public Socket getSocket() {
        return socket;
    }

    private final ClientReadHandler clientReadHandler;
    private final ClientWriteHandler clientWriteHandler;
    public ClientWriteHandler getClientWriteHandler() {
        return clientWriteHandler;
    }

    public ClientHandler(Socket socket,User user) throws IOException {
        this.socket = socket;
        this.clientWriteHandler = new ClientWriteHandler(socket.getOutputStream());
        this.clientReadHandler = new ClientReadHandler(socket.getInputStream(),user,this);
    }
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.clientWriteHandler = new ClientWriteHandler(socket.getOutputStream());
        this.clientReadHandler = new ClientReadHandler(socket.getInputStream());
    }
    
    @Override
    public void run() {
        super.run();
        clientReadHandler.start();
        clientWriteHandler.start();
    }
}
