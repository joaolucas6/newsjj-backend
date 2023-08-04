package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeDTO {

    private Long id;
    private Long authorId;
    private Long contentId;
    private LocalDateTime instant;
    private String likeType;

    public LikeDTO(CommentLike commentLike){
        setId(commentLike.getId());
        setAuthorId(commentLike.getAuthor().getId());
        setContentId(commentLike.getComment().getId());
        setInstant(commentLike.getInstant());
        setLikeType("Comment Like");
    }

    public LikeDTO(NewsLike newsLike){
        setId(newsLike.getId());
        setAuthorId(newsLike.getAuthor().getId());
        setContentId(newsLike.getNews().getId());
        setInstant(newsLike.getInstant());
        setLikeType("News Like");
    }
}
