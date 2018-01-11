package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class SystemInfo extends UIPanel{

	private Text name;
	
	public SystemInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 775, 450, 300, active);
		
		name = new Text(this, "", 5, 0, 20, Vars.SERIF, 1, false, true);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void enable() {
		super.enable();
		name.text().setText(GameHandler.game.getSelectedSystem().getName() + " " +
				((GameHandler.game.getSelectedSystem().getEmpire() != null) ? "(" + GameHandler.game.getSelectedSystem().getEmpire().getName() + ")": ""));
	}
				
}
