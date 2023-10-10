package com.mirea.JavaBack.Controllers.API;

import com.mirea.JavaBack.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class UserControllerAPI {

    private UserService userService;

//    @GetMapping("/api/users")
//    String getAll() {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(userService.getAll());
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @GetMapping("/api/users/{id}")
//    String getById(@PathVariable Long id) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(userService.getById(id));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @PostMapping("/api/users")
//    String create(@RequestBody User user) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(userService.create(user));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @PutMapping("/api/users")
//    String update(@RequestBody User user) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(userService.update(user));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @DeleteMapping("/api/users/{id}")
//    String delete(@PathVariable Long id) {
//        try {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            return ow.writeValueAsString(userService.delete(id));
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//    }
}
