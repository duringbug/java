package cloud.duringbug.UDP.Provider;
/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-11 18:39:30
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 10:00:30
 */
import cloud.duringbug.UDP.Provider.impl.Provider;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/5/11 18:39
 * @description:
 */
public class UDPProvider implements Provider{
    @Override
    public void providerOpen() throws IOException {
        // 1. 创建接受者端的DatagramSocket，并指定端口
        DatagramSocket datagramSocket = new DatagramSocket(9091);
        // 2. 创建数据报，用于接受客户端发来的数据
        byte[] buf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, 0, buf.length); 
        // 3. 接受客户端发送的数据，此方法在收到数据报之前会一直阻塞 
        System.out.println("阻塞等待发送者的消息...");
        datagramSocket.receive(receivePacket);
        // 4. 解析数据
        String ip = receivePacket.getAddress().getHostAddress();
        int port = receivePacket.getPort();
        int len = receivePacket.getLength();
        String data = new String(receivePacket.getData(),0, len);
        System.out.println("我是接受者，" + ip + ":" + port + " 的发送者说: "+ data);
        String responseData = "已收到你的消息";
        byte[] responseBytes = responseData.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(responseBytes, responseBytes.length, receivePacket.getAddress(), receivePacket.getPort());
        datagramSocket.send(sendPacket);
        // 5. 关闭datagramSocket
        datagramSocket.close();
    }
}
