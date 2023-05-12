/*
 * @Description: 监听本地的9091端口
 * @Author: 唐健峰
 * @Date: 2023-05-11 19:08:12
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 09:11:16
 */
package cloud.duringbug.UDP.Provider;

import cloud.duringbug.UDP.Provider.impl.Provider;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;



public class BroadcastProvider implements Provider{

    @Override
    public void providerOpen() throws IOException {
        System.out.println("UDPProvider started.");

        // 创建 DatagramSocket 对象并绑定监听的端口号
        DatagramSocket datagramSocket = new DatagramSocket(9091);

        // 循环监听客户端的广播消息
        while (true) {
            // 创建接收数据报对象
            byte[] receiveBytes = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
            datagramSocket.receive(receivePacket);

            // 解析接收到的数据，并构建回送数据
            String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("UDPProvider received data: " + data);
            int port = parsePort("port:"+receivePacket.getPort());
            if (port > 0) {
                String tag = UUID.randomUUID().toString();
                byte[] responseBytes = buildWithTag(tag);
                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, receivePacket.getAddress(), port);
                datagramSocket.send(responsePacket);
                System.out.println("UDPProvider sent response to port " + port);
            }
        }
    }

    private static int parsePort(String data) {
        System.out.println(data);
        // 解析数据中的端口号
        int index = data.indexOf(":");
        if (index >= 0) {
            String portStr = data.substring(index + 1);
            try {
                return Integer.parseInt(portStr.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private static byte[] buildWithTag(String tag) {
        // 构建回送数据
        StringBuffer sb = new StringBuffer();
        sb.append("tag:");
        sb.append(tag);
        sb.append("\n");
        String response = sb.toString();
        return response.getBytes();
    }
}
