package com.simaflux.spacetrade.UI.components;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector4f;

public class AmountPicker extends UIComponent {

	private Button button1, button2;
	private int amount;
	private Text text;
	
	public AmountPicker(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		amount = 0;
		
		text = new Text(this, "", width / 2.0f, 0, 15, Vars.SERIF, 1, true, true);
		
		button1 = new Button(this, 0, 0, width / 4.0f, height, true) {
			@Override
			public void click() {
				amount--;
			}
		};
		
		button1.addText(new Text(button1, "-", button1.getSize().x / 2.0f, 0, 15, Vars.SERIF, 1, true, true));
		
		button2 = new Button(this, width / 4.0f * 3.0f, 0, width / 4.0f, height, true) {
			@Override
			public void click() {
				amount++;
			}
		};
		button2.addText(new Text(button2, "+", button1.getSize().x / 2.0f, 0, 15, Vars.SERIF, 1, true, true));
		
		setBox(new RenderBox(this));
		box.setColor(new Vector4f(0.9f, 0.9f, 1.0f, 0.9f));
	}
	
	@Override
	public void update() {
		text.text().setText(Integer.toString(amount));
	}

	public int getAmount() {
		return amount;
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
