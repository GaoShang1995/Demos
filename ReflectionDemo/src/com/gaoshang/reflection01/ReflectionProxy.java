package com.gaoshang.reflection01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectionProxy {
    public static void main(String[] args) {
        RealClothFactory realClothFactory = new RealClothFactory();
        ClothFactory proxyInstance = (ClothFactory) RealClothFacotryProxy.getProxyInstance(realClothFactory);
        proxyInstance.getFactory();

    }
}

interface ClothFactory{
    void getFactory();
}

//被代理类
class RealClothFactory implements ClothFactory{

    @Override
    public void getFactory() {
        System.out.println("被代理类");
    }
}

//代理类
class RealClothFacotryProxy {
    public static Object getProxyInstance(Object obj){//被代理对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),myInvocationHandler);
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}