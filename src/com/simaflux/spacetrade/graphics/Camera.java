package com.simaflux.spacetrade.graphics;

import com.simaflux.spacetrade.input.Input;
import com.simaflux.spacetrade.objects.space.AstronomicalObject;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Camera {
	
	private Vector3f position, rotation;
	private Vector3f dPos;
	
	private Vector3f relRot, tempPos;
	private float dScroll;
	
	private AstronomicalObject boundObject;
	
	private boolean moving;
	private int time, startTime;
	
	private Matrix4f viewMatrix;
	
	public Camera() {
		position = new Vector3f();
		rotation = new Vector3f(10, 0, 0);
		relRot = new Vector3f(0, 0, 10);
		
		tempPos = new Vector3f();
//		relPos = new Vector3f();
		viewMatrix = Matrix4f.createViewMatrix(this);
		
		boundObject = null;
	}
	
	public void update() {
		
		// Camera movement
		if(moving) {
			
			float speed = (float) ((startTime / 2.0 - Math.sqrt(Math.pow(time - startTime / 2.0, 2))) / startTime * 4);
			
			tempPos = tempPos.add(dPos.scale(speed));
			relRot.z += dScroll * speed;
			
			time--;
			if(time == 0) {
				moving = false;
			}
		}
		
		// Camera rotation movement
		relRot.z -= Input.scrollD * boundObject.getScale() * Vars.MOUSE_SCROLL;
		if(Input.buttons[1]) {
			relRot.y += Input.mouseD.x * 0.0017f;
			relRot.x = (float) Math.min(Math.max(Input.mouseD.y * 0.0023f + relRot.x, 0.0), Math.PI / 2.0);
			
		}
		
		if(!moving) {
			relRot.z = relRot.z < boundObject.getScale() * Vars.MIN_SCROLL ? boundObject.getScale() * Vars.MIN_SCROLL : relRot.z;
			relRot.z = relRot.z > boundObject.getScale() * Vars.MAX_SCROLL ? boundObject.getScale() * Vars.MAX_SCROLL : relRot.z;
		}
		
		if(boundObject != null) {
			
			Vector3f relPos = new Vector3f();
			float dist = Maths.cos(relRot.x) * relRot.z;
			relPos.x = Maths.cos(relRot.y) * dist;
			relPos.z = Maths.sin(relRot.y) * dist;
			relPos.y = Maths.sin(relRot.x) * relRot.z;
			
			rotation.y = relRot.y + Maths.PI * 1.5f;
			rotation.x = relRot.x + 0.05f;
			
			if(moving) {
				position = tempPos.add(relPos);
			} else {
				position = boundObject.getPosition().add(relPos);
			}
		}
		
		viewMatrix = Matrix4f.createViewMatrix(this);
	}
	
	private void setRelRot(Vector3f pos) {
		relRot.z = pos.length();
		relRot.x = Maths.asin(pos.y / relRot.z);
		relRot.y = Maths.atan(pos.x, pos.z) - Maths.PI * 0.5f;
	}
	
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public void moveTo(AstronomicalObject object, Vector3f newPos, Vector3f newRot) {
		rotation = newRot.copy();
		boundObject = object;
		
		setRelRot(newPos);
		
		viewMatrix = Matrix4f.createViewMatrix(this);
	}
	
	public void moveTo(AstronomicalObject object, float scroll, int time) {
		this.startTime = time;
		this.time = time;
		moving = true;
		
		tempPos = boundObject.getPosition();
		boundObject = object;
		
		rotation = rotation.mod(Maths.PI * 2).cent(Maths.PI * 2);
		dPos = boundObject.getExpectedPos(time).subtract(tempPos).divide(time);
		dScroll = (scroll - relRot.z) / time;
		
		viewMatrix = Matrix4f.createViewMatrix(this);
	}
	
	public void oldMoveStuff() {
		
//		float moveSpeed = 30f, rotationSpeed = 0.02f;
//		
//		if(Input.keys[GLFW.GLFW_KEY_W]) position.z -= 2f;
//		if(Input.keys[GLFW.GLFW_KEY_S]) position.z += 2f;
//		if(Input.keys[GLFW.GLFW_KEY_A]) position.x -= 2f;
//		if(Input.keys[GLFW.GLFW_KEY_D]) position.x += 2f;
//		if(Input.keys[GLFW.GLFW_KEY_Q]) position.y -= 1f;
//		if(Input.keys[GLFW.GLFW_KEY_E]) position.y += 1f;
//		if(Input.keys[GLFW.GLFW_KEY_UP]) rotation.x -= rotationSpeed;
//		if(Input.keys[GLFW.GLFW_KEY_DOWN]) rotation.x += rotationSpeed;
//		if(Input.keys[GLFW.GLFW_KEY_RIGHT]) rotation.y += rotationSpeed;
//		if(Input.keys[GLFW.GLFW_KEY_LEFT]) rotation.y -= rotationSpeed;
//		
//		Vector3f direction = new Vector3f(
//				Maths.cos(rotation.y - Maths.PI / 2f) * Maths.cos(rotation.x),
//				Maths.sin(rotation.x) * - 1f, 
//				Maths.sin(rotation.y - Maths.PI / 2f) * Maths.cos(rotation.x));
//		
//		if(Input.keys[GLFW.GLFW_KEY_W]) relPos = relPos.add(direction.scale(moveSpeed));
//		if(Input.keys[GLFW.GLFW_KEY_S]) relPos = relPos.add(direction.scale(- moveSpeed));
//		
//		direction = new Vector3f(Maths.cos(rotation.y), 0, Maths.sin(rotation.y));
//		
//		if(Input.keys[GLFW.GLFW_KEY_D]) relPos = relPos.add(direction.scale(moveSpeed));
//		if(Input.keys[GLFW.GLFW_KEY_A]) relPos = relPos.add(direction.scale(- moveSpeed));
		
	}
	
	
	
	
}
