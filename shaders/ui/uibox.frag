#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec4 pass_color;

} fs_in;

void main() {

	color = fs_in.pass_color;

}
