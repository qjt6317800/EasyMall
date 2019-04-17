package com.EasyMall.factory;

import com.EasyMall.utils.PropUtils;

public class BasicFactory {
    private static BasicFactory factory = new BasicFactory();
    //单例
    private BasicFactory(){};
    //返回工厂实例
    public static BasicFactory getFactory(){
        return factory;
    }
    //根据配置文件中的配置项生产UserDao实例
    public <T> T getInstance(Class<T> clazz){
        try{
            //读取配置文件的内容
            String className = PropUtils.getProperty(clazz.getSimpleName());
            Class clz = Class.forName(className);
            return (T)clz.newInstance();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
