package com.seal.apollo.apolloclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config方式
 * @author fengzhiqiang
 * @date 2020/7/11 15:01
 **/
@Configuration
public class JavaConfigBean {
    @Value("${timeout:20}")
    private int timeout;

    public int getTimeout() {
        return timeout;
    }
}
