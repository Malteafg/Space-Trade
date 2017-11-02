package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.graphics.fontengine.FontType;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Text extends UIComponent {
	
	protected final RenderText text;

	public Text(UIComponent parentComponent, String text, float x, float y, float fontSize, FontType font, float maxLineLength, boolean centered, boolean active) {
		super(parentComponent, x, y, 0, 0, active);
		
		glpos = new Vector2f(glpos.x + (centered ? 0 : 1.0f), glpos.y);
		
		this.text = new RenderText(this, text, fontSize, font, maxLineLength, centered);
		
	}

	@Override
	public void update() {
		
	}
	
	public RenderText text() {
		return text;
	}

}
