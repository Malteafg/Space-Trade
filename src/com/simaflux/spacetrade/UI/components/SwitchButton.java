package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;

public class SwitchButton extends UIComponent {
	
	private Button button1, button2;
	private int state;

	public SwitchButton(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		
		button1 = new Button(this, x, y, width / 2, height, true) {
			@Override
			public void click() {
				switch(state) {
				case -1:
					button1.setDown(false);
					state = 0;
					break;
				case 0:
					button1.setDown(true);
					state = -1;
					break;
				case 1:
					button1.setDown(true);
					button2.setDown(false);
					state = -1;
					break;
				}
			}
		};
		button2 = new Button(this, x + width / 2, y, width / 2, height, true) {
			@Override
			public void click() {
				switch(state) {
				case -1:
					button2.setDown(true);
					button1.setDown(false);
					state = 1;
					break;
				case 0:
					button2.setDown(true);
					state = 1;
					break;
				case 1:
					button2.setDown(false);
					state = 0;
					break;
				}
			}
		};
	}

	@Override
	public void update() {}

	public Button getButton1() {
		return button1;
	}

	public Button getButton2() {
		return button2;
	}

	public int getState() {
		return state;
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
