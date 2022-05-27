package study.studySocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2022/5/27
 * Time: 16:14
 *
 * @Author SillyBaka
 * Description：
 **/
public class ServerSocketTest {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(9999)){

            Socket socket;
            while ((socket = serverSocket.accept())!=null){
                System.out.println("接收到客户端的连接");
                threadPool.submit(new workThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class workThread implements Runnable{
        private Socket socket;
        public workThread(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                String clientMessage = bufferedReader.readLine();
                System.out.println("客户端说："+clientMessage);

                bufferedWriter.write("6666666");
                bufferedWriter.flush();

                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
