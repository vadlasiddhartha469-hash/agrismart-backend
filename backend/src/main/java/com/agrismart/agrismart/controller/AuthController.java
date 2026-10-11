package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.LoginRequest;
import com.agrismart.agrismart.entity.User;
import com.agrismart.agrismart.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/users") @CrossOrigin(origins="*")
public class AuthController {
    private final UserService users; private final PasswordEncoder encoder; private final JwtService jwt;
    public AuthController(UserService users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
    @PostMapping("/register") public Map<String,Object> register(@RequestBody User u){
        User saved=users.register(u); return Map.of("message","Registration successful","userId",saved.getId(),"name",saved.getName(),"email",saved.getEmail());
    }
    @PostMapping("/login") public Map<String,Object> login(@RequestBody LoginRequest r){
        User u=users.byEmail(r.getEmail());
        if(!encoder.matches(r.getPassword(),u.getPassword())) throw new RuntimeException("Invalid email or password");
        Map<String,Object> m=new HashMap<>(); m.put("message","Login successful");m.put("token",jwt.generateToken(u.getEmail()));m.put("userId",u.getId());m.put("name",u.getName());m.put("email",u.getEmail());return m;
    }
}
