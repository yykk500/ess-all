package com.ess.example.goods.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JobScheduleAop {

//    @Pointcut("@annotation(ssm.annotation.Log)")
    @Pointcut("execution (* com.ess.example.goods.schedule..*.*(..))")
    public void scheduleAspect() {
    }


    //    配置controller环绕通知,使用在方法aspect()上注册的切入点
//    @Around("scheduleAspect()")
//    public void around(JoinPoint joinPoint) {
//        System.out.println("==========1开始执行controller环绕通知===============");
//        long start = System.currentTimeMillis();
//        try {
//            ((ProceedingJoinPoint) joinPoint).proceed();
//            long end = System.currentTimeMillis();
//            System.out.println("==========3开始执行controller环绕通知结束 "+(end-start)+"===============");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }


    @Before("scheduleAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========2执行scheduleAspect前置通知===============");
    }


    @After("scheduleAspect()")
    public void after(JoinPoint joinPoint) {
        System.out.println("==========4执行scheduleAspect后置通知===============");
    }


    @AfterReturning("scheduleAspect()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("==========5执行scheduleAspect后置Return通知===============");
    }
    /**
     * 159      * 异常通知 用于拦截记录异常日志
     * 160      *
     * 161      * @param joinPoint
     * 162      * @param e
     * 163
     */
    @AfterThrowing(pointcut = "scheduleAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("==========执行scheduleAspect 异常通知===============");
    }
}
