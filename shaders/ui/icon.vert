#version 400

layout (location = 0) in vec2 position;

out DATA {

	vec2 textureCoords;

} vs_out;

uniform mat4 transformationMatrix;

void main(){

	gl_Position = transformationMatrix * vec4(position, 0.0, 1.0);
	vs_out.textureCoords = vec2((position.x + 1.0) / 2.0, 1 - (position.y + 1.0) / 2.0);

}
