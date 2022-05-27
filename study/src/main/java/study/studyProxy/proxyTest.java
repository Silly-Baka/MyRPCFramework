package study.studyProxy;

import java.lang.reflect.Proxy;

/**
 * Date: 2022/5/26
 * Time: 20:34
 *
 * @Author SillyBaka
 * Description：
 **/
public class proxyTest {
    public static void main(String[] args) {
        // 被代理的对象
        Person person = new Student("李四");
        // 用于调用被代理对象的调用处理程序
        stuInvocationHandler<Person> stuInvocationHandler = new stuInvocationHandler<>(person);

        // 创建一个代理对象
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, stuInvocationHandler);

        stuProxy.giveMoney();
    }
}
