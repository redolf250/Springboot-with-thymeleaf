package com.redolf.thymeleaf.controller;

import com.redolf.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forms")
public class FormsController {

    @GetMapping("/register")
    private String register(){
        return "forms/register";
    }
}
