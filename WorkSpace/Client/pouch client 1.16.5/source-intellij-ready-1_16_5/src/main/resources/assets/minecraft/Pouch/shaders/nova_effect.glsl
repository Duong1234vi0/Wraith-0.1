#version 120

uniform vec4 u_Color;
uniform float u_Alpha;
uniform float u_Time;
uniform float u_Crit;

float sdSegment(vec2 p, vec2 a, vec2 b) {
    vec2 pa = p - a;
    vec2 ba = b - a;
    float h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);
    return length(pa - ba * h);
}

void main() {
    vec2 uv = gl_TexCoord[0].st * 2.0 - 1.0;
    float r = length(uv);
    if (r > 1.02) discard;

    float t = u_Time;
    float a0 = -1.5707963 + t * 0.7;
    vec2 p0 = 0.88 * vec2(cos(a0), sin(a0));
    vec2 p1 = 0.88 * vec2(cos(a0 + 1.2566371), sin(a0 + 1.2566371));
    vec2 p2 = 0.88 * vec2(cos(a0 + 2.5132742), sin(a0 + 2.5132742));
    vec2 p3 = 0.88 * vec2(cos(a0 + 3.7699113), sin(a0 + 3.7699113));
    vec2 p4 = 0.88 * vec2(cos(a0 + 5.0265484), sin(a0 + 5.0265484));

    float lineW = 0.02;
    float dStar = min(min(min(sdSegment(uv, p0, p2), sdSegment(uv, p2, p4)), min(sdSegment(uv, p4, p1), sdSegment(uv, p1, p3))), sdSegment(uv, p3, p0));
    float star = 1.0 - smoothstep(0.0, 0.05, dStar - lineW);

    float ringD = abs(r - 0.9);
    float ring = 1.0 - smoothstep(0.0, 0.06, ringD - 0.012);

    float pulse = 0.75 + 0.25 * sin(t * 3.2);
    float critBurst = exp(-pow(max(r - (0.18 + t * 0.22), 0.0) * 9.0, 2.0)) * u_Crit;

    float alpha = clamp((star * 0.95 + ring * 0.65 + critBurst * 0.8) * pulse, 0.0, 1.0) * u_Alpha;
    if (alpha < 0.015) discard;

    vec3 col = mix(u_Color.rgb * 0.65, u_Color.rgb, star + ring * 0.3);
    col += vec3(1.0, 0.95, 0.85) * critBurst * 0.45;

    gl_FragColor = vec4(col, alpha * u_Color.a);
}
