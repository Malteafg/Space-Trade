#version 400 core

layout (location = 0) in vec3 position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

out DATA {

	vec2 textureCoords;

} vs_out;

void main() {

	gl_Position = projectionMatrix * viewMatrix * vec4(position, 1.0);
	vs_out.textureCoords = position.xy;

}
