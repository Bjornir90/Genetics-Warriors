package com.scep.genetics.genAlgo;

public class ADN {

    public static final int MAX_POINTS = 100, NUMBER_CARAC = 7;

    public int damage, health, atk_speed, mov_speed, crit_chance, crit_damage, dodge;

    public ADN(int damage, int health, int atk_speed, int mov_speed, int crit_chance, int crit_damage, int dodge) {
        this.damage = damage;
        this.health = health;
        this.atk_speed = atk_speed;
        this.mov_speed = mov_speed;
        this.crit_chance = crit_chance;
        this.crit_damage = crit_damage;
        this.dodge = dodge;
    }

}
