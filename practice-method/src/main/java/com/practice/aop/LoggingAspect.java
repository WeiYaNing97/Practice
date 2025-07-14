package com.practice.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 面向切面编程示例
 *
 * 在Spring AOP（面向切面编程）中，一个切面类通常包含多个通知（Advice），这些通知定义了切面的行为。
 *
 * 在这个例子中，LoggingAspect类是一个切面类，它包含了五个通知：
 *
 * @Before：前置通知。在目标方法执行之前运行。可以用来记录日志、验证权限等。
 * @AfterReturning：后置通知。在目标方法成功执行并返回结果之后运行。可以用来记录日志、处理返回值等。
 * @AfterThrowing：异常通知。在目标方法抛出异常时运行。可以用来记录错误日志、发送告警等。
 * @After：最终通知。无论目标方法是否成功执行或抛出异常，都会在最后运行。可以用来释放资源、记录日志等。
 * @Around：环绕通知。在目标方法执行前后都运行。可以用来记录日志、性能监控、事务管理等。
 *
 * 每个通知都有一个切入点表达式，用于指定该通知应该应用到哪些方法上。
 * 例如，execution(* com.example..*.*(..))
 * 表示该通知将应用于com.example 包下的所有类的所有方法。
 *
 * 配置AOP 在Spring Boot项目中，AOP默认是开启的，无需额外配置。
 * 如果需要自定义AOP配置，可以在application.properties或application.yml文件中进行配置。
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * 在Spring AOP中，execution表达式用于定义切点（Pointcut），它描述了哪些方法应该被拦截。execution表达式的设置规范如下：
     *
     * 访问修饰符：可以使用public, private, protected, *等来表示方法的访问修饰符。其中*表示任意访问修饰符。
     *
     * 返回类型：可以是具体的类名，也可以使用通配符*表示任意返回类型。
     *
     * 包名：可以使用完整的包名，也可以使用..表示任意子包。
     * 例如，com.example.service表示com.example包下的service包及其子包，
     * 而com.example..service表示com.example包及其所有子包下的service包。
     *
     * 类名：可以使用具体的类名，也可以使用通配符*表示任意类名。
     *
     * 方法名：可以使用具体的函数名，也可以使用通配符*表示任意函数名。
     *
     * 参数列表：可以使用具体的参数类型，也可以使用通配符*表示任意参数类型。多个参数之间用逗号分隔。可以使用..表示任意数量的参数。
     *
     * 组合规则：可以使用&&和||来组合多个表达式，实现更复杂的匹配规则。
     *
     * 下面是一些示例：
     *
     * execution(* com.example.service.*.*(..))：匹配com.example.service包下的所有类的所有方法。
     * execution(public * com.example.service.UserService.*(..))：匹配com.example.service包下的UserService类的公共方法。
     * execution(* com.example.service.*.update*(..))：匹配com.example.service包下的所有类中以update开头的方法。
     * execution(public void com.example.service.UserService.updateUserInfo(String, int))：匹配com.example.service.UserService类的updateUserInfo方法，该方法为公共方法，接收一个字符串和一个整数作为参数。
     *
     * 注意：在实际使用中，建议尽量避免过于宽泛的匹配规则，以免影响性能。
     */


    // 前置通知
    // 使用execution表达式定义切点，匹配com.example包及其所有子包下的service包中的任意类的任意方法
    // 前置通知将在目标方法执行之前执行
    @Before("execution(* com.practice..service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 记录日志，输出正在执行的前置通知方法的信息
        logger.info("(前置通知) Before method: " + joinPoint.getSignature());
    }

    // 后置通知
    @AfterReturning(pointcut = "execution(* com.practice..service.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        logger.info("(后置通知) After returning method: " + joinPoint.getSignature() + " with result: " + result);
    }

    // 异常通知
    // 当执行com.example包下service包中任意类的任意方法并抛出异常时，触发此通知
    @AfterThrowing(pointcut = "execution(* com.practice..service.*.*(..))", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        // 记录日志，打印异常信息
        logger.error("(异常通知) After throwing method: " + joinPoint.getSignature() + " with exception: " + ex);
    }

    // 最终通知
    @After("execution(* com.practice..service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("(最终通知) After method: " + joinPoint.getSignature());
    }

    // 环绕通知
    @Around("execution(* com.practice..service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("(环绕通知) Before method: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        return result;
    }

}