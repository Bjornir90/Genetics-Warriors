package com.scep.genetics.genAlgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ADN {

    public static final int MAX_POINTS = 100, NUMBER_CARAC = 7;

    public HashMap<Caracteristic, Integer> genes;

    public ADN(int damage, int health, int atk_speed, int mov_speed, int crit_chance, int crit_damage, int dodge) {
        genes = new HashMap<>();
        genes.put(Caracteristic.DAMAGE, damage);
        genes.put(Caracteristic.HEALTH, health);
        genes.put(Caracteristic.ATK_SPEED, atk_speed);
        genes.put(Caracteristic.MOV_SPEED, mov_speed);
        genes.put(Caracteristic.CRIT_CHANCE, crit_chance);
        genes.put(Caracteristic.CRIT_DAMAGE, crit_damage);
        genes.put(Caracteristic.DODGE, dodge);
    }

    public ADN mutate(int maxOvershoot){

        int total = MAX_POINTS;
        List<Integer> modifiedCaracs = new ArrayList<>();

        while(modifiedCaracs.size() < NUMBER_CARAC){

            int rankToMutate = ThreadLocalRandom.current().nextInt(0, NUMBER_CARAC+1);
            if(!modifiedCaracs.contains(rankToMutate)){

                modifiedCaracs.add(rankToMutate);
                //Costly !
                Caracteristic toChange = Caracteristic.values()[rankToMutate];

                int randomChange = ThreadLocalRandom.current().nextInt(-maxOvershoot, maxOvershoot);

                int originalValue = genes.get(toChange);

                if(total < MAX_POINTS-maxOvershoot && originalValue <= 100-maxOvershoot){
                    randomChange = maxOvershoot;
                } else if (total > MAX_POINTS+maxOvershoot && originalValue >= maxOvershoot){
                    randomChange = -maxOvershoot;
                }

                genes.replace(toChange, originalValue+randomChange);
                total += randomChange;
            }
        }

        while(total != MAX_POINTS){
            int rankToMutate = ThreadLocalRandom.current().nextInt(0, NUMBER_CARAC+1);
            int change = MAX_POINTS-total;

            Caracteristic toChange = Caracteristic.values()[rankToMutate];
            int originalValue = genes.get(toChange);

            if(originalValue > MAX_POINTS-change || originalValue < -change)
                continue;

            genes.replace(toChange, originalValue+change);
            total += change;
        }
        return this;
    }


}
