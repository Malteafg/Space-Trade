package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.input.Input;
import com.simaflux.spacetrade.input.MouseListener;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Button extends UIComponent implements MouseListener {

	private Text text;
	private Icon icon;
	private Vector3f color;
	private boolean down;

	public Button(UIComponent parentComponent, float x, float y, float width, float height) {
		super(parentComponent, x, y, width, height);
		
		text = null;
		icon = null;
		color = Vars.STANDARD_BLUE.copy();
		
		down = false;
		
		setBox(new RenderBox(this));
		
		Input.addButton(this);
	}

	@Override
	public void update() {}
	
	public Text getText() {
		return text;
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
	public void click() {}
	@Override
	public void release() {}
	@Override
	public void enter() {}
	@Override
	public void exit() {}

}
