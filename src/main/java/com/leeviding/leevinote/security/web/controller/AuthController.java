package com.leeviding.leevinote.security.web.controller;

import com.leeviding.leevinote.infrastructure.web.response.Msg;
import com.leeviding.leevinote.security.util.JwtTokenUtil;
import com.leeviding.leevinote.security.web.response.UserAuthDto;
import com.leeviding.leevinote.user.application.UserUseCase;
import com.leeviding.leevinote.user.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserUseCase userUseCase;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<Msg<User>> register(@RequestBody User user) {
        User registeredUser = userUseCase.register(user);
        return new ResponseEntity<>(Msg.success(registeredUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userUseCase.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(Msg.success(UserAuthDto.buildUser(user.getId(), user.getUsername(), jwtTokenUtil.generateToken(user.getUsername()))));
    }
}