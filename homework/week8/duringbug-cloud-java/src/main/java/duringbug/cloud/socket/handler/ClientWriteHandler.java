package duringbug.cloud.socket.handler;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/4/28 10:06
 * @description:
 */
public class ClientWriteHandler extends Thread{
    private final PrintWriter printWriter;
    private final Scanner sc;
    ClientWriteHandler(OutputStream outputStream) {
        this.printWriter = new PrintWriter(new OutputStreamWriter(outputStream,
                StandardCharsets.UTF_8), true);
        this.sc = new Scanner(System.in);
    }
    public void send(String str){
        this.printWriter.println(str);
    }
    @Override
    public void run() {
        while (sc.hasNext()) {
            // 拿到控制台数据
            String str = sc.next();
            send(str);
        }
    }
}
