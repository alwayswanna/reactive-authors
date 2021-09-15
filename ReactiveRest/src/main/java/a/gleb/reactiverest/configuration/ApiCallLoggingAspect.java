package a.gleb.reactiverest.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Order(10)
@Slf4j
@Component
public class ApiCallLoggingAspect {

    @Pointcut("execution(* a.gleb.reactiverest.controllers.AdministratorAuthorizationController.*(..))")
    public void administratorOperationsPointCut(){}

    @Pointcut("execution(* a.gleb.reactiverest.controllers.AuthorAuthorizationController.*(..))")
    public void authorOperationsPointCut(){}

    @Pointcut("execution(* a.gleb.reactiverest.controllers.UnauthorizedController.*(..))")
    public void nonAuthorityOperationPointCut(){}

    @Pointcut("execution(* a.gleb.reactiverest.controllers.AuthenticationController.*(..))")
    public void securityOperationPointCut(){}

    @Before("administratorOperationsPointCut()")
    public void beforeAdministratorOperationLogging(JoinPoint jP){
        MethodSignature mS = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        log.info("Administration controller operation: " + mS.getName() + ". " + "Following arguments: " + Arrays.toString(signArgs));
    }

    @Before("authorOperationsPointCut()")
    public void beforeAuthorOperationLogging(JoinPoint jP){
        MethodSignature mS = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        log.info("Author controller operation: " + mS.getName() + ". " + "Following arguments: " + Arrays.toString(signArgs));
    }

    @Before("nonAuthorityOperationPointCut()")
    public void beforeUnauthorizedOperationLogging(JoinPoint jP){
        MethodSignature mS = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        log.info("Unauthorized controller operation: " + mS.getName() + ". " + "Following arguments: " + Arrays.toString(signArgs));
    }

    @Before("securityOperationPointCut()")
    public void beforeSecurityOperationLogging(JoinPoint jP){
        MethodSignature mS = (MethodSignature) jP.getSignature();
        Object [] signArgs = jP.getArgs();
        log.info("Security controller operation: " + mS.getName() + ". " + "Following arguments: " + Arrays.toString(signArgs));
    }
}
