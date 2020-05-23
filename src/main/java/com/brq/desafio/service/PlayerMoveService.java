package com.brq.desafio.service;

import com.brq.desafio.model.Player;
import com.brq.desafio.model.PlayerMove;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerMoveService {

    List<PlayerMove> playersMove;
    private PlayerService playerService;


    public PlayerMoveService(PlayerService playerService) {
        this.playersMove = new ArrayList<>();
        this.playerService = playerService;
    }


    public PlayerMove save(PlayerMove playerMove) {
        if (getByOptionAndByPlayerNumber(playerMove.getOption(), playerMove.getPlayer().getPlayerNumber()) == null){
            if (playerMove.getOption().toLowerCase().equals("pedra") || playerMove.getOption().toLowerCase().equals("papel")
                    || playerMove.getOption().toLowerCase().equals("tesoura")){
                Player player =  playerService.getByNumber(playerMove.getPlayer().getPlayerNumber());
                if (player != null){
                    this.playersMove.add(playerMove);
                    return playerMove;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return  null;
        }

    }

    public int count() {
       return playersMove.size();
    }

    public PlayerMove getByOptionAndByPlayerNumber(String option, long playNumber) {
        if (playersMove != null && playersMove.size() >0 ){
            return playersMove.stream()
                    .filter((PlayerMove p) -> p.getOption() == option && p.getPlayer().getPlayerNumber() == playNumber  )
                    .findAny().orElse(null);
        } else {
            return null;
        }

    }

    public PlayerMove getByPlayerNumber( long playNumber) {
        if (playersMove != null && playersMove.size() >0 ){
            return playersMove.stream()
                    .filter((PlayerMove p) -> p.getPlayer().getPlayerNumber() == playNumber  )
                    .findAny().orElse(null);
        } else {
            return null;
        }

    }

    public List<PlayerMove> listMoves() {
        return playersMove;
    }

    public void removeByPlayerNumber(long playNumber) {
        playersMove.removeIf(p-> p.getPlayer().getPlayerNumber() == playNumber);
        playerService.remove(playNumber);
    }

    public void removeByPlayerNumberAndOption(long playNumber, String option) {
        playersMove.removeIf(p-> p.getPlayer().getPlayerNumber() == playNumber && p.getOption().equals(option));
        playerService.remove(playNumber);

    }
}
