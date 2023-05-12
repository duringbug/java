/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 19:44:42
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-04 21:02:01
 */
package duringbug.cloud.socket.entity;

import duringbug.cloud.socket.handler.ClientHandler;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;



public class User {
    private List<ClientHandler> clients = new ArrayList<>();
    public void addClient(ClientHandler client) {
        clients.add(client);
        System.out.println("用户 " + client.getSocket().getRemoteSocketAddress() + " 已连接");
        broadcastMessage("用户 " + client.getSocket().getRemoteSocketAddress() + " 加入聊天室！");
    }
    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.getClientWriteHandler().send(message);
        }
    }
    public void removeClient(ClientHandler client) {
        clients.remove(client);
        SocketAddress clientIP =client.getSocket().getRemoteSocketAddress();
        System.out.println("用户 " + client.getName() + " (" + clientIP.toString() + ") 退出");
        broadcastMessage("用户 " + client.getName() + " (" + clientIP.toString() + ") 退出");
        broadcastOnlineUsers();
    }
    public void broadcastOnlineUsers() {
        StringBuilder sb = new StringBuilder("当前在线用户：");
        for (ClientHandler client : clients) {
            sb.append(client.getSocket().getRemoteSocketAddress().toString()).append(", ");
        }
        String onlineUsers = sb.toString().substring(0, sb.length() - 2);
        broadcastMessage(onlineUsers);
    }
}
