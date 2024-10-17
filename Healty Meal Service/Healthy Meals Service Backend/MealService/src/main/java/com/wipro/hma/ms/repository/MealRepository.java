package com.wipro.hma.ms.repository;

import com.wipro.hma.ms.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByPartnerId(Long partnerId);
}
