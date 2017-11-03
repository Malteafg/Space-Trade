package com.simaflux.spacetrade.UI;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simaflux.spacetrade.UI.renderComponents.RenderBox;
import com.simaflux.spacetrade.UI.renderComponents.RenderIcon;
import com.simaflux.spacetrade.UI.renderComponents.RenderText;
import com.simaflux.spacetrade.graphics.fontengine.FontType;
import com.simaflux.spacetrade.graphics.fontengine.TextMeshData;
import com.simaflux.spacetrade.loader.Memory;
import com.simaflux.spacetrade.loader.ModelLoader;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Layer {
	
	private List<RenderBox> boxes;
	private List<RenderIcon> icons;
	private Map<FontType, List<RenderText>> texts;

	private List<UIComponent> uibuttons = new ArrayList<>();
	
	public Layer() {
		boxes = new ArrayList<>();
		icons = new ArrayList<>();
		texts = new HashMap<>();
	}
	
	public void addBox(RenderBox box) {
		boxes.add(box);
	}
	
	public void addIcon(RenderIcon icon) {
		icons.add(icon);
	}
	
	public void addText(RenderText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = ModelLoader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<RenderText> textBatch = texts.get(font);
		if (textBatch == null) {
			textBatch = new ArrayList<>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public void addButton(UIComponent b) {
		uibuttons.add(b);
	}
	
	public List<UIComponent> getButtons() {
		return uibuttons;
	}
	
	public void render() {
		Memory.getShader("UIBox").start();
		glBindVertexArray(LayerManager.box.getVaoID());
		glEnableVertexAttribArray(0);
		
		for(RenderBox b : boxes) {
			if(b.isEnabled()) {
				Memory.getShader("UIBox").loadUniformMat4f("transformationMatrix", 
						Matrix4f.translate(b.getGlpos().x, b.getGlpos().y, 1)
						.multiply(Matrix4f.scale(b.getGlsize().x, b.getGlsize().y, 1)));
				Memory.getShader("UIBox").loadUniformVec3f("color", Vars.STANDARD_BLUE);
				glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
			}
		}
		
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		Memory.getShader("UIBox").stop();
		
		Memory.getShader("font").start();
		for(FontType font : texts.keySet()) {
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, font.getTextureAtlas());
			for(RenderText text : texts.get(font)) {
				if(text.isEnabled()) {
					glBindVertexArray(text.getMesh());
					glEnableVertexAttribArray(0);
					glEnableVertexAttribArray(1);
					Memory.getShader("font").loadUniformVec3f("textcolor", text.getColor());
					Memory.getShader("font").loadUniformVec2f("translation", new Vector2f(text.getGlpos().x / 2.0f, (1 - text.getGlpos().y) / 2.0f));
					glDrawArrays(GL_TRIANGLES, 0, text.getVertexCount());
					glDisableVertexAttribArray(0);
					glDisableVertexAttribArray(1);
					glBindVertexArray(0);
				}
			}
		}
		Memory.getShader("font").stop();
	}
	
}