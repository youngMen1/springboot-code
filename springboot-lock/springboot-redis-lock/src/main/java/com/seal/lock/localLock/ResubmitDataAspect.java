package com.seal.lock.localLock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seal.lock.config.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 15:04
 * @description 数据重复提交校验
 **/
@Slf4j
@Aspect
@Component
public class ResubmitDataAspect {

    private final static String DATA = "data";
    private final static Object PRESENT = new Object();

    @Around("@annotation(com.seal.lock.localLock.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";
        //获取第一个参数
        Object firstParam = pointArgs[0];
        // if (firstParam instanceof R) {
        //解析参数
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(firstParam));
        // JSONObject data = JSONObject.parseObject(requestDTO.getString(DATA));
        if (data != null) {
            StringBuffer sb = new StringBuffer();
            data.forEach((k, v) -> {
                sb.append(v);
            });
            //生成加密参数 使用了content_MD5的加密方式
            key = ResubmitLock.handleKey(sb.toString());
        }
        // }
        //执行锁
        boolean lock = false;
        try {
            //设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                return new R<>(9999, "异常", null);
            }
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
