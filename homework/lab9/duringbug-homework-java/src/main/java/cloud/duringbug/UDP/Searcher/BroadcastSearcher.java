/*
 * @Description: 向255.255.255.255:9091发送udp
 * @Author: 唐健峰
 * @Date: 2023-05-11 19:09:59
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 09:19:42
 */
package cloud.duringbug.UDP.Searcher;

import cloud.duringbug.UDP.Searcher.impl.Searcher;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;


public class BroadcastSearcher implements Searcher{

    @Override
    public void searcherOpen() throws IOException {
        // 1. 定义要发送的数据
        String sendData = "BroadcastSearcher发送的数据";
        byte[] sendBytes = sendData.getBytes(StandardCharsets.UTF_8);
        // 设置广播地址和端口号
        InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
        int port = 9091;
        DatagramPacket sendPacket = new DatagramPacket(sendBytes, sendBytes.length, broadcastAddress, port);
        try (
            // 2. 创建发送者端的DatagramSocket对象
            DatagramSocket datagramSocket = new DatagramSocket(9092)) {
            datagramSocket.setBroadcast(true);
            datagramSocket.send(sendPacket);
            while(true){
                byte[] receiveBytes = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(receivePacket);
                String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("已接收到 Provider 的回送消息：" + receiveData);
                break;
            }
            // 5. 关闭datagramSocket
            datagramSocket.close();
        } 
    }
}
