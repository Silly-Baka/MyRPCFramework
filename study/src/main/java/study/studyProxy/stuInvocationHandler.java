package study.studyProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date: 2022/5/26
 * Time: 20:31
 *
 * @Author SillyBaka
 * Description：
 **/
public class stuInvocationHandler<T> implements InvocationHandler {

    T target;

    public stuInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行"+method.getName()+"方法");
        Object result = method.invoke(target, args);
        System.out.println("代理方法执行完毕");

        return result;
    }
}
