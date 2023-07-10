package com.ssm.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Cors extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, token");
        response.addHeader("Access-Control-Max-Age", "1800");
        return true;
    }

    public void out (HttpServletResponse httpServletResponse, String response){
        try {
            httpServletResponse.setContentType("application/json;charset=utf-8;");
            PrintWriter out = httpServletResponse.getWriter();
            out.print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
