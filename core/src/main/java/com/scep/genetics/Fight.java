package com.scep.genetics;

import java.util.concurrent.Callable;

public class Fight implements Callable<FightResult> {
    private Fighter fighter1, fighter2;

    public Fight(Fighter fighter1, Fighter fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    @Override
    public FightResult call() throws Exception {
        return runFight();
    }

    private FightResult runFight(){
        long startTime = System.nanoTime();

        while(fighter1.health > 0 && fighter2.health > 0){
            long delta = startTime - System.nanoTime();
            startTime = System.nanoTime();
            fighter1.update(delta);
            fighter2.update(delta);
        }

        FightResult result;
        if(fighter1.health <= 0){
            result = new FightResult(fighter2, fighter1);
        } else {
            result = new FightResult(fighter1, fighter2);
        }
        return result;
    }
}
