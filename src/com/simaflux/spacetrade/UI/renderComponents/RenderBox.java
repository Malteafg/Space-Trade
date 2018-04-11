package com.simaflux.spacetrade.UI.renderComponents;

import com.simaflux.spacetrade.UI.LayerManager;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.utils.math.Vector4f;

public class RenderBox extends RenderComponent {
	
	private Vector4f color;
	
	private boolean darker;

	public RenderBox(UIComponent component) {
		super(component);
		
		darker = false;
		
		LayerManager.addBox(this);
	}

	public Vector4f getColor() {
		if(darker) return new Vector4f(color.x * 0.6f, color.y * 0.6f, color.z * 0.6f, color.w);
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
	
	public void darken() {
		darker = true;
	}
	
	public void lighten() {
		darker = false;
	}
	
	public void dispose() {
		
	}
	
}
