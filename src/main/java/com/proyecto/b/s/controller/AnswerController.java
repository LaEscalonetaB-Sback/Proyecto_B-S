package com.proyecto.b.s.controller;

import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.service.service.AnswerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bs/answer")
@CrossOrigin("*")

// TODO: 11/4/2023 eliminar answer controller ya que no se utiliza
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<List<Answer>> findAnswerList(){
        return ResponseEntity.status(HttpStatus.OK).body(answerService.listAnswer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findAnswerById(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(answerService.findById(id));
    }

}
