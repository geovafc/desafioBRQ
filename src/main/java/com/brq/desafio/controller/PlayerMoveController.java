package com.brq.desafio.controller;

import com.brq.desafio.model.PlayerMove;
import com.brq.desafio.service.PlayerMoveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/playerMovements"})
public class PlayerMoveController {

    private PlayerMoveService service;

    public PlayerMoveController(PlayerMoveService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createPlayerMove(@RequestBody PlayerMove playerMove){
        PlayerMove playerMoveSaved = service.save(playerMove);
        if (playerMoveSaved != null){
            return new ResponseEntity<>("{\n" +
                    "\"result\":\"Jogada salva com sucesso.\"\n" +
                    "}", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("{\n" +
                    "\"result\":\"Falha ao cadastrar jogada, verifique se o número do jogador ou a opção da partida já foram cadastrados ou se a opção escolhida é válida!!!.\"\n" +
                    "}",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity <List<PlayerMove>> findAll(){
        return new ResponseEntity<>(service.listMoves() , HttpStatus.OK);    }

    @GetMapping(path ="/{number}/{option}")
    public Optional<PlayerMove> byNumberAndOption(@PathVariable long number, @PathVariable String option){
        return Optional.ofNullable(service.getByOptionAndByPlayerNumber(option,number));
    }

    @DeleteMapping(path ={"/{number}/{option}"})
    public ResponseEntity<?> delete(@PathVariable long number, @PathVariable String option) {
        service.removeByPlayerNumberAndOption(number,option);
        return ResponseEntity.ok().build();
    }
}
