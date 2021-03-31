package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(value = "/whiskies")
//    public ResponseEntity <List<Whisky>> findWhiskiesByYear(
//            @RequestParam (name = "year") int year
//    ) {
//        return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
//    }


    @GetMapping(value = "/whiskies")
    public ResponseEntity findWhiskies(
            @RequestParam (name = "distilleryName", required = false) String distilleryName,
            @RequestParam (name = "age", required = false) Integer age,
            @RequestParam (name = "year", required = false) Integer year,
            @RequestParam (name = "region", required = false) String region) {
        if (distilleryName != null && age != null) {
            List<Whisky> foundWhisky = whiskyRepository.findWhiskiesByDistilleryNameAndAge(distilleryName, age);
            return new ResponseEntity(foundWhisky, HttpStatus.OK);
        }
        if (year != null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if(region != null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<List<Whisky>>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}







