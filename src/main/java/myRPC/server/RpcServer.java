package myRPC.server;

import lombok.extern.slf4j.Slf4j;
import myRPC.protocol.RpcRequest;
import myRPC.protocol.RpcResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Date: 2022/5/27
 * Time: 18:50
 *
 * @Author SillyBaka
 *
 * Description：rpc的服务端
 **/
@Slf4j
public class RpcServer {
    /**
     * 用于分配给客户端的线程池
     */
    private final ExecutorService threadPool;

    public RpcServer(){
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 60;
        ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);

        threadPool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,keepAliveTime,
                TimeUnit.SECONDS,
                blockingQueue,
                Executors.defaultThreadFactory());
    }
    /**
     * 注册服务 并让服务一直监听某个端口 接收来自客户端的连接
     * @param port 要监听的端口
     * @param service 服务
     */
    public void register(int port,Object service){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            log.info("服务端正在启动，监听的端口为{}",port);
            Socket socket;
            // 一直监听 获取连接
            while ((socket = serverSocket.accept()) != null){
                log.info("客户端已连接！ip地址为:{}",socket.getInetAddress());
                threadPool.submit(new WorkThread(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("连接错误！");
        }
    }

    /**
     * 用于处理请求的进程任务
     */
    class WorkThread implements Runnable{
        /**
         * 连接对象
         */
        private Socket socket;
        /**
         * 接口的实例对象
         */
        private Object service;

        public WorkThread(Socket socket,Object service){
            this.socket = socket;
            this.service = service;
        }

        @Override
        public void run() {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){

                // todo 1. 获取客户端发来的请求信息
                RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                // todo 2. 处理请求 通过反射调用服务
                String interfaceName = rpcRequest.getInterfaceName();
                String methodName = rpcRequest.getMethodName();
                Class<?>[] parameterType = rpcRequest.getParameterType();
                Object[] parameters = rpcRequest.getParameters();

                Class<?> serviceClass = service.getClass();
                Method method = serviceClass.getDeclaredMethod(methodName, parameterType);

                Object resultObject = method.invoke(service, parameters);
                // todo 3. 将处理请求结果包装 然后通过socket返回给客户端
                RpcResponse<Object> rpcResponse = RpcResponse.success(resultObject);

                objectOutputStream.writeObject(rpcResponse);
                objectOutputStream.flush();

            } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.error("处理请求时出现错误:",e);
            }
        }
    }

}
