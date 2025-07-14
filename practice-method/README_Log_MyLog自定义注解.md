
这段代码是一个 **自定义注解（Custom Annotation）** 的定义，它在 Java 中常用于 **标记某些方法**，并可以携带一些元数据信息。这个注解名为 `@MyLog`，可用于方法上，运行时可通过反射获取它的信息，通常用于 **记录方法执行日志、审计、权限控制、性能监控等 AOP（面向切面编程）场景**。

---

## 🔍 代码逐行解析：

```java
@Target(ElementType.METHOD)
```
- **作用**：指定这个注解可以**应用在哪些程序元素上**。
- **`ElementType.METHOD`** 表示：这个注解只能用在**方法**上。
- 其他可选项包括：
    - `TYPE`（类、接口、枚举）
    - `FIELD`（字段）
    - `PARAMETER`（参数）
    - `CONSTRUCTOR`（构造方法）
    - 等等。

```java
@Retention(RetentionPolicy.RUNTIME)
```
- **作用**：指定这个注解的**生命周期（保留策略）**。
- **`RetentionPolicy.RUNTIME`** 表示：这个注解会被保留到**运行时阶段**，也就是说可以通过反射获取它的信息。
- 可选值有：
    - `SOURCE`：注解只保留在源码中，编译时丢弃（如 `@Override`）。
    - `CLASS`：注解在编译时保留，但运行时不保留（默认）。
    - `RUNTIME`：注解在运行时依然可用（适合 AOP、框架解析使用）。

```java
public @interface MyLog {
    String value() default "执行日志";
}
```
- 定义了一个名为 `MyLog` 的注解。
- 注解内部定义了一个属性 `value()`，类型是 `String`，默认值为 `"执行日志"`。
- 使用时可以指定值，也可以不指定，不指定则使用默认值。

---

## ✅ 示例：如何使用这个注解

### 1. 在方法上使用 `@MyLog`

```java
@Service
public class UserService {

    @MyLog("用户登录")
    public void login(String username) {
        System.out.println(username + " 登录成功");
    }

    @MyLog  // 使用默认值："执行日志"
    public void logout() {
        System.out.println("用户登出");
    }
}
```

---

## 🧠 通常搭配 AOP 使用（记录日志）

你可以使用 Spring AOP 来拦截所有带有 `@MyLog` 注解的方法，并记录日志信息。

### 示例：定义一个切面类

```java
@Aspect
@Component
public class MyLogAspect {

    // 定义切点：所有带有 @MyLog 注解的方法
    @Pointcut("@annotation(com.example.demo.annotation.MyLog)")
    public void myLog() {}

    // 在目标方法执行前拦截
    @Before("myLog()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取注解对象
        MyLog myLog = method.getAnnotation(MyLog.class);
        String desc = myLog.value();

        System.out.println("【日志】开始执行方法：" + desc);
    }

    // 在目标方法执行后拦截
    @After("myLog()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);
        String desc = myLog.value();

        System.out.println("【日志】方法执行结束：" + desc);
    }
}
```

---

## ✅ 应用场景总结：

| 场景 | 说明 |
|------|------|
| 日志记录 | 记录方法执行前后的时间、参数、结果等 |
| 权限控制 | 根据不同注解判断是否有权限执行 |
| 性能监控 | 统计方法执行时间 |
| 审计日志 | 记录谁在什么时候做了什么操作 |
| 事务控制 | 自定义事务注解，结合 AOP 实现更灵活的事务管理 |

---

## 📌 小结

| 特性 | 说明 |
|------|------|
| 注解名称 | `@MyLog` |
| 可用位置 | 方法上（`@Target(ElementType.METHOD)`） |
| 生命周期 | 运行时保留（`@Retention(RUNTIME)`） |
| 属性 | `String value()`，默认 `"执行日志"` |
| 常见用途 | 结合 AOP 实现日志记录、权限控制等 |

---




===================================================

# 如何让自定义注解支持多个参数？


如果你希望自定义注解 `@MyLog` 能够接收 **多个参数**，你可以通过在注解中定义多个属性来实现。Java 注解支持的属性类型是有限的（基本类型、String、Class、枚举、数组等），但完全可以通过添加多个字段来传递多个参数。

---

## ✅ 示例：让 `@MyLog` 支持多个参数

### 📌 修改后的注解定义：

```java
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
    String value() default "执行日志";         // 描述信息
    String module() default "系统模块";         // 模块名称
    boolean logInput() default true;           // 是否记录输入参数
    boolean logOutput() default true;          // 是否记录输出结果
    Level level() default Level.INFO;          // 日志级别
    String[] excludeParams() default {};       // 排除不记录的参数名
}

// 枚举类型用于表示日志级别
enum Level {
    INFO, DEBUG, WARN, ERROR
}
```

---

## ✅ 使用示例：

```java
@MyLog(
    value = "用户登录",
    module = "用户管理",
    logInput = true,
    logOutput = false,
    level = Level.INFO,
    excludeParams = {"password", "token"}
)
public void login(String username, String password) {
    System.out.println(username + " 登录成功");
}
```

---

## ✅ 在 AOP 中获取这些参数：

```java
@Aspect
@Component
public class MyLogAspect {

    @Pointcut("@annotation(com.example.demo.annotation.MyLog)")
    public void myLog() {}

    @Before("myLog()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        MyLog annotation = method.getAnnotation(MyLog.class);

        String desc = annotation.value();
        String module = annotation.module();
        boolean logInput = annotation.logInput();
        boolean logOutput = annotation.logOutput();
        Level level = annotation.level();
        String[] excludeParams = annotation.excludeParams();

        System.out.println("【" + module + "】操作描述：" + desc);
        System.out.println("日志级别：" + level);
        System.out.println("是否记录输入：" + logInput);
        System.out.println("是否记录输出：" + logOutput);
        System.out.println("忽略参数：" + Arrays.toString(excludeParams));
    }
}
```