package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.AmountPicker;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.containers.UIContainer;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class PlanetInfo extends UIPanel {
	
	private UIContainer container;
	
	private Text name, powerConsumption, powerProduction, planetSize;
	
	private AmountPicker claimPicker;
	private Button claim;
	
	private Text[] resources;
	
	public PlanetInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 775, 450, 300, active);
		
		container = new UIContainer(this, 5, 5, size.x - 5, size.y - 300, true, 1, 8, 10);
		
		name = new Text(container, "", 5, 0, 20, Vars.SERIF, 1, false, true);
		powerConsumption = new Text(container, "", 5, 0, 15, Vars.SERIF, 1, false, true);
		powerProduction = new Text(container, "", 5, 0, 15, Vars.SERIF, 1, false, true);
		
		planetSize = new Text(this, "", 5, this.size.y - 70, 16, Vars.SERIF, 1, false, true);
		claimPicker = new AmountPicker(this, 5, this.size.y - 40, 70, 30, true);
		claim = new Button(this, 90, this.size.y - 40, 70, 30, true) {
			@Override
			public void click() {
				GameHandler.game.getUser().claimLand(claimPicker.getAmount());
				updateSize();
			}
		};
		claim.addText(new Text(claim, "Claim", claim.getSize().x / 2, 2, 15, Vars.SERIF, 1, true, true));
		
		container.addComponent(name, 0, 0);
		container.addComponent(powerConsumption, 0, 1);
		container.addComponent(powerProduction, 0, 2);
		
		resources = new Text[5];
		for(int i = 0; i < resources.length; i++) {
			resources[i] = new Text(container, "", 5, 0, 15, Vars.SERIF, 1, false, false);
			container.addComponent(resources[i], 0, i + 3);
		}
	}
	
	@Override
	public void enable() {
		super.enable();
		
		name.text().setText(GameHandler.game.getSelectedPlanet().getName() + " (" + GameHandler.game.getSelectedPlanet().getPlanetType() + ")");
		updateSize();
		
		for(int i = 0; i < GameHandler.game.getSelectedPlanet().getResources().size(); i++) {
			resources[i].enable();
			resources[i].text().setText(GameHandler.game.getSelectedPlanet().getResources().get(i).getName());
		}
		
		container.pack();
	}
	
	private void updateSize() {
		planetSize.text().setText("Size: " + GameHandler.game.getSelectedPlanet().getSize() + " (" + GameHandler.game.getSelectedPlanet().cm.getPlayerOwned(GameHandler.game.getUser()) + ")");
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
		powerProduction.text().setText("Power Production: " + GameHandler.game.getUser().getPowerProductionOfPlanet(GameHandler.game.getSelectedPlanet()));
		powerConsumption.text().setText("Power Consumption: " + GameHandler.game.getUser().getPowerConsumptionOfPlanet(GameHandler.game.getSelectedPlanet()));
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
