#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec3 surfaceNormal;
	vec3 toLightVector;
	vec3 color;

} fs_in;

uniform vec3 lightColor;

void main() {

<<<<<<< HEAD
	vec3 unitNormal = normalize(fs_in.surfaceNormal);
	vec3 unitLightVector = normalize(fs_in.toLightVector);

	float nDotl = dot(unitNormal, unitLightVector);
	float brightness = max(nDotl, 0.1);
	vec3 diffuse = brightness * lightColor;

	color = vec4(diffuse, 1.0) * vec4(fs_in.color.xyz, 1.0);
=======
	color = vec4((max(dot(normalize(fs_in.surfaceNormal), normalize(fs_in.toLightVector)), - 0.2) + 0.3) * lightColor, 1.0) * vec4(fs_in.color.xyz, 1.0);
>>>>>>> e7a2d8b88c6b795db8905f0f93cd994e3239ded1

}
