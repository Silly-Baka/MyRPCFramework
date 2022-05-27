package myRPC.protocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Date: 2022/5/26
 * Time: 21:12
 *
 * @Author SillyBaka
 *
 * Description： RPC请求协议
 **/
@Data
@Builder
public class RpcRequest implements Serializable {
    /**
     * 要调用的接口名字
     */
    private String interfaceName;
    /**
     * 要调用的方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parameterType;
    /**
     * 参数列表
     */
    private Object[] parameters;
}
