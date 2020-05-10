package com.pyg.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/showName")
    public String showName(){
        //从SpringSecurity中去获取用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
