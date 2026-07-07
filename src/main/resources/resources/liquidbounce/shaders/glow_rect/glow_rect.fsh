#version 330

// Outer-glow rounded-rectangle GUI fragment shader (LiquidBounce fork).
//
// Evaluates the signed-distance field of a rounded box (Inigo Quilez), then
// maps the OUTSIDE distance (d > 0) to a soft glow alpha. The inner region is
// left transparent so this composites as a halo around an existing panel
// rather than filling it.
//
// The falloff borrows Complementary-Unbound's "soft bright core, long gentle
// tail" feel: an exponential core for a luminous edge multiplied by a
// smoothstep tail for a clean fade, plus a mild Reinhard-style tonemap on the
// colour so the brightest part of the glow doesn't clip to a flat block.

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

in vec4 vColor;
in vec2 vUv;
flat in vec2 vHalfSize;
flat in vec2 vRadiusSpread;

out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, float r) {
    vec2 q = abs(p) - b + vec2(r);
    return min(max(q.x, q.y), 0.0) + length(max(q, vec2(0.0))) - r;
}

void main() {
    vec4 color = vColor * ColorModulator;
    if (color.a < 0.001) discard;

    // vHalfSize is the EXPANDED half-extent (panel half + spread). Recover the
    // true panel half-extent by subtracting the spread so the SDF measures
    // distance from the real panel edge outward.
    float spread = max(vRadiusSpread.y, 0.5);
    vec2 bExpanded = max(vHalfSize, vec2(0.5));
    vec2 b = max(bExpanded - vec2(spread), vec2(0.5));
    vec2 p = vUv * bExpanded;
    float maxR = min(b.x, b.y);
    float r = clamp(vRadiusSpread.x, 0.0, maxR);

    float d = sdRoundBox(p, b, r);

    // Normalised outside distance 0..1 across the spread band.
    float t = clamp(d / spread, 0.0, 1.0);

    // Soft luminous core (exp) * clean tail (smoothstep), 0 inside the panel.
    float core = exp(-3.2 * t);
    float tail = 1.0 - smoothstep(0.0, 1.0, t);
    float glow = core * tail;

    // Kill the contribution strictly inside the panel so we only draw a halo.
    glow *= step(0.0, d);

    // Mild Reinhard tonemap to keep the bright edge from flat-clipping.
    vec3 rgb = color.rgb;
    rgb = rgb / (rgb + vec3(0.85)) * 1.85;

    float alpha = color.a * glow;
    if (alpha < 0.001) discard;

    fragColor = vec4(rgb, alpha);
}
