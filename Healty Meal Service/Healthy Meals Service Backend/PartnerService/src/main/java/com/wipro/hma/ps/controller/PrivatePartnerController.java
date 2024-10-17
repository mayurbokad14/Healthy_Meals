package com.wipro.hma.ps.controller;

import com.wipro.hma.ps.dto.PartnerDTO;
import com.wipro.hma.ps.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/partners")
public class PrivatePartnerController {

    @Autowired
    private PartnerService partnerService;


    @PutMapping("/{partnerId}")
    public ResponseEntity<PartnerDTO> updatePartner(@PathVariable Long partnerId, @RequestBody PartnerDTO partnerDTO) {
        PartnerDTO updatedPartner = partnerService.updatePartner(partnerId, partnerDTO);
        return new ResponseEntity<>(updatedPartner, HttpStatus.OK);
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<PartnerDTO> getPartnerById(@PathVariable Long partnerId) {
        PartnerDTO partnerDTO = partnerService.getPartnerById(partnerId);
        return new ResponseEntity<>(partnerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{partnerId}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long partnerId) {
        partnerService.deletePartner(partnerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        List<PartnerDTO> partners = partnerService.getAllPartners();
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }
}
