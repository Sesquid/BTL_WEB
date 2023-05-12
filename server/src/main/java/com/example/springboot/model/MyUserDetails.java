package com.example.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(user.getRole() == 0)
//            return Collections.singleton(new SimpleGrantedAuthority("USER"));
//        else
//            return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        return Collections.singleton(new SimpleGrantedAuthority(("USER")));
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}