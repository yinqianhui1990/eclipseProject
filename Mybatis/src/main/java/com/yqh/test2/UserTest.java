package com.yqh.test2;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yqh.test.User;

public class UserTest {
public static void main(String[] args) {
	String resource = "Mybatis-conf.xml";
	 
    Reader reader = null;
    try {
        reader = Resources.getResourceAsReader(resource);
    } catch (IOException e) {
        e.printStackTrace();
    }

    SqlSessionFactory factory = new SqlSessionFactoryBuilder()
            .build(reader);
    //factory.getConfiguration().addMapper(UserInfoMapper.class);
    SqlSession sqlSession = factory.openSession();
    UserMap userMapper = sqlSession
            .getMapper(UserMap.class);
    User user = userMapper.findUserById(1);
    System.out.println("===="+user);
}
}
