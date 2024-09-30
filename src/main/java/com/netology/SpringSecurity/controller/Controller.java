package com.netology.SpringSecurity.controller;


import com.netology.SpringSecurity.entity.Persons;
import com.netology.SpringSecurity.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private final PersonsService personsService;

    public Controller(PersonsService personsService) {

        this.personsService = personsService;
    }


    @GetMapping("/persons/by-city_of_living")
    public List<Persons> getPersons(@RequestParam("city_of_living") String cityOfLiving) {

        return personsService.getByCityOfLiving(cityOfLiving);
    }

    @GetMapping("/persons/by-age")
    public List<Persons> getPersons(@RequestParam("age") int age) {

        return personsService.getByAgeLessThan(age);
    }

    @GetMapping(value = "/persons/by")
    public Optional<Persons> getPersons(@RequestParam("name") String name, @RequestParam("surname") String surname) {

        return personsService.getByNameAndSurname(name, surname);
    }


}
