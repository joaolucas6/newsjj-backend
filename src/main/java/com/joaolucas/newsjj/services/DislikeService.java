package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.repositories.dislikes.CommentDislikeRepository;
import com.joaolucas.newsjj.repositories.dislikes.NewsDislikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikeService {

    private final NewsDislikeRepository newsDislikeRepository;
    private final CommentDislikeRepository commentDislikeRepository;

    public List<DislikeDTO> findAllNewsDislikes(){
        return newsDislikeRepository.findAll().stream().map(DislikeDTO::new).toList();
    }

    public DislikeDTO findNewsDislikeById(Long id){
        return new DislikeDTO(newsDislikeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("News dislike was not found with ID: " + id)));
    }

    public List<DislikeDTO> findAllCommentDislikes(){
        return commentDislikeRepository.findAll().stream().map(DislikeDTO::new).toList();
    }

    public DislikeDTO findCommentDislikeById(Long id){
        return new DislikeDTO(commentDislikeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment dislike was not found with ID: " + id)));
    }

}
