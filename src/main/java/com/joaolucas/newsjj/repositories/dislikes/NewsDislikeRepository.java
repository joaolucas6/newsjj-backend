package com.joaolucas.newsjj.repositories.dislikes;

import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDislikeRepository extends JpaRepository<NewsDislike, Long> {
}
