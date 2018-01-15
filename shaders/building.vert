#version 400 core

layout (location = 0) in vec3 position;
layout (location = 2) in vec3 normal;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 transformationMatrix;
uniform vec3 lightPos;
uniform vec3 color;

out DATA {

	vec3 surfaceNormal;
	vec3 toLightVector;
	vec3 color;

} vs_out;

void main() {

	vec4 worldPos = transformationMatrix * vec4(position.xyz, 1.0);
	gl_Position = projectionMatrix * viewMatrix * worldPos;

	vs_out.surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;
	vs_out.toLightVector = lightPos - worldPos.xyz;

	vs_out.color = color;

}
