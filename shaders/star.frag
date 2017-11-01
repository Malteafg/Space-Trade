#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec3 surfaceNormal;
	vec3 camPos;

} fs_in;

void main() {

	vec3 unitNormal = normalize(fs_in.surfaceNormal);
	vec3 unitCamVector = normalize(fs_in.camPos);
	
	float nDotl = dot(unitNormal, unitCamVector);

	color = vec4(max(nDotl, 1), max(nDotl, 1), max(nDotl * 0.7, 0.3), 1.0);
	

}
