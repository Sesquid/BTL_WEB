package com.example.springboot.service;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.MyUserDetails;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.security.JwtTokenProvider;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserDetails(user);
    }

    public UserDetails loadUserById(long userid) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isEmpty()) {
            return null;
        }
        return new MyUserDetails(user.get());
    }

    public JsonObject login(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            if(user == null) {
                return BaseResponse.createFullMessageResponse(10, "username_not_exist");
            }
            else {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if(passwordEncoder.matches(password, user.getPassword()) == false) {
                    return BaseResponse.createFullMessageResponse(11, "invalid_password");
                }
                MyUserDetails userDetails = new MyUserDetails(user);
                String token = jwtTokenProvider.generateToken(userDetails);
                JsonObject res = new JsonObject();
                res.addProperty("token", token);
                res.addProperty("role", userDetails.getAuthorities().toString());
                return BaseResponse.createFullMessageResponse(0, "success", res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject register(String username, String password) {
        User a = userRepository.findByUsername(username);
        if(a != null) {
            return BaseResponse.createFullMessageResponse(10, "user_exist");
        }
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(password);
            User user = new User(null, username, encodePassword, 0, System.currentTimeMillis());
            userRepository.save(user);
            return login(username, password);
        }
    }
}