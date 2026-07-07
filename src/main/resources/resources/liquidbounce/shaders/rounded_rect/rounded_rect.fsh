#version 330

// Anti-aliased rounded-rectangle GUI fragment shader.
// Evaluates Inigo Quilez's signed-distance field for a rounded box in pixel
// space (vUv * vHalfSize), then converts the signed distance to alpha via
// fwidth-based smoothstep. When stroke width > 0 a band of that width on the
// inside of the edge is kept, otherwise the interior is filled.
//
// #Edit By Claude 2026-06-11: two improvements ported (in concept) from the
// celestial asset shaders:
//   1. PER-CORNER radius — sdRoundBoxPerCorner takes a vec4 of the four corner
//      radii so a panel can round only some corners (e.g. a title band rounded
//      on top, flat on the bottom). The radius for the current fragment's
//      quadrant is selected from vCornerMask (bit per corner; 0xF = all four,
//      the default = old behaviour).
//   2. DITHERING — a tiny hash noise (+/- 0.5/255) is added to the colour,
//      exactly like celestial's gradient.frag / gradient_rect.glsl, to break up
//      the colour banding that dark GUI gradients show on 8-bit panels.

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

in vec4 vColor;
in vec2 vUv;
flat in vec2 vHalfSize;
flat in float vTopRadius;
flat in float vBottomRadius;
flat in float vStroke;
flat in int vCornerMask;

out vec4 fragColor;

#define DITHER (0.5 / 255.0)

// SDF of a rounded box with independent corner radii.
// r = (topRight, bottomRight, topLeft, bottomLeft). GUI UV has top at
// negative Y, so p.y < 0 selects the top radius.
float sdRoundBoxPerCorner(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
    r.x  = (p.y < 0.0) ? r.x  : r.y;
    vec2 q = abs(p) - b + r.x;
    return min(max(q.x, q.y), 0.0) + length(max(q, vec2(0.0))) - r.x;
}

void main() {
    vec4 color = vColor * ColorModulator;
    if (color.a < 0.001) discard;

    vec2 b = max(vHalfSize, vec2(0.5));
    vec2 p = vUv * b;
    float maxR = min(b.x, b.y);
    float topR = clamp(vTopRadius, 0.0, maxR);
    float bottomR = clamp(vBottomRadius, 0.0, maxR);
    float w = max(vStroke, 0.0);

    // Expand the shared radius into the four corners, zeroing any the mask
    // leaves unset. Mask bits: 0 TL, 1 TR, 2 BR, 3 BL.
    float tl = ((vCornerMask & 0x1) != 0) ? topR : 0.0;
    float tr = ((vCornerMask & 0x2) != 0) ? topR : 0.0;
    float br = ((vCornerMask & 0x4) != 0) ? bottomR : 0.0;
    float bl = ((vCornerMask & 0x8) != 0) ? bottomR : 0.0;
    // sdRoundBoxPerCorner wants (topRight, bottomRight, topLeft, bottomLeft).
    vec4 radii = vec4(tr, br, tl, bl);

    float d = sdRoundBoxPerCorner(p, b, radii);
    float aa = max(fwidth(d), 1e-4);

    float alpha;
    if (w > 0.0) {
        float inner = smoothstep(0.0, -aa, d);
        float band = smoothstep(-w - aa, -w + aa, d);
        alpha = inner * band;
    } else {
        alpha = smoothstep(0.0, -aa, d);
    }

    color.a *= alpha;
    if (color.a < 0.001) discard;

    // Ordered hash dither to kill 8-bit gradient banding (celestial-style).
    float noise = fract(sin(dot(gl_FragCoord.xy, vec2(12.9898, 78.233))) * 43758.5453);
    color.rgb += mix(DITHER, -DITHER, noise);

    fragColor = color;
}
