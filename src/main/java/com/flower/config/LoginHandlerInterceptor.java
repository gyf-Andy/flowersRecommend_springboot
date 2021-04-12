package com.flower.config;

import com.flower.entity.Admin;
import com.flower.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置拦截器
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        Admin admin=(Admin)request.getSession().getAttribute("admin");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
