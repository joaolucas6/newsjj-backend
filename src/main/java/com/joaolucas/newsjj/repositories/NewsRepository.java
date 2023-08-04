package com.joaolucas.newsjj.repositories;

import com.joaolucas.newsjj.model.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
