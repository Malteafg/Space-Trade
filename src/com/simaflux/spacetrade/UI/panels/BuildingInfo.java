package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class BuildingInfo extends UIPanel {
	
	private Text header;
	
	private Text noPower, noResource;

	public BuildingInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 460, 775, 300, 300, active);
		addCross(50);
		
		header = new Text(this, "Building", 5, 5, 20, Vars.SERIF, 1, false, true);
		
		noPower = new Text(this, "P", 160, 5, 15, Vars.SERIF, 1, false, false, 15);
		noResource = new Text(this, "R", 180, 5, 15, Vars.SERIF, 1, false, false, 15);
		
		noPower.setBox(new RenderBox(noPower));
		noResource.setBox(new RenderBox(noResource));
	}
	
	@Override
	public void receive(String message) {
		header.text().setText(message);
	}

	@Override
	public void update() {
		if(GameHandler.game.getUser().getBuilding(GameHandler.game.getSelectedPlanet(), header.text().getTextString()).hasEnoughPower()) {
			noPower.disable();
		} else noPower.enable();
		if(GameHandler.game.getUser().getBuilding(GameHandler.game.getSelectedPlanet(), header.text().getTextString()).hasEnoughResources()) {
			noResource.disable();
		} else noResource.enable();
	}

}
