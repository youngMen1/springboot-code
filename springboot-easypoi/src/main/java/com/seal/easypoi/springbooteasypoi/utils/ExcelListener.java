package com.seal.easypoi.springbooteasypoi.utils;

import cn.afterturn.easypoi.cache.manager.POICacheManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ExcelListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        POICacheManager.setFileLoader(new FileLoaderImpl());
    }
}