package ru.tkachenko.ecare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tkachenko.ecare.models.Client;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String login);
}
