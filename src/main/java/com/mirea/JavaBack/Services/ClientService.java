package com.mirea.JavaBack.Services;

import com.mirea.JavaBack.Domain.Entities.Client;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public BaseResponse<List<Client>> getAll() {
        try {
            var clients = clientRepository.findAll();
            if (clients.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", clients, StatusCode.Ok);
            }
            return new BaseResponse<>(null, clients, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Client> getById(Long id) {
        try {
            var client = clientRepository.findById(id).orElse(null);
            if (client == null) {
                return new BaseResponse<>("Client not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, client, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Client> create(Client client) {
        try {
            client = clientRepository.save(client);
            return new BaseResponse<>(null, client, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Client> update(Client client) {
        try {
            var clientFromDb = clientRepository.findById(client.getId()).orElse(null);
            if (clientFromDb == null) {
                return new BaseResponse<>("Client not found", null, StatusCode.NotFound);
            }
            client = clientRepository.save(client);
            return new BaseResponse<>(null, client, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Boolean> delete(Long id) {
        try {
            clientRepository.deleteById(id);
            return new BaseResponse<>(null, true, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), false, StatusCode.InternalServerError);
        }
    }
}
