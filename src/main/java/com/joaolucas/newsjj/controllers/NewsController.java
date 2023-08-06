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
    public ResponseEntity delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/images/{newsId}/{imageUrl}")
    public ResponseEntity<List<String>> addImage(@PathVariable Long newsId, @PathVariable String imageUrl){
        return ResponseEntity.ok(newsService.addImage(newsId, imageUrl));
    }

    @DeleteMapping("/images/{newsId}/{imageUrl}")
    public ResponseEntity<List<String>> removeImage(@PathVariable Long newsId, @PathVariable String imageUrl){
        return ResponseEntity.ok(newsService.removeImage(newsId, imageUrl));
    }

    @PostMapping("/topics/{newsId}/{topicId}")
    public ResponseEntity<List<TopicDTO>> addTopic(@PathVariable Long newsId, @PathVariable Long topicId){
        return ResponseEntity.ok(newsService.addTopic(newsId, topicId));
    }

    @DeleteMapping("/topics/{newsId}/{topicId}")
    public ResponseEntity<List<TopicDTO>> removeTopic(@PathVariable Long newsId, @PathVariable Long topicId){
        return ResponseEntity.ok(newsService.removeTopic(newsId, topicId));
    }

    @PostMapping("/likes/{userId}/{newsId}")
    public ResponseEntity<List<LikeDTO>> like(@PathVariable Long userId, @PathVariable Long newsId){
        return ResponseEntity.ok(newsService.like(userId, newsId));
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<List<LikeDTO>> removeLike(@PathVariable Long likeId){
        return ResponseEntity.ok(newsService.removeLike(likeId));
    }

    @PostMapping("/dislikes/{userId}/{newsId}")
    public ResponseEntity<List<DislikeDTO>> dislike(@PathVariable Long userId, @PathVariable Long newsId){
        return ResponseEntity.ok(newsService.dislike(userId, newsId));
    }

    @DeleteMapping("/dislikes/{dislikeId}")
    public ResponseEntity<List<DislikeDTO>> removeDislike(@PathVariable Long dislikeId){
        return ResponseEntity.ok(newsService.removeDislike(dislikeId));
    }

}
