#version 400 core

layout (location = 0) in vec2 position;

uniform mat4 transformationMatrix;
uniform vec3 color;

out DATA {

	vec4 c;

} vs_out;

void main()  {

	gl_Position = transformationMatrix * vec4(position, 0.0, 1.0);

	if(position.y > 0) {
		vs_out.c = vec4(color.xyz, 1.0);
	} else {
		vs_out.c = vec4(color.xyz * 0.6, 1.0);
	}

}
