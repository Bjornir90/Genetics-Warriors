package com.scep.genetics;

import org.mini2Dx.miniscript.core.GameFuture;

public class MoveFuture extends GameFuture {
    private Fighter fighter;
    private float angle;


    public MoveFuture(Fighter fighter, float angle) {
        super();
        this.fighter = fighter;
        this.angle = angle;
    }

    @Override
    protected boolean update(float delta) {
        try {
            float movementX = (float) (Math.cos(angle) * fighter.mov_speed);
            float movementY = (float) (-Math.sin(angle) * fighter.mov_speed);
            fighter.moveBy(movementX, movementY);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onFutureSkipped() {

    }

    @Override
    protected void onScriptSkipped() {

    }
}
