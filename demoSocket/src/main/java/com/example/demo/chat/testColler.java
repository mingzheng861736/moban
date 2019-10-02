package com.example.demo.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testColler {
    @RequestMapping("/")
    public String aa(){
        return "test01";
    }
}
