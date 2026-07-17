package com.karthik.IntelliTutor.controllers;

import com.karthik.IntelliTutor.records.CheckAnswer;
import com.karthik.IntelliTutor.records.QuestionResult;
import com.karthik.IntelliTutor.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rag/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public QuestionResult.RegisterRequest getRegister(@RequestBody QuestionResult.RegisterRequest req){
        return service.getRegister(req);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody CheckAnswer.LoginRequest req) {
       return ResponseEntity.status(HttpStatus.OK).body(service.getLogin(req));
    }

}
