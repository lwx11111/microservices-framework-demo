package org.example.aop;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.annotation.Logs;
import org.example.domain.Log;

import org.example.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
@Aspect
@Component
@Slf4j
public class LogsAspect {

    private static final String SUCCESS = "成功";
    private static final String FAIL = "失败";

    private static final ThreadLocal<Log> invokeThreadLocal = new ThreadLocal<>();

    @Autowired
    private ILogService invokeLogService;

    @Pointcut("@annotation(org.example.annotation.Logs)")
    public void logService() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void startDateService() {
    }


    /**
     * 进入方法前校验
     *
     * @param joinPoint pointcut
     */
    @Before("startDateService()")
    public void beforeStartInvoke(JoinPoint joinPoint) throws Exception {
        Log params = new Log();
        params.setTime(LocalDateTime.now());
        invokeThreadLocal.set(params);
    }

    /**
     * 正常返回
     *
     * @param joinPoint pointcut
     * @param ret       返回结果
     */
    @AfterReturning(value = "startDateService()", returning = "ret")
    public void afterReturn(JoinPoint joinPoint, Object ret) {
        invokeThreadLocal.remove();
    }

    /**
     * 正常返回
     *
     * @param joinPoint pointcut
     * @param ret       返回结果
     */
    @AfterReturning(value = "logService()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        String receiveMsg = JSONUtil.toJsonStr(ret);
        saveInvokeLog(joinPoint, receiveMsg, SUCCESS);
    }

    /**
     * 抛出异常
     *
     * @param joinPoint pointcut
     * @param ex        异常
     */
    @AfterThrowing(value = "logService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        // 调用class 全名
        String className = joinPoint.getTarget().getClass().getName();
        // 调用方法名
        String methodName = joinPoint.getSignature().getName();
        log.error("Error encountered while invoking {}.{}", className, methodName, ex);
    }

    /**
     * 抛出异常
     *
     * @param joinPoint pointcut
     * @param ex        异常
     */
    @AfterThrowing(value = "startDateService()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Throwable ex) {
        invokeThreadLocal.remove();
    }

    /**
     * 存服务调用日志
     * 可以获取swagger注解或者新注解信息来写入调用信息
     *
     * @param joinPoint   pointcut
     * @param receiveMsg  接收消息
     * @param invokeState 执行状态
     */
    private void saveInvokeLog(JoinPoint joinPoint, String receiveMsg, String invokeState) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作，参数为自定义注解的字节码，返回注解上value的值
        Logs apiOperation = method.getAnnotation(Logs.class);

        // 调用class 全名
        String className = joinPoint.getTarget().getClass().getName();
        // 调用方法名
        String methodName = joinPoint.getSignature().getName();
        // 入参
        Object[] args = joinPoint.getArgs();
        // 接口异常写入invoke_log
        Log log = new Log();
        log.setName(methodName);
        log.setRemark("invoke class: " + className + ", method:" + methodName);
        invokeLogService.save(log);
    }

}
