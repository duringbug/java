/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-04 17:44:58
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-04 20:55:39
 */
package duringbug.cloud.socket.handler;

import duringbug.cloud.socket.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/4/28 09:58
 * @description:
 */
public class ClientReadHandler extends Thread{
    private final BufferedReader bufferedReader;
    private User user;
    private ClientHandler clientHandler;
    ClientReadHandler(InputStream inputStream,User user,ClientHandler clientHandler) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8));
        this.user=user;
        this.clientHandler = clientHandler;
    }
    ClientReadHandler(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 拿到客户端一条数据
                String str = bufferedReader.readLine();
                if (str == null) {
                    user.removeClient(ClientReadHandler.this.clientHandler);
                    System.out.println("用户"+this.getName()+"退出");
                    break;
                } else {
                    if(str.equals("stop")){
                        break;
                    }
                    System.out.println("读到的数据为:" + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
