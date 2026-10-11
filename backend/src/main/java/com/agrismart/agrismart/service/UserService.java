package com.agrismart.agrismart.service;
import com.agrismart.agrismart.entity.User;
import com.agrismart.agrismart.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo; private final PasswordEncoder encoder;
    public UserService(UserRepository repo, PasswordEncoder encoder){this.repo=repo;this.encoder=encoder;}
    public User register(User u){
        if(repo.findByEmail(u.getEmail()).isPresent()) throw new RuntimeException("Email already registered");
        u.setPassword(encoder.encode(u.getPassword())); return repo.save(u);
    }
    public User byEmail(String email){return repo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));}
}
