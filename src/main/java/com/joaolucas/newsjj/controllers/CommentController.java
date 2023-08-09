package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.CommentDTO;
import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping("/{authorId}/{newsId}")
    public ResponseEntity<CommentDTO> create(
            @PathVariable Long authorId,
            @PathVariable Long newsId,
            @RequestBody CommentDTO commentDTO
    ){
        return ResponseEntity.ok(commentService.create(authorId, newsId, commentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO updateRequest){
        return ResponseEntity.ok(commentService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/likes/{userId}/{commentId}")
    public ResponseEntity<List<LikeDTO>> like(@PathVariable Long userId, @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.like(userId, commentId));
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<List<LikeDTO>> removeLike(@PathVariable Long likeId){
        return ResponseEntity.ok(commentService.removeLike(likeId));
    }

    @PostMapping("/dislikes/{userId}/{commentId}")
    public ResponseEntity<List<DislikeDTO>> dislike(@PathVariable Long userId, @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.dislike(userId, commentId));
    }

    @DeleteMapping("/dislikes/{dislikeId}")
    public ResponseEntity<List<DislikeDTO>> removeDislike(@PathVariable Long dislikeId){
        return ResponseEntity.ok(commentService.removeDislike(dislikeId));
    }

}
