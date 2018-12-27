package com.yang.rpc.provider;

/**
 * 服务提供者实现类
 * 
 */
public class ProviderDemoImpl implements ProviderDemo {
 
    public String printMsg(String msg) {
        System.out.println("----传入的参数为" + msg + "----");
        return "欢迎你 " + msg;
    }
}
