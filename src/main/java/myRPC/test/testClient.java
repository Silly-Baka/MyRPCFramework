package myRPC.test;

import myRPC.client.RpcClientProxy;
import myRPC.model.HelloObject;
import myRPC.service.HelloService;

/**
 * Date: 2022/5/27
 * Time: 20:00
 *
 * @Author SillyBaka
 * Description：
 **/
public class testClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 9999);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);

        helloService.hello(new HelloObject(10,"牛逼!!!"));
    }
}
