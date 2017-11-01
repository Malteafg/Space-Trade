package com.simaflux.spacetrade.loader;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.graphics.Shader;
import com.simaflux.spacetrade.graphics.textures.Texture;
import com.simaflux.spacetrade.models.RawModel;

public class Memory {
	
	/*
	 * SHADERS
	 */
	public static Map<String, Shader> shaders = new HashMap<>();
	
	public static Shader getShader(String name) {
		Shader shader = shaders.get(name);
		if(shader == null) System.err.println("Shader isn't loaded: " + name);
		return shader;
	}
	
	/*
	 * MODELS
	 */
	public static Map<String, RawModel> models = new HashMap<>();
	
	public static RawModel getModel(String name) {
		RawModel model = models.get(name);
		if(model == null) System.err.println("Model isn't loaded: " + name);
		return model;
	}
	
	/*
	 * TEXTURES
	 */
	public static Map<String, Texture> textures = new HashMap<>();
	
	public static Texture getTexture(String name) {
		Texture texture = textures.get(name);
		if(texture == null) System.err.println("Texture isn't loaded: " + name);
		return texture;
	}

}
