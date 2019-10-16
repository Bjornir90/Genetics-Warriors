package com.scep.genetics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class WarriorsGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.scep.geneticswarriors";
	Mage mage;

	@Override
    public void initialise() {
    	mage = new Mage("sprite.png");

    }
    
    @Override
    public void update(float delta) {
        mage.update(delta);
    }
    
    @Override
    public void interpolate(float alpha) {
        mage.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g) {
		mage.render(g);

    }
}
