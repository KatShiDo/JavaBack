package com.mirea.JavaBack.Services;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.Role;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BaseResponse<List<User>> getAll() {
        try {
            var users = userRepository.findAll();
            if (users.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", users, StatusCode.Ok);
            }
            return new BaseResponse<>(null, users, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> getById(Long id) {
        try {
            var user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return new BaseResponse<>("Client not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, user, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> getByPrincipal(Principal principal) {
        try {
            if (principal == null) {
                return new BaseResponse<>("Principal is null", null, StatusCode.NotFound);
            }

            var user = userRepository.findByLogin(principal.getName());
            return new BaseResponse<>(null, user, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> create(User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                return new BaseResponse<>("User with this email already exists", null, StatusCode.AlreadyExists);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (userRepository.findAll().isEmpty()) {
                user.getRoles().add(Role.ROLE_ADMIN);
            }
            else {
                user.getRoles().add(Role.ROLE_USER);
            }
            user = userRepository.save(user);
            return new BaseResponse<>(null, user, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> update(User user) {
        try {
            var userFromDb = userRepository.findById(user.getId()).orElse(null);
            if (userFromDb == null) {
                return new BaseResponse<>("Client not found", null, StatusCode.NotFound);
            }
            user = userRepository.save(user);
            return new BaseResponse<>(null, user, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Boolean> delete(Long id) {
        try {
            userRepository.deleteById(id);
            return new BaseResponse<>(null, true, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), false, StatusCode.InternalServerError);
        }
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
