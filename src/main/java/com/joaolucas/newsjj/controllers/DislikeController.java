package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.services.DislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dislikes")
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;

    @GetMapping("/news")
    public ResponseEntity<List<DislikeDTO>> findAllNewsDislikes(){
        return ResponseEntity.ok(dislikeService.findAllNewsDislikes());
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<DislikeDTO> findNewsDislikeById(@PathVariable Long id){
        return ResponseEntity.ok(dislikeService.findNewsDislikeById(id));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<DislikeDTO>> findAllCommentDislikes(){
        return ResponseEntity.ok(dislikeService.findAllCommentDislikes());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<DislikeDTO> findCommentDislikeById(@PathVariable Long id){
        return ResponseEntity.ok(dislikeService.findCommentDislikeById(id));
    }
}
