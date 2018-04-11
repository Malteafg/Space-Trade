package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class EmpirePanel extends UIPanel {
	
	private Empire empire;
	private Text name, people;
	private Button contract;

	public EmpirePanel(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 40, 300, Vars.HEIGHT - 350, active);
		
		empire = null;
		Runnable r;
		addCross(40, () -> {Interface.enablePanel("ResourceTab");});
		
		name = new Text(this, "", 10, 10, 20, Vars.SERIF, 1, false, true);
		people = new Text(this, "People", 10, 70, 16, Vars.SERIF, 1, false, true);
		
		contract = new Button(this, 20, 150, 180, 50, true) {
			@Override
			public void click() {
				
			}
		};
		contract.addText(new Text(contract, "Propose Contract", 5, 5, 14, Vars.SERIF, 1, false, true));
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void receive(String message) {
		empire = GameHandler.game.getEmpire(message);
		name.text().setText(message);
		people.text().setText("People: " + empire.getPeople());
	}
	
}
