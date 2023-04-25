package com.proyecto.b.s.controller;

import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.service.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/answer")
@CrossOrigin("*")

public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Answer>> findAnswerList() {

        return ResponseEntity.status(HttpStatus.OK).body(answerService.listAnswer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findAnswerById(@PathVariable Long id) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(answerService.findById(id));
    }
}
