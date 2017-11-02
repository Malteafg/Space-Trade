package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.containers.UIContainer;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class PlanetInfo extends UIPanel {
	
	private UIContainer container;
	
	private Text name, power;
	
	private Text[] resources;
	
	public PlanetInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 775, 450, 300, active);
		
		container = new UIContainer(this, 5, 5, size.x - 5, size.y - 5, true, 1, 7, 10);
		
		name = new Text(container, "", 5, 0, 20, Vars.SERIF, 1, false, true);
		power = new Text(container, "", 5, 0, 15, Vars.SERIF, 1, false, true);
		
		container.addComponent(name, 0, 0);
		container.addComponent(power, 0, 1);
		
		resources = new Text[5];
		for(int i = 0; i < resources.length; i++) {
			resources[i] = new Text(container, "", 5, 0, 15, Vars.SERIF, 1, false, false);
			container.addComponent(resources[i], 0, 2 + i);
		}
	}
	
	@Override
	public void enable() {
		super.enable();
		
		name.text().setText(GameHandler.game.getSelectedPlanet().getName() + " (" + GameHandler.game.getSelectedPlanet().getPlanetType() + ")");
		power.text().setText("POWER");
		
		for(int i = 0; i < GameHandler.game.getSelectedPlanet().getResources().size(); i++) {
			resources[i].enable();
			resources[i].text().setText(GameHandler.game.getSelectedPlanet().getResources().get(i).getName());
		}
		
		container.pack();
	}
	
	@Override
	public void disable() {
		super.disable();
		
		for(int i = 0; i < resources.length; i++) {
			resources[i].disable();
		}
	}
	
	@Override
	public void update() {
		
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
