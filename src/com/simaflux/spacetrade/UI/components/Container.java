package com.simaflux.spacetrade.UI.components;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.Layout;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Container extends UIComponent {

	private List<UIComponent> components;
	private Layout.LAYOUT layout;

	public Container(UIComponent parentComponent, float x, float y, float width, float height, Layout.LAYOUT layout) {
		super(parentComponent, x, y, width, height);

		this.layout = layout;
		components = new ArrayList<>();
	}

	public void positionize() {

		switch(layout) {

		case VERTICAL:
			float yPos = 0;

			for(UIComponent c : components) {

				if(c.isEnabled()) {
					c.setPos(new Vector2f(0, yPos));
					yPos += c.getSize().y + 10f;
				}
			}

			break;

		case HORIZONTAL:
			break;

		default:
			break;

		}

	}

	@Override
	public void update() {
		positionize();
	}

	public void addComponent(UIComponent c) {
		components.add(c);
	}

}
