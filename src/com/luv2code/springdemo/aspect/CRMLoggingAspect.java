package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDAOPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {

    }

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        //display the method we're calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> calling method: " + theMethod);

        //display the arguments to the method
        Object[] args = theJoinPoint.getArgs();
        for (Object arg : args) {
            myLogger.info("====>> argument: " + arg);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        //display the method we're returning
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> returning method: " + theMethod);

        //display data we're returning
        myLogger.info("====>> result: " + theResult);
    }
}
