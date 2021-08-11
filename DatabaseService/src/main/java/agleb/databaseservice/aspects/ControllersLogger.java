package agleb.databaseservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class ControllersLogger {

    @Pointcut("execution(* agleb.databaseservice.controllers.AccountController.*(..))")
    private void databaseAccountOperation(){}

    @Pointcut("execution(* agleb.databaseservice.controllers.PostController.*(..))")
    private void databasePostOperation(){}

    @Pointcut("execution(* agleb.databaseservice.controllers.SecurityController.*(..))")
    private void tokenRequestOperation(){}

    @Before("databaseAccountOperation()")
    public void beforeAccountOperationLogging(JoinPoint jP){
        MethodSignature methodSignature = (MethodSignature) jP.getSignature();
        Object [] singArgs = jP.getArgs();
        log.info("Account controller operation: " + methodSignature.getMethod().getName() + ". "
                + "Method arguments: " + Arrays.toString(singArgs)
        );
    }

    @Before("databasePostOperation()")
    public void beforePostOperationLogging(JoinPoint jP){
        MethodSignature methodSignature = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        log.info("Post controller operation: " + methodSignature.getMethod().getName() + ". "
                + "Method arguments: " + Arrays.toString(signArgs)
        );
    }

    @Before("tokenRequestOperation()")
    public void loginOperationLogging(JoinPoint jP){
        MethodSignature methodSignature = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        if (signArgs.length >= 1){
            String username = (String) signArgs[0];
                log.info("Authorization controller operation: " + methodSignature.getMethod().getName() + ". "
                        + "Attempt to login with username: " + username
                );
        }else {
                log.warn("Authorization controller operation: " + methodSignature.getMethod().getName() + ". "
                            + "Method arguments is null. Bad request"
            );
        }
    }

}
