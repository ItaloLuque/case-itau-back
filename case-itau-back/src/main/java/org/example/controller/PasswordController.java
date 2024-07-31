package org.example.controller;

import org.example.model.PasswordRequest;
import org.example.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    private PasswordValidator passwordValidator;

    @PostMapping("/validate-password")
    public ResponseEntity<Boolean> validatePassword(@RequestBody PasswordRequest passwordRequest) {
        String password = passwordRequest.getPassword();
        System.out.println("Received password: " + password);
        return ResponseEntity.ok(passwordValidator.isValid(password));
    }

}
