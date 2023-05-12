/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-05 10:00:38
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-05 11:03:39
 */
package duringbug.cloud.socket.Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NIOServer{
    private static List<SocketChannel> channelList = new ArrayList<>();
    private static int BYTE_LENGTH = 64;
    int port;
    public NIOServer(int port){
        this.port=port;
    }
    public void openNIOServer() throws IOException{
        ServerSocketChannel serverSocket = ServerSocketChannel.open() ;
        serverSocket.socket().bind(new InetSocketAddress(9091));
         // 设置ServerSocketChannel为非阻塞 
        serverSocket.configureBlocking(false); 
        System.out.println("服务端启动");
        for (;;) {
            // accept方法不阻塞
            SocketChannel socketChannel = serverSocket.accept(); 
            if (socketChannel != null) {
                System.out.println("连接成功"); 
                socketChannel.configureBlocking(false); 
                channelList.add(socketChannel);
            }
            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_LENGTH); // read方法不阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println("服务端接收到消息:" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    // 如果客户端断开，把socket从集合中去掉 
                    iterator.remove(); 
                    System.out.println("客户端断开连接");
                } 
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open() ;
        serverSocket.socket().bind(new InetSocketAddress(9091));
         // 设置ServerSocketChannel为非阻塞 
        serverSocket.configureBlocking(false); 
        System.out.println("服务端启动");
        for (;;) {
            // accept方法不阻塞
            SocketChannel socketChannel = serverSocket.accept(); 
            if (socketChannel != null) {
                System.out.println("连接成功"); 
                socketChannel.configureBlocking(false); 
                channelList.add(socketChannel);
            }
            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_LENGTH); // read方法不阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println("服务端接收到消息:" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    // 如果客户端断开，把socket从集合中去掉 
                    iterator.remove(); 
                    System.out.println("客户端断开连接");
                } 
            }
        }
    }
}
