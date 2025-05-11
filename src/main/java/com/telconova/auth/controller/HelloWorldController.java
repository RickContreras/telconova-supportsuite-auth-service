package com.telconova.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String home() {
        return "Bienvenido a TelcoNova Support Backend!";
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola Mundo!";
    }
}