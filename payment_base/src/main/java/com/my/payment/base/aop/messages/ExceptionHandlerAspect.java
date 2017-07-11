package com.my.payment.base.aop.messages;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.my.payment.base.aop.BaseAspect;
import com.my.payment.base.exception.BaseException;



public class ExceptionHandlerAspect extends BaseAspect implements MessageSourceAware {

    private final Logger  logger = Logger.getLogger(this.getClass());

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Object exceptionHandler(ProceedingJoinPoint pjp) throws Throwable {

        try {

            return pjp.proceed();

        } catch (BaseException e) {

            String msg = messageSource.getMessage(e.getErrCode(), e.getParams().toArray(),
                Locale.CHINESE);

            e.setMessage(msg);

            throw e;

        } catch (Exception e) {

            logger.error(e);

            throw e;
        }
    }

}
