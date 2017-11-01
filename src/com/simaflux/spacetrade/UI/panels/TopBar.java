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
		
		new Text(this, "Cash", 5, 5, 20, Vars.SERIF, 1, true, true) {
			@Override
			public void update() {
				text.setText(df.format(GameHandler.game.getUser().getCash()));
			}
		};
		
	}

	@Override
	public void update() {}

}
