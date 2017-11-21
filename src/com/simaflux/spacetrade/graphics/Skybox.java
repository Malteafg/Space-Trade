package com.simaflux.spacetrade.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.graphics.models.RawModel;
import com.simaflux.spacetrade.loader.Memory;
import com.simaflux.spacetrade.loader.ModelLoader;
import com.simaflux.spacetrade.loader.TextureLoader;

public class Skybox {
	
	private static final float SIZE = 500f;

	private static final float[] VERTICES = {        
	    -SIZE,  SIZE, -SIZE,
	    -SIZE, -SIZE, -SIZE,
	    SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	
	    -SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,
	
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	
	    -SIZE, -SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,
	
	    -SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE, -SIZE,
	
	    -SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE
	};
	
	private static String[] TEXTURE_FILES = {"right", "left", "up", "down", "back", "front"};
	
	private RawModel cube;
	
	private int texture;
	
	public Skybox() {
		cube = ModelLoader.loadToVAO(VERTICES, 3);
		texture = TextureLoader.loadCubeMapTexture(TEXTURE_FILES);
	}
	
	public void render() {
		Memory.getShader("skybox").start();
		Memory.getShader("skybox").loadUniformMat4f("viewMatrix", GameHandler.game.camera.getViewMatrix());
		
		glBindVertexArray(cube.getVaoID());
		glEnableVertexAttribArray(0);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_CUBE_MAP, texture);
		glDrawArrays(GL_TRIANGLES, 0, cube.getVertexCount());
		glDisable(0);
		glBindVertexArray(0);
		
		Memory.getShader("skybox").stop();
	}
	
}
