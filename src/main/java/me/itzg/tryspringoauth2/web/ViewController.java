package me.itzg.tryspringoauth2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/app/**")
    public String appView() {
        return "/app.html";
    }
}
