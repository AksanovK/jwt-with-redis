package ru.itis.rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest_api.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

}
