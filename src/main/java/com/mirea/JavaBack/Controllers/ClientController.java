package com.mirea.JavaBack.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mirea.JavaBack.Domain.Entities.Client;
import com.mirea.JavaBack.Services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @GetMapping("/clients")
    String getAll() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(clientService.getAll());
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/clients/{id}")
    String getById(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(clientService.getById(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/clients")
    String create(@RequestBody Client client) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(clientService.create(client));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/clients")
    String update(@RequestBody Client client) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(clientService.update(client));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/clients/{id}")
    String delete(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(clientService.delete(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
