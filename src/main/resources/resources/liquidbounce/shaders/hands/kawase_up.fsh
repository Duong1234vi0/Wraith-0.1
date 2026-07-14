#version 330

uniform sampler2D Sampler0;

layout(std140) uniform HandsConfig {
    vec4 Color0;
    vec4 Color1;
    vec4 Params0;
    vec4 Params1;
    vec4 Params2;
    vec4 Params3;
    ivec4 Flags0;
};

in vec2 TexCoord;
out vec4 fragColor;

void main() {
    vec2 h = Params0.xy * Params0.z;
    vec2 u = TexCoord;
    vec4 s =
        texture(Sampler0, u + vec2(-h.x * 2.0, 0.0)) +
        texture(Sampler0, u + vec2(-h.x, h.y)) * 2.0 +
        texture(Sampler0, u + vec2(0.0, h.y * 2.0)) +
        texture(Sampler0, u + vec2(h.x, h.y)) * 2.0 +
        texture(Sampler0, u + vec2(h.x * 2.0, 0.0)) +
        texture(Sampler0, u + vec2(h.x, -h.y)) * 2.0 +
        texture(Sampler0, u + vec2(0.0, -h.y * 2.0)) +
        texture(Sampler0, u + vec2(-h.x, -h.y)) * 2.0;
    fragColor = s / 12.0;
}
