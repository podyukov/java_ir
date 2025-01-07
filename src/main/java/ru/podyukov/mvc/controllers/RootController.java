package ru.podyukov.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {
    @GetMapping("/")
    public String index() {
        return "root/index";
    }
}
