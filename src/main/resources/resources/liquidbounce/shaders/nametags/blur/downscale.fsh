#version 330

uniform sampler2D Sampler0;

layout(std140) uniform KawaseConfig {
    vec4 Params;
};

in vec2 TexCoord;

out vec4 fragColor;

#define uHalfTexelSize Params.xy
#define uOffset Params.z

void main() {
    fragColor = (
        texture(Sampler0, TexCoord) * 4.0 +
        texture(Sampler0, TexCoord - uHalfTexelSize.xy * uOffset) +
        texture(Sampler0, TexCoord + uHalfTexelSize.xy * uOffset) +
        texture(Sampler0, TexCoord + vec2(uHalfTexelSize.x, -uHalfTexelSize.y) * uOffset) +
        texture(Sampler0, TexCoord - vec2(uHalfTexelSize.x, -uHalfTexelSize.y) * uOffset)
    ) / 8.0;

    fragColor.a = 1.0;
}
