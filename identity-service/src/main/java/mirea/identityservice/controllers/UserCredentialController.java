package mirea.identityservice.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mirea.identityservice.domain.entities.UserCredential;
import mirea.identityservice.repositories.UserCredentialRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserCredentialController {

    private final UserCredentialRepository userCredentialRepository;

    @GetMapping
    public List<UserCredential> getAll() {
        var actualList = new ArrayList<UserCredential>();
        userCredentialRepository.findAll().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    @PostConstruct
    public void save() {
        var user = new UserCredential();
        user.setId(1);
        user.setName("123");
        user.setEmail("234");
        user.setPassword("1234");
        userCredentialRepository.save(user);
    }
}
