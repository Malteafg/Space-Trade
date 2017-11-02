package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Button extends UIComponent {

	private Text text;
	private Icon icon;
	private Vector3f color;
	private boolean down;

	public Button(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		
		text = null;
		icon = null;
		color = Vars.STANDARD_BLUE.copy();
		
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

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
