package com.scep.genetics;


import org.mini2Dx.core.collisions.RegionQuadTree;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.graphics.viewport.Viewport;
import org.mini2Dx.miniscript.core.GameScriptingEngine;
import org.mini2Dx.miniscript.core.exception.InsufficientCompilersException;
import org.mini2Dx.miniscript.python.PythonGameScriptingEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WarriorsGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.scep.geneticswarriors";
	public static final float GAME_RENDER_X = 1920f, GAME_RENDER_Y = 1080f;
	private GameScriptingEngine scriptingEngine;
    private HashMap<String, Integer> scripts;
    private HashMap<Integer, Fighter> fightersByID;
    private RegionQuadTree<CollisionBox> collisions;
    private Viewport viewport;

    private void loadScript(String name){
        String content;
        try {
            content = new Scanner(new File(name+".py")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int id = 0;
        try {
            id = scriptingEngine.compileScript(content);
        } catch (InsufficientCompilersException e) {
            System.err.println("Error while loading scripts : ");
            e.printStackTrace();
            return;
        }
        scripts.put(name, id);
    }

	private void loadScripts(){
        scriptingEngine = new PythonGameScriptingEngine();
        scripts = new HashMap<String, Integer>();
        loadScript("mage_behavior");
        loadScript("warrior_behavior");
        loadScript("thief_behavior");
        loadScript("mage_stat");
        loadScript("warrior_stat");
        loadScript("thief_stat");
    }

	@Override
    public void initialise() {

        //loadScripts();

        viewport = new FitViewport(GAME_RENDER_X, GAME_RENDER_Y);

        collisions = new RegionQuadTree<>(10, 0f, 0f, GAME_RENDER_X, GAME_RENDER_Y);

        fightersByID = new HashMap<>();
        Fighter f1 = new Warrior(1, "sprite.png");
        Fighter f2 = new Warrior(2, "sprite.png");

        f1.setOpponent(f2);
        f2.setOpponent(f1);
        f2.moveBy(400f, 200f);
        fightersByID.put(1, f1);
        fightersByID.put(2, f2);

    }
    
    @Override
    public void update(float delta) {
        //scriptingEngine.update(delta);
        fightersByID.values().forEach(fighter -> fighter.update(delta));
    }
    
    @Override
    public void interpolate(float alpha) {
        fightersByID.values().forEach(fighter -> fighter.interpolate(alpha));
    }
    
    @Override
    public void render(Graphics g) {
        viewport.apply(g);
        fightersByID.values().forEach(fighter -> fighter.render(g));
    }
}
