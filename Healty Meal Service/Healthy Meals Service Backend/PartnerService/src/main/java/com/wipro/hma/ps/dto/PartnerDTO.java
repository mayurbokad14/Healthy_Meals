package com.wipro.hma.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
    private Long partnerId;
    private String partnerName;
    private String shopName;
    private String partnerEmail;
    private String phoneNumber;
    private String password;  // Should be encrypted
    private String role;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String shopDescription; // Description of the shop (optional)
    private String logoUrl;         // URL for the shop logo (optional)
    private List<Long> mealIds;     // List of meals offered by the partner
}
