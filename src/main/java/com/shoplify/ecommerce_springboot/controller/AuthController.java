package com.shoplify.ecommerce_springboot.controller;

import com.shoplify.ecommerce_springboot.DTO.APIResponse;
import com.shoplify.ecommerce_springboot.DTO.LoginForm;
import com.shoplify.ecommerce_springboot.DTO.RegisterForm;
import com.shoplify.ecommerce_springboot.entity.Product;
import com.shoplify.ecommerce_springboot.entity.User;
import com.shoplify.ecommerce_springboot.repository.UserRepository;
import com.shoplify.ecommerce_springboot.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<APIResponse<Map<String, Object>>> authenticateUser(@RequestBody LoginForm userForm) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userForm.email(),
                        userForm.password()
                )
        );

        Map<String, Object> data = new HashMap<>();

        data.put("token", jwtUtils.generateToken(userForm.email()));
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        APIResponse<Map<String, Object>> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Authenticated Successfully",
                data
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody @Valid RegisterForm userForm) {
        if (userRepository.existsByEmail(userForm.email())) {
            return "User already Exists";
        }

        System.out.println("TEST AUTHO REGISTER");

        final User newUser = new User(
                userForm.firstName(),
                userForm.middleName(),
                userForm.lastName(),
                userForm.email(),
                encoder.encode(userForm.password()),
                null,
                true
        );

        userRepository.save(newUser);
        return "User Registered Successfully";
    }
}
