package myRPC.service.impl;

import myRPC.model.HelloObject;
import myRPC.service.HelloService;

/**
 * Date: 2022/5/26
 * Time: 21:03
 *
 * @Author SillyBaka
 * Description：
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(HelloObject helloObject) {
        System.out.println("接收到来自"+helloObject.getName()+"的消息");
    }
}
