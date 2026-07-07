#version 330

// Anti-aliased rounded-rectangle GUI vertex shader (LiquidBounce fork port).
// Per-vertex SDF parameters: half-extents (HalfSize) and corner radius / stroke
// width (RadiusStroke), both packed as fixed-point integers (px * 16) into ivec2
// attributes so the same pipeline draws fill, outline and gradient rectangles
// without a per-element UBO.
//
// #Edit By Claude 2026-06-11: RadiusStroke.y now ALSO carries a 4-bit per-corner
// rounding mask in its high bits (1.26.2 VertexConsumer only exposes UV0/UV1/UV2,
// so there is no room for a third ivec2 attribute). Layout of RadiusStroke.y:
//   bits 0..10  = stroke width  (px * 16, fixed point, up to ~127px)
//   bits 11..14 = corner mask   (bit0 TL, bit1 TR, bit2 BR, bit3 BL)
// A mask of 0xF rounds all four corners (the default, identical to old behaviour).
// #Edit By Codex 2026-06-19: negative RadiusStroke.x now means top/bottom
// radii are packed as 7-bit fixed point values (px * 4), preserving the
// positive shared-radius encoding for every existing draw call.

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
flat out float vTopRadius;
flat out float vBottomRadius;
flat out float vStroke;
flat out int vCornerMask;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vColor = Color;
    vUv = UV0;
    vHalfSize = vec2(HalfSize) * (1.0 / 16.0);

    int rsx = RadiusStroke.x;
    if (rsx < 0) {
        int packedRadius = -rsx;
        vTopRadius = float(packedRadius & 0x7F) * (1.0 / 4.0);
        vBottomRadius = float((packedRadius >> 7) & 0x7F) * (1.0 / 4.0);
    } else {
        float sharedRadius = float(rsx) * (1.0 / 16.0);
        vTopRadius = sharedRadius;
        vBottomRadius = sharedRadius;
    }

    int rsy = RadiusStroke.y;
    vStroke = float(rsy & 0x7FF) * (1.0 / 16.0);
    vCornerMask = (rsy >> 11) & 0xF;
}
