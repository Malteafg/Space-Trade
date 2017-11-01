package com.simaflux.spacetrade.UI.panels;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.UIComponent;
import com.simaflux.spacetrade.UI.UIPanel;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.UI.components.Text;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Vars;

public class PauseMenu extends UIPanel {

	public PauseMenu(UIComponent parentComponent, boolean active) {
		super(parentComponent, 760, 100, 400, 800, active);

		Button resume = new Button(this, 50, 50, 300, 50, active) {
			@Override
			public void click() {
				GameHandler.unpause();
				Interface.disablePanel("PauseMenu");
			}
		};
		resume.addText(new Text(this, "Resume", resume.getSize().x * 0.5f , resume.getSize().y * 0.2f, 20, Vars.SERIF, 1, true, active));
//		resume.getText().setColor(1, 0, 0);

		Button save = new Button(this, 50, 150, 300, 50, active) {
			@Override
			public void click() {
				GameHandler.save();
			}
		};
		save.addText(new Text(this, "Save", save.getSize().x * 0.5f , resume.getSize().y * 0.2f, 20, Vars.SERIF, 1, true, active));
//		resume.getText().setColor(1, 0, 0);
		
		Button settings = new Button(this, 50, 250, 300, 50, active) {
			@Override
			public void click() {
				
			}
		};
		settings.addText(new Text(this, "Settings", settings.getSize().x * 0.5f , settings.getSize().y * 0.2f, 20, Vars.SERIF, 1, true, active));
//		settings.getText().setColor(1, 0, 0);
		
		Button menu = new Button(this, 50, 350, 300, 50, active) {
			@Override
			public void click() {
				GameHandler.stopGame();
				Interface.disablePanel("PauseMenu");
				Interface.enablePanel("MainMenu");
			}
		};
		menu.addText(new Text(this, "Main Menu", menu.getSize().x * 0.5f , menu.getSize().y * 0.2f, 20, Vars.SERIF, 1, true, active));
//		menu.getText().setColor(1, 0, 0);
		
		Button exit = new Button(this, 50, 450, 300, 50, active) {
			@Override
			public void click() {
				System.exit(0);
			}
		};
		exit.addText(new Text(this, "Exit Game", exit.getSize().x * 0.5f , exit.getSize().y * 0.2f, 20, Vars.SERIF, 1, true, active));
//		exit.getText().setColor(1, 0, 0);
	}

	@Override
	public void update() {}

}
