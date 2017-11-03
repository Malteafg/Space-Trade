package com.simaflux.spacetrade.UI.panels;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.SwitchButton;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.containers.UIContainer;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.utils.Vars;

public class ResourceTab extends UIPanel {
	
	private UIContainer container;
	
	private List<ResourceContainer> resources;

	public ResourceTab(UIComponent parentComponent, boolean active) {
		super(parentComponent, 5, 40, 200, Vars.HEIGHT - 350, active);
		
		container = new UIContainer(this, 5, 5, size.x - 10, size.y - 10, true, 1, GameLoader.playerResources.length, 10) {
			@Override
			public void click() {
				for(ResourceContainer c : resources) {
					c.closeButton();
					c.pack();
				}
				container.pack();
			}
		};
		resources = new ArrayList<>();
		
		for(int i = 0; i < GameLoader.playerResources.length; i++) {
			resources.add(new ResourceContainer(container, GameLoader.playerResources[i].getName()));
			container.addComponent(resources.get(i), 0, i);
		}
	}
	
	private class ResourceContainer extends UIContainer {
		
		private Text name, amount, price, course;
		private SwitchButton button;
		
		private String rname;

		public ResourceContainer(UIComponent parentComponent, String rname) {
			super(parentComponent, 0, 0, parentComponent.getSize().x, 0, true, 2, 3, 5);
			
			this.rname = rname;
			
			name = new Text(this, rname, 5, 0, 12, Vars.SERIF, 1, false, true);
			amount = new Text(this, "", 5, 0, 12, Vars.SERIF, 1, false, true);
			price = new Text(this, "", 85, 0, 12, Vars.SERIF, 1, false, true);
			course = new Text(this, "", 85, 0, 12, Vars.SERIF, 1, false, true);
			
			button = new SwitchButton(this, 5, 0, 100, 30, false);
			
			addComponent(name, 0, 0);
			addComponent(amount, 0, 1);
			addComponent(price, 1, 0);
			addComponent(course, 1, 1);
			addComponent(button, 1, 2);
			
			makeClickable();
		}
		
		public void closeButton() {
			button.disable();
		}
		
		@Override
		public void update() {
			super.update();
			
			amount.text().setText(Vars.df2.format(GameHandler.game.getUser().getQuantity(rname)));
			price.text().setText(Vars.df2.format(GameHandler.game.market.getPrice(rname)));
			course.text().setText(Vars.df2.format((GameHandler.game.market.getGrowthFactor(rname) - 1) * 100) + "%");
		}
		
		@Override
		public void click() {
			super.click();
			button.enable();
			pack();
			container.pack();
		}
		
	}

	@Override
	public void update() {
		
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
