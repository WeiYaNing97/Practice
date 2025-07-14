package com.practice.http;

import com.practice.log.LogUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Enumeration;

/**
 * 自定义拦截器，用于在请求处理之前执行一些操作。
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前执行的拦截器方法。
     *
     * @param request  HTTP请求对象
     * @param response HTTP响应对象
     * @param handler  被调用的处理器对象，即Controller
     * @return 如果返回true，则继续执行请求；如果返回false，则中断请求，不再执行后续的拦截器或控制器
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 获取HTTP请求的方法
        // 在这里获取HTTP请求的相关信息
        String method = request.getMethod();

        // 获取HTTP请求的URI
        String uri = request.getRequestURI();

        String token = (String) request.getAttribute("token");

        // 打印请求方法和请求URI
        LogUtils.info("请求方法：" + method);
        LogUtils.info("请求URI：" + uri);
        LogUtils.info("token：" + token);

        // ...打印其他信息
        Collection<String> responseheaderNames = response.getHeaderNames();
        for (String name : responseheaderNames) {
            LogUtils.info(name + " : " + response.getHeader(name));
        }


        // 获取HTTP请求头信息
        Enumeration<String> requestheaderNames = request.getHeaderNames();
        if (requestheaderNames != null) {
            while (requestheaderNames.hasMoreElements()) {
                String name = requestheaderNames.nextElement();
                // 打印请求头的名称和值
                LogUtils.info("Header：" + name + "=" + request.getHeader(name));
            }
        }

        // 获取HTTP请求的Cookie信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 打印Cookie信息
                LogUtils.info("Cookie：" + cookie);
            }
        }

        // 获取HTTP请求的Session信息
        HttpSession session = request.getSession();
        if (session != null) {
            // 打印Session ID
            LogUtils.info("Session ID：" + session.getId());
        }

        // 如果一切正常，则返回true表示继续执行请求
        return true; // 返回true表示继续执行请求，返回false表示中断请求
    }

    /**
     * 请求处理完成后的逻辑
     *
     * @param request    代表客户端发送的HTTP请求
     * @param response   代表服务器响应给客户端的HTTP响应
     * @param handler    被调用处理器，也就是Controller，返回的是Object，注意如果返回null，则表示没有处理器处理
     * @param modelAndView 包含模型数据的ModelAndView对象，可能为null
     * @throws Exception 抛出异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 请求处理完成后的逻辑
        LogUtils.info("请求处理完成  postHandle= " + handler.toString());
    }

    /**
     * 在请求完成后执行的逻辑
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param handler 控制器方法对象
     * @param ex 异常对象，如果有异常抛出则为该异常，否则为null
     * @throws Exception 抛出异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后的逻辑
        LogUtils.info("请求处理完成  afterCompletion= " + handler.toString());
    }
}

