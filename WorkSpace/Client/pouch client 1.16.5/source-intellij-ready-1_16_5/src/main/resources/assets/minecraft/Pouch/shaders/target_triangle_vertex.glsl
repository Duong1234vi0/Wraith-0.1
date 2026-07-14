#version 120

varying vec2 TexCoord;
varying vec4 VertexColor;

void main() {
    TexCoord = gl_MultiTexCoord0.st;
    VertexColor = gl_Color;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
