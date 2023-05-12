package duringbug.homework.java.socket.Start.Interface;

import java.io.IOException;

public interface StartClinet extends Runnable {
    public void sendMsg(String msg) throws IOException;

    public void stopConnection();
}
