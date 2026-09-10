package ch.szclsb.test.jpa.api.simple;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {
    public Logger log = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Around("@annotation(ch.szclsb.test.jpa.api.simple.ExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        var startTime = System.currentTimeMillis();
        var result = joinPoint.proceed();
        var endTime = System.currentTimeMillis();

        log.debug("execution time of {}.{} was {}ms", className, methodName, endTime - startTime);

        return result;
    }
}
