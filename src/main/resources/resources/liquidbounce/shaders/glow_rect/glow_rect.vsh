#version 330

// Outer-glow rounded-rectangle GUI vertex shader (LiquidBounce fork).
// Shares the per-vertex packing scheme of rounded_rect.vsh: HalfSize and
// RadiusStroke are fixed-point ivec2 (px * 16). Here RadiusStroke.y carries
// the glow SPREAD (in px) instead of a stroke width, and the quad is expanded
// by that spread on the CPU side so the fragment shader can fade the SDF
// distance outward into transparency.

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
in ivec2 HalfSize;
in ivec2 RadiusStroke;

out vec4 vColor;
out vec2 vUv;
flat out vec2 vHalfSize;
flat out vec2 vRadiusSpread;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vColor = Color;
    vUv = UV0;
    vHalfSize = vec2(HalfSize) * (1.0 / 16.0);
    vRadiusSpread = vec2(RadiusStroke) * (1.0 / 16.0);
}
