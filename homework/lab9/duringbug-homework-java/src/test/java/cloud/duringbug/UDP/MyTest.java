/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-11 18:37:35
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-12 10:28:53
 */
package cloud.duringbug.UDP;
import cloud.duringbug.TCP.Provider.TCPFileReceiver;
import cloud.duringbug.TCP.Searcher.TCPFileSender;
import cloud.duringbug.UDP.Provider.BroadcastProvider;
import cloud.duringbug.UDP.Provider.UDPFileReceiver;
import cloud.duringbug.UDP.Provider.UDPProvider;
import cloud.duringbug.UDP.Provider.impl.Provider;
import cloud.duringbug.UDP.Searcher.BroadcastSearcher;
import cloud.duringbug.UDP.Searcher.UDPFileSender;
import cloud.duringbug.UDP.Searcher.UDPSearcher;
import cloud.duringbug.UDP.Searcher.impl.Searcher;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-11 18:37:35
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-11 19:26:14
 */



/**
 * @author 唐健峰
 * @version 1.0
 * @date 2023/5/11 18:37
 * @description:
 */
public class MyTest {
    @Test
    public void testUDPProvider() throws IOException {
       UDPProvider udpProvider=new UDPProvider();
       udpProvider.providerOpen();
    }
    @Test
    public void testUDPSearcher() throws IOException {
       UDPSearcher udpSearcher=new UDPSearcher();
       udpSearcher.searcherOpen();
    }
   @Test
   public void testBroadcastProvider() throws IOException{
      Provider bProvider=new BroadcastProvider();
      bProvider.providerOpen();
   }
   @Test
   public void testBroadcastSearcher1() throws IOException{
      Searcher bSearcher =new BroadcastSearcher();
      bSearcher.searcherOpen();
   }
   @Test
   public void testBroadcastSearcher2() throws IOException{
      Searcher bSearcher =new BroadcastSearcher();
      bSearcher.searcherOpen();
   }
   @Test
   public void testSendFile() throws IOException{
      Searcher fSearcher=new UDPFileSender();
      fSearcher.searcherOpen();
   }
   @Test
   public void testReciveFile() throws IOException{
      Provider fProvider=new UDPFileReceiver();
      fProvider.providerOpen();
   }
   @Test
   public void testTCPReciveFile(){
      TCPFileReceiver tcpFileReceiver=new TCPFileReceiver();
      tcpFileReceiver.receiveFile("checksum_recv.txt");
   }
   @Test 
   public void testTCPSendFile(){
      TCPFileSender tcpFileSender=new TCPFileSender();
      tcpFileSender.sendFile();
   }
}
