#version 150

uniform sampler2D Sampler0;
uniform float Saturation;

in vec2 TexCoord;
in vec4 FragColor;

out vec4 OutColor;

void main() {
    vec4 color = texture(Sampler0, TexCoord) * FragColor;
    float luma = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    color.rgb = mix(vec3(luma), color.rgb, Saturation);
    OutColor = color;
}
