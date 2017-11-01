package com.simaflux.spacetrade.UI.renderComponents;

import com.simaflux.spacetrade.UI.LayerManager;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class RenderBox extends RenderComponent {
	
	private Vector3f color;

	public RenderBox(UIComponent component) {
		super(component);
		
		LayerManager.addBox(this);
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	public void dispose() {
		
	}
	
}
