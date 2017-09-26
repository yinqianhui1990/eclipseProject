package com.yqh.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yqh.test.UserMap;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println("开始=====》》》");
		String resource = "Mybatis-conf.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			/*方法一：
			 *com.yqh.test.UserMap 为map.xml文件的名称*
			 同时<mapper namespace="com.yqh.test.UserMap">要引用此文件的路径*/
		 User user = (User) session.selectOne("com.yqh.test.UserMap.findUserById", 1);
		 System.out.println(user.getUsername()+"=="+user.getPassword());
		  
		  /*方法二：
		   * 使用对于给定语句能够合理描述参数和返回值的接口（比如说BlogMapper.class），
		      你现在不但可以执行更清晰和类型安全的代码，而且还不用担心易错的字符串字面值以及强制类型转换。
		  <mapper namespace="com.yqh.test2.UserMap">要引用接口的类路径*/
		  UserMap mapper = session.getMapper(UserMap.class);
		  User user2 = mapper.findUserById(2);
		  System.out.println(user2.getUsername()+"=="+user2.getPassword());
		  
/*	mysql -u root -p ;设置密码
 * 回车；
 * 回车；	 
 * set password =password('root');
 * */	
	} finally {
		  session.close();
		}

	}

}
