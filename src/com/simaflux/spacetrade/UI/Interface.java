package com.simaflux.spacetrade.UI;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.tooltips.TooltipManager;

public abstract class Interface {

	public static UIScreen screen;
	public static final List<UIComponent> components = new ArrayList<>();
	
	public static TooltipManager tm;

	public static void init() {
		LayerManager.init();

		screen = new UIScreen();
		
		tm = new TooltipManager();
	}

	public static void enablePanel(String panel) {
		screen.panels.get(panel).enable();
	}

	public static void enablePanel(UIPanel panel) {
		for(String s : screen.panels.keySet()) {
			if(screen.panels.get(s).equals(panel)) {
				screen.panels.get(s).enable();
			}
		}
	}

	public static void disablePanel(String panel) {
		screen.panels.get(panel).disable();
	}

	public static void disablePanel(UIPanel panel) {
		for(String s : screen.panels.keySet()) {
			if(screen.panels.get(s).equals(panel)) {
				screen.panels.get(s).disable();
			}
		}
	}

	public static UIPanel getPanel(String panel) {
		return screen.panels.get(panel);
	}
	
	public static void sendMessage(String panel, String message) {
		getPanel(panel).receive(message);
	}

	public static void toggleUI() {
		if (screen.isEnabled()) screen.disable();
		else if (!screen.isEnabled()) screen.enable();
	}
	
	public static void update() {
		for(UIComponent c : components) {
			if(c.isEnabled()) {
				c.update();
			}
		}
		
		tm.update();
	}

	public static void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_DEPTH_TEST);

		for (Layer layer : LayerManager.layers) {
			layer.render();
		}
		
		tm.render();

		glDisable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
	}

}
