package mirea.identityservice.repositories;

import mirea.identityservice.domain.entities.UserCredential;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialRepository extends CrudRepository<UserCredential, Integer> {
}
