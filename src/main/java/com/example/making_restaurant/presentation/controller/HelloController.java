package com.example.making_restaurant.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) { 
        model.addAttribute("message","Hello");
        return "hello";
    }
    @GetMapping("bye")
    public String bye() {
        return "bye";
    }
}
