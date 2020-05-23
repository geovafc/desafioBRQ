package com.brq.desafio.service;

import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JokenpoServiceTest {

    @Autowired
    private JokenpoService service;

    @Autowired
    private PlayerMoveService playerMoveService;
    @Autowired
    private PlayerService playerService;

    @Test
    void shouldPlay() {
        Player player1 = new  Player(1L);
        playerService.save(player1);
        PlayerMove move1 = new PlayerMove(player1,"Papel");
        playerMoveService.save(move1);

        Player player2 = new  Player(2L);
        playerService.save(player2);
        PlayerMove move2 = new PlayerMove(player2,"Tesoura");
        playerMoveService.save(move2);

        List<String> resultados = service.play();
       assertNotNull(resultados);
    }





    @AfterEach
    void tearDown () {
        playerMoveService.playersMove.clear();
    }

}
