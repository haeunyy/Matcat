package com.greedy.matcat.aspect;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class logAspect {

    private static final Logger logger = LoggerFactory.getLogger(logAspect.class);

    @Around("within(com.greedy.matcat..*)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        String params = getRequestParams();

        long startAt = System.currentTimeMillis();

		/*
		 * logger.info(" REQUEST : {}({}) = {}",
		 * pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(),
		 * params);
		 */

        Object result = pjp.proceed();

        long endAt = System.currentTimeMillis();

		/*
		 * logger.info(" RESPONSE : {}({}) = {} ({}ms)",
		 * pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(),
		 * result, endAt-startAt);
		 */

        return result;

    }

    // get requset value
    private String getRequestParams() {

        String params = "";

        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();

        if(requestAttribute != null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            Map<String, String[]> paramMap = request.getParameterMap();

            if(!paramMap.isEmpty()) {
                params = " [" + paramMapToString(paramMap) + "]";
            }
        }
        return params;
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.toString();
    }
}