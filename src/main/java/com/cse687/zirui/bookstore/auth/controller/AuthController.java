package com.cse687.zirui.bookstore.auth.controller;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.messaging.AuthProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthProducer producer;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogIn cmd) {
        try {
            producer.publishLogInCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogOut cmd) {
        try {
            producer.publishLogOutCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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

    @PutMapping("/update_member_account")
    public ResponseEntity<?> updateMemberAccount(@RequestBody UpdateMemberAccount cmd) {
        try {
            producer.publishUpdateMemberAccountCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_account")
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccount cmd) {
        try {
            producer.publishDeleteAccountCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
