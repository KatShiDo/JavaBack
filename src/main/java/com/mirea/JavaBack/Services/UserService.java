package com.mirea.JavaBack.Services;

import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public BaseResponse<List<User>> getAll() {
        try {
            var clients = userRepository.findAll();
            if (clients.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", clients, StatusCode.Ok);
            }
            return new BaseResponse<>(null, clients, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> getById(Long id) {
        try {
            var client = userRepository.findById(id).orElse(null);
            if (client == null) {
                return new BaseResponse<>("Client not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, client, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> create(User user) {
        try {
            user = userRepository.save(user);
            return new BaseResponse<>(null, user, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<User> update(User user) {
        try {
            var clientFromDb = userRepository.findById(user.getId()).orElse(null);
            if (clientFromDb == null) {
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
}
