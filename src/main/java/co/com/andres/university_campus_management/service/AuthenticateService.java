package co.com.andres.university_campus_management.service;

import co.com.andres.university_campus_management.model.DTO.AuthenticateRequest;
import co.com.andres.university_campus_management.model.DTO.AuthenticateResponse;

public interface AuthenticateService {
    
    AuthenticateResponse loggin(AuthenticateRequest authenticateRequest);
}
