package com.mirea.JavaBack.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mirea.JavaBack.Domain.Entities.WashingMachine;
import com.mirea.JavaBack.Services.WashingMachineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class WashingMachineController {

    private WashingMachineService washingMachineService;

    @GetMapping("/washing_machines")
    String getAll() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(washingMachineService.getAll());
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/washing_machines/{id}")
    String getById(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(washingMachineService.getById(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/washing_machines")
    String create(@RequestBody WashingMachine washingMachine) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(washingMachineService.create(washingMachine));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/washing_machines")
    String update(@RequestBody WashingMachine washingMachine) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(washingMachineService.update(washingMachine));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/washing_machines/{id}")
    String delete(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(washingMachineService.delete(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}

