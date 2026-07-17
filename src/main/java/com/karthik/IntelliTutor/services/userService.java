package com.karthik.IntelliTutor.services;

import com.karthik.IntelliTutor.entities.AuthEntity;
import com.karthik.IntelliTutor.repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class userService implements UserDetailsService {

    private final AuthRepo authRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthEntity user=authRepo.findByEmail(username).orElseThrow(
                ()-> new RuntimeException("User not Found")
        );

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }
}
