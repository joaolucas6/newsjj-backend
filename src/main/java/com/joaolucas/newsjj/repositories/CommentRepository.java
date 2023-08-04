package com.joaolucas.newsjj.repositories;

import com.joaolucas.newsjj.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
