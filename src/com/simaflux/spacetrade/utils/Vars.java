package com.simaflux.spacetrade.utils;

import java.io.File;

import com.simaflux.spacetrade.graphics.fontengine.FontType;
import com.simaflux.spacetrade.loader.TextureLoader;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class Vars {
	
	public static final float WIDTH = 1920f, HEIGHT = 1080f;
	
	// Rendering constants
	public static final float FOV = 70, NEAR_PLANE = 0.1f, FAR_PLANE = 10000.0f;
	public static final Matrix4f perspectiveProjection = Matrix4f.perspective(FOV, WIDTH / HEIGHT, NEAR_PLANE, FAR_PLANE);
	public static final Matrix4f uiProjection = Matrix4f.orthographic(0, WIDTH, HEIGHT, 0, 10, 0);

	// Input sensetivty
	public static final float
			MOUSE_SCROLL = 2f;
	
	// Standard camera scroll
	public static final float
			DEF_CAM_SCROLL = 3000,
			DEF_STAR_SCROLL = 250,
			DEF_PLANET_SCROLL = 50,
			MIN_SCROLL = 5f,
			MAX_SCROLL = 30f;
	
	// Standard camera positions
	public static final Vector3f 
			DEF_CAM_POS = new Vector3f(0, 2000, 2200),
			DEF_CAM_ROT = new Vector3f(0.8f, 0, 0),
			DEF_STARCAM_POS = new Vector3f(0, 10, 10),
			DEF_STARCAM_ROT = new Vector3f(0.8f, 0, 0),
			DEF_PLANETCAM_POS = new Vector3f(0, 5, 7),
			DEF_PLANETCAM_ROT = new Vector3f(0.7f, 0, 0);
	
	// Temporary standard font type
	public static final FontType 
			SERIF = new FontType(TextureLoader.loadTexture("font/serif"), new File("resources/font/serif.fnt"));
	
	// Standard font color
	public static final Vector3f
			FONT_COLOR = new Vector3f(1f, 0, 0); 
	
	// Standard UI background color
	public static final Vector3f
			STANDARD_BLUE = new Vector3f(0.0390625f, 0.15234375f, 0.21875f);
	
	// Planet types
	public static final String 
			GAS_GIANT = "Gas Giant",
			NATURALLY_HABITABLE = "Naturally Habitable",
			TERRESTRIAL = "Terrestrial",
			ICE = "Ice",
			LAVA = "Lava";
	
}
