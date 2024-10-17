package com.wipro.hma.as.controller;

import com.wipro.hma.as.dto.AdminDTO;
import com.wipro.hma.as.security.JwtUtil;
import com.wipro.hma.as.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AuthAdminController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

//    @Autowired
//    PartnerService partnerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AdminDTO partnerDTO) {
        try {
            authenticationManager.authenticate(             //internally uses loadUser and Bcrypt password method
                    new UsernamePasswordAuthenticationToken(partnerDTO.getAdminName(), partnerDTO.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(partnerDTO.getAdminName());
        final String jwt = jwtUtil.generateToken(userDetails);
        //return ResponseEntity.ok(new JwtResponse(jwt));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
