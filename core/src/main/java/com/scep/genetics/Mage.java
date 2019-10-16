package com.scep.genetics;

import java.util.List;

public class Mage extends Fighter {


    public Mage(String spritePath) {

        super(spritePath);
        this.damage = 10;
        this.initial_health = 100;
        this.secondPerAttack = 1;
        this.mov_speed = 0.00000001;
        // Percentages
        this.crit_chance = 5;
        this.crit_damage = 150;
        this.dodge = 3;

    }

    @Override
    public void setCarac(List<Integer> pts) throws IndexOutOfBoundsException{
        this.damage += (int) (0.4*pts.get(0));
        this.initial_health += (int) (2*pts.get(1));
        this.secondPerAttack -= 0.008*pts.get(2);
        this.mov_speed = this.mov_speed * 0.05 * pts.get(3);
        this.crit_chance += 0.55*pts.get(4);

    }

    @Override
    protected void attack(float distanceToOpponent) {

    }

}
