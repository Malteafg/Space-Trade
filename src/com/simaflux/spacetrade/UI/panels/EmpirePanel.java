package com.simaflux.spacetrade.UI.panels;

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
		super(parentComponent, Vars.WIDTH / 4, Vars.HEIGHT / 4 - 100, Vars.WIDTH / 2, Vars.HEIGHT / 2 + 200, active);
		
		empire = null;
		
		addCross(40);
		
		name = new Text(this, "", 10, 10, 40, Vars.SERIF, 1, false, true);
		people = new Text(this, "People", 10, 70, 18, Vars.SERIF, 1, false, true);
		
		contract = new Button(this, this.pos.x - 200, 150, 180, 50, true) {
			@Override
			public void click() {
				
			}
		};
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
