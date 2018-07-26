package com.zackma.controller;

import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.zackma.entity.JfUsers;

import java.util.List;

public class IndexController extends Controller{

    public void index(){
        String token = PropKit.get("token");
        System.out.println(token);
        renderJsp("index.jsp");
    }

    public void users(){
        List<JfUsers> users = JfUsers.Dao.find("select * from jfusers where 1=1");
        renderJson(users);
    }
}
