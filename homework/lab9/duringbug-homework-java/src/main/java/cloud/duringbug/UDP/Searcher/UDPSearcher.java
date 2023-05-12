package cloud.duringbug.UDP.Searcher;
/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-11 18:41:16
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 09:19:48
 */
import cloud.duringbug.UDP.Searcher.impl.Searcher;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;


/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/5/11 18:41
 * @description:
 */
public class UDPSearcher implements Searcher{
    @Override
    public void searcherOpen() throws IOException {
        // 1. 定义要发送的数据
        String sendData = "用户名admin; 密码123";
        byte[] sendBytes = sendData.getBytes(StandardCharsets.UTF_8);
        // 2. 创建发送者端的DatagramSocket对象
        DatagramSocket datagramSocket = new DatagramSocket(9092);
        // 3. 创建数据报，包含要发送的数据
        DatagramPacket sendPacket = new DatagramPacket(sendBytes, 0, sendBytes.length,
        InetAddress.getLocalHost(), 9091);
        // 4. 向接受者端发送数据报 
        datagramSocket.send(sendPacket); 
        System.out.println("数据发送完毕...");
        byte[] receiveBytes = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
        datagramSocket.receive(receivePacket);
        String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
        System.out.println("已接收到 Provider 的回送消息：" + receiveData);
        // 5. 关闭datagramSocket
        datagramSocket.close();
    }
}
