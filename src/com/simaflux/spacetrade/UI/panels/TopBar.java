package com.simaflux.spacetrade.UI.panels;

import java.text.DecimalFormat;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class TopBar extends UIPanel {

	public TopBar(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 5, Vars.WIDTH - 10, 30, active);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		new Text(this, "Cash", 10, 3, 15, Vars.SERIF, 1, false, true) {
			@Override
			public void update() {
				text.setText("Cash: " + df.format(GameHandler.game.getUser().getCash()));
			}
		};
		
		new Text(this, "Date", size.x - 90, 3, 15, Vars.SERIF, 1, false, true) {
			@Override
			public void update() {
				text.setText(GameHandler.game.dm.getDay() + "/" + GameHandler.game.dm.getMonth() + "/" + GameHandler.game.dm.getYear());
			}
		};
	}

	@Override
	public void update() {}

	@Override
	public void click() {}
	@Override
	public void release() {}
	@Override
	public void enter() {}
	@Override
	public void exit() {}

}
