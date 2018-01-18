package com.simaflux.spacetrade.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.UI.Interface;
import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.game.date.DateManager;
import com.simaflux.spacetrade.graphics.Camera;
import com.simaflux.spacetrade.input.MousePicker;
import com.simaflux.spacetrade.loader.Memory;
import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.objects.space.AstronomicalObject;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.objects.space.SolarSystem;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.players.User;
import com.simaflux.spacetrade.relations.RelationManager;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Game implements Serializable {
	
	private static final long serialVersionUID = -6277853297072439991L;

	private long time, marketTime;
	
	public DateManager dm;
	public Market market;
	public RelationManager rm;
	public NameManager nm;
	
	public Camera camera;
	
	private SolarSystem system;
	private Planet selectedPlanet;
	private Building selectedBuilding;
	
	private Player[] players;
	
	private List<Empire> empires;
	
	public Game() {
		int empireNum = 7;
		int planetNum = 10;
		
		market = new Market();
		
		time = System.currentTimeMillis();
		marketTime = System.currentTimeMillis();
		
		players = new Player[1];
		players[0] = new User("Bob Johnson");
		
		dm = new DateManager();
		rm = new RelationManager();
		nm = new NameManager(planetNum);
		
		camera = new Camera();
		
		system = new SolarSystem(nm.getStarName(), new Vector3f(0, 0, 0), Maths.random() * 12 + 8, planetNum, nm);
		generateEmpires(empireNum);
		generateRelations();
		
		camera.moveTo(system.getStar(), Vars.DEF_CAM_POS, Vars.DEF_CAM_ROT);
	}

	private void generateEmpires(int amount) {
		empires = new ArrayList<>();
		
		for(int i = 0; i < amount; i++) {
			Planet planet;
			do {
				planet = system.getPlanets().get(system.getPlanets().keySet().toArray()[(int) (Maths.random() * (system.getPlanets().size() - 1))]);
			} while(planet.getEmpire() != null);
			empires.add(new Empire(planet, nm.getEmpireNaming(nm.getEmpireName(planet.getName()))));
			planet.setEmpire(empires.get(empires.size() - 1));
		}
	}
	
	private void generateRelations() {
		rm.createEmpireRelations(empires);
		rm.createPlayerRelations(players);
		rm.createPlayerEmpireRelations(players, empires);
	}
	
	public void update() {
		if((System.currentTimeMillis() - time) > 2000) {
			time = System.currentTimeMillis();
			
			dm.tick();
			
			for(String planet : system.getPlanets().keySet()) {
				Planet p = system.getPlanets().get(planet);
				p.tick();
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
		
		system.update();
	}
	
	public void goBack() {
		selectedPlanet = null;
		selectedBuilding = null;
		camera.moveTo(system.getStar(), Vars.DEF_STAR_SCROLL, (int) (Vars.CAM_MOVETIME * 0.9f));
		Interface.disablePanel("PlanetInfo");
		Interface.disablePanel("BuildingStore");
		Interface.disablePanel("BuildingInfo");
	}
	
	public void castRay() {
		MousePicker.update();
		List<AstronomicalObject> objects = new ArrayList<>();
		
		if(selectedPlanet == null) {
			for(String s : system.getPlanets().keySet()) {
				objects.add(system.getPlanets().get(s));
			}
		}
		
		AstronomicalObject s = MousePicker.checkIntersection(objects, new Vector3f(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z));
		
		if(s != null) {
			selectedPlanet = system.getPlanets().get(s.getName());
			camera.moveTo(s, Vars.DEF_PLANET_SCROLL, Vars.CAM_MOVETIME);
			Interface.enablePanel("PlanetInfo");
			Interface.enablePanel("BuildingStore");
		}	
	}
	
	public void render() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		glBindVertexArray(Memory.getModel("sphere").getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		
		// star rendering
		Memory.getShader("star").start();
		Memory.getShader("star").loadUniformMat4f("viewMatrix", camera.getViewMatrix());
		Memory.getShader("star").loadUniformVec3f("camPos", camera.getPosition());
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
		Memory.getShader("star").stop();

		// planet rendering
		Memory.getShader("planet").start();
		Memory.getShader("planet").loadUniformMat4f("viewMatrix", camera.getViewMatrix());
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

		Memory.getShader("planet").stop();
		
		// building rendering
		glBindVertexArray(Memory.getModel("factory").getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		
		Memory.getShader("building").start();
		Memory.getShader("building").loadUniformMat4f("viewMatrix", camera.getViewMatrix());
		Memory.getShader("building").loadUniformVec3f("lightPos", system.getStar().getLight().getPosition());
		Memory.getShader("building").loadUniformVec3f("lightColor", system.getStar().getLight().getColor());
		for(Player p : players) {
			for(Building b : p.getAllBuildings()) {
				Vector3f pos = b.getPlanet().getPosition().add(b.getPosition().scale(b.getPlanet().getScale()));
				
				Memory.getShader("planet").loadUniformMat4f("transformationMatrix",
						Matrix4f.translate(
								pos.x, 
								pos.y, 
								pos.z)
						.multiply(Matrix4f.rotate(b.getRotation().x, 1, 0, 0))
						.multiply(Matrix4f.rotate(b.getRotation().y, 0, 1, 0))
						.multiply(Matrix4f.rotate(b.getRotation().z, 0, 0, 1))
						.multiply(Matrix4f.scale(
								1, 
								1, 
								1)));
				Memory.getShader("building").loadUniformVec3f("color", new Vector3f(0.2f, 0.3f, 0.4f));

				glDrawElements(GL_TRIANGLES, Memory.getModel("factory").getVertexCount(), GL_UNSIGNED_INT, 0);
			}			
		}

		Memory.getShader("building").stop();
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
		
	}

	public Planet getSelectedPlanet() {
		return selectedPlanet;
	}
	
	public Player getUser() {
		return players[0];
	}
	
	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}
	
	public Empire getEmpire(String name) {
		for(Empire e : empires) {
			if(name.equals(e.getName())) return e;
		}
		
		System.err.println(name + " not found");
		
		return null;
	}
	
}
