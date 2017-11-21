#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec3 surfaceNormal;
	vec3 toLightVector;
	vec3 color;

} fs_in;

uniform vec3 lightColor;

void main() {

	color = vec4((max(dot(normalize(fs_in.surfaceNormal), normalize(fs_in.toLightVector)), - 0.1) + 0.2) * lightColor, 1.0) * vec4(fs_in.color.xyz, 1.0);

}
