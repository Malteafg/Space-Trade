package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class SystemInfo extends UIPanel{

	private Text name;
	private Button empire;
	
	public SystemInfo(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 775, 450, 300, active);
		
		name = new Text(this, "", 5, 0, 20, Vars.SERIF, 1, false, true);
		empire = new Button(this, size.x - 150, 2, 130, 40, true) {
			@Override
			public void click() {
				Interface.sendMessage("EmpirePanel", empire.text().getTextString());
				Interface.enablePanel("EmpirePanel");
			}
		};
		empire.addText(new Text(empire, "", empire.getSize().x / 2, 2, 20, Vars.SERIF, 1, true, true));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void enable() {
		super.enable();
		name.text().setText(GameHandler.game.getSelectedSystem().getName());
		
		if(GameHandler.game.getSelectedSystem().getEmpire() != null) {
			empire.enable();
			empire.text().setText(GameHandler.game.getSelectedSystem().getEmpire().getName());
		} else empire.disable();
	}
				
}
