package com.example.kiemtra.controller;

import com.example.kiemtra.base.VsResponseUtil;
import com.example.kiemtra.dto.SecurityResponse;
import com.example.kiemtra.dto.UserDTO;
import com.example.kiemtra.service.Impl.MyUserDetailsService;
import com.example.kiemtra.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password");
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new SecurityResponse(userDTO.getUsername(), jwt));
    }
}
