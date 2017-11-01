package com.simaflux.spacetrade.loader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShaderLoader {
	
	public static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("shaders/" + file));
			String line;
			while((line = reader.readLine()) != null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		
		if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		
		return shaderID;
	}

}
