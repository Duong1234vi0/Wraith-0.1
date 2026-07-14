#version 150

#moj_import <mre:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 Size;
uniform vec4 Radius;
uniform float Smoothness;

uniform float Time;
uniform float ShineWidth;

out vec4 OutColor;

void main() {
    float rectAlpha = ralpha(Size, FragCoord, Radius, Smoothness);
    if (rectAlpha <= 0.0) discard;

    float shineCenter = Time * Size.x;
    float dx = abs(FragCoord.x * Size.x - shineCenter);
    float shineHalf = ShineWidth * Size.x * 0.5;
    float shineFactor = 1.0 - smoothstep(0.0, shineHalf, dx);

    float a = FragColor.a * rectAlpha * shineFactor;
    if (a <= 0.001) discard;

    OutColor = vec4(FragColor.rgb, a);
}
