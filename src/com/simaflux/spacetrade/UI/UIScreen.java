package com.simaflux.spacetrade.UI;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.UI.panels.BuildingStore;
import com.simaflux.spacetrade.UI.panels.MainMenu;
import com.simaflux.spacetrade.UI.panels.PauseMenu;
import com.simaflux.spacetrade.UI.panels.ResourceTab;
import com.simaflux.spacetrade.UI.panels.TopBar;
import com.simaflux.spacetrade.utils.Vars;

public class UIScreen extends UIComponent {
	
	public Map<String, UIPanel> panels = new HashMap<>();

	public UIScreen() {
		super(null, 0, 0, Vars.WIDTH, Vars.HEIGHT);
		
		panels.put("ResourceTab", new ResourceTab(this));
		panels.put("BuildingStore", new BuildingStore(this));
		panels.put("TopBar", new TopBar(this));
		
//		panels.put("PlanetInfo", new PlanetInfo(this));
		
		panels.put("MainMenu", new MainMenu(this));
		panels.put("PauseMenu", new PauseMenu(this));
		
		enablePanel("MainMenu");
	}
	
	public void enablePanel(String panel) {
		panels.get(panel).enable();
	}
	
	public void disablePanel(String panel) {
		panels.get(panel).disable();
	}
	
	public UIPanel getPanel(String panel) {
		return panels.get(panel);
	}

	@Override
	public void update() {}
	
	@Override
	public int getParentAmount() {
		return 0;
	}

}
