# 面向切面编程
 日志、事务、权限等功能的处理，几乎都会使用面向切面编程来处理。
 日志真实应用过。
 
## 面向切面编程（AOP）
    1：类上 添加注解：@Aspect 与 @Component
    2：切入点表达式 ：execution(* com.atguigu.service.*.*(..))
    3：通知类型 ：前置通知 @Before
                后置通知 @After
                返回通知 @AfterReturning
                异常通知 @AfterThrowing
                环绕通知 @Around