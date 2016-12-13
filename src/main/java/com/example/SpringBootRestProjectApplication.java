package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootRestProjectApplication {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    		return "Hello "+name+"! This is Spring Boot Docker Example";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestProjectApplication.class, args);
    }

}