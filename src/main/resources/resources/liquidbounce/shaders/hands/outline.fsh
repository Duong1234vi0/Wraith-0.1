#version 330

uniform sampler2D MaskTexture;
uniform sampler2D BloomTexture;

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
#define ViewHeight Params0.z
#define OutlineWidth Params1.x
#define RainbowSpeed Params2.x
#define RainbowScale Params2.y
#define Saturation Params2.z
#define ColorMode Flags0.x

vec3 hue2rgb(float h) {
    vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0 / 3.0, 1.0 / 3.0)) * 6.0 - 3.0);
    return clamp(p - 1.0, 0.0, 1.0);
}

void main() {
    vec2 uv = TexCoord;
    vec4 c = texture(MaskTexture, uv);
    if (c.a > 0.01) {
        fragColor = vec4(0.0);
        return;
    }

    vec2 texelSize = 1.0 / vec2(textureSize(MaskTexture, 0));
    float maxA = 0.0;
    for (int x = -2; x <= 2; x++) {
        for (int y = -2; y <= 2; y++) {
            if (x == 0 && y == 0) {
                continue;
            }
            vec2 d = vec2(float(x), float(y)) * texelSize * OutlineWidth;
            maxA = max(maxA, texture(MaskTexture, uv + d).a);
        }
    }

    if (maxA < 0.01) {
        fragColor = vec4(0.0);
        return;
    }

    vec3 col;
    if (ColorMode == 1) {
        float yN = gl_FragCoord.y / max(ViewHeight, 1.0);
        float h = fract(yN * RainbowScale + Time * RainbowSpeed);
        col = hue2rgb(h);
    } else if (ColorMode == 2) {
        vec4 b = texture(BloomTexture, uv);
        vec3 cb = b.rgb / max(b.a, 0.001);
        float m = max(cb.r, max(cb.g, cb.b));
        if (m > 0.001) {
            cb /= m;
        }
        float l = dot(cb, vec3(0.299, 0.587, 0.114));
        col = clamp(mix(vec3(l), cb, Saturation), 0.0, 1.0);
    } else {
        col = Color0.rgb;
    }

    fragColor = vec4(col, maxA * Color0.a);
}
