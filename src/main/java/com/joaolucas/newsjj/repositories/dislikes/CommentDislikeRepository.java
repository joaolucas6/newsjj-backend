package com.joaolucas.newsjj.repositories.dislikes;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDislikeRepository extends JpaRepository<CommentDislike, Long> {
}
