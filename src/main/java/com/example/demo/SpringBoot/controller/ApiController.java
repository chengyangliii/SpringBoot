package com.example.demo.SpringBoot.controller;


import com.example.demo.SpringBoot.common.Result;
import com.example.demo.SpringBoot.common.ResultGenerator;
import com.example.demo.SpringBoot.dao.UserDao;
import com.example.demo.SpringBoot.entity.Topic;
import com.example.demo.SpringBoot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    UserDao userDao;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getOne(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        User user = userDao.getUserById(id);
        if (user == null) {
            return ResultGenerator.genFailResult("Data not existed");
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> queryAll() {
        List<User> users = userDao.findAllUsers();
        return ResultGenerator.genSuccessResult(users);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert(@RequestBody User user) {

        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        return ResultGenerator.genSuccessResult(userDao.insertUser(user) > 0);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody User tempUser) {
        //参数验证
        if (tempUser.getId() == null || tempUser.getId() < 1 || StringUtils.isEmpty(tempUser.getName()) || StringUtils.isEmpty(tempUser.getPassword())) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        //实体验证，不存在则不继续修改操作
        User user = userDao.getUserById(tempUser.getId());
        if (user == null) {
            return ResultGenerator.genFailResult("Parameter incorrect");
        }
        user.setName(tempUser.getName());
        user.setPassword(tempUser.getPassword());
        return ResultGenerator.genSuccessResult(userDao.updUser(user) > 0);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("Lack parameter");
        }
        return ResultGenerator.genSuccessResult(userDao.delUser(id) > 0);
    }
}