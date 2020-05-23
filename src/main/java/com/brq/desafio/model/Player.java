package com.brq.desafio.model;

import java.io.Serializable;

public class Player implements Serializable{
    Long playerNumber;


    public Player(long playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Player() {
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }

}
