package com.brq.desafio.service;

import com.brq.desafio.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    List<Player> players;


    public PlayerService() {
        this.players = new ArrayList<>();
    }

    public Player save(Player player) {
        if (getByNumber(player.getPlayerNumber()) == null){
            this.players.add(player);
            return player;
        } else {
            return null;
        }
    }

    public int count() {
        return this.players.size();
    }

    public Player getByNumber(long playNumber) {
        if (players != null && players.size() >0 ){
            return players.stream()
                    .filter((Player p) -> p.getPlayerNumber() == playNumber  )
                    .findAny().orElse(null);
        } else {
            return null;
        }

    }

    public void remove(long playNumber) {
        players.removeIf(p-> p.getPlayerNumber() == playNumber);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
