#version 330

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

layout(std140) uniform Projection {
    mat4 ProjMat;
};

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in ivec2 RangeThickness;
in ivec2 SmoothOutline;

out vec2 TexCoord;
out vec4 FragColor;
out vec3 MsdfParams;
flat out int OutlineFlag;

const float FIXED_POINT = 1024.0;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    TexCoord = UV0;
    FragColor = Color;
    MsdfParams = vec3(
        float(RangeThickness.x) / FIXED_POINT,
        float(RangeThickness.y) / FIXED_POINT,
        float(SmoothOutline.x) / FIXED_POINT
    );
    OutlineFlag = SmoothOutline.y;
}
