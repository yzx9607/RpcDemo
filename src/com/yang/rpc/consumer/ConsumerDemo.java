package com.yang.rpc.consumer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import com.yang.rpc.provider.ProviderDemo;

/**
 * ����������
 * 
 */
public class ConsumerDemo {
 
    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException {
        //��ȡ�����ṩ�ߵĽӿ�����һ��RPC��ܶ��Ǳ�¶�����ṩ�ߵĽӿڶ���
        String providerInterface = ProviderDemo.class.getName();
 
        //��ҪԶ��ִ�еķ�������ʵ���������ߵ��������ߵķ���
        Method method = ProviderDemo.class.getMethod("printMsg", java.lang.String.class);
 
        //��Ҫ���ݵĲ���
        Object[] rpcArgs = {"RPC!"};
 
        Socket consumer = new Socket("127.0.0.1", 8899);
 
        //���������ƺͲ��� ���ݸ�����������
        ObjectOutputStream output = new ObjectOutputStream(consumer.getOutputStream());
        output.writeUTF(providerInterface);
        output.writeUTF(method.getName());
        output.writeObject(method.getParameterTypes());
        output.writeObject(rpcArgs);
 
        //�������߶�ȡ���صĽ��
 
        ObjectInputStream input = new ObjectInputStream(consumer.getInputStream());
        Object result = input.readObject();
 
        System.out.println(result.toString());
 
    }
}
