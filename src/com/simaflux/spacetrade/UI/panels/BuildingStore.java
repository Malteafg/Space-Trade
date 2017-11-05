package com.simaflux.spacetrade.UI.panels;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.containers.UIContainer;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.utils.Vars;

public class BuildingStore extends UIPanel {
	
	private UIContainer container;
	
	private List<BuildingContainer> buildings;

	public BuildingStore(UIComponent parentComponent, boolean active) {
		super(parentComponent, Vars.WIDTH - 205, 40, 200, Vars.HEIGHT - 350, active);
		
		container = new UIContainer(this, 0, 0, size.x, size.y, true, 1, GameLoader.buildingNames.length, 8) {
			@Override
			public void click() {
				for(BuildingContainer c : buildings) {
					c.close();
					c.pack();
				}
			}
		};
		buildings = new ArrayList<>();
		
		for(int i = 0; i < GameLoader.buildingNames.length; i++) {
			buildings.add(new BuildingContainer(container, GameLoader.buildingNames[i], GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNcost().length + 2));
			container.addComponent(buildings.get(i), 0, i);
		}
	}
	
	private class BuildingContainer extends UIContainer {
		
		private Text name, cash;
		private Text[] process;
		private Button[] resourceCost;
		private Button buy;
		
		private String bname;

		public BuildingContainer(UIComponent parentComponent, String bname, int ny) {
			super(parentComponent, 5, 0, parentComponent.getSize().x - 10, parentComponent.getGlsize().y, true, 2, ny, 5);
			
			this.bname = bname;
			
			name = new Text(this, bname, 5, 0, 12, Vars.SERIF, 1, false, true);
			cash = new Text(this, "Cash", 95, 0, 12, Vars.SERIF, 1, false, true);
			
			addComponent(name, 0, 0);
			addComponent(cash, 1, 0);
			
			process = new Text[ny - 1];
			for(int i = 0; i < process.length; i++) {
				process[i] = new Text(this, "", 5, 0, 12, Vars.SERIF, 1, false, false);
				addComponent(process[i], 0, i + 1);
			}
			
			resourceCost = new Button[ny - 2];
			for(int i = 0; i < resourceCost.length; i++) {
				resourceCost[i] = new Button(this, 95, 0, 70, 30, false);
				resourceCost[i].addText(new Text(resourceCost[i], 
						GameLoader.getBuildingInfo(bname).getNcost()[i] + ": " + GameLoader.getBuildingInfo(bname).getVcost()[i], 
						resourceCost[i].getSize().x / 2, 5, 12, Vars.SERIF, 1, true, true));
				addComponent(resourceCost[i], 1, i + 1);
			}
			
			buy = new Button(this, 95, 0, 70, 30, false) {
				@Override
				public void click() {
					GameHandler.game.getUser().buyBuilding(bname, GameHandler.game.getSelectedPlanet());
				}
			};
			buy.addText(new Text(buy, "Buy", buy.getSize().x / 2, 5, 12, Vars.SERIF, 1, true, true));
			buy.text().setColor(1, 0, 0);
			addComponent(buy, 1, ny - 1);
			
			makeClickable();
		}
		
		public void close() {
			buy.disable();
			for(Text t : process) {
				t.disable();
			}
			for(Button b : resourceCost) {
				b.disable();
			}
		}
		
		@Override
		public void update() {
			cash.text().setText("Price: " + Vars.df2.format(GameHandler.game.market.getBuildingCost(bname)));
		}
		
		@Override
		public void click() {
			boolean bo = buy.isEnabled();
			container.click();
			if(!bo) {
				buy.enable();
				for(Text t : process) {
					t.enable();
				}
				for(Button b : resourceCost) {
					b.enable();
				}
			} else {
				buy.disable();
				for(Text t : process) {
					t.disable();
				}
				for(Button b : resourceCost) {
					b.disable();
				}
			}
			pack();
			container.pack();
		}
		
	}

	@Override
	public void update() {}

	@Override
	public void click() {}	
	@Override
	public void release() {}
	@Override
	public void enter() {}
	@Override
	public void exit() {}

}
