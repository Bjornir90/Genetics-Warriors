package com.scep.genetics.genAlgo;

import com.scep.genetics.*;
import org.python.modules._hashlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class caracAlgo {

    private List<Fighter> fighters;
    private List<Fight> fights;
    private HashMap<Integer, Float> idToScore;
    private HashMap<Integer, ADN> idToADN;

    public caracAlgo() {
        this(100);
    }

    public caracAlgo(int numberOfFighters){

        fighters = new ArrayList<>();
        idToScore = new HashMap<>();
        idToADN = new HashMap<>();

        for (int i = 0; i < numberOfFighters; i++) {
            switch (i%3) {
                case 0:
                    fighters.add(new Warrior(i, "sprite.png"));
                    break;
                case 1:
                    fighters.add(new Thief(i, "sprite.png"));
                    break;
                case 2:
                    fighters.add(new Mage(i, "sprite.png"));
                    break;
            }

            int damage = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int health = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int atk_speed = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int mov_speed = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int crit_chance = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int crit_damage = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);
            int dodge = ThreadLocalRandom.current().nextInt(1, ADN.MAX_POINTS-ADN.NUMBER_CARAC);

            ADN adn = new ADN(damage, health, atk_speed, mov_speed, crit_chance, crit_damage, dodge);

            idToADN.put(i, adn);
            idToScore.put(i, 0f);
        }
    }


    private void computeScore(FightResult result){
        int idToChange = result.getWinner().getId();
        idToScore.replace(idToChange, idToScore.get(idToChange)+1);
    }

    private void computeFinalScore(){
        for(Integer id : idToScore.keySet()){
            idToScore.replace(id, idToScore.get(id)/fighters.size());
        }
    }

    private void createMatches(){
        fights = new ArrayList<>();
        for(Fighter fighter : fighters){
            fighters.forEach(fighter1 -> fights.add(new Fight(fighter, fighter1)));
        }
    }

    private void reproduce(){
        HashMap<Integer, ADN> idToADNnew = new HashMap<>();
        for (int fighterNumber = 0; fighterNumber < fighters.size(); fighterNumber++) {

            float maxChanceToReproduce = 0f;
            int idToReproduce = 0;

            //We only reproduce warriors with warriors, a magician adn will never be passed down to a thief or a warrior
            for (int id = fighterNumber; id < idToScore.size(); id+=3) {

                //The chance to reproduce is a random number multiplied by the score of the fighter
                float chance = ThreadLocalRandom.current().nextFloat()*idToScore.get(id);
                if(chance > maxChanceToReproduce){
                    maxChanceToReproduce = chance;
                    idToReproduce = id;
                }

            }
            idToADNnew.put(fighterNumber, idToADN.get(idToReproduce).mutate(4));
        }
        idToADN = idToADNnew;
    }

    public void runMatches() throws Exception {
        createMatches();
        for(Fight fight : fights){
            FightResult result = fight.call();
            computeScore(result);
        }
        computeFinalScore();
        reproduce();
    }

}
