#version 400 core

layout (location = 0) out vec4 color;

uniform vec3 textcolor;
uniform sampler2D fontAtlas;

in DATA {

	vec2 pass_texCoords;

} fs_in;

const float width = 0.5;
const float edge = 0.15;

void main() {

	float distance = 1.0 - texture(fontAtlas, fs_in.pass_texCoords).a;
	float alpha = 1.0 - smoothstep(width, width + edge, distance);
	color = vec4(textcolor.xyz, alpha);

}
