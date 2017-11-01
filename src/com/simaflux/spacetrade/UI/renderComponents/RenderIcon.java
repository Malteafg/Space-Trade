package com.simaflux.spacetrade.UI.renderComponents;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.graphics.textures.Texture;

public class RenderIcon extends RenderComponent {

	private Texture texture;

	public RenderIcon(UIComponent component) {
		super(component);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void dispose() {
		
	}

}
