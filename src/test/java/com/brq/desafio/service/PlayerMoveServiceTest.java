package com.brq.desafio.service;

import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayerMoveServiceTest {

    @Autowired
    private PlayerMoveService service;

    @Autowired
    private PlayerService playerService;

    @Test
    void shouldSaveAPlayerMove() {
        Player player1 = new  Player(1L);
        playerService.save(player1);
        PlayerMove move = new PlayerMove(player1,"Papel");

        service.save(move);

        assertEquals(1L, service.count());
    }

    @Test
    void shouldReturnNullIfPlayerHasNotSaved() {
        Player player1 = new  Player(1L);

        PlayerMove move = new PlayerMove(player1,"Papel");

        PlayerMove saved = service.save(move);

        assertEquals(null, saved);
    }

    @Test
    void shouldReturnNullIfSaved() {
        Player player1 = new  Player(1L);
        PlayerMove move = new PlayerMove(player1,"Papel");
        service.save(move);

        PlayerMove saved = service.save(move);

        assertEquals(null, saved);
    }


    @Test
    void shouldRemoveByPlayerNumberAndOption() {
        Player player = new Player(1L);
        playerService.save(player);
        PlayerMove move = new PlayerMove(player,"Papel");
        service.save(move);

        Player player2 = new Player(2L);
        playerService.save(player2);
        PlayerMove move2 = new PlayerMove(player2,"Tesoura");
        service.save(move2);

        playerService.remove(1);
        service.removeByPlayerNumberAndOption(1,"Papel");

        assertEquals(1L, service.playersMove.size());
    }

    @AfterEach
    void tearDown () {
        service.playersMove.clear();
    }

}
