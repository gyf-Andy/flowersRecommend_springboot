package com.flower.controller;

import com.flower.entity.Response;
import com.flower.entity.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录操作
     * @return 是否登录成功
     */
    @PostMapping("/login")
    @ResponseBody
    public Response loginOp(User user, HttpSession session) {
        User loginUser = userService.login(user);
        if (loginUser != null) {
            session.setMaxInactiveInterval(30*60);
            session.setAttribute("user", loginUser);
            return new Response("200", "登录成功");
        }
        return new Response("300", "用户名或密码错误");
    }

    /**
     * 注册页面
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册操作
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @ResponseBody
    public Response registerOp(User user) {
        Date date = new Date();

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
        user.setRegisterdate(new SimpleDateFormat("yyyy-MM-dd").format(date));

        if (userService.findByUsername(user.getUsername()) != null) {
            return new Response("300", "该用户名已存在请更换");
        } else {
            return userService.add(user) > 0 ?
                    new Response("200", "注册成功,前往登录") :
                    new Response("300", "注册失败");
        }
    }

    /**
     * 登出系统
     */
    @PostMapping("/logout")
    @ResponseBody
    public Response logout(HttpSession session) {
        session.invalidate();
        return new Response("200", "即将退出系统");
    }
}
