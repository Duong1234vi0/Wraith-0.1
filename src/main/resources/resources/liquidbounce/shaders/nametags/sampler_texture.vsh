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
in vec4 Color;
in vec2 Location;
in ivec2 Size;
in ivec2 Radius;

out vec4 vColor;
flat out vec2 vLocation;
flat out vec2 vSize;
flat out vec4 vRound;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vColor = vec4(Color.rgb, ColorModulator.a);
    vLocation = Location;
    vSize = vec2(Size) * (1.0 / 16.0);
    float radius = float(Radius.x) * (1.0 / 256.0);
    vRound = vec4(radius);
}
