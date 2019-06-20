package com.hftang.ssm.controller;

import com.hftang.ssm.domain.SysLog;
import com.hftang.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author hftang
 * @date 2019-06-19 10:08
 * @desc
 */

@Component
@Aspect
public class LogAop {

    private Date visitTime;//访问时间
    private Class clazz;//访问类型
    private Method method;//访问方法

    //request
    @Autowired
    HttpServletRequest request;
    @Autowired
    private ISysLogService iSysLogService;


    //前置通知
    @Before("execution(* com.hftang.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {

        visitTime = new Date();
        //访问的是那个类
        clazz = joinPoint.getTarget().getClass(); //访问的具体是那个类
        String methodName = joinPoint.getSignature().getName();//获取到方法的名称
        //获取到方法的参数
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            //只能获取到无参的方法
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }


    }

    //后置通知
    @After("execution(* com.hftang.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {

        long time = new Date().getTime() - visitTime.getTime();//获取了访问的时长

        String url = "";

        if (clazz != null && method != null & clazz != LogAop.class) {
            //1 获取到类上的注解 @RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2 获取@RequestMapping上的方法
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        //如何访问访问的ip
        String ip = request.getRemoteAddr();

        //获取当前所用用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        String username = user.getUsername();

        SysLog sysLog = new SysLog();

        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //然后把日志保存到数据库

        iSysLogService.save(sysLog);
    }
}
