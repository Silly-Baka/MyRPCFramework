package myRPC.client;

import lombok.extern.slf4j.Slf4j;
import myRPC.protocol.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Date: 2022/5/27
 * Time: 16:30
 *
 * @Author SillyBaka
 * Description：rpc客户端
 **/
@Slf4j
public class RpcClient {
    public RpcClient(){

    }
    /**
     * 将请求通过网络传输给服务端，并且获取响应结果
     * @param rpcRequest 调用请求
     * @return 调用后的响应结果
     */
    public Object sendRequest(RpcRequest rpcRequest,String host,Integer port){
        try(Socket socket = new Socket(host,port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // todo 1. 通过JDK自带的序列化 将请求体写入socket 通过socket发给服务端
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            // todo 2. 获取从服务端返回的响应信息 并返回
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("发送请求错误,错误信息为{}",e.getMessage());
        }
        return null;
    }
}
