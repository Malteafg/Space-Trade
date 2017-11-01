#version 400

layout (location = 0) out vec4 color;

in DATA {

	vec2 textureCoords;

} fs_in;

uniform sampler2D guiTexture;

void main() {

	color = texture(guiTexture, fs_in.textureCoords);

}
