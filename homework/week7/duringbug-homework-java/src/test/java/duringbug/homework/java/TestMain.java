package duringbug.homework.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duringbug.homework.java.socket.Config.ClinetType;
import duringbug.homework.java.socket.Config.Config;
import duringbug.homework.java.socket.Config.ServerType;
import duringbug.homework.java.utils.TCP;

public class TestMain {
    @Test
    public void testMulti() throws IOException, InterruptedException {
        Config serverConfig = new Config(9091, ServerType.MULTI);
        TCP server = new TCP(serverConfig);
        server.runServer();
    }

    @Test
    public void testSimple() throws IOException, InterruptedException {
        List<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("stop");
        Config clinetConfig = new Config(9091, "127.0.0.1", list, ClinetType.SIMPLE);
        TCP clinet = new TCP(clinetConfig);
        clinet.runServer();
    }
}
