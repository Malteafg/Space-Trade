package com.simaflux.spacetrade.UI;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.UI.renderComponents.RenderIcon;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.graphics.models.RawModel;
import com.simaflux.spacetrade.loader.ModelLoader;

public class LayerManager {

	private static final float[] POSITIONS = { 0, 0, 0, - 1, 1, 0, 1, - 1 };
	public static RawModel box;
	
	public static final List<Layer> layers = new ArrayList<>();
	
	public static void init() {
		box = ModelLoader.loadToVAO(POSITIONS, 2);
	}
	
	public static void addBox(RenderBox box) {
		createLayer(box.getLayer());
		layers.get(box.getLayer()).addBox(box);
	}
	
	public static void addIcon(RenderIcon icon) {
		createLayer(icon.getLayer());
		layers.get(icon.getLayer()).addIcon(icon);
	}
	
	public static void addText(RenderText text) {
		createLayer(text.getLayer());
		layers.get(text.getLayer()).addText(text);
	}
	
	public static void addButton(UIComponent button) {
		createLayer(button.getParentAmount());
		layers.get(button.getParentAmount()).addButton(button);
	}
	
	private static void createLayer(int layer) {
		if(layer >= layers.size()) {
			layers.add(new Layer());
			createLayer(layer);
		}
	}

}
