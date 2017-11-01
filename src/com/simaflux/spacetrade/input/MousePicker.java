package com.simaflux.spacetrade.input;

import java.util.List;

import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.objects.space.AstronomicalObject;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector3f;
import com.simaflux.spacetrade.utils.math.Vector4f;

public class MousePicker {
	
	private static Vector3f currentRay;
	
	public static Vector3f getRay() {
		return currentRay;
	}
	
	public static void update() {
		currentRay = calculateMouseRay();
	}
	
	private static Vector3f calculateMouseRay() {
		Vector4f clipCoords = new Vector4f(2 * Input.mousePos.x / Vars.WIDTH - 1, (2 * Input.mousePos.y / Vars.HEIGHT - 1) * -1, -1, 1);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		Vector3f worldRay = toWorldCoords(eyeCoords);
		
		return worldRay;
	}
	
	private static Vector4f toEyeCoords(Vector4f clipCoords) {
		Vector4f negatedProjection = Vars.perspectiveProjection.invert().multiply(clipCoords);		
		return new Vector4f(negatedProjection.x, negatedProjection.y, -1, 0);
	}
	
	private static Vector3f toWorldCoords(Vector4f eyeCoords) {
		Vector4f invertedView = GameHandler.game.camera.getViewMatrix().invert().multiply(eyeCoords);
		Vector3f mouseRay = new Vector3f(invertedView.x, invertedView.y, invertedView.z);
		mouseRay = mouseRay.normalize();
		
		return mouseRay;
	}
	
	
	private static final int RECURSION_COUNT = 3500;
	
	public static AstronomicalObject checkIntersection(List<AstronomicalObject> objects, Vector3f point, int number) {
		point = point.add(currentRay);
		
		for(AstronomicalObject object : objects) {
			Vector3f dist = new Vector3f(object.getPosition().x - point.x, object.getPosition().y - point.y, object.getPosition().z - point.z);
			
			if(dist.length() < object.getScale()) {
				return object;
			}
		}
		
		if(number >= RECURSION_COUNT) {
			return null;
		}
		
		return checkIntersection(objects, point, number + 1);
	}

	public static AstronomicalObject checkIntersection(List<AstronomicalObject> objects, Vector3f point) {
		AstronomicalObject hit = null;
		float hitDist = 0;
		
		for(AstronomicalObject object : objects) {
			Vector3f dist = new Vector3f(object.getPosition().x - point.x, object.getPosition().y - point.y, object.getPosition().z - point.z);
			Vector3f distRay = currentRay.scale(dist.length()).add(point);

			Vector3f objectDistRay = new Vector3f(object.getPosition().x - distRay.x, object.getPosition().y - distRay.y, object.getPosition().z - distRay.z);
			if(objectDistRay.length() < object.getScale() * 3f) {
				if(dist.length() < hitDist || hit == null) {
					hit = object;
					hitDist = dist.length();
				}
			}
		}
		
		return hit;
	}
	

}
