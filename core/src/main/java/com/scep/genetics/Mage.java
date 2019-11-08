package com.scep.genetics;

import java.util.List;

public class Mage extends Fighter {

    final static float DAMAGE_RATE = 0.4f;
    final static float HEALTH_RATE = 2;
    final static float ATQ_SPEED_RATE = 0.008f;
    final static float MOV_SPEED_RATE = 0.05f;
    final static float CRIT_CHANC_RATE = 0.55f;
    final static float CRIT_DMG_RAGE = 3;

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
        this.damage += (int) (DAMAGE_RATE * pts.get(0));
        this.initial_health += (int) (HEALTH_RATE * pts.get(1));
        this.secondPerAttack -= ATQ_SPEED_RATE * pts.get(2);
        this.mov_speed = this.mov_speed * MOV_SPEED_RATE * pts.get(3);
        this.crit_chance += CRIT_CHANC_RATE * pts.get(4);
        this.crit_damage += CRIT_DMG_RAGE * pts.get(5);
    }

    @Override
    protected void attack(float distanceToOpponent) {

    }

}
