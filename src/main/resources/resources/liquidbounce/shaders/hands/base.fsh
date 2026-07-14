#version 330

uniform sampler2D SceneBefore;
uniform sampler2D RawAfter;
uniform sampler2D DepthSampler;
uniform sampler2D HandMaskSampler;
uniform sampler2D BlurredScene;

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

#define Time Params0.x
#define ViewWidth Params0.y
#define ViewHeight Params0.z
#define FillOpacity Params1.x
#define ShadingStrength Params1.y
#define MirrorMix Params1.z
#define RainbowSpeed Params2.x
#define RainbowScale Params2.y
#define Mode Flags0.x
#define Rainbow Flags0.y
#define KeepShading Flags0.z
#define MaskMode Flags0.w
#define MASK_VANILLA_CAPTURE 0
#define MASK_IRIS_EXACT_MASK 1

vec3 hue2rgb(float h) {
    vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0 / 3.0, 1.0 / 3.0)) * 6.0 - 3.0);
    return clamp(p - 1.0, 0.0, 1.0);
}

float handMask(vec2 uv) {
    if (MaskMode == MASK_IRIS_EXACT_MASK) {
        return texture(HandMaskSampler, uv).r;
    }

    float depth = texture(DepthSampler, uv).r;
    float depthMask = float(depth >= 0.001 && depth < 0.9999);
    vec3 beforeRgb = texture(SceneBefore, uv).rgb;
    vec3 afterRgb = texture(RawAfter, uv).rgb;
    float diff = length(afterRgb - beforeRgb);
    return depthMask * smoothstep(0.002, 0.02, diff);
}

void main() {
    vec2 uv = TexCoord;
    float alpha = handMask(uv);
    if (alpha < 0.01) {
        fragColor = vec4(0.0);
        return;
    }

    vec4 srcColor = vec4(texture(RawAfter, uv).rgb, alpha);

    if (Mode == 0) {
        vec2 blurredUV = gl_FragCoord.xy / max(vec2(ViewWidth, ViewHeight), vec2(1.0));
        blurredUV.y = 1.0 - blurredUV.y;
        vec4 blurTinted = texture(BlurredScene, blurredUV) * Color0 * alpha;
        fragColor = mix(srcColor, blurTinted, MirrorMix);
        fragColor.a = alpha;
        return;
    }

    vec3 fillColor;
    if (Rainbow == 1) {
        float yN = gl_FragCoord.y / max(ViewHeight, 1.0);
        float h = fract(yN * RainbowScale + Time * RainbowSpeed);
        fillColor = hue2rgb(h);
    } else {
        fillColor = Color0.rgb;
    }

    if (KeepShading == 1) {
        float luminance = dot(srcColor.rgb, vec3(0.299, 0.587, 0.114));
        fillColor *= mix(1.0, luminance, ShadingStrength);
    }

    fragColor = vec4(mix(srcColor.rgb, fillColor, FillOpacity), srcColor.a);
}
