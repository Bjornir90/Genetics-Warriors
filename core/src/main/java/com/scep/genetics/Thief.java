package com.scep.genetics;

import java.util.List;

public class Thief extends Fighter {

    public Thief(String spritePath) {
        super(spritePath);
        this.damage = 8;
        this.initial_health = 70;
        this.secondPerAttack = 1;
        this.mov_speed = 0.000000015;
        // Percentages
        this.crit_chance = 30;
        this.crit_damage = 200;
        this.dodge = 20;
    }

    @Override
    public void setCarac(List<Integer> pts) {

    }

    @Override
    protected void attack(float distanceToOpponent) {

    }


}
