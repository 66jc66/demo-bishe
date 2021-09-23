package com.xyxy.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/*
* 数据库增删改查的基础dao类：给其他类继承使用
* */
public class BaseDao<T> {

    //创建执行sql对象QueryRunner
    private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
    //定义类类型变量
    private Class<T> type;

    /*type赋值*/
    public BaseDao(){
        //获取当前类对象的字节码对象
        Class<? extends BaseDao> clazz = this.getClass();
        //获取当前类的父类的类型
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        //获取父类泛型的参数数组
        Type[] types = genericSuperclass.getActualTypeArguments();
        //给type属性赋值:泛型参数只有一个所以对应数组的第一个元素
        this.type= (Class<T>) types[0];
    }

    /*
    * 增删改方法
    * */
    public int update(String sql, Object... params){
        int row=0;
        try {
            row=  queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  row;
    }
    /*
    * 获取当个对象方法
    * */
    public T getBean(String sql, Object... params){
        try {
           T t= queryRunner.query(sql,new BeanHandler<T>(type),params);
           return  t;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*获取多个对象的方法*/
    public List<T> getBeanList(String sql, Object... params){
        try {
            List<T> list = queryRunner.query(sql,
                    new BeanListHandler<T>(type), params);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*获取聚合查询的值*/
    public int getNumber(String sql, Object... params){
        int num=0;
        try {
            Object obj = queryRunner.query(sql, new ScalarHandler<Object>(), params);
            return  Integer.valueOf(obj.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  num;
    }

}
