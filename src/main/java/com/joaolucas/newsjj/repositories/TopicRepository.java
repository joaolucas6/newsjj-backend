package com.joaolucas.newsjj.repositories;

import com.joaolucas.newsjj.model.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
