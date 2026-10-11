package com.agrismart.agrismart.controller;
import org.springframework.web.bind.annotation.*;
@RestController public class TestController {
 @GetMapping("/api/test") public String test(){return "AgriSmart Backend is running!";}
}
