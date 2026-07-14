#version 120

uniform float time;
uniform float alpha;
uniform vec4 color;
uniform float isCrit;

float sdSegment(vec2 p, vec2 a, vec2 b) {
    vec2 pa = p - a, ba = b - a;
    float h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);
    return length(pa - ba * h);
}

float sdCross(vec2 p, float gap, float len, float thick) {
    float s = 0.7071067811865476;
    vec2 rp = vec2(s * p.x + s * p.y, -s * p.x + s * p.y);
    float d = 1e10;
    d = min(d, sdSegment(rp, vec2(gap, 0.0), vec2(len, 0.0)));
    d = min(d, sdSegment(rp, vec2(-gap, 0.0), vec2(-len, 0.0)));
    d = min(d, sdSegment(rp, vec2(0.0, gap), vec2(0.0, len)));
    d = min(d, sdSegment(rp, vec2(0.0, -gap), vec2(0.0, -len)));
    return d - thick;
}

void main() {
    vec2 uv = gl_TexCoord[0].st * 2.0 - 1.0;

    float appear = smoothstep(0.0, 0.15, time);
    float gap = mix(0.08, 0.18, appear);
    float len = mix(0.35, 0.55, appear);
    float thick = 0.055;

    float d = sdCross(uv, gap, len, thick);

    float shape = 1.0 - smoothstep(-0.01, 0.02, d);
    float glow = exp(-max(d, 0.0) * 12.0) * 0.5;
    float outerGlow = exp(-max(d, 0.0) * 4.0) * 0.15;

    float pulse = 0.92 + 0.08 * sin(time * 8.0);
    shape *= pulse;

    float critRing = 0.0;
    float critSparkle = 0.0;
    if (isCrit > 0.5) {
        float dist = length(uv);
        float ringPos = 0.2 + time * 0.6;
        float ring = exp(-pow((dist - ringPos) * 8.0, 2.0)) * (1.0 - time);
        critRing = ring * 0.7;

        float angle = atan(uv.y, uv.x);
        float sparkle = pow(max(sin(angle * 8.0 + time * 20.0), 0.0), 8.0);
        sparkle *= exp(-max(dist - ringPos, 0.0) * 6.0) * (1.0 - time);
        critSparkle = sparkle * 0.5;
    }

    float finalAlpha = (shape + glow + outerGlow + critRing + critSparkle) * alpha;
    finalAlpha = clamp(finalAlpha, 0.0, 1.0);

    vec3 col = color.rgb;
    float brightness = shape + glow * 0.5;
    col = mix(col, vec3(1.0), brightness * 0.15);

    if (isCrit > 0.5) {
        col = mix(col, vec3(1.0, 0.95, 0.8), (critRing + critSparkle) * 0.6);
    }

    gl_FragColor = vec4(col, finalAlpha * color.a);
}
