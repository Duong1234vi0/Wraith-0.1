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
        texture(Sampler0, TexCoord + vec2(-uHalfTexelSize.x * 2.0, 0.0) * uOffset) +
        texture(Sampler0, TexCoord + vec2(-uHalfTexelSize.x, uHalfTexelSize.y) * uOffset) * 2.0 +
        texture(Sampler0, TexCoord + vec2(0.0, uHalfTexelSize.y * 2.0) * uOffset) +
        texture(Sampler0, TexCoord + uHalfTexelSize * uOffset) * 2.0 +
        texture(Sampler0, TexCoord + vec2(uHalfTexelSize.x * 2.0, 0.0) * uOffset) +
        texture(Sampler0, TexCoord + vec2(uHalfTexelSize.x, -uHalfTexelSize.y) * uOffset) * 2.0 +
        texture(Sampler0, TexCoord + vec2(0.0, -uHalfTexelSize.y * 2.0) * uOffset) +
        texture(Sampler0, TexCoord - uHalfTexelSize * uOffset) * 2.0
    ) / 12.0;

    fragColor.a = 1.0;
}
