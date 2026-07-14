#version 330

uniform sampler2D BloomTexture;
uniform sampler2D MaskTexture;

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

#define Brightness Params1.x
#define Saturation Params2.z
#define AutoColor Flags0.x

void main() {
    vec2 u = TexCoord;
    vec4 b = texture(BloomTexture, u);
    float i = b.a * (1.0 - texture(MaskTexture, u).a) * Brightness;

    vec3 col;
    if (AutoColor == 1) {
        vec3 c = b.rgb / max(b.a, 0.001);
        float m = max(c.r, max(c.g, c.b));
        if (m > 0.001) {
            c /= m;
        }
        float l = dot(c, vec3(0.299, 0.587, 0.114));
        col = clamp(mix(vec3(l), c, Saturation), 0.0, 1.0);
    } else {
        col = mix(Color0.rgb, Color1.rgb, u.y);
    }

    fragColor = vec4(col, clamp(i, 0.0, 1.0));
}
