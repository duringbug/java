package duringbug.homework.java.socket.Start.Interface;

import duringbug.homework.java.socket.Server.Interface.TCPServer;

public interface StartServer extends Runnable {
    public TCPServer getTCPServer();

    public void setPort(int port);

    public void setTCPServer(TCPServer tcpServer);
}
