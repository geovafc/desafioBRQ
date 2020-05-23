package com.brq.desafio.service;

import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService service;

    @Autowired
    private PlayerMoveService playerMoveService;

    @Test
    void shouldSaveAPlayer() {
        Player player = new Player(1L);

        service.save(player);

        assertEquals(1L, service.count());
    }

    @Test
    void shouldRemoveAPlayer() {
        Player player = new Player(1L);
        service.save(player);
        PlayerMove move = new PlayerMove(player,"Papel");
        playerMoveService.save(move);

        Player player2 = new Player(2L);
        service.save(player2);
        PlayerMove move2 = new PlayerMove(player2,"Papel");
        playerMoveService.save(move2);

        //service.remove(1L);
        playerMoveService.removeByPlayerNumber(1L);

        assertEquals(1L, service.players.size());
    }

    @Test
    void shouldReturnNull() {
        Player player = new Player(1L);

        service.save(player);

        Player playerSaved =service.save(player);

        assertEquals(null, playerSaved);
    }

    @Test
    void shouldReturnList() {
        Player player = new Player(1L);
        service.save(player);

        Player player2 = new Player(2L);
        service.save(player2);

        assertNotNull(service.players);

    }


    @AfterEach
    void tearDown () {
        service.players.clear();
    }


}
