package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.dto.NewsDTO;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDTO>> findAll(){
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsService.findById(id));
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<NewsDTO> create(@PathVariable Long authorId, @RequestBody NewsDTO newsDTO){
        return ResponseEntity.ok(newsService.create(newsDTO, authorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @RequestBody NewsDTO updateRequest){
        return ResponseEntity.ok(newsService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/images/{newsId}/{imageUrl}")
    public ResponseEntity<Void> addImage(@PathVariable Long newsId, @PathVariable String imageUrl){
        newsService.addImage(newsId, imageUrl);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/images/{newsId}/{imageUrl}")
    public ResponseEntity<Void> removeImage(@PathVariable Long newsId, @PathVariable String imageUrl){
        newsService.removeImage(newsId, imageUrl);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/topics/{newsId}/{topicId}")
    public ResponseEntity<Void> addTopic(@PathVariable Long newsId, @PathVariable Long topicId){
        newsService.addTopic(newsId, topicId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/topics/{newsId}/{topicId}")
    public ResponseEntity<Void> removeTopic(@PathVariable Long newsId, @PathVariable Long topicId){
        newsService.removeTopic(newsId, topicId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/likes/{userId}/{newsId}")
    public ResponseEntity<Void> like(@PathVariable Long userId, @PathVariable Long newsId){
        newsService.like(userId, newsId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long likeId){
        newsService.removeLike(likeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislikes/{userId}/{newsId}")
    public ResponseEntity<Void> dislike(@PathVariable Long userId, @PathVariable Long newsId){
        newsService.dislike(userId, newsId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dislikes/{dislikeId}")
    public ResponseEntity<Void> removeDislike(@PathVariable Long dislikeId){
        newsService.removeDislike(dislikeId);
        return ResponseEntity.ok().build();
    }

}
