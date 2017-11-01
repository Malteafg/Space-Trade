package com.simaflux.spacetrade.graphics.textures;

public class ModelTexture {

	private int textureID;
	
	private boolean transparent;
	
	public ModelTexture(int id) {
		textureID = id;
	}
	
	public int getID() {
		return textureID;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

}
