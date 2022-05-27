package myRPC.test;

import myRPC.server.RpcServer;
import myRPC.service.HelloService;
import myRPC.service.impl.HelloServiceImpl;

/**
 * Date: 2022/5/27
 * Time: 19:55
 *
 * @Author SillyBaka
 * Description：
 **/
public class testServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        RpcServer rpcServer = new RpcServer();

        rpcServer.register(9999,helloService);
    }
}
