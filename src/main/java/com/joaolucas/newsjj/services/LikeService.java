package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.repositories.likes.CommentLikeRepository;
import com.joaolucas.newsjj.repositories.likes.NewsLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final NewsLikeRepository newsLikeRepository;
    private final CommentLikeRepository commentLikeRepository;


    public List<LikeDTO> findAllNewsLike(){
        return newsLikeRepository.findAll().stream().map(LikeDTO::new).toList();
    }

    public LikeDTO findNewsLikeById(Long id){
        return new LikeDTO(newsLikeRepository.findById(id).orElseThrow());
    }

    public List<LikeDTO> findAllCommentLikes(){
        return commentLikeRepository.findAll().stream().map(LikeDTO::new).toList();
    }

    public LikeDTO findCommentLikeById(Long id){
        return new LikeDTO(commentLikeRepository.findById(id).orElseThrow());
    }
    
}
