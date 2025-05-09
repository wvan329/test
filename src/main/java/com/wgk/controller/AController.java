package com.wgk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {

    @GetMapping("/a")
    public String a() {
        return "永远相信，美好的事情即将发生";
    }
}
