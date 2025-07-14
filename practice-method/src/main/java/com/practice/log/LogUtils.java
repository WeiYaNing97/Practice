package com.practice.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理并记录日志文件
 * 在Spring Boot中整合日志记录（Logger）是非常简单的，
 * 因为Spring Boot已经为Java Util Logging、Log4J2和Logback提供了默认配置。
 * 默认情况下，Spring Boot使用的是Logback作为其日志框架。
 *
 * 1. 默认配置
 * 如果你不想做任何额外配置，Spring Boot会自动为你配置一个基础的日志设置。这意味着你可以直接使用org.slf4j.Logger和org.slf4j.LoggerFactory来创建你的logger，并开始记录信息。
 * 2: 自定义配置 如果你想自定义日志配置，你可以通过在resources目录下创建logback-spring.xml或log4j2-spring.xml文件来实现。
 * 可以配置日志框架来删除过期的日志文件。以Logback为例，你可以使用TimeBasedRollingPolicy或SizeAndTimeBasedRollingPolicy策略，并结合maxHistory属性来实现自动删除过期日志文件的功能。
 * @author ruoyi
 */
@Component
public class LogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class.getName());
    /*LOGGER.info("这是一条信息级别的日志");
      LOGGER.warning("这是一条警告级别的日志");
      LOGGER.severe("这是一条严重级别的日志");*/

    /**
     * 将给定的对象作为信息日志输出，并返回该日志的字符串表示（包含方括号）。
     *
     * @param msg 要输出的信息日志消息，如果为null，则默认为空字符串
     * @return 包含方括号的信息日志字符串
     */
    public static String info(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg.toString() + "]";
        LOGGER.info(log);
        return log;
    }

    /**
     * 将给定的对象作为警告日志输出，并返回该日志的字符串表示（包含方括号）。
     *
     * @param msg 要输出的警告日志消息，如果为null，则默认为空字符串
     * @return 包含方括号的警告日志字符串
     */
    public static String warn(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg.toString() + "]";
        LOGGER.warn(log);
        return log;
    }

    /**
     * 将传入的消息以严重级别输出到日志中，并返回该消息字符串（包含方括号）。
     *
     * @param msg 要输出的消息对象，可以为null，但会被转换为空字符串
     * @return 带有方括号的消息字符串
     */
    public static String error(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg + "]";
        LOGGER.error(log);
        return log;
    }


    /**
     * 将异常信息记录到日志文件中，并返回异常信息的字符串表示
     *
     * @param exception 异常对象
     * @return 异常信息的字符串表示
     */
    public String setExceptionInLog(Exception exception)
    {
        // 获取异常信息的字符串表示
        String severe = error(exception);

        // 获取当前日期时间字符串
        String date = LocalDateTime.now().toString();

        // 创建一个ArrayList用于存储日志信息
        List<String> information_list = new ArrayList<>();
        // 将日期时间字符串和异常信息拼接后添加到信息列表中
        information_list.add(date + exception);

        // 获取异常的堆栈跟踪信息
        StackTraceElement[] stackTrace = exception.getStackTrace();
        // 将堆栈跟踪信息转换为字符串列表，并将每条信息前添加日期时间字符串
        List<String> collect = Arrays.asList(stackTrace).stream().map(x -> date+x).collect(Collectors.toList());
        // 将转换后的堆栈跟踪信息添加到信息列表中
        information_list.addAll(collect);

        // 返回异常信息的字符串表示
        return severe;
    }

}
