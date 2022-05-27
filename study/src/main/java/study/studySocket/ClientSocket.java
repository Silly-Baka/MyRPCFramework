package study.studySocket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Date: 2022/5/27
 * Time: 16:20
 *
 * @Author SillyBaka
 * Description：
 **/
public class ClientSocket {
    public static void main(String[] args) {
        // todo 1. 创建一个客户端socket
        try (Socket socket = new Socket("127.0.0.1", 9999);){
            // todo 2. 获取socket的输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // todo 3. 写出数据
            bufferedWriter.write("你好，这是我的第一个socket");

            bufferedWriter.flush();

            socket.shutdownOutput();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String readLine = bufferedReader.readLine();

            System.out.println("服务端说："+readLine);
            // todo 4. 告诉服务端 写完了
//            socket.shutdownOutput();
//            socket.shutdownInput();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
