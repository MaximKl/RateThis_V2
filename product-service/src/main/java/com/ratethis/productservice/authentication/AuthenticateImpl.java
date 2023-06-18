package com.ratethis.productservice.authentication;

import com.ratethis.productservice.constants.Constants;
import com.ratethis.productservice.exception.IdentificationException;
import com.ratethis.productservice.model.UserRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticateImpl {

    @Before("@annotation(Authenticate)")
    public void logExecutionTime(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        UserRole currentRole = Constants.userRoleIdentification((String)joinPoint.getArgs()[joinPoint.getArgs().length - 1]);
        if(currentRole==UserRole.ADMIN) {
            return;
        }
        if (Constants.userRoleIdentification((String) joinPoint.getArgs()[joinPoint.getArgs().length - 1]) != signature.getMethod().getAnnotation(Authenticate.class).role())
            throw new IdentificationException();
    }

}