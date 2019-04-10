package com.netease.homework;

import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.ProductPOMapper;
import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.ProductPOMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductTest {
    private static SqlSessionFactory factory;
    @BeforeClass
    public static void setupBeforeClass() throws IOException {
        String resource="mybatisConfig.xml";
        InputStream inputStream=Resources.getResourceAsStream(resource);
        factory =new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        SqlSession sqlSession=factory.openSession();
        ProductPOMapper ProductPOMapper=sqlSession.getMapper(ProductPOMapper.class);
        //测试select
        List<ProductPO> ProductPOList=ProductPOMapper.selectAll();
        for(ProductPO productPO:ProductPOList){
            System.out.println(productPO.getId()+" "+productPO.getName()+" "+productPO.getDescribe()+" "+productPO.getNumbers());
        }
    }
}
