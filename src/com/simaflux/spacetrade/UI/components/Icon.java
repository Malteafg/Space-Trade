package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderIcon;

public class Icon extends UIComponent {
	
	private final RenderIcon icon;

	public Icon(UIComponent parentComponent, float x, float y, float width, float height, RenderIcon icon) {
		super(parentComponent, x, y, width, height);
		
		this.icon = icon;
	}

	@Override
	public void update() {
		
	}
	
	public RenderIcon icon() {
		return icon;
	}

}
