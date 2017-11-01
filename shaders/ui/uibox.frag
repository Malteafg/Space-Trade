#version 400 core

layout (location = 0) out vec4 color;

in DATA {

	vec4 c;

} fs_in;

void main() {

	color = vec4(fs_in.c.x,fs_in.c.y,fs_in.c.z, 1.0);

}
