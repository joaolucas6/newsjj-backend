package com.joaolucas.newsjj.repositories;

import com.joaolucas.newsjj.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
