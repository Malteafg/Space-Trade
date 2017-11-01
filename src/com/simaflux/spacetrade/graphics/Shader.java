package com.simaflux.spacetrade.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.system.MemoryStack;

import com.simaflux.spacetrade.loader.ShaderLoader;
import com.simaflux.spacetrade.utils.math.Matrix2f;
import com.simaflux.spacetrade.utils.math.Matrix3f;
import com.simaflux.spacetrade.utils.math.Matrix4f;
import com.simaflux.spacetrade.utils.math.Vector2f;
import com.simaflux.spacetrade.utils.math.Vector3f;
import com.simaflux.spacetrade.utils.math.Vector4f;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	public static final int NORMAL_ATTRIB = 2;
	
	private final int programID;
	private final int vertID, fragID;
	
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	public Shader(String vertexFile, String fragmentFile, int[] attribs) {
		vertID = ShaderLoader.loadShader(vertexFile, GL_VERTEX_SHADER);
		fragID = ShaderLoader.loadShader(fragmentFile, GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		glAttachShader(programID, vertID);
		glAttachShader(programID, fragID);
		bindAttributes(attribs);
		glLinkProgram(programID);
		glValidateProgram(programID);
	}
	public void bindAttributes(int[] attribs) {
		for(int i = 0; i < attribs.length; i++) {
			if(attribs[i] == VERTEX_ATTRIB) bindAttribute(0, "position");
			if(attribs[i] == TCOORD_ATTRIB) bindAttribute(1, "texCoords");
			if(attribs[i] == NORMAL_ATTRIB) bindAttribute(2, "normal");
		}
	}
	
	public void bindAttribute(int attribute, String variable) {
		glBindAttribLocation(programID, attribute, variable);
	}
	
	public void loadUniformFloat(String name, float value) {
		glUniform1f(getUniform(name), value);
	}
	
	public void loadUniformBoolean(String name, boolean value) {
		float toLoad = 0;
		if(value) toLoad = 1;
		glUniform1f(getUniform(name), toLoad);
	}

	public void loadUniformVec2f(String name, Vector2f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(2);
            value.toBuffer(buffer);
            glUniform2fv(getUniform(name), buffer);
        }
    }

	public void loadUniformVec3f(String name, Vector3f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(3);
            value.toBuffer(buffer);
            glUniform3fv(getUniform(name), buffer);
        }
    }

	public void loadUniformVec4f(String name, Vector4f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4);
            value.toBuffer(buffer);
            glUniform4fv(getUniform(name), buffer);
        }
    }

	public void loadUniformMat2f(String name, Matrix2f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(2 * 2);
            value.toBuffer(buffer);
            glUniformMatrix2fv(getUniform(name), false, buffer);
        }
    }

	public void loadUniformMat3f(String name, Matrix3f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(3 * 3);
            value.toBuffer(buffer);
            glUniformMatrix3fv(getUniform(name), false, buffer);
        }
    }

	public void loadUniformMat4f(String name, Matrix4f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4 * 4);
            value.toBuffer(buffer);
            glUniformMatrix4fv(getUniform(name), false, buffer);
        }
    }
	
	public int getUniform(String name) {
		if (locationCache.containsKey(name))
			return locationCache.get(name);
		
		int result = glGetUniformLocation(programID, name);
		if (result == -1) 
			System.err.println("Could not find uniform variable '" + name + "'!");
		else
			locationCache.put(name, result);
		return result;
	}
	
	public void start() {
		glUseProgram(programID);
	}
	
	public void stop() {
		glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		glDetachShader(programID, vertID);
		glDetachShader(programID, fragID);
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		glDeleteProgram(programID);
	}

}
