package com.netease.homework;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        // 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 然后根据 sqlSessionFactory 得到 session
        SqlSession session = sqlSessionFactory.openSession();
        // 最后通过 session 的 selectList() 方法调用 sql 语句 listStudent
//        List<UserPO> listUser = session.selectList("listUser");
//        List<UserPO> listUser = session.selectList("selectAll");
//        for (UserPO userPO : listUser) {
//            System.out.println("ID:" + userPO.getId() + ",NAME:" + userPO.getUsername()+",email: "+userPO.getEmail());
//        }
//
//        List<UserPO> userPOList=session.selectList("selectByName","seller");
//        for(UserPO userPO:userPOList){
//            System.out.println("Id: "+userPO.getId()+",Name :"+userPO.getUsername()+", email: "+userPO.getEmail());
//        }
        session.commit();
        session.close();
    }
}
