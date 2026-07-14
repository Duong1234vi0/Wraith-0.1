#version 330

uniform sampler2D CurrentMain;
uniform sampler2D BaseHand;
uniform sampler2D GlowTexture;
uniform sampler2D OutlineTexture;
uniform sampler2D TrailTexture;

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

#define UseGlow Flags0.x
#define UseOutline Flags0.y
#define UseTrail Flags0.z

vec3 overRgb(vec3 dst, vec4 src) {
    float a = clamp(src.a, 0.0, 1.0);
    return mix(dst, src.rgb, a);
}

void main() {
    vec2 uv = TexCoord;
    vec3 color = texture(CurrentMain, uv).rgb;

    vec4 base = texture(BaseHand, uv);
    color = overRgb(color, base);

    if (UseGlow == 1) {
        vec4 glow = texture(GlowTexture, uv);
        color += glow.rgb * glow.a;
    }

    if (UseTrail == 1) {
        vec4 trail = texture(TrailTexture, uv);
        color += trail.rgb * trail.a;
    }

    if (UseOutline == 1) {
        vec4 outline = texture(OutlineTexture, uv);
        color = overRgb(color, outline);
    }

    fragColor = vec4(clamp(color, 0.0, 1.0), 1.0);
}
