package com.yang.rpc.provider;

/**
 * �����ṩ��ʵ����
 * 
 */
public class ProviderDemoImpl implements ProviderDemo {
 
    public String printMsg(String msg) {
        System.out.println("----����Ĳ���Ϊ" + msg + "----");
        return "��ӭ�� " + msg;
    }
}
