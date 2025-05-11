package com.wgk.listener;

import com.wgk.event.MyCustomEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyCustomEventListener {

    @Async
    @EventListener
    public void onApplicationEvent(MyCustomEvent event) {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        System.out.println(event.getMessage());
    }
}
