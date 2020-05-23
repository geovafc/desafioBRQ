package com.brq.desafio.controller;

import com.brq.desafio.model.Player;
import com.brq.desafio.service.PlayerMoveService;
import com.brq.desafio.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/players"})
public class PlayerController {

    private PlayerService service;

    private PlayerMoveService playerMoveService;

    public PlayerController(PlayerService service, PlayerMoveService playerMoveService) {
        this.service = service;
        this.playerMoveService = playerMoveService;
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody Player player){

        Player playerSaved = service.save(player);
        if (playerSaved != null){
            return new ResponseEntity<>("{\n" +
                    "\"result\":\"Jogador salvo com sucesso.\"\n" +
                    "}", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("{\n" +
                    "\"result\":\"Falha ao cadastrar jogador, verifique se o jogador já foi cadastrado!!!.\"\n" +
                    "}",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity <List<Player>> findAll(){
        return new ResponseEntity<>(service.getPlayers() , HttpStatus.OK);    }

    @GetMapping(path ="/{number}")
    public Optional<Player> playerByNumber(@PathVariable("number")  long number){
        return Optional.ofNullable(service.getByNumber(number));
    }

    @DeleteMapping(path ={"/{number}"})
    public ResponseEntity<?> delete(@PathVariable long number) {

        if (playerMoveService.getByPlayerNumber(number) != null ){
            playerMoveService.removeByPlayerNumber(number);
        } else {
            service.remove(number);
        }


        return ResponseEntity.ok().build();
    }
}
