package mls_data_classification_engine.mls_engine.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mls_data_classification_engine.mls_engine.model.ClassificationLevel;
import mls_data_classification_engine.mls_engine.model.User;
import mls_data_classification_engine.mls_engine.repository.UserRepository;
import mls_data_classification_engine.mls_engine.security.JwtService;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User registUser(String userName, String rawPassword) {
        // hash pass
        String hashedPass = passwordEncoder.encode(rawPassword);
        // register user object
        User user = new User(null, ClassificationLevel.UNCLASSIFIED, userName, hashedPass);
        // return saved user
        return userRepository.save(user);
    }

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
}