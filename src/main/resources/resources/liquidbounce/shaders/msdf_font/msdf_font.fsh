#version 330

uniform sampler2D Sampler0;

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

in vec2 TexCoord;
in vec4 FragColor;
in vec3 MsdfParams;
flat in int OutlineFlag;

out vec4 fragColor;

float median(vec3 color) {
    return max(min(color.r, color.g), min(max(color.r, color.g), color.b));
}

void main() {
    float Range = MsdfParams.x;
    float Thickness = MsdfParams.y;
    float Smoothness = MsdfParams.z;
    bool Outline = OutlineFlag != 0;
    float OutlineThickness = 0.0;
    vec4 OutlineColor = vec4(0.0);

    float dist = median(texture(Sampler0, TexCoord).rgb) - 0.5 + Thickness;
    vec2 h = vec2(dFdx(TexCoord.x), dFdy(TexCoord.y)) * textureSize(Sampler0, 0);
    float pixels = Range * inversesqrt(h.x * h.x + h.y * h.y);
    float alpha = smoothstep(-Smoothness, Smoothness, dist * pixels);
    vec4 color = vec4(FragColor.rgb, FragColor.a * alpha);

    if (Outline) {
        color = mix(OutlineColor, FragColor, alpha);
        color.a *= smoothstep(
            -Smoothness,
            Smoothness,
            (dist + OutlineThickness) * pixels
        );
    }

    fragColor = color;
}
