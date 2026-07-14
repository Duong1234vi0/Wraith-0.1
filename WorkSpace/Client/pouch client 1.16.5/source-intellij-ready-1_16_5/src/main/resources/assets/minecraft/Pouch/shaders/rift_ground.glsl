#version 120

uniform float time;
uniform float alpha;
uniform vec4 color;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));
    vec2 u = f * f * (3.0 - 2.0 * f);
    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);
}

float ringMask(float r, float radius, float width) {
    return smoothstep(width, 0.0, abs(r - radius));
}

void main() {
    vec2 uv = gl_TexCoord[0].st * 2.0 - 1.0;
    float r = length(uv);
    float a = atan(uv.y, uv.x);

    float ang = (a + 3.1415926) / 6.2831853;
    float rot = time * 0.15;
    float n = noise(vec2(uv.x * 6.0 + rot, uv.y * 6.0 - rot)) * 0.8;

    float glyph = smoothstep(0.25, 0.85, sin(ang * 18.0 + n * 7.0) * 0.5 + 0.5);
    float ring1 = ringMask(r, 0.60, 0.022);
    float ring2 = ringMask(r, 0.40, 0.020);
    float ring3 = ringMask(r, 0.25, 0.018);
    float band = (ring1 * 1.0 + ring2 * 0.7 + ring3 * 0.4) * glyph;

    float spokes = smoothstep(0.10, 0.0, abs(sin(a * 8.0 + time * 0.7))) * smoothstep(0.72, 0.12, r);
    float inner = smoothstep(0.42, 0.0, r) * 0.18;

    float edge = smoothstep(1.0, 0.55, r);
    float flicker = 0.86 + 0.14 * sin(time * 4.2 + ang * 12.0);

    float glow = (band * 0.9 + spokes * 0.5 + inner) * edge;
    float halo = smoothstep(0.95, 0.55, r) * 0.22;

    float aOut = (glow + halo) * alpha * flicker;
    gl_FragColor = vec4(color.rgb, aOut * color.a);
}
