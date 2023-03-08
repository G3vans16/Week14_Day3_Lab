package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

    //FIND BY YEAR:
    List<Whisky> findByYear(int year);

    //FIND WHISKIES BY DISTILLERY AND YEAR:
    List<Whisky> findWhiskiesByDistilleryIdAndAge(Long id, Integer age);

    List<Whisky> findByDistillery(Distillery distillery);
}
