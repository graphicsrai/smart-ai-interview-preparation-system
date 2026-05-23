package interview_prep_system.controller;

import interview_prep_system.dto.RegisterRequest;
import interview_prep_system.entity.User;
import interview_prep_system.service.UserService;
import org.springframework.web.bind.annotation.*;
import interview_prep_system.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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
            String response = userService.login(request);

            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }
}