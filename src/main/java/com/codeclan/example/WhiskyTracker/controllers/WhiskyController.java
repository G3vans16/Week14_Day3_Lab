package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    DistilleryRepository distilleryRepository;


    //GET ALL WHISKEY BY YEAR:
    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies (
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="distillery", required = false) Long distillery_id,
            @RequestParam(name="age", required = false) Integer age
            ) {

        if (distillery_id != null && age != null) {

            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryIdAndAge(distillery_id, age), HttpStatus.OK);

        }

        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear((int)year), HttpStatus.OK);
        }
        if (distillery_id != null) {
            Distillery distillery = distilleryRepository.getById(distillery_id);
            return new ResponseEntity<>(whiskyRepository.findByDistillery(distillery), HttpStatus.OK);
        }

        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
