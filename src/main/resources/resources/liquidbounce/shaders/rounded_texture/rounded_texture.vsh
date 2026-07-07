#version 330

// Rounded textured GUI quad. UV0 carries texture coordinates while LocalPos
// carries normalized quad coordinates in [-1, 1] for the SDF clip mask.

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
in ivec2 LocalPos;

out vec2 vTexCoord;
out vec2 vLocalPos;
out vec4 vColor;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vTexCoord = UV0;
    vLocalPos = vec2(LocalPos) * (1.0 / 32767.0);
    vColor = Color;
}
