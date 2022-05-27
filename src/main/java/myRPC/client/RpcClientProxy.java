package myRPC.client;

import myRPC.protocol.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * Date: 2022/5/26
 * Time: 23:07
 *
 * @Author SillyBaka
 *
 * Description：客户端代理工厂
 *
 **/
public class RpcClientProxy implements InvocationHandler {
    /**
     * 服务端的地址
     */
    private String host;
    /**
     * 服务端的端口
     */
    private Integer port;

    public RpcClientProxy(){

    }
    public RpcClientProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
    /**
     * 获得接口的代理对象
     * @param clazz 接口类
     * @return 返回代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // todo 将调用信息按照传输协议封装成 rpc请求对象
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterType(method.getParameterTypes())
                .parameters(args)
                .build();

        RpcClient rpcClient = new RpcClient();
        return rpcClient.sendRequest(rpcRequest,host,port);
    }

}
