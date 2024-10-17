package com.wipro.hma.rs.service;

import com.wipro.hma.rs.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(RatingDTO ratingDTO);      // Add a rating
    List<RatingDTO> getRatingsByPartnerId(String partnerId);  // Fetch all ratings for a partner
    List<RatingDTO> getRatingsByUserId(String userId);
}
