package com.wipro.hma.ps.controller;

import com.wipro.hma.ps.dto.PartnerDTO;
import com.wipro.hma.ps.response.JwtResponse;
import com.wipro.hma.ps.security.JwtUtil;
import com.wipro.hma.ps.service.PartnerService;
import com.wipro.hma.ps.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/partners")
@CrossOrigin("http://localhost:4200")
public class AuthPartnerController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PartnerService partnerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<PartnerDTO> createUser(@RequestBody PartnerDTO partnerDTO) {
        PartnerDTO createdUser = partnerService.registerPartner(partnerDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody PartnerDTO partnerDTO) {
        try {
            authenticationManager.authenticate(             //internally uses loadUser and Bcrypt password method
                    new UsernamePasswordAuthenticationToken(partnerDTO.getPartnerName(), partnerDTO.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(partnerDTO.getPartnerName());
        final String jwt = jwtUtil.generateToken(userDetails);
        PartnerDTO user = partnerService.getUserByUsername(partnerDTO.getPartnerName());  // Assuming this method returns UserDTO object

        // Create JwtResponse object containing both the token and the user details
        JwtResponse jwtResponse = new JwtResponse(jwt, user);

        // Return the JwtResponse object in the response
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
