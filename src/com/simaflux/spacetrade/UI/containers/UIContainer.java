package com.simaflux.spacetrade.UI.containers;

import com.simaflux.spacetrade.UI.UIComponent;

public class UIContainer extends UIComponent {
	
	private UIComponent[][] components;
	private final float space;

	public UIContainer(UIComponent parentComponent, float x, float y, float width, float height, boolean active, int nx, int ny, float space) {
		super(parentComponent, x, y, width, height, active);
		
		this.components = new UIComponent[nx][ny];
		this.space = space;
	}
	
	public void addComponent(UIComponent c, int nx, int ny) {
		components[nx][ny] = c;
	}

	@Override
	public void update() {
		float l = 0;
		for(int y = 0; y < components[0].length; y++) {
			float maxH = 0;
			
			for(int x = 0; x < components.length; x++) {
				if(components[x][y] != null) {
					if(components[x][y].isEnabled()) {
						components[x][y].setPos(components[x][y].getRelPos().x, l);
						if(components[x][y].getSize().y > maxH) maxH = components[x][y].getSize().y;
					}
				}
			}
			l += maxH + (maxH == 0 ? 0 : space);
		}
	}

}
