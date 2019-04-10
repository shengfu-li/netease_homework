package com.netease.homework;

import com.netease.homework.repository.UserPO;
import com.netease.homework.repository.UserPOMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UserTest {

    private  static  SqlSessionFactory factory;
    @BeforeClass
    public static  void setUpBeforeClass() throws IOException {
        String resource="mybatisConfig.xml";
        InputStream inputStream=Resources.getResourceAsStream(resource);
        factory =new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        SqlSession sqlSession=factory.openSession();
        UserPOMapper userPOMapper=sqlSession.getMapper(UserPOMapper.class);
//        测试selectById
        UserPO userPO =userPOMapper.selectById("1");
        System.out.println(userPO.getId()+" "+userPO.getUsername()+" "+userPO.getEmail());

//        //测试selectByName
//        List<UserPO> userPOList=userPOMapper.selectByName("seller");
//        for(UserPO userPO1:userPOList){
//            System.out.println(userPO1.getId()+" "+userPO1.getUsername()+" "+userPO1.getEmail());
//
//        }

        //测试insertUser
        UserPO userPO2=new UserPO();
        userPO2.setEmail("hello@163.com");
        userPO2.setUsername("seller");
        userPO2.setPassword("test");
        userPOMapper.insertUser(userPO2);
//        List<UserPO> userPOList=userPOMapper.selectByName("seller");
//        for(UserPO userPO1:userPOList){
//            System.out.println(userPO1.getId()+" "+userPO1.getUsername()+" "+userPO1.getEmail());
//
//        }
        //测试deleteUser
        userPOMapper.deleteUser(userPO2);
        List<UserPO> userPOList=userPOMapper.selectByName("seller");
        for(UserPO userPO1:userPOList){
            System.out.println(userPO1.getId()+" "+userPO1.getUsername()+" "+userPO1.getEmail());

        }

    }
}
