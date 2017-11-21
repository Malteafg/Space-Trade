package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class BuildingInfo extends UIPanel {
	
	private Text header;
	private Text noPower, noResource, input[], output[], powerConsumtion;

	public BuildingInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 460, 775, 300, 300, active);
		addCross(50);
		
		header = new Text(this, "Building", 5, 5, 20, Vars.SERIF, 1, false, true);
		
		noPower = new Text(this, "P", 180, 130, 15, Vars.SERIF, 1, false, false, 15);
		noResource = new Text(this, "R", 200, 130, 15, Vars.SERIF, 1, false, false, 15);
		
		noPower.setBox(new RenderBox(noPower));
		noResource.setBox(new RenderBox(noResource));
		
		input = new Text[6];
		output = new Text[3];
		
		for(int i = 0; i < input.length; i++) {
			input[i] = new Text(this, "", 20, 70 + 25 * i, 15, Vars.SERIF, 1, false, true);
		}
		
		for(int i = 0; i < output.length; i++) {
			output[i] = new Text(this, "", 170, 70 + 25 * i, 15, Vars.SERIF, 1, false, true);
		}
		
		input[0].text().setText("Consumed:");
		output[0].text().setText("Produced:");
		
		powerConsumtion = new Text(this, "", 50, 250, 15, Vars.SERIF, 1, false, true);
		
	}
	
	@Override
	public void enable() {
		super.enable();
		
		int power = GameHandler.game.getSelectedBuilding().getPower();
		
		powerConsumtion.text().setText((power < 0 ? " Power Consumed: " : " Power Produced: ") + (power < 0 ? -1 * power : power));
		
		for(int i = 0; i < GameHandler.game.getSelectedBuilding().getConsumed().length; i++) {
			input[i + 1].text().setText(GameHandler.game.getSelectedBuilding().getConsumed()[i].getName() + " (" + GameHandler.game.getSelectedBuilding().getConsumed()[i].getAmount() + ")");
		}
		
		for(int i = 0; i < GameHandler.game.getSelectedBuilding().getProduced().length; i++) {
			output[i + 1].text().setText(GameHandler.game.getSelectedBuilding().getProduced()[i].getName() + " (" + GameHandler.game.getSelectedBuilding().getProduced()[i].getAmount() + ")");
		}
	}
	
	@Override
	public void receive(String message) {
		header.text().setText(message);
	}

	@Override
	public void update() {
		if(GameHandler.game.getSelectedBuilding().hasEnoughPower()) {
			noPower.disable();
		} else noPower.enable();
		if(GameHandler.game.getSelectedBuilding().hasEnoughResources()) {
			noResource.disable();
		} else noResource.enable();
	}
	
	@Override
	public void addCross(int s) {
		UIPanel p = this;
		Button b = new Button(p, this.size.x - s - 5, 5, s, s, true) {
			@Override
			public void click() {
				Interface.disablePanel(p);
				GameHandler.game.setSelectedBuilding(null);
			}
		};
		b.addText(new Text(b, "X", s / 2, 0, s * 0.7f, Vars.SERIF, 1, true, true));
	}

}
