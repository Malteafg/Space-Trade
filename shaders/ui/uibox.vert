#version 400 core

layout (location = 0) in vec2 position;

uniform mat4 transformationMatrix;
uniform vec4 color;

out DATA {

	vec4 pass_color;

} vs_out;

void main()  {

	gl_Position = transformationMatrix * vec4(position, 0.0, 1.0);

	if(position.y < 0) {
		vs_out.pass_color = vec4(color.x * 0.4, color.y * 0.7, color.z * 0.75, color.w);
	} else {
		vs_out.pass_color = color;
	}

}
