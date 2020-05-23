package com.brq.desafio.controller;

import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import com.brq.desafio.service.JokenpoService;
import com.brq.desafio.service.PlayerMoveService;
import com.brq.desafio.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class JokenpoControllerTest {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    private JokenpoService service;

    @MockBean
    private PlayerMoveService playerMoveService;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldCreatePlayer() throws Exception {
        Player player1 = new  Player(1L);

        when(playerService.save(any(Player.class))).thenReturn(player1);

        ObjectMapper objectMapper = new ObjectMapper();
        String playerToJson = objectMapper.writeValueAsString(player1);

        ResultActions result = mockMvc.perform(post("/api/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playerToJson)
        );
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("Jogador salvo com sucesso."));


    }



    @Test
    void shouldCreatePlayerMove() throws Exception {
        Player player1 = new  Player(1L);
        PlayerMove move = new PlayerMove(player1,"Papel");

        when(playerMoveService.save(any(PlayerMove.class))).thenReturn(move);

        ObjectMapper objectMapper = new ObjectMapper();
        String playerMoveToJson = objectMapper.writeValueAsString(move);

        ResultActions result = mockMvc.perform(post("/api/playerMovements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playerMoveToJson)
        );
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value("Jogada salva com sucesso."));

    }

    @Test
    void shouldPlay() throws Exception {
        List<String> matchResultList = new ArrayList<>();

        Player player1 = new  Player(1L);
        Player player2 = new  Player(2L);

        List<PlayerMove> moves = new ArrayList<>();
        PlayerMove move1 = new PlayerMove(player1,"Pedra");
        moves.add(move1);
        PlayerMove move2 = new PlayerMove(player2,"Papel");
        moves.add(move2);

        matchResultList.add("Resultado jogador 2 Vitória");
        when(service.play()).thenReturn(matchResultList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/play")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @AfterEach
    void tearDown () {
        playerMoveService.listMoves().clear();
    }

}
