package com.example.demo.SpringBoot.dao;

import com.example.demo.SpringBoot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Select("select * from tb_user")
    List<User> findAllUsers();

    @Insert("insert into tb_user(name,password) values(#{name},#{password})")
    int insertUser(User User);

    @Update("update tb_user set name=#{name},password=#{password} where id=#{id}")
    int updUser(User User);

    @Delete("delete from tb_user where id=#{id}")
    int delUser(Integer id);
}