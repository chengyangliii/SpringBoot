package com.example.demo.SpringBoot.dao;

import com.example.demo.SpringBoot.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseDao {

    @Select("select id,name,year from Course where id = #{id}")
    Course getCourseById(Integer id);

    @Select("select * from Course")
    List<Course> findAllCourses();

    @Insert("insert into Course(name,year) values(#{name},#{year})")
    int insertCourse(Course course);

    @Update("update Course set name=#{name},year=#{year} where id=#{id}")
    int updCourse(Course course);

    @Delete("delete from Course where id=#{id}")
    int delCourse(Integer id);

}
