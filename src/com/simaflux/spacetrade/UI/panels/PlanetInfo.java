package com.simaflux.spacetrade.UI.panels;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.objects.space.Planet;

public class PlanetInfo extends UIPanel {
	
	private RenderText name;
	private List<RenderText> resources;
	
	public PlanetInfo(UIComponent parentComponent) {
		super(parentComponent, 5, 775, 450, 300);
		
//		name = new RenderText(this, this, "", 30, Vars.SERIF, size.x / 2f, 5, 1, true, layer + 1);
		resources = new ArrayList<>();
	}
	
	@Override
	public void enable() {
		super.enable();
		
		Planet planet = GameHandler.game.getSelectedPlanet();
		
//		for(RenderText t : resources) {
////			ComponentMaster.removeText(t);
//		}
		
		resources.clear();
		
		name.setText(planet.getName() + "  (" + planet.getPlanetType() + ")");

		for(int i = 0; i < planet.getResources().size(); i++) {
//			resources.add(new RenderText(this, this, planet.getResources().get(i).name, 15, Vars.SERIF, 5, 80 + 30 * i, 1, false, layer + 1));
		}
		
	}

	@Override
	public void update() {}

}
