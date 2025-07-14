# 拦截器个人理解

    MyInterceptor 自定义了一个拦截器，实现了HandlerInterceptor接口，
    在preHandle方法中拦截了所有请求，并且在拦截器中加入了登录校验的逻辑。
    `HandlerInterceptor` 是 Spring MVC 框架中的一个接口，用于拦截处理器（Handler）的执行。它允许你在请求被处理之前、处理器执行之后以及视图渲染完成之后执行一些操作。通过实现这个接口，你可以对请求进行预处理、后处理或者终止请求处理流程，从而实现如权限检查、日志记录、性能监控等功能。


        `HandlerInterceptor` 接口包含三个方法：
        1. `preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception`
            - 在处理器执行之前被调用。
            - 返回值为 boolean 类型，返回 true 表示继续执行后续的拦截器或处理器；返回 false 则表示中断后续执行，常用于权限验证等场景。
        
        2. `postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception`
            - 在处理器执行之后，但在视图渲染之前被调用。
            - 可以在这个方法中修改处理器返回的数据或视图模型。
    
        3. `afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception`
            - 在整个请求处理完成后（包括视图渲染）被调用。
            - 通常用于资源清理、异常处理等。
        
        要使用 `HandlerInterceptor`，你需要创建一个实现了该接口的类，并且在 Spring 配置文件中或通过注解配置将其注册到拦截器链中。这样，当有请求到来时，Spring MVC 就会按照顺序调用这些拦截器的方法。

# 自定义拦截器配置类，用于注册自定义的拦截器。

    1：类名不必须要求 WebConfig 但是要求添加上 @Configuration 注解。
        表明这是一个 Spring 的配置类，会被 Spring 容器加载。
    2：实现 WebMvcConfigurer 接口
        这样你就可以重写它的方法（如 addInterceptors()、addCorsMappings() 等），
        用于自定义 Spring MVC 的行为。
        
        `WebMvcConfigurer` 接口提供了多个方法，允许开发者自定义 Spring MVC 的配置。以下是 `WebMvcConfigurer` 中一些常用的方法及其解释：
        
           1. **addFormatters(FormatterRegistry registry)**
               - 允许你添加自定义的格式化器（Formatter）或转换器（Converter），用于数据绑定和类型转换。
        
           2. **configureMessageConverters(List<HttpMessageConverter<?>> converters)**
               - 配置消息转换器（HttpMessageConverter），这些转换器用于将请求和响应的内容进行转换。例如，从JSON到Java对象或者反之亦然。
        
           3. **extendMessageConverters(List<HttpMessageConverter<?>> converters)**
               - 扩展默认的消息转换器列表，而不是替换它们。相比`configureMessageConverters`，此方法不会覆盖默认的转换器设置。
        
           4. **configureContentNegotiation(ContentNegotiationConfigurer configurer)**
               - 用于配置内容协商机制，决定如何根据请求的内容类型解析请求，并确定返回给客户端的内容类型。
        
           5. **addInterceptors(InterceptorRegistry registry)**
               - 注册自定义拦截器（HandlerInterceptor），这些拦截器可以用来预处理请求、后处理响应等操作。
        
           6. **addResourceHandlers(ResourceHandlerRegistry registry)**
               - 配置静态资源处理器，指定如何映射URL到特定的资源位置，如图片、CSS文件等。
        
           7. **configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)**
               - 配置默认的Servlet处理器，通常用于提供对静态资源的支持。
        
           8. **addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)**
               - 注册自定义的参数解析器，这些解析器用于解析控制器方法参数。
        
           9. **addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers)**
               - 注册自定义的返回值处理器，这些处理器用于处理控制器方法的返回值。
        
           10. **configureViewResolvers(ViewResolverRegistry registry)**
               - 配置视图解析器（ViewResolver），用于解析逻辑视图名到实际视图实现。
        
           11. **configureAsyncSupport(AsyncSupportConfigurer configurer)**
               - 配置异步支持选项，包括任务执行器和异常处理器等。
        
           12. **configurePathMatch(PathMatchConfigurer configurer)**
               - 配置路径匹配选项，比如是否使用后缀模式匹配（如`.html`）、是否忽略请求中的末尾斜杠等。
        
        通过实现 `WebMvcConfigurer` 接口中一个或多个方法，可以根据项目的具体需求来定制Spring MVC的行为，而不需要完全替代默认的配置。这使得自定义配置更加灵活且易于维护。


        好的！下面我将通过代码示例的方式，展示 `WebMvcConfigurer` 接口中一些常用方法的 **实际应用场景**。这些示例可以帮助你更好地理解如何使用 Spring MVC 提供的配置扩展能力。

---

## ✅ 示例 1：添加拦截器（`addInterceptors`）

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor())
                .addPathPatterns("/**")              // 拦截所有请求
                .excludePathPatterns("/login", "/css/**", "/js/**"); // 排除路径
    }
}
```

> 自定义拦截器类：

```java
@Component
public class MyCustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Before handling the request");
        return true; // 返回 false 可以阻止请求继续执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("After handling the request");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Request completed");
    }
}
```

---

## ✅ 示例 2：配置静态资源访问（`addResourceHandlers`）

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
}
```

> 这样就可以通过 `/static/css/style.css` 访问 `src/main/resources/static/css/style.css` 文件。

---

## ✅ 示例 3：自定义消息转换器（`configureMessageConverters`）

```java
@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // 添加一个支持 XML 的转换器
    converters.add(new Jaxb2RootElementHttpMessageConverter());
    
    // 添加 JSON 转换器（默认已存在）
    converters.add(new MappingJackson2HttpMessageConverter());
}
```

> 或者使用 `extendMessageConverters()` 来扩展而不是替换默认的转换器列表。

---

## ✅ 示例 4：配置内容协商（`configureContentNegotiation`）

```java
@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer
        .favorParameter(true)
        .parameterName("mediaType")
        .ignoreAcceptHeader(false)
        .useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON);
}
```

> 这表示可以通过 URL 参数 `?mediaType=json` 控制返回的数据格式。

---

## ✅ 示例 5：配置视图解析器（`configureViewResolvers`）

```java
@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/WEB-INF/views/", ".jsp");
}
```

> 假设你的控制器返回 `"home"`，那么它会映射到 `/WEB-INF/views/home.jsp`。

---

## ✅ 示例 6：添加参数解析器（`addArgumentResolvers`）

```java
@Override
public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new CustomArgumentResolver());
}
```

> 实现一个自定义参数解析器：

```java
public class CustomArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CustomUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String username = webRequest.getParameter("username");
        return new CustomUser(username);
    }
}
```

---

## ✅ 示例 7：配置路径匹配规则（`configurePathMatch`）

```java
@Override
public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(false); // 禁止 .html 后缀匹配
    configurer.setUseTrailingSlashMatch(true);  // 允许末尾斜杠匹配
}
```

---

## ✅ 示例 8：配置异步支持（`configureAsyncSupport`）

```java
@Override
public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    configurer.setDefaultTimeout(30_000); // 设置异步请求默认超时时间
    configurer.registerTaskExecutor(taskExecutor()); // 注册任务执行器
}

@Bean
public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(20);
    executor.setThreadNamePrefix("async-executor-");
    executor.initialize();
    return executor;
}
```

---

## ✅ 示例 9：添加自定义格式化器（`addFormatters`）

```java
@Override
public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
}
```

---

## ✅ 示例 10：配置默认 Servlet 处理（`configureDefaultServletHandling`）

```java
@Override
public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable(); // 启用默认 Servlet 处理静态资源
}
```

---

## 📌 总结对比表

| 方法名 | 用途 |
|-------|------|
| `addInterceptors` | 添加拦截器，处理请求前/后操作 |
| `addResourceHandlers` | 配置静态资源访问路径 |
| `configureMessageConverters` | 自定义 HTTP 请求/响应的消息转换器 |
| `configureContentNegotiation` | 内容协商机制配置 |
| `configureViewResolvers` | 配置视图解析器（如 JSP、Thymeleaf） |
| `addArgumentResolvers` | 添加自定义控制器方法参数解析器 |
| `configurePathMatch` | 配置 URL 匹配策略 |
| `configureAsyncSupport` | 异步请求支持与线程池配置 |
| `addFormatters` | 添加数据格式化器 |
| `configureDefaultServletHandling` | 静态资源交给默认 Servlet 处理 |

---