package com.practice.aop;


import com.practice.log.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLogAspect {

    /**
     * @Around("@annotation(log)") 的作用
     *
     * @Around("@annotation(log)") 是 Spring AOP（面向切面编程）中的一个表达式，
     * 用于定义一个切面（Aspect）中的环绕通知（Around Advice）。
     *
     * 下面是对这个表达式的详细解释：
     * @Around：
     * 这是 Spring AOP 中的一个注解，用于声明一个方法作为环绕通知。
     * 环绕通知可以在目标方法执行前后执行自定义逻辑，甚至可以决定是否继续执行目标方法。
     *
     * "@annotation(log)"：
     * 这里使用了 @annotation 关键字，它用于匹配所有被特定注解标记的方法。
     * 这是一个切入点表达式（Pointcut Expression），用于指定通知（Advice）应该应用于哪些连接点（Join Point）。
     * @annotation(log) 表示这个切入点匹配所有被 @MyLog 注解标记的方法。这里的 @MyLog 应该是一个自定义的注解，用于标识需要进行日志记录或其他操作的方法。
     * 使用场景
     * 假设你有一个自定义注解 @MyLog，用于标记需要进行日志记录的方法。你可以创建一个切面类，并使用 @Around("@annotation(log)") 来定义一个环绕通知，
     * 这个通知会在所有被 @MyLog 注解标记的方法执行前后执行。你可以在环绕通知中添加日志记录的代码，从而实现对这些方法的日志记录功能。
     */
    //@Around("@annotation(log)")
    public Object logExecution(ProceedingJoinPoint joinPoint, MyLog log) throws Throwable {
        // 打印开始执行的信息
        System.err.println("开始执行：" + log.value());

        // 执行被代理的方法
        Object result = joinPoint.proceed();

        // 打印执行结束的信息
        System.err.println("执行结束：" + log.value());

        // 返回执行结果
        return result;
    }

}