package com.simaflux.spacetrade.UI;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.UI.panels.BuildingInfo;
import com.simaflux.spacetrade.UI.panels.BuildingStore;
import com.simaflux.spacetrade.UI.panels.MainMenu;
import com.simaflux.spacetrade.UI.panels.PauseMenu;
import com.simaflux.spacetrade.UI.panels.PlanetInfo;
import com.simaflux.spacetrade.UI.panels.ResourceTab;
import com.simaflux.spacetrade.UI.panels.TopBar;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class UIScreen extends UIComponent {
	
	public Map<String, UIPanel> panels = new HashMap<>();

	public UIScreen() {
		super(null, 0, 0, Vars.WIDTH, Vars.HEIGHT, true);
		
		glpos = new Vector2f(-1, 1);
		
		panels.put("ResourceTab", new ResourceTab(this, false));
		panels.put("BuildingStore", new BuildingStore(this, false));
		panels.put("TopBar", new TopBar(this, false));
		
		panels.put("PlanetInfo", new PlanetInfo(this, false));
		panels.put("BuildingInfo", new BuildingInfo(this, false));
		
		panels.put("MainMenu", new MainMenu(this, true));
		panels.put("PauseMenu", new PauseMenu(this, false));
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
