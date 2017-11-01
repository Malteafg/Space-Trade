package com.simaflux.spacetrade.UI.renderComponents;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.utils.math.Vector2f;

public abstract class RenderComponent {
	
	private UIComponent component;
	
	public RenderComponent(UIComponent component) {
		this.component = component;
	}
	
	public UIComponent getComponent() {
		return component;
	}
	
	public Vector2f getGlpos() {
		return component.getGlpos();
	}
	
	public Vector2f getGlsize() {
		return component.getGlsize();
	}
	
	public Vector2f getPos() {
		return component.getPos();
	}
	
	public Vector2f getSize() {
		return component.getSize();
	}
	
	public boolean isEnabled() {
		return component.isEnabled();
	}
	
	public int getLayer() {
		return component.getParentAmount();
	}
	
	public abstract void dispose();

}
