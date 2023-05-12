package duringbug.cloud.socket.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    private static int BYTE_LENGTH = 64;
    private Selector selector;

    public static void main(String[] args) throws IOException {
        new NIOClient().startClient();
    }

    public void startClient() throws IOException{
        this.selector = Selector.open();

        // 创建 SocketChannel，并连接服务器
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9091));
        channel.configureBlocking(false);

        // 向服务器发送消息
        String message = "Hello Server";
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
        channel.write(writeBuffer);

        // 监听读事件
        channel.register(selector, SelectionKey.OP_READ);

        System.out.println("客户端已启动");

        for (;;) {
            int readyCount = selector.select();

            if (readyCount == 0) {
                continue;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator iterator = readyKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isReadable()){
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BYTE_LENGTH);
        int numRead = -1;
        numRead = channel.read(buffer);
        if (numRead == -1) {
            channel.close();
            key.cancel();
            return;
        }
        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        System.out.println("客户端已收到消息: " + new String(data));
    }
}
