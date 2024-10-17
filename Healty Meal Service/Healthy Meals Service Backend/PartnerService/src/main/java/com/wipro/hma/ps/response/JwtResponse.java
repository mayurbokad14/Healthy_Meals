package com.wipro.hma.ps.response;

import com.wipro.hma.ps.dto.PartnerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private PartnerDTO user;

}
