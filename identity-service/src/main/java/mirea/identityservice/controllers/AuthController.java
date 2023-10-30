package mirea.identityservice.controllers;

import jakarta.ws.rs.HeaderParam;
import lombok.RequiredArgsConstructor;
import mirea.identityservice.domain.dto.AuthRequest;
import mirea.identityservice.domain.dto.UserInfo;
import mirea.identityservice.domain.entities.UserCredential;
import mirea.identityservice.domain.enums.Role;
import mirea.identityservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

//    @GetMapping
//    public List<UserCredential> getAll() {
//        var actualList = new ArrayList<UserCredential>();
//        userCredentialRepository.findAll().iterator().forEachRemaining(actualList::add);
//        return actualList;
//    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody UserCredential user) {
        authService.save(user);
        return "user added to the system";
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()));
        return ResponseEntity.ok().body(authService.generateToken(authRequest.getUsername()));
    }

    @GetMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "validated";
    }

    @GetMapping("/get")
    public UserInfo getUser(@RequestParam String secret, @RequestHeader("Authorization") String token) {
        if (Objects.equals(secret, "123")) {
            var user = authService.findByToken(token);
            if (user == null) {
                return null;
            }
            else {
                return UserInfo
                        .builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole().toString())
                        .books(user.getBooks())
                        .build();
            }
        }
        else {
            return null;
        }
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody UserInfo userInfo, @RequestParam String secret, @HeaderParam("Authorization") String token) {
        if (Objects.equals(secret, "secret")) {
            var user = authService.findByUsername(userInfo.getUsername());
            user.setBooks(userInfo.getBooks());
            user.setRole(Role.valueOf(userInfo.getRole()));
            authService.save(user);
        }
    }
}
