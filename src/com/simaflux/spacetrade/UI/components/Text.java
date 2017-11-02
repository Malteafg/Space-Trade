package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.graphics.fontengine.FontType;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Text extends UIComponent {
	
	protected final RenderText text;

	public Text(UIComponent parentComponent, String text, float x, float y, float fontSize, FontType font, float maxLineLength, boolean centered, boolean active) {
		super(parentComponent, x, y, 0, fontSize * 1.3f, active);
		
		this.text = new RenderText(this, text, fontSize, font, maxLineLength, centered);
		
		setPos(x, y);
	}
	
	@Override
	public void setPos(Vector2f pos) {
		if(text == null) return;
		this.pos = new Vector2f(pos.x, pos.y);
		this.glpos = new Vector2f(pos.x / Vars.WIDTH * 2.0f + (text.isCentered() ? 0 : 1.0f), (pos.y / Vars.HEIGHT * 2.0f) * - 1);
	}
	
	@Override
	public void setPos(float x, float y) {
		if(text == null) return;
		this.pos = new Vector2f(x, y);
		this.glpos = new Vector2f(x / Vars.WIDTH * 2.0f + (text.isCentered() ? 0 : 1.0f), (y / Vars.HEIGHT * 2.0f) * - 1);
	}

	@Override
	public void update() {
		
	}
	
	public RenderText text() {
		return text;
	}

	@Override
	public void click() {}
	@Override
	public void release() {}
	@Override
	public void enter() {}
	@Override
	public void exit() {}

}
