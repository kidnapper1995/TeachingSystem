package com.example.Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class StudentController {
    @RequestMapping("/check")
    public String check(){
        return "";
    }
}
