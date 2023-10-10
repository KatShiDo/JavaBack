package com.mirea.JavaBack.Controllers.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mirea.JavaBack.Services.TelephoneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class TelephoneControllerAPI {

    private TelephoneService telephoneService;

    @GetMapping("/api/telephones")
    String getAll() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(telephoneService.getAll());
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/api/telephones/{id}")
    String getById(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(telephoneService.getById(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

//    @PostMapping("/api/telephones")
//    String create(@RequestBody Telephone telephone) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(telephoneService.create(telephone));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @PutMapping("/api/telephones")
//    String update(@RequestBody Telephone telephone) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(telephoneService.update(telephone));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @DeleteMapping("/api/telephones/{id}")
//    String delete(@PathVariable Long id) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(telephoneService.delete(id));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
}
