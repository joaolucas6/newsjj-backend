package com.joaolucas.newsjj.repositories.likes;

import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsLikeRepository extends JpaRepository<NewsLike, Long> {
}
