package myRPC.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * Date: 2022/5/26
 * Time: 21:16
 *
 * @Author SillyBaka
 * Description： RPC响应协议
 *
 * @param <T> 响应数据的类型
 *
 **/
@Data
public class RpcResponse<T> implements Serializable {
    /**
     * 响应码
     */
    private Integer statusCode;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T responseData;

    /**
     *
     * @param data 响应数据
     * @return 返回成功的响应信息
     */
    public static <T> RpcResponse<T> success(T data){
        RpcResponse<T> rpcResponse = new RpcResponse<>();

        rpcResponse.setStatusCode(RpcStatus.SUCCESS.getCode());
        rpcResponse.setResponseData(data);

        return rpcResponse;
    }

    /**
     *
     * @param message 失败信息
     * @return 返回失败的响应信息
     */
    public static RpcResponse<Object> fail(String message){
        RpcResponse<Object> rpcResponse = new RpcResponse<>();

        rpcResponse.setStatusCode(RpcStatus.FAIL.getCode());
        rpcResponse.setMessage(message);

        return rpcResponse;
    }
}
