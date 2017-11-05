package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;

public class Button extends UIComponent {

	private Text text;
	private Icon icon;
	private boolean down;

	public Button(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		
		text = null;
		icon = null;
		
		down = false;
		
		setBox(new RenderBox(this));
		
		makeClickable();
	}

	@Override
	public void update() {}
	
	public Text UIText() {
		return text;
	}
	
	public RenderText text() {
		return text.text();
	}
	
	public void addText(Text text) {
		this.text = text;
	}

	public void setText(String text) {
		this.text.text().setText(text);
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		if(icon != null) icon.icon().dispose();
		this.icon = icon;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		if(down) box.darken();
		if(!down) box.lighten();
		this.down = down;
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
