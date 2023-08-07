package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DislikeDTO {

    private Long id;
    private Long authorId;
    private Long contentId;
    private LocalDateTime instant;
    private String dislikeType;

    public DislikeDTO(CommentDislike commentDislike){
        setId(commentDislike.getId());
        setAuthorId(commentDislike.getAuthor().getId());
        setContentId(commentDislike.getComment().getId());
        setInstant(commentDislike.getInstant());
        setDislikeType("Comment Dislike");
    }

    public DislikeDTO(NewsDislike newsDislike){
        setId(newsDislike.getId());
        setAuthorId(newsDislike.getAuthor().getId());
        setContentId(newsDislike.getNews().getId());
        setInstant(newsDislike.getInstant());
        setDislikeType("News Dislike");
    }
}
