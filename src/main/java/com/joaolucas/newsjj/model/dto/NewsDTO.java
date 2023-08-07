package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class NewsDTO {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime instant;
    private Long authorId;
    private List<String> imagesUrl;
    private List<Long> topicsId;
    private List<Long> likesId;
    private List<Long> dislikesId;

    public NewsDTO(News news){

        setId(news.getId());
        setTitle(news.getTitle());
        setText(news.getText());
        setInstant(news.getInstant());
        setAuthorId(news.getAuthor().getId());
        setImagesUrl(news.getImagesUrl());
        setTopicsId(news.getTopics().stream().map(Topic::getId).toList());
        setLikesId(news.getLikes().stream().map(NewsLike::getId).toList());
        setDislikesId(news.getDislikes().stream().map(NewsDislike::getId).toList());

    }
}
