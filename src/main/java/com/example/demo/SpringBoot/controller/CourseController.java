package com.example.demo.SpringBoot.controller;

import com.example.demo.SpringBoot.common.Result;
import com.example.demo.SpringBoot.common.ResultGenerator;
import com.example.demo.SpringBoot.dao.CourseDao;
import com.example.demo.SpringBoot.entity.Course;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CourseController {

    @Resource
    CourseDao courseDao;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Course>> queryAllCourse() {
        List<Course> courses = courseDao.findAllCourses();
        return ResultGenerator.genSuccessResult(courses);
    }


    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Course> getCourse(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        Course course = courseDao.getCourseById(id);
        if (course == null) {
            return ResultGenerator.genFailResult("Data not existed");
        }
        return ResultGenerator.genSuccessResult(course);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insertCourse(@RequestBody Course course) {

        if (StringUtils.isEmpty(course.getName()) || StringUtils.isEmpty(course.getYear())) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        return ResultGenerator.genSuccessResult(courseDao.insertCourse(course) > 0);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> updateCourse(@RequestBody Course tempUser) {
        //参数验证
        if (tempUser.getId() == null || tempUser.getId() < 1 || StringUtils.isEmpty(tempUser.getName()) || StringUtils.isEmpty(tempUser.getYear())) {
            return ResultGenerator.genFailResult("Lack parameter");
        }

        Course course = courseDao.getCourseById(tempUser.getId());
        if (course == null) {
            return ResultGenerator.genFailResult("Parameter incorrect");
        }
        course.setName(tempUser.getName());
        course.setYear(tempUser.getYear());
        return ResultGenerator.genSuccessResult(courseDao.updCourse(course) > 0);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> deleteCourse(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        return ResultGenerator.genSuccessResult(courseDao.delCourse(id) > 0);
    }

}
