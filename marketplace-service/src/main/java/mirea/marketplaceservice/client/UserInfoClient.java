package mirea.marketplaceservice.client;

import jakarta.ws.rs.HeaderParam;
import mirea.marketplaceservice.domain.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userInfo", url = "${application.config.userInfo}")
public interface UserInfoClient {

    @GetMapping("/get")
    UserInfo getUser(@RequestParam String secret, @RequestHeader("Authorization") String token);

    @PostMapping("/save")
    void saveUser(@RequestBody UserInfo userInfo, @RequestParam String secret/*, @HeaderParam("Authorization") String token*/);
}
