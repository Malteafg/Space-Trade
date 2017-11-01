package com.simaflux.spacetrade.UI.panels;

import java.text.DecimalFormat;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class TopBar extends UIPanel {

	public TopBar(UIComponent parentComponent) {
		super(parentComponent, 5, 5, Vars.WIDTH - 10, 30);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		new Text(this, "", 5, 5, 20, Vars.SERIF, 1, false) {
			@Override
			public void update() {
				text.setText(df.format(GameHandler.game.getUser().getCash()));
			}
		};
	}

	@Override
	public void update() {}

}
