package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.utils.Vars;

public class ResourceTab extends UIPanel {

	public ResourceTab(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 40, 200, Vars.HEIGHT - 350, active);
	}

	@Override
	public void update() {}

}
