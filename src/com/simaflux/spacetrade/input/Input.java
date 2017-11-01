package com.simaflux.spacetrade.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.UI.components.Button;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Input {
	
	public static boolean[] keys = new boolean[349], prevKeys = new boolean[349];
	public static boolean[] buttons = new boolean[8], prevButtons = new boolean[8];
	
	public static Vector2f mousePos = new Vector2f();
	public static Vector2f prevMousePos = new Vector2f();
	public static Vector2f mouseD = new Vector2f();
	
	public static int scroll, prevScroll, scrollD;
	
	public static List<Button> uibuttons = new ArrayList<>();
	
	public static void update() {
		// keyboard input
		for(int i = 0; i < keys.length; i++) {
			if(!prevKeys[i] && keys[i]) {
				if(keys[GLFW.GLFW_KEY_ESCAPE]) {
					if(GameHandler.game == null) {
						System.exit(0);
					} else {
						if(GameHandler.paused) {
							Interface.disablePanel("PauseMenu");
							GameHandler.unpause();
						} else {
							Interface.enablePanel("PauseMenu");
							GameHandler.pause();
						}
					}
				}
				if(keys[GLFW.GLFW_KEY_F1]) {
					Interface.toggleUI();
				}
				
				/*
				 * Game
				 */
				if(GameHandler.game != null) {
					if(!GameHandler.paused) {
						if(keys[GLFW.GLFW_KEY_LEFT_ALT]) {
							GameHandler.game.goBack();
						}
					}
					
					if(keys[GLFW.GLFW_KEY_SPACE]) {
						if(GameHandler.paused) {
							GameHandler.unpause();
						} else {
							GameHandler.pause();
						}
					}
				}
			}
			prevKeys[i] = keys[i];
		}
		
		// Mouse input
		
		mouseD = mousePos.subtract(prevMousePos);
		prevMousePos = mousePos.copy();
		
		scrollD = scroll - prevScroll;
		prevScroll = scroll;
				
		// Ray casting
		for(int i = 0; i < buttons.length; i++) {
			if(!prevButtons[i] && buttons[i]) {
				// interface
				for(Button button : uibuttons) {
					if(button.isEnabled()) {
						if(Maths.containsMouse(button.getPos(), button.getSize())) {
							button.click();
						}
					}
				}
				
				// 3d picking
				if(GameHandler.game != null && !GameHandler.paused) {
					if(buttons[GLFW.GLFW_MOUSE_BUTTON_1]) {
						GameHandler.game.castRay();
					}
				}
			}
			prevButtons[i] = buttons[i];
		}
	}
	
	public static void addButton(Button b) {
		uibuttons.add(b);
	}
	
	public static final class KeyInput extends GLFWKeyCallback {

		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			if(action == GLFW_PRESS) keys[key] = true;
			if(action == GLFW_RELEASE) keys[key] = false;
		}
		
	}
	
	public static final class MouseInput extends GLFWMouseButtonCallback {

		@Override
		public void invoke(long window, int button, int action, int mods) {
			if(action == GLFW_PRESS) buttons[button] = true;
			if(action == GLFW_RELEASE) buttons[button] = false;
		}
		
	}
	
	public static final class MousePos extends GLFWCursorPosCallback {

		@Override
		public void invoke(long window, double xpos, double ypos) {
			mousePos.x = (float) xpos;
			mousePos.y = (float) ypos;
		}
		
	}
	
	public static final class MouseScroll extends GLFWScrollCallback {

		@Override
		public void invoke(long window, double xoffset, double yoffset) {
			scroll += yoffset;
		}
		
	}

}
