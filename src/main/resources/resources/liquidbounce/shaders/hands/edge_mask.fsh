#version 330

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

float expandedHandMask(vec2 uv) {
    vec2 px = 1.0 / vec2(textureSize(MaskTexture, 0));

    float result = texture(MaskTexture, uv).a;
    result = max(result, texture(MaskTexture, uv + vec2( px.x, 0.0)).a);
    result = max(result, texture(MaskTexture, uv + vec2(-px.x, 0.0)).a);
    result = max(result, texture(MaskTexture, uv + vec2(0.0,  px.y)).a);
    result = max(result, texture(MaskTexture, uv + vec2(0.0, -px.y)).a);

    return result;
}

void main() {
    vec2 uv = TexCoord;
    vec4 center = texture(MaskTexture, uv);
    fragColor = vec4(center.rgb, expandedHandMask(uv));
}
