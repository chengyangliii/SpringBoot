package com.example.demo.SpringBoot.dao;

import com.example.demo.SpringBoot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select id,name,password from Student where id = #{id}")
    User getUserById(Integer id);

    @Select("select * from Student")
    List<User> findAllUsers();

    @Insert("insert into Student(name,password) values(#{name},#{password})")
    int insertUser(User User);

    @Update("update Student set name=#{name},password=#{password} where id=#{id}")
    int updUser(User User);

    @Delete("delete from Student where id=#{id}")
    int delUser(Integer id);





}

