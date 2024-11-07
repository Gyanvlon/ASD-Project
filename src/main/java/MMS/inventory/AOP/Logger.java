package MMS.inventory.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;

@Aspect
@Slf4j
@Component
public class Logger {
    @Around("execution(* MMS.inventory.services.ManagerServiceImpl.*(..))")
    public Object aroundManager(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MMS.inventory.services.ManagerServiceImpl.*(..))",throwing = "ex")
    public void logManagerException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MMS.inventory.services.PatientServiceImpl.*(..))")
    public Object aroundPatient(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MMS.inventory.services.PatientServiceImpl.*(..))",throwing = "ex")
    public void logPatientException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MMS.inventory.services.DrugServiceImpl.*(..))")
    public Object aroundDrug(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MMS.inventory.services.DrugServiceImpl.*(..))",throwing = "ex")
    public void logDrugException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MMS.inventory.services.DoctorServiceImpl.*(..))")
    public Object aroundDoctor(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MMS.inventory.services.DoctorServiceImpl.*(..))",throwing = "ex")
    public void logDoctorException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MMS.inventory.services.PharmacyServiceImpl.*(..))")
    public Object aroundPharmacist(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MMS.inventory.services.PharmacyServiceImpl.*(..))",throwing = "ex")
    public void logPharmacistException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    private Object logAround(ProceedingJoinPoint joinpoint) throws Throwable {
        log.info(joinpoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        Object returnObj = joinpoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute " + joinpoint.getSignature().toString() + " method is : "+timeElapsed);
        log.info(joinpoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

}
