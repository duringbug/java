/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-16 19:09:37
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 20:34:43
 */
/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-04-16 19:09:37
 * @LastEditors: ${author}
 * @LastEditTime: 2023-04-16 19:15:34
 */
package duringbug.homework.java.socket.Config;

import java.util.List;

public class Config {
    public int port;
    public String host;
    public ClinetType clinetTyle;
    public ServerType serverType;
    public List<String> msg;

    public Config(int port, String host, List<String> msg, ClinetType clinetTyle) {
        this.port = port;
        this.host = host;
        this.msg = msg;
        this.clinetTyle = clinetTyle;
    }

    public Config(int port, ServerType serverType) {
        this.port = port;
        this.serverType = serverType;
    }
}
