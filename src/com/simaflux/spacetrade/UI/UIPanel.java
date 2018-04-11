package com.simaflux.spacetrade.UI;

import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.utils.Vars;

public abstract class UIPanel extends UIComponent {

	protected UIPanel(UIComponent parentComponent, float x, float y, float width, float height, boolean active) {
		super(parentComponent, x, y, width, height, active);
		
		setBox(new RenderBox(this));
	}
	
	protected void addCross(int s) {
		addCross(s, null);
	}
	
	protected void addCross(int s, Runnable r) {
		UIPanel p = this;
		Button b = new Button(p, this.size.x - s - 5, 5, s, s, true) {
			@Override
			public void click() {
				Interface.disablePanel(p);
				if(r != null) r.run();
			}
		};
		b.addText(new Text(b, "X", s / 2, 0, s * 0.7f, Vars.SERIF, 1, true, true));
	}
	
	protected void receive(String message) {}
	
}
