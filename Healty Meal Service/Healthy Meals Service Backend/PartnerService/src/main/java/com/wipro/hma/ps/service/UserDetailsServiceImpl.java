package com.wipro.hma.ps.service;

import com.wipro.hma.ps.entity.Partner;
import com.wipro.hma.ps.repository.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PartnerRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner user = userRepo.findByPartnerName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Partner not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPartnerName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
