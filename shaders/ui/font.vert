#version 400 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoords;

uniform vec2 translation;

out DATA {

	vec2 pass_texCoords;

} vs_out;

void main() {

	gl_Position = vec4(position.xy + translation * vec2(2.0, -2.0), 0.0, 1.0);
	vs_out.pass_texCoords = texCoords;

}
