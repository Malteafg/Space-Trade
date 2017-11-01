package com.simaflux.spacetrade.UI;

import static org.lwjgl.opengl.GL11.*;

public abstract class Interface {

	public static UIScreen screen;

	public static void init() {
		LayerManager.init();

		screen = new UIScreen();
	}

	public static void enablePanel(String panel) {
		screen.panels.get(panel).enable();
	}

	public static void disablePanel(String panel) {
		screen.panels.get(panel).disable();
	}

	public static UIPanel getPanel(String panel) {
		return screen.panels.get(panel);
	}

	public static void toggleUI() {
		if (screen.isEnabled()) screen.disable();
		else if (!screen.isEnabled()) screen.enable();
	}

	public static void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_DEPTH_TEST);

		for (Layer layer : LayerManager.layers) {
			layer.render();
		}

		glDisable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
	}

}
