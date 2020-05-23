package com.brq.desafio.service;

import com.brq.desafio.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JokenpoService {
    PlayerMoveService playerMoveService;

    public JokenpoService(PlayerMoveService playerMoveService) {
        this.playerMoveService = playerMoveService;
    }

    public List<String> play() {
        //Só deixar iniciar se o numero de jogada for maior q 1
        List<String> matchResultList = new ArrayList<>();

        if (playerMoveService.listMoves().size() > 1){
            //um jogador pode ter varias jogadas
            Map<String ,List<PlayerMove> > mapGroupByMoviment = playerMoveService.listMoves().stream()
                    .collect(  Collectors.groupingBy(PlayerMove::getOption));

            if (mapGroupByMoviment.size() == 2 ){
                final String[] movementsToplay = {"",""};
                mapGroupByMoviment.forEach(   (k , v ) -> {
                    if (movementsToplay[0].equals("")){
                        movementsToplay[0] = k;
                    } else {
                        movementsToplay[1] = k;
                    }
                });
                if (!movementsToplay[0].equals(movementsToplay[1])){

                    if (movementsToplay[0].equals("pedra") && movementsToplay[1].equals("tesoura") ||
                            movementsToplay[0].equals("papel") && movementsToplay[1].equals("pedra") ||
                            movementsToplay[0].equals("tesoura") && movementsToplay[1].equals("papel")
                            ) {
                        final int[] cont = {0};
                        mapGroupByMoviment.get(movementsToplay[0]).stream().forEach(playerMove -> {
                            matchResultList.add("Resultado jogador "+playerMove.getPlayer().getPlayerNumber()+" Vitória");
                        });
                        return matchResultList;
                    } else {
                        mapGroupByMoviment.get(movementsToplay[1]).stream().forEach(playerMove -> {
                            matchResultList.add("Resultado jogador "+playerMove.getPlayer().getPlayerNumber()+" Vitória");
                        });
                        return matchResultList;
                    }
                }
                else {
                    matchResultList.add("O jogo empatou");
                    return matchResultList;
                }
            } else {
                matchResultList.add("O jogo não teve ganhador");
                return matchResultList;
            }
        } else {
            matchResultList.add("O número de jogadas precisa ser maior que 1");
            return matchResultList;
        }

    }

}
