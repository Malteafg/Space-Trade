package com.simaflux.spacetrade.utils;

import java.io.File;
import java.text.DecimalFormat;

import com.simaflux.spacetrade.graphics.fontengine.FontType;
import com.simaflux.spacetrade.loader.TextureLoader;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class Vars {
	
	// Screen size
	public static final float WIDTH = 1920f, HEIGHT = 1080f;
	
	// Rendering constants
	public static final float FOV = 70, NEAR_PLANE = 0.1f, FAR_PLANE = 10000.0f;
	public static final Matrix4f perspectiveProjection = Matrix4f.perspective(FOV, WIDTH / HEIGHT, NEAR_PLANE, FAR_PLANE);
	public static final Matrix4f uiProjection = Matrix4f.orthographic(0, WIDTH, HEIGHT, 0, 10, 0);

	// Input sensitivity
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
			DEF_CAM_POS = new Vector3f(0, 2500, 2750),
			DEF_CAM_ROT = new Vector3f(0.6f, 0, 0),
			DEF_STARCAM_POS = new Vector3f(0, 10, 10),
			DEF_STARCAM_ROT = new Vector3f(0.8f, 0, 0),
			DEF_PLANETCAM_POS = new Vector3f(0, 5, 7),
			DEF_PLANETCAM_ROT = new Vector3f(0.7f, 0, 0);
	
	public static final int
			CAM_MOVETIME = 40;
	
	// Temporary standard font type
	public static final FontType 
			SERIF = new FontType(TextureLoader.loadTexture("font/serif"), new File("resources/font/serif.fnt"));
	
	// Standard font color
	public static final Vector3f
			FONT_COLOR = new Vector3f(1f, 0, 0); 
	
	// Standard UI background color
	public static final Vector3f
			STANDARD_BLUE = new Vector3f(0.1390625f, 0.29234375f, 0.39875f),
			NOT_POSSIBLE_RED = new Vector3f(0.812f, 0.03f, 0.2458f);
	
	// Planet types
	public static final String 
			GAS_GIANT = "Gas Giant",
			NATURALLY_HABITABLE = "Naturally Habitable",
			TERRESTRIAL = "Terrestrial",
			ICE = "Ice",
			LAVA = "Lava";
	
	// Decimal format
	public static final DecimalFormat df2 = new DecimalFormat();
	
	public static void init() {
		df2.setMaximumFractionDigits(2);
	}
	
	// Resources
	public static final String
			IRON = "Iron", CARBON = "Carbon", OIL = "Oil", ALUMINUM = "Aluminum", GOLD = "Gold", 
			PLASTIC = "Plastic", STEEL = "Steel", ELECTRONICS = "Electronics", COMPUTER = "Computer",
			SPACESHIP = "Spaceship", FUEL = "Fuel";
	
	// Buildings
	public static final String 
			IRON_MINE = "Iron Mine", CARBON_EXTRACTOR = "Carbon Extractor", ALUMINUM_MINE = "Aluminum Mine", OIL_PUMP = "Oil Pump", GOLD_MINE = "Gold Mine",
			STEEL_FACTORY = "Steel Factory", OIL_REFINERY = "Oil Refinery", ELECTRONICS_FACTORY = "Electronics Factory", COMPUTER_FACTORY = "Computer Factory", SPACESHIP_FACTORY = "Spaceship Factory", FUEL_FACTORY = "Fuel Factory", 
			SOLAR_POWER_PLANT = "Solar Power Plant", OIL_POWER_PLANT = "Oil Power Plant", NUCLEAR_POWER_PLANT = "Nuclear Power Plant";
	
	// Opinion Modifiers
	
}
