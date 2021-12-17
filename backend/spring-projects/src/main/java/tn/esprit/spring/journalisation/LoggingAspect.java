package tn.esprit.spring.journalisation;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoggingAspect {
    private static final Logger l = (Logger) LogManager.getLogger(LoggingAspect.class);
    @Before("execution(* tn.esprit.spring.service.FactureService.*(..))")
            public void logMethodEntry(JoinPoint joinPoint){
                String name=joinPoint.getSignature().getName();
                l.info("in method"+ name +":");
    }

    @After("execution(* tn.esprit.spring.service.FactureService.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name =joinPoint.getSignature().getName();
        l.info("out of "+ name + ":");
    }
}
//    @Around("execution(* com.example.demo.service.StockService.*.*(..))")
//     public Object profile(ProceedingJoinPoint pjp) throws Throwable{
//        long start = System.currentTimeMillis();
//        Object obj = pjp.proceed();
//        long elapsedTime = System.currentTimeMillis() - start;
//        l.info("Method execution time: " + elapsedTime + " milliseconds.");
//        return obj;
//    }

