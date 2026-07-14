#version 330

uniform sampler2D HistoryTexture;
uniform sampler2D CurrentGlowTexture;
uniform sampler2D ModelTexture;

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

#define Fade Params1.x
#define ModelOpacity Params1.y
#define Time Params3.x
#define DeltaTime Params3.y
#define Turbulence Params3.z
#define Flicker Params3.w
#define Offset Params0.xy
#define ModelTrail Flags0.x
#define ClearHistory Flags0.y

float wob(float y, float tt) {
    return sin(y * 9.0 + tt * 4.3) * 0.6
        + sin(y * 17.0 - tt * 2.1) * 0.3
        + sin(y * 4.0 + tt * 1.3) * 0.4
        + sin(y * 28.0 + tt * 5.7) * 0.15;
}

vec4 blurSample(vec2 p) {
    vec2 px = 1.0 / max(vec2(textureSize(HistoryTexture, 0)), vec2(1.0));
    vec4 c = texture(HistoryTexture, p) * 0.36;
    c += texture(HistoryTexture, p + vec2( px.x, 0.0)) * 0.16;
    c += texture(HistoryTexture, p + vec2(-px.x, 0.0)) * 0.16;
    c += texture(HistoryTexture, p + vec2(0.0,  px.y)) * 0.16;
    c += texture(HistoryTexture, p + vec2(0.0, -px.y)) * 0.16;
    return c;
}

vec4 over(vec4 dst, vec4 src) {
    float a = clamp(src.a, 0.0, 1.0);
    return vec4(mix(dst.rgb, src.rgb, a), max(dst.a, a));
}

void main() {
    if (ClearHistory == 1) {
        fragColor = vec4(0.0);
        return;
    }

    vec2 uv = TexCoord;
    float dxTurb = (wob(uv.y, Time) - wob(uv.y, Time - DeltaTime)) * Turbulence;
    float yFactor = 0.7 + uv.y * 0.8;
    vec2 disp = vec2(Offset.x + dxTurb, Offset.y * yFactor);
    vec4 c = blurSample(uv - disp);

    float flick = 1.0 - Flicker + Flicker * (0.5 + 0.5 * sin(Time * 14.0) + 0.25 * sin(Time * 9.3));
    float oldA = c.a;
    float newA = max(0.0, oldA * (0.985 * flick) - Fade);
    float scale = oldA > 0.001 ? newA / oldA : 0.0;
    vec4 result = vec4(c.rgb * scale, newA);

    vec4 currentGlow = texture(CurrentGlowTexture, uv);
    result.rgb += currentGlow.rgb * currentGlow.a;
    result.a = max(result.a, currentGlow.a);

    if (ModelTrail == 1) {
        vec4 model = texture(ModelTexture, uv);
        model.a *= ModelOpacity;
        result = over(result, model);
    }

    fragColor = clamp(result, 0.0, 1.0);
}
