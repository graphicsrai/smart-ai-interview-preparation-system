package interview_prep_system.service;

import interview_prep_system.dto.RegisterRequest;
import interview_prep_system.entity.User;
import interview_prep_system.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import interview_prep_system.dto.LoginRequest;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        return "Login successful";
    }
}