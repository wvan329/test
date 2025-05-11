package com.wgk.controller;

import com.wgk.event.MyCustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/a")
    public String a() {
        return "永远相信，美好的事情即将发生！谢谢大家.heihei";
    }

    @GetMapping("/publish")
    public String publish() {
        log.error("error xixi");
        MyCustomEvent event = new MyCustomEvent(this, "Hello from custom event!");
        publisher.publishEvent(event);
        return "xixi";
    }
}
