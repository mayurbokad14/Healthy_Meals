package com.wipro.hma.rs.service;

import com.wipro.hma.rs.dto.RatingDTO;
import com.wipro.hma.rs.entity.Rating;
import com.wipro.hma.rs.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setUserId(ratingDTO.getUserId());
        rating.setPartnerId(ratingDTO.getPartnerId());
        rating.setRating(ratingDTO.getRating());
        rating.setFeedback(ratingDTO.getFeedback());

        Rating savedRating = ratingRepository.save(rating);
        return convertToDTO(savedRating);
    }

    @Override
    public List<RatingDTO> getRatingsByPartnerId(String partnerId) {
        List<Rating> ratings = ratingRepository.findByPartnerId(partnerId);
        return ratings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RatingDTO> getRatingsByUserId(String userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RatingDTO convertToDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setRatingId(rating.getRatingId());
        dto.setUserId(rating.getUserId());
        dto.setPartnerId(rating.getPartnerId());
        dto.setRating(rating.getRating());
        dto.setFeedback(rating.getFeedback());
        dto.setCreatedAt(rating.getCreatedAt());
        return dto;
    }
}
