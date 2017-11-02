package com.simaflux.spacetrade.UI;

import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.input.Input;
import com.simaflux.spacetrade.input.MouseListener;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector2f;

public abstract class UIComponent implements MouseListener {
	
	protected UIComponent parent;
	protected boolean active;
	
	protected Vector2f glpos, glsize;
	protected Vector2f pos, size;
	
	protected RenderBox box;

	protected UIComponent(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		this.parent = parentComponent;

		setSize(width, height);
		setPos(x, y);
		
		this.active = active;
		
		box = null;
		
		Interface.components.add(this);
		Input.addButton(this);
	}
	
	public abstract void update();
	
	public boolean isEnabled() {
		if(parent == null) return active;
		return parent.isEnabled() && active;
	}

	public void enable() {
		active = true;
	}
	
	public void disable() {
		active = false;
	}

	public Vector2f getGlpos() {
		if(parent == null) return glpos;
		return glpos.add(parent.getGlpos());
	}

	public Vector2f getGlsize() {
		return glsize;
	}

	public Vector2f getPos() {
		if(parent == null) return pos;
		return pos.add(parent.getPos());
	}
	
	public Vector2f getRelPos() {
		return pos;
	}

	public Vector2f getSize() {
		return size;
	}
	
	public void setPos(Vector2f pos) {
		this.pos = new Vector2f(pos.x, pos.y);
		this.glpos = new Vector2f(pos.x / Vars.WIDTH * 2.0f, (pos.y / Vars.HEIGHT * 2.0f) * - 1);
	}
	
	public void setPos(float x, float y) {
		this.pos = new Vector2f(x, y);
		this.glpos = new Vector2f(x / Vars.WIDTH * 2.0f, (y / Vars.HEIGHT * 2.0f) * - 1);
	}
	
	public void setSize(Vector2f size) {
		this.size = new Vector2f(size.x, size.y);
		this.glsize = new Vector2f(size.x / Vars.WIDTH * 2, size.y / Vars.HEIGHT * 2);
	}
	
	public void setSize(float width, float height) {
		this.size = new Vector2f(width, height);
		this.glsize = new Vector2f(width / Vars.WIDTH * 2, height / Vars.HEIGHT * 2);
	}
	
	public RenderBox getBox() {
		return box;
	}
	
	public void setBox(RenderBox box) {
		this.box = box;
	}
	
	public int getParentAmount() {
		return parent.getParentAmount() + 1;
	}
	
	public void makeClickable() {
		Input.addButton(this);
	}
	
}
