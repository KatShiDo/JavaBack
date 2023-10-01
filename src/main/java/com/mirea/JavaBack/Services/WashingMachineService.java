package com.mirea.JavaBack.Services;

import com.mirea.JavaBack.Domain.Entities.WashingMachine;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.WashingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WashingMachineService {

    private final WashingMachineRepository washingMachineRepository;

    public BaseResponse<List<WashingMachine>> getAll() {
        try {
            var washingMachines = washingMachineRepository.findAll();
            if (washingMachines.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", washingMachines, StatusCode.Ok);
            }
            return new BaseResponse<>(null, washingMachines, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<WashingMachine> getById(Long id) {
        try {
            var washingMachine = washingMachineRepository.findById(id).orElse(null);
            if (washingMachine == null) {
                return new BaseResponse<>("WashingMachine not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, washingMachine, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<WashingMachine> create(WashingMachine washingMachine) {
        try {
            washingMachine = washingMachineRepository.save(washingMachine);
            return new BaseResponse<>(null, washingMachine, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<WashingMachine> update(WashingMachine washingMachine) {
        try {
            var washingMachineFromDb = washingMachineRepository.findById(washingMachine.getId()).orElse(null);
            if (washingMachineFromDb == null) {
                return new BaseResponse<>("WashingMachine not found", null, StatusCode.NotFound);
            }
            washingMachine = washingMachineRepository.save(washingMachine);
            return new BaseResponse<>(null, washingMachine, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Boolean> delete(Long id) {
        try {
            washingMachineRepository.deleteById(id);
            return new BaseResponse<>(null, true, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), false, StatusCode.InternalServerError);
        }
    }
}
