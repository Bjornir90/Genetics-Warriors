package com.scep.genetics.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.scep.genetics.WarriorsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(WarriorsGame.GAME_IDENTIFIER);
		config.vSyncEnabled = true;
		config.title = "Genetics Warriors";
		config.width = 1920;
		config.height = 1080;
		new DesktopMini2DxGame(new WarriorsGame(), config);
	}
}
