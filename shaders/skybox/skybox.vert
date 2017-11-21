#version 400

layout (location = 0) in vec3 position;

out DATA {

	vec2 textureCoords;

} vs_out;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void){
	
	gl_Position = projectionMatrix * viewMatrix * vec4(position, 1.0); 
	vs_out.textureCoords = position.xy;
	
}
