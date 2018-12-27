package com.yang.rpc.provider;
/**
 * 服务提供者接口，用于暴露给服务消费者进行消费
 *
 */
public interface ProviderDemo {
    /**
     * 服务提供者打印Msg方法
     */
    public String printMsg(String msg);
}
