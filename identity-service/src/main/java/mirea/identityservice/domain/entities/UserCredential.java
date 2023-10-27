package mirea.identityservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("UserCredential")
public class UserCredential implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String password;
}
