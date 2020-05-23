package com.brq.desafio.controller;

import com.brq.desafio.service.JokenpoService;
import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class JokenpoController {

    private JokenpoService service;
    List<Player> players;
    List<PlayerMove> moves;

    public JokenpoController(JokenpoService service) {
        this.service = service;
    }

    @GetMapping(path ="/play")
    public ResponseEntity<List <String>> play(){

        return new ResponseEntity<>(service.play(), HttpStatus.OK);    }
}
