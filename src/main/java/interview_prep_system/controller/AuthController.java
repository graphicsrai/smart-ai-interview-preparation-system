package interview_prep_system.controller;

import interview_prep_system.dto.RegisterRequest;
import interview_prep_system.entity.User;
import interview_prep_system.service.UserService;
import org.springframework.web.bind.annotation.*;
import interview_prep_system.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import interview_prep_system.security.JwtService;
import interview_prep_system.dto.LoginResponse;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    public AuthController(
            UserService userService,
            JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {

            userService.login(request);

            String token =
                    jwtService.generateToken(
                            request.getEmail());

            return ResponseEntity.ok(
                    new LoginResponse(token));

        } catch (RuntimeException ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }
}