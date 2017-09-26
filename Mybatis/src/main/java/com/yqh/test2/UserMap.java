package com.yqh.test2;

import org.apache.ibatis.annotations.Select;

import com.yqh.test.User;

/*注解方式
 * 与map.xml文件同名就好
 *字段名称必须与数据库一样，大小写可以随意。。但是字符必须一样哦哦！
 */
public interface UserMap {
    @Select("select * from days_user where id = #{id}")  
	public User findUserById(int id) ;
	

}
