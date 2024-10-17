package com.wipro.hma.as.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private Long adminId;

    private String adminName;

    private String adminEmail;

    private String password;

}
