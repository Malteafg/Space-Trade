package com.simaflux.spacetrade.UI.tooltips;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.simaflux.spacetrade.UI.LayerManager;
import com.simaflux.spacetrade.input.Input;
import com.simaflux.spacetrade.loader.Memory;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector4f;

public class TooltipManager {
	
	private Tooltip activeTooltip = null;
	
	private int time;
	
	private final int transitionTime = 10;
	private final float transparency = 0.95f;
	
	public void update() {
		if(activeTooltip != null && time >= 0) time--;
	}
	
	public void render() {
		if(activeTooltip != null) {
			Memory.getShader("UIBox").start();
			glBindVertexArray(LayerManager.box.getVaoID());
			glEnableVertexAttribArray(0);
			
			Memory.getShader("UIBox").loadUniformMat4f("transformationMatrix", 
					Matrix4f.translate(
							(Input.mousePos.x - activeTooltip.getSize().x) / Vars.WIDTH * 2.0f - 1, 
							(((Input.mousePos.y - activeTooltip.getSize().y) / Vars.HEIGHT) * - 2.0f) + 1, 1)
					.multiply(Matrix4f.scale(activeTooltip.getGlsize().x, activeTooltip.getGlsize().y, 1)));
			Memory.getShader("UIBox").loadUniformVec4f("color", 
					new Vector4f(Vars.STANDARD_BLUE, 
							time > transitionTime ? 0 : 
								(time < 0 ? transparency : 
									(float) (((time * - 1 + transitionTime) * transparency) / transitionTime))));
			glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
			
			glDisableVertexAttribArray(0);
			glBindVertexArray(0);
			Memory.getShader("UIBox").stop();
		}
	}
	
	public void deactivateTooltip() {
		activeTooltip = null;
	}
	
	public void activateTooltip(Tooltip t) {
		activeTooltip = t;
		time = t.getTime();
	}

}
