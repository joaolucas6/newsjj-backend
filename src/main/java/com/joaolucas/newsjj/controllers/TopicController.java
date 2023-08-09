package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicDTO>> findAll(){
        return ResponseEntity.ok(topicService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(topicService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TopicDTO> create(@RequestBody TopicDTO topicDTO){
        return ResponseEntity.ok(topicService.create(topicDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody TopicDTO updateRequest){
        return ResponseEntity.ok(topicService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }


}
