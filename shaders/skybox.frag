#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec3 textureCoords;

} fs_in;

uniform samplerCube cubeMap;

void main() {

    color = texture(cubeMap, fs_in.textureCoords);
    
}