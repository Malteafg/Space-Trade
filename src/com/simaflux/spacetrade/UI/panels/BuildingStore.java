package com.simaflux.spacetrade.UI.panels;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.containers.UIContainer;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector4f;

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
			int consumedLength = (GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNconsumed().length != 0) ? GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNconsumed().length + 1 : 0;
			int producedLength = (GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNproduced().length != 0) ? GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNproduced().length + 1 : 0;
			
			buildings.add(new BuildingContainer(container, GameLoader.buildingNames[i], GameLoader.getBuildingInfo(GameLoader.buildingNames[i]).getNcost().length + 1, consumedLength, producedLength));
			container.addComponent(buildings.get(i), 0, i);
		}
	}

	@Override
	public void enable() {
		super.enable();
		setContainers();
	}
	
	private void setContainers() {
		for(int i = 0; i < buildings.size(); i++) {
			if(GameHandler.game.getUser().hasBuildingOnPlanet(buildings.get(i).getName(), GameHandler.game.getSelectedPlanet())) {
				buildings.get(i).disable();
			} else {
				buildings.get(i).enable();
			}
		}
		container.pack();
	}
	
	private class BuildingContainer extends UIContainer {
		
		private Text name, cash;
		private Text[] process;
		private Button[] resourceCost;
		private Button buy;
		private boolean locked;
		
		private String bname;

		public BuildingContainer(UIComponent parentComponent, String bname, int ny, int cl, int pl) {
			super(parentComponent, 5, 0, parentComponent.getSize().x - 10, parentComponent.getGlsize().y, true, 2, Math.max(cl + pl, ny) + 1, 8);
			
			this.bname = bname;
			
			name = new Text(this, bname, 5, 0, 12, Vars.SERIF, 1, false, true);
			cash = new Text(this, "Cash", 125, 0, 12, Vars.SERIF, 1, false, true);
			
			addComponent(name, 0, 0);
			addComponent(cash, 1, 0);
			
			process = new Text[cl + pl];
			for(int i = 0; i < cl; i++) {
				if(i == 0) {
					process[i] = new Text(this, "Consumed:", 5, 0, 12, Vars.SERIF, 1, false, false);
				} else {
					process[i] = new Text(this, GameLoader.getBuildingInfo(bname).getNconsumed()[i - 1] + ": " + GameLoader.getBuildingInfo(bname).getVconsumed()[i - 1], 5, 0, 12, Vars.SERIF, 1, false, false);
				}
				addComponent(process[i], 0, i + 1);
			}
			for(int i = 0; i < pl; i++) {
				if(i == 0) {
					process[i + cl] = new Text(this, "Produced:", 5, 0, 12, Vars.SERIF, 1, false, false);
				} else {
					process[i + cl] = new Text(this, GameLoader.getBuildingInfo(bname).getNproduced()[i - 1] + ": " + GameLoader.getBuildingInfo(bname).getVproduced()[i - 1], 5, 0, 12, Vars.SERIF, 1, false, false);
				}
				addComponent(process[i + cl], 0, i + 1 + cl);
			}
			
			resourceCost = new Button[ny - 2];
			for(int i = 0; i < resourceCost.length; i++) {
				resourceCost[i] = new Button(this, 100, 0, 85, 20, false);
				resourceCost[i].addText(new Text(resourceCost[i], 
						GameLoader.getBuildingInfo(bname).getNcost()[i] + ": " + GameLoader.getBuildingInfo(bname).getVcost()[i], 
						resourceCost[i].getSize().x / 2, 1, 12, Vars.SERIF, 1, true, true));
				addComponent(resourceCost[i], 1, i + 1);
			}
			
			buy = new Button(this, 100, 0, 85, 20, false) {
				@Override
				public void click() {
					if(!locked) {
						GameHandler.game.getUser().buyBuilding(bname, GameHandler.game.getSelectedPlanet());
						Interface.sendMessage("PlanetInfo", "building");
						setContainers();
					}
				}
			};
			buy.addText(new Text(buy, "Buy", buy.getSize().x / 2, 1, 12, Vars.SERIF, 1, true, true));
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
			double price = GameHandler.game.market.getBuildingCost(bname);
			cash.text().setText(Vars.df2.format(price) + "$");
			locked = !GameHandler.game.getUser().hasCash(price) || !GameHandler.game.getUser().planetHasResource(GameLoader.buildings.get(bname).getNaturalResource(), GameHandler.game.getSelectedPlanet());
			
			if(locked) buy.getBox().setColor(new Vector4f(Vars.NOT_POSSIBLE_RED, 0.75f));
			else buy.getBox().setColor(new Vector4f(Vars.STANDARD_BLUE, 0.75f));
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

		public String getName() {
			return bname;
		}
		
	}

	@Override
	public void update() {}

}
