package com.netease.homework.controller;

import com.netease.homework.repository.UserPO;
import com.netease.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //得到所有的用户
    @ResponseBody
    @GetMapping
    public List<UserPO> getAllUsers(){
        return  userService.getAllUsers();
    }

    //根据名字得到某个用户
    @ResponseBody
    @GetMapping(value = "/{username}")
    public List<UserPO> getUserByName(@PathVariable String username){
        return  userService.getUserByName(username);
    }

    @ResponseBody
    @GetMapping(value="/{username}/type")
    public String getUserType(@PathVariable String username){
        return userService.getUserType(username);
    }


}
