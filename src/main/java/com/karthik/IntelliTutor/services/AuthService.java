package com.karthik.IntelliTutor.services;

import com.karthik.IntelliTutor.entities.AuthEntity;
import com.karthik.IntelliTutor.records.CheckAnswer;
import com.karthik.IntelliTutor.records.QuestionResult;
import com.karthik.IntelliTutor.repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public QuestionResult.RegisterRequest getRegister(QuestionResult.RegisterRequest req){

        AuthEntity auth=toEntity(req);
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authRepo.save(auth);
        return toDto(auth);

    }

    public Authentication auth(CheckAnswer.LoginRequest req) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
    }

    public Map<String, String> getLogin(CheckAnswer.LoginRequest req) {
        Authentication authentication = auth(req);
        if (authentication == null) {
            throw new RuntimeException("User is Not Registered!");
        }
        Map<String,String> loginDetails=new HashMap<>();
        loginDetails.put("email",authentication.getName());
        loginDetails.put("message","Logged in Successfully!");
        loginDetails.put("token",jwtService.generateToken(authentication.getName()));
        return loginDetails;
    }

    private QuestionResult.RegisterRequest toDto(AuthEntity auth) {
        return QuestionResult.RegisterRequest.builder()
                .userId(auth.getUserId())
                .userName(auth.getUserName())
                .email(auth.getEmail())
                .createdAt(auth.getCreatedAt())
                .build();
    }

    private AuthEntity toEntity(QuestionResult.RegisterRequest req) {
        return  AuthEntity.builder().
                userId(req.getUserId())
                .userName(req.getUserName())
                .email(req.getEmail())
                .password(req.getPassword())
                .createdAt(req.getCreatedAt())
                .build();
    }
}
