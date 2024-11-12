package com.cse687.zirui.bookstore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sell")
    public String sellPage() {
        return "sellPage";
    }

    @GetMapping("/customer")
    public String customerPage() {
        return "customerPage";
    }
}
