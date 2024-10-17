package com.wipro.hma.as.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private String adminName = "admin";

    private String adminEmail = "admin@gmail.com";

    private String password = "admin@123";
}
