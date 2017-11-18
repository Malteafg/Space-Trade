package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.utils.Vars;

public class BuildingInfo extends UIPanel {
	
	private Text header;

	public BuildingInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 460, 775, 300, 300, active);
		addCross(50);
		
		header = new Text(this, "Building", 5, 5, 20, Vars.SERIF, 1, false, true);
	}
	
	@Override
	public void receive(String message) {
		header.text().setText(message);
	}

	@Override
	public void update() {
		
	}

}
