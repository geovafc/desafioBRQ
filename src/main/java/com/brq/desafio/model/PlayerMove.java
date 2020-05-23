package com.brq.desafio.model;

import java.io.Serializable;

public class PlayerMove implements Serializable {
    String option;
    Player player;

    public PlayerMove(Player player, String option) {
        this.player = player;
        this.option = option;
    }

    public PlayerMove() {
    }

    public String getOption() {
        return option;
    }


    public Player getPlayer() {
        return player;
    }

}
