package com.reyzotech.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@CrossOrigin("*")
public class DoctorController {

    @GetMapping("/")
    public String helloDoctorController() {
        return "Doctor level access";
    }

}
