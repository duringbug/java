package cloud.duringbug.UDP.Provider;

import cloud.duringbug.UDP.Provider.impl.Provider;
import cloud.duringbug.UDP.utils.MD5Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class UDPFileReceiver implements Provider{

    @Override
    public void providerOpen() throws IOException {
        File file = new File("checksum_recv.txt"); // 要接收的文件存放路径 
        FileOutputStream output = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        DatagramSocket ds = new DatagramSocket(9091);
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

        while (true) {
            ds.receive(dp); // 接收数据包

            // 将接收到的数据写入文件
            output.write(dp.getData(), dp.getOffset(), dp.getLength());

            // 如果接收到的数据不足 buffer.length 字节，则说明已经读完了
            if (dp.getLength() < buffer.length) {
                break;
            }
        }

        output.close();
        ds.close();
        System.out.println("接收来自"+dp.getAddress().toString()+"的文件已完成!对方所使用的 端口号为:"+dp.getPort());
        file = new File("checksum_recv.txt");
        System.out.println("接收文件的md5为: " + MD5Util.getMD5(file));
    }
}
