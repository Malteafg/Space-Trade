package com.simaflux.spacetrade.UI;

import com.simaflux.spacetrade.UI.renderComponents.RenderBox;

public abstract class UIPanel extends UIComponent {

	protected UIPanel(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		
		setBox(new RenderBox(this));
	}
	
}
