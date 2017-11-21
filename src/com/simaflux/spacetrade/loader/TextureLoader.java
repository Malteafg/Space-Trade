package com.simaflux.spacetrade.loader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL30.*;

import com.simaflux.spacetrade.graphics.textures.Texture;

public class TextureLoader {

	public static int loadTexture(String fileName) {
		Texture texture = null;
		try {
			texture = new Texture("resources/" + fileName + ".png");
			glGenerateMipmap(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);		
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_LOD_BIAS, -0.4f);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Tried to load texture " + fileName + ".png, didn't work");
			System.exit(-1);
		}
		int textureID = texture.getTextureID();
		ModelLoader.textures.add(textureID);
		Memory.textures.put(fileName, texture);
		return textureID;
	}

}
