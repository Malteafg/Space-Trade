package com.simaflux.spacetrade.game;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.utils.FileHandler;

public abstract class GameHandler {
	
	public static Game game = null;
	
	public static boolean paused;
	
	public static void update() {
		if(game != null && !paused) game.update();
	}
	
	public static void render() {
		if(game != null) game.render();
	}
	
	public static void pause() {
		paused = true;
	}
	
	public static void unpause() {
		paused = false;
	}
	
	public static void startNewGame() {
		GameLoader.loadNames();
		game = new Game();
		
		initGame();
	}
	
	public static void stopGame() {
		game = null;
		if(paused = true) unpause();
		Interface.disablePanel("TopBar");
		Interface.disablePanel("ResourceTab");
	}
	
	public static void save() {
		if(game == null) return;

		FileHandler.writeObjectToFile(game, "game1");
	}
	
	public static void loadGame() {
		game = (Game) FileHandler.readObjectFromFile("game1");
		
		initGame();
	}
	
	private static void initGame() {
		Interface.enablePanel("TopBar");
		Interface.enablePanel("ResourceTab");
	}

}
