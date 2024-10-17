package com.wipro.hma.rs.controller;

import com.wipro.hma.rs.dto.RatingDTO;
import com.wipro.hma.rs.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        RatingDTO createdRating = ratingService.createRating(ratingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByPartnerId(@PathVariable String partnerId) {
        List<RatingDTO> ratings = ratingService.getRatingsByPartnerId(partnerId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByUserId(@PathVariable String userId) {
        List<RatingDTO> ratings = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratings);
    }
}
