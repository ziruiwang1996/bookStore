package com.cse687.zirui.bookstore.auth.controller;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.messaging.AuthProducer;
import com.cse687.zirui.bookstore.auth.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authServ;
    private final AuthProducer producer;

    @Autowired
    public AuthController(AuthService authServ, AuthProducer producer) {
        this.authServ = authServ;
        this.producer = producer;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogIn cmd) {
        try {
            String token = authServ.logIn(cmd);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = JwtService.extractUserId(token);
            authServ.logOut(new LogOut(userId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAccount cmd) {
        try {
            producer.publishRegisterAccountCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = JwtService.extractUserId(token);
            producer.publishDeleteAccountCmd(new DeleteAccount(userId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
