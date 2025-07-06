package co.com.andres.university_campus_management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.AuthenticateRequest;
import co.com.andres.university_campus_management.model.DTO.AuthenticateResponse;
import jakarta.validation.Valid;

@RestController
public class AuthenticateController {
    

    @PostMapping
    public AuthenticateResponse authenticate( @Valid @RequestBody AuthenticateRequest authenticateRequest ){

        return null;
    }

    ;

}
