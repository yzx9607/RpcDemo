package com.yang.rpc.provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * ���������еķ�����
 * 
 */
public class ProviderServer {
 
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
 
        //���ڴ�������߷���ӿڵ�Map,ʵ�ʵĿ���л���ר�ű�������ṩ�ߵ�
        Map<String, Object> serviceMap = new HashMap();
        serviceMap.put(ProviderDemo.class.getName(), new ProviderDemoImpl());
 
        //������
        ServerSocket server = new ServerSocket(8899);
    	System.out.println("����ȴ�����");
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String interfaceName = input.readUTF(); //��ȡ������������Ҫ���ѷ���Ľӿ���
            String methodName = input.readUTF(); ////��ȡ������������Ҫ���ѷ���ķ�����
            
            //����������
            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
            //�����Ķ���
            Object[] rpcArgs = (Object[]) input.readObject();
 
            //ִ�е��ù���
            Class providerInteface = Class.forName(interfaceName); //�õ��ӿ�Class
            Object provider = serviceMap.get(interfaceName);//ȡ�÷���ʵ�ֵĶ���
 
            //��ȡ��Ҫִ�еķ���
            Method method = providerInteface.getMethod(methodName, parameterTypes);
            //ͨ��������е���
            Object result = method.invoke(provider, rpcArgs);
 
            //���ظ��ͻ��˼���������������
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
            System.out.println("������ý���");
        }
    }
}

