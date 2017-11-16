package com.simaflux.spacetrade.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.game.date.DateManager;
import com.simaflux.spacetrade.graphics.Camera;
import com.simaflux.spacetrade.input.MousePicker;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.loader.Memory;
import com.simaflux.spacetrade.objects.space.AstronomicalObject;
import com.simaflux.spacetrade.objects.space.Galaxy;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.objects.space.SolarSystem;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.players.User;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Game implements Serializable {
	
	private static final long serialVersionUID = -6277853297072439991L;

	private long time, marketTime;
	
	public DateManager dm;
	public Market market;
	
	public Camera camera;
	
	private Map<String, SolarSystem> systems;
	private SolarSystem selectedSystem;
	private Planet selectedPlanet;
	
	private Player[] players;
	
	public Game() {
		market = new Market();
		
		time = System.currentTimeMillis();
		marketTime = System.currentTimeMillis();
		
		players = new Player[1];
		players[0] = new User("Bob Johnson");
		
		dm = new DateManager();
		
		camera = new Camera();
		
		systems = new HashMap<>();
		generateSystems(5, 25);
		
		camera.moveTo(new Galaxy(new Vector3f(0, 0, 0)), Vars.DEF_CAM_POS, Vars.DEF_CAM_ROT);
	}
	
	private void generateSystems(int arms, int stars) {
		float a = 0.3f;
		float f = 80;
		int dist = 300;
		int cDist = 300;
		
		for(int i = 0; i < stars / arms; i++) {
			for(int s = 0; s < arms; s++) {
				float angle = Maths.PI * 2.0f / arms * s + a * i;

				String name = GameLoader.getName(GameLoader.starNames);
				systems.put(name + " System", new SolarSystem(name, new Vector3f(
						Maths.cos(angle) * (i * dist + cDist) + (Maths.random() * f - f / 2), 
						Maths.random() * 200 - 100f,
						Maths.sin(angle) * (i * dist + cDist) + (Maths.random() * f - f / 2)), 
						Maths.random() * 12 + 8));
			}
		}
	}

	public void update() {
		if((System.currentTimeMillis() - time) > 2000) {
			time = System.currentTimeMillis();
			
			dm.tick();
			
			for(String system : systems.keySet()) {
				SolarSystem s = systems.get(system);
				for(String planet : s.getPlanets().keySet()) {
					Planet p = s.getPlanets().get(planet);
					p.tick();
				}
			}
			
			for(Player p : players) {
				p.tick();
			}
		}
		
		if((System.currentTimeMillis() - marketTime) > 400) {
			marketTime = System.currentTimeMillis();
			
			market.tick();
		}
		
		camera.update();
		MousePicker.update();	
		
		for(String s : systems.keySet()) {
			systems.get(s).update();
		}
	}
	
	public void goBack() {
		if(selectedSystem != null) {
			if(selectedPlanet == null) {
				selectedSystem = null;
				camera.moveTo(new Galaxy(new Vector3f()), Vars.DEF_CAM_SCROLL, (int) (Vars.CAM_MOVETIME * 0.9f));
			} else {
				selectedPlanet = null;
				camera.moveTo(selectedSystem.getStar(), Vars.DEF_STAR_SCROLL, (int) (Vars.CAM_MOVETIME * 0.9f));
				Interface.disablePanel("PlanetInfo");
				Interface.disablePanel("BuildingStore");
			}
		}
	}
	
	public void castRay() {
		MousePicker.update();
		List<AstronomicalObject> objects = new ArrayList<>();
		
		if(selectedSystem == null) {
			for(String s : systems.keySet()) {
				objects.add(systems.get(s).getStar());
			}
		} else {
			if(selectedPlanet == null) {
				for(String s : selectedSystem.getPlanets().keySet()) {
					objects.add(selectedSystem.getPlanets().get(s));
				}
			}
		}
		
		AstronomicalObject s = MousePicker.checkIntersection(objects, new Vector3f(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z));
		
		if(s != null) {
			if(selectedSystem == null) {
				selectedSystem = s.getSystem();
				camera.moveTo(s, Vars.DEF_STAR_SCROLL, Vars.CAM_MOVETIME);
			} else {
				selectedPlanet = selectedSystem.getPlanets().get(s.getName());
				camera.moveTo(s, Vars.DEF_PLANET_SCROLL, Vars.CAM_MOVETIME);
				Interface.enablePanel("PlanetInfo");
				Interface.enablePanel("BuildingStore");
			}
		}	
	}
	
	public void render() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		glBindVertexArray(Memory.getModel("sphere").getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		
		Memory.getShader("star").start();
		Memory.getShader("star").loadUniformMat4f("viewMatrix", camera.getViewMatrix());
		Memory.getShader("star").loadUniformVec3f("camPos", camera.getPosition());
		
		for(String s : systems.keySet()) {
			SolarSystem system = systems.get(s);
			Memory.getShader("star").loadUniformMat4f("transformationMatrix",
					Matrix4f.translate(
							system.getStar().getPosition().x, 
							system.getStar().getPosition().y, 
							system.getStar().getPosition().z)
					.multiply(Matrix4f.rotate(system.getStar().getRotation().x, 1, 0, 0))
					.multiply(Matrix4f.rotate(system.getStar().getRotation().y, 0, 1, 0))
					.multiply(Matrix4f.rotate(system.getStar().getRotation().z, 0, 0, 1))
					.multiply(Matrix4f.scale(system.getStar().getScale(), 
							system.getStar().getScale(), 
							system.getStar().getScale())));
	
			glDrawElements(GL_TRIANGLES, Memory.getModel("sphere").getVertexCount(), GL_UNSIGNED_INT, 0);
		}
		Memory.getShader("star").stop();

		Memory.getShader("planet").start();
		Memory.getShader("planet").loadUniformMat4f("viewMatrix", camera.getViewMatrix());

		for(String s : systems.keySet()) {
			SolarSystem system = systems.get(s);
			
			Memory.getShader("planet").loadUniformVec3f("lightPos", system.getStar().getLight().getPosition());
			Memory.getShader("planet").loadUniformVec3f("lightColor", system.getStar().getLight().getColor());
			
			for(String p : system.getPlanets().keySet()) {
				Planet planet = system.getPlanets().get(p);
				
				Memory.getShader("planet").loadUniformMat4f("transformationMatrix",
						Matrix4f.translate(
								planet.getPosition().x, 
								planet.getPosition().y, 
								planet.getPosition().z)
						.multiply(Matrix4f.rotate(planet.getRotation().x, 1, 0, 0))
						.multiply(Matrix4f.rotate(planet.getRotation().y, 0, 1, 0))
						.multiply(Matrix4f.rotate(planet.getRotation().z, 0, 0, 1))
						.multiply(Matrix4f.scale(
								planet.getScale(), 
								planet.getScale(), 
								planet.getScale())));
				Memory.getShader("planet").loadUniformVec3f("color", planet.getColor());
	
				glDrawElements(GL_TRIANGLES, Memory.getModel("sphere").getVertexCount(), GL_UNSIGNED_INT, 0);
			}
		}
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);

		Memory.getShader("planet").stop();
	}

	public Planet getSelectedPlanet() {
		return selectedPlanet;
	}
	
	public Player getUser() {
		return players[0];
	}
	
}
