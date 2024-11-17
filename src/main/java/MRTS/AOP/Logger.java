package MRTS.AOP;

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

    @Around("execution(* MRTS.services.Impl.UserServiceImpl.*(..))")
    public Object aroundUser(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "* MRTS.services.Impl.UserServiceImpl.*(..))",throwing = "ex")
    public void logUserException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }
    @Around("execution(* MRTS.services.Impl.ManagerServiceImpl.*(..))")
    public Object aroundManager(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MRTS.services.Impl.ManagerServiceImpl.*(..))",throwing = "ex")
    public void logManagerException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MRTS.services.Impl.PatientServiceImpl.*(..))")
    public Object aroundPatient(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MRTS.services.Impl.PatientServiceImpl.*(..))",throwing = "ex")
    public void logPatientException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MRTS.services.Impl.DrugServiceImpl.*(..))")
    public Object aroundDrug(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MRTS.services.Impl.DrugServiceImpl.*(..))",throwing = "ex")
    public void logDrugException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MRTS.services.Impl.DoctorServiceImpl.*(..))")
    public Object aroundDoctor(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MRTS.services.Impl.DoctorServiceImpl.*(..))",throwing = "ex")
    public void logDoctorException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    @Around("execution(* MRTS.services.Impl.PharmacyServiceImpl.*(..))")
    public Object aroundPharmacist(ProceedingJoinPoint joinpoint) throws Throwable {
        return logAround(joinpoint);
    }
    @AfterThrowing(value = "execution(* MRTS.services.Impl.PharmacyServiceImpl.*(..))",throwing = "ex")
    public void logPharmacistException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

    private Object logAround(ProceedingJoinPoint joinpoint) throws Throwable {
        log.info(joinpoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        Object returnObj = joinpoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute " + joinpoint.getSignature().toString() + " method is : "+timeElapsed + "ms");
        log.info(joinpoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

}
