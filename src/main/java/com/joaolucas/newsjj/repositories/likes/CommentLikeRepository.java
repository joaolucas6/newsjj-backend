package com.joaolucas.newsjj.repositories.likes;

import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}
