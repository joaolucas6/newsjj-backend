package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private LocalDateTime instant;
    private Long authorId;
    private Long newsId;
    private List<Long> likes;
    private List<Long> dislikes;

    public CommentDTO(Comment comment){
        setId(comment.getId());
        setText(comment.getText());
        setInstant(comment.getInstant());
        setAuthorId(comment.getAuthor().getId());
        setNewsId(comment.getNews().getId());
        setLikes(comment.getLikes().stream().map(CommentLike::getId).toList());
        setDislikes(comment.getDislikes().stream().map(CommentDislike::getId).toList());
    }
}
