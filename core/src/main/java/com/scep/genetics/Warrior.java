package com.scep.genetics;

import java.util.List;

public class Warrior extends Fighter {

    public Warrior(int id, String spritePath) {
        super(id, spritePath);
        this.damage = 8;
        this.initial_health = 150;
        this.secondPerAttack = 1.2;
        this.mov_speed = 0.000000008;
        // Percentages
        this.crit_chance = 5;
        this.crit_damage = 150;
        this.dodge = 3;
        this.armor = 3;
    }

    @Override
    public void setCarac(List<Integer> pts) {

    }

    @Override
    protected void attack(float distanceToOpponent) {

    }

}
