package com.scep.genetics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.miniscript.core.GameScriptingEngine;
import org.mini2Dx.miniscript.core.ScriptBindings;
import org.mini2Dx.miniscript.core.exception.InsufficientCompilersException;
import org.mini2Dx.miniscript.python.PythonGameScriptingEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WarriorsGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.scep.geneticswarriors";
	private GameScriptingEngine scriptingEngine;
    private HashMap<String, Integer> scripts;

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


    }
    
    @Override
    public void update(float delta) {
        scriptingEngine.update(delta);
    }
    
    @Override
    public void interpolate(float alpha) {
    }
    
    @Override
    public void render(Graphics g) {
    }
}
