package com.mirea.JavaBack.Services;

import com.mirea.JavaBack.Domain.Entities.Telephone;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.TelephoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;

    public BaseResponse<List<Telephone>> getAll() {
        try {
            var telephones = telephoneRepository.findAll();
            if (telephones.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", telephones, StatusCode.Ok);
            }
            return new BaseResponse<>(null, telephones, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Telephone> getById(Long id) {
        try {
            var telephone = telephoneRepository.findById(id).orElse(null);
            if (telephone == null) {
                return new BaseResponse<>("Telephone not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, telephone, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Telephone> create(Telephone telephone) {
        try {
            telephone = telephoneRepository.save(telephone);
            return new BaseResponse<>(null, telephone, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Telephone> update(Telephone telephone) {
        try {
            var telephoneFromDb = telephoneRepository.findById(telephone.getId()).orElse(null);
            if (telephoneFromDb == null) {
                return new BaseResponse<>("Telephone not found", null, StatusCode.NotFound);
            }
            telephone = telephoneRepository.save(telephone);
            return new BaseResponse<>(null, telephone, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Boolean> delete(Long id) {
        try {
            telephoneRepository.deleteById(id);
            return new BaseResponse<>(null, true, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), false, StatusCode.InternalServerError);
        }
    }
}
