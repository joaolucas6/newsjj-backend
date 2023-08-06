package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/news")
    public ResponseEntity<List<LikeDTO>> findAllNewsLikes(){
        return ResponseEntity.ok(likeService.findAllNewsLike());
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<LikeDTO> findNewsLikeById(@PathVariable Long id){
        return ResponseEntity.ok(likeService.findNewsLikeById(id));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<LikeDTO>> findAllCommentLikes(){
        return ResponseEntity.ok(likeService.findAllCommentLikes());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<LikeDTO> findCommentLikeById(@PathVariable Long id){
        return ResponseEntity.ok(likeService.findCommentLikeById(id));
    }


}
