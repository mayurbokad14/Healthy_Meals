package com.wipro.hma.rs.repository;

import com.wipro.hma.rs.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPartnerId(String partnerId);  // Find all ratings for a specific partner
    List<Rating> findByUserId(String userId);        // Find all ratings given by a specific user
}
