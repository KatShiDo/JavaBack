package mirea.identityservice.services;

import lombok.RequiredArgsConstructor;
import mirea.identityservice.domain.entities.UserCredential;
import mirea.identityservice.repositories.UserCredentialRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void save(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
    }

    public UserCredential findByUsername(String username) {
        return repository.findById(username).orElse(null);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public UserCredential findByToken(String token) {
        var username = jwtService.extractUsername(token);
        return repository.findById(username).orElse(null);
    }
}
