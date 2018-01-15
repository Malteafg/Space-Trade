package com.simaflux.spacetrade.loader;

import com.simaflux.spacetrade.graphics.Shader;
import com.simaflux.spacetrade.utils.Vars;

public class Loader {

	public static void load() {
		loadAllShaders();
		loadAllModels();
		loadAllTextures();
		
		GameLoader.loadResources();
		GameLoader.loadBuildings();
	}
	
	private static void loadAllShaders() {
		Memory.shaders.put("UIBox", new Shader("ui/uibox.vert", "ui/uibox.frag", new int[] {0}));
		
		Memory.shaders.put("planet", new Shader("planet.vert", "planet.frag", new int[] {0, 2}));
		Memory.shaders.get("planet").start();
		Memory.shaders.get("planet").loadUniformMat4f("projectionMatrix", Vars.perspectiveProjection);
		Memory.shaders.get("planet").stop();
		
		Memory.shaders.put("star", new Shader("star.vert", "star.frag", new int[] {0, 2}));
		Memory.shaders.get("star").start();
		Memory.shaders.get("star").loadUniformMat4f("projectionMatrix", Vars.perspectiveProjection);
		Memory.shaders.get("star").stop();
		
		Memory.shaders.put("building", new Shader("building.vert", "building.frag", new int[] {0, 2}));
		Memory.shaders.get("building").start();
		Memory.shaders.get("building").loadUniformMat4f("projectionMatrix", Vars.perspectiveProjection);
		Memory.shaders.get("building").stop();

		Memory.shaders.put("font", new Shader("ui/font.vert", "ui/font.frag", new int[] {0, 1}));

		Memory.shaders.put("icon", new Shader("ui/icon.vert", "ui/icon.frag", new int[] {0}));
	}
	
	public static void loadAllModels() {
		OBJLoader.loadObjModel("models", "sphere");
		OBJLoader.loadObjModel("models", "factory");
	}
	
	public static void loadAllTextures() {
		TextureLoader.loadTexture("logo");
	}

}
