package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.UI.tooltips.Tooltip;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class MainMenu extends UIPanel {

	public MainMenu(UIComponent parentComponent, boolean active) {
		super(parentComponent, 760, 100, 400, 800, active);
		
		Button newgame = new Button(this, 50, 50, 300, 50, active) {
			@Override
			public void click() {
				GameHandler.startNewGame();
				Interface.disablePanel("MainMenu");
			}
		};
		newgame.addText(new Text(newgame, "New Game", newgame.getSize().x * 0.5f, newgame.getSize().y * 0.1f, 20, Vars.SERIF, 1, true, active));
		newgame.addTooltip(new Tooltip(200, 100, 60));
		
		Button loadgame = new Button(this, 50, 150, 300, 50, active) {
			@Override
			public void click() {
				GameHandler.loadGame();
				Interface.disablePanel("MainMenu");
			}
		};
		loadgame.addText(new Text(loadgame, "Load Game", loadgame.getSize().x * 0.5f , loadgame.getSize().y * 0.1f, 20, Vars.SERIF, 1, true, active));
		
		Button settings = new Button(this, 50, 250, 300, 50, active) {
			@Override
			public void click() {
			}
		};
		settings.addText(new Text(settings, "Settings", settings.getSize().x * 0.5f , settings.getSize().y * 0.1f, 20, Vars.SERIF, 1, true, active));
		
		Button exit = new Button(this, 50, 350, 300, 50, active) {
			@Override
			public void click() {
				System.exit(0);
			}
		};
		exit.addText(new Text(exit, "Exit Game", exit.getSize().x * 0.5f , exit.getSize().y * 0.1f, 20, Vars.SERIF, 1, true, active));
	}

	@Override
	public void update() {}

}
