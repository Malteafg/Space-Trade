package com.simaflux.spacetrade;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.input.Input;
import com.simaflux.spacetrade.input.Input.KeyInput;
import com.simaflux.spacetrade.input.Input.MouseInput;
import com.simaflux.spacetrade.input.Input.MousePos;
import com.simaflux.spacetrade.input.Input.MouseScroll;
import com.simaflux.spacetrade.loader.Loader;
import com.simaflux.spacetrade.utils.Vars;

public class Main implements Runnable {
	
	private Thread thread;
	private boolean running;
	
	private int updates, frames;
	
	private long window;
	
	private void init() {
		// initting opengl
		if(glfwInit() != true) {
			System.err.println("Could not initialize GLFW!");
			return;
		}
		
		// creating window
		long monitor = glfwGetPrimaryMonitor();
		GLFWVidMode vidmode = glfwGetVideoMode(monitor);
		glfwWindowHint(GLFW_RED_BITS, vidmode.redBits());
		glfwWindowHint(GLFW_GREEN_BITS, vidmode.greenBits());
		glfwWindowHint(GLFW_BLUE_BITS, vidmode.blueBits());
		glfwWindowHint(GLFW_REFRESH_RATE, vidmode.refreshRate());
		window = glfwCreateWindow((int) Vars.WIDTH, (int) Vars.HEIGHT, "Space Trade Engine", monitor, NULL);
		if(window == NULL) {
			System.err.println("Could not create GLFW window!");
			return;
		}
		glfwSetWindowPos(window, vidmode.width() / 2 - (int) Vars.WIDTH / 2, vidmode.height() / 2 - (int) Vars.HEIGHT / 2);
		
		// setting up input
		glfwSetKeyCallback(window, new KeyInput());
		glfwSetMouseButtonCallback(window, new MouseInput());
		glfwSetCursorPosCallback(window, new MousePos());
		glfwSetScrollCallback(window, new MouseScroll());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		GL.createCapabilities();

		// initting vars
		Vars.init();
		
		// setting graphics
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
		Loader.load();
		
		Interface.init();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	@Override
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int u = 0;
		int f = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				gameUpdate();
				u++;
				delta--;
			}
			
			gameRender();
			f++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				updates = u;
				frames = f;
				
				System.out.println("UPS: " + updates + "  FPS: " + frames);
				//if(updates < 15 || updates > 100) System.exit(0);
				
				u = 0;
				f = 0;
			}
			
			if(glfwWindowShouldClose(window) == true) {
				running = false;
			}
		}
	}

	private void gameUpdate() {
		GameHandler.update();
		Interface.update();
		Input.update();
		glfwPollEvents();
	}

	private void gameRender() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		GameHandler.render();
		Interface.render();
		
//		int i = glfwGetError();
//		if(i != GL_NO_ERROR) {
//			System.out.println(i);
//		}
		glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
	
}
