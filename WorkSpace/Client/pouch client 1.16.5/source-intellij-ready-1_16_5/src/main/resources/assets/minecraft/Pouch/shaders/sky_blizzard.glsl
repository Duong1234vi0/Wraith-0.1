#version 120

varying vec3 vWorldDir;

uniform vec4 u_Color;
uniform float u_Scale;
uniform float u_Time;

#define TAU 6.2831853

float hash12(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

float noise2(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    vec2 u = f * f * (3.0 - 2.0 * f);
    return mix(
        mix(hash12(i), hash12(i + vec2(1.0, 0.0)), u.x),
        mix(hash12(i + vec2(0.0, 1.0)), hash12(i + vec2(1.0, 1.0)), u.x),
        u.y
    );
}

void main() {
    vec3 rd = normalize(vWorldDir);
    float sc = max(0.4, u_Scale);
    float t = u_Time;

    float phi = atan(rd.x, rd.z);
    float y = rd.y;

    vec3 fogLo = vec3(0.72, 0.78, 0.88);
    vec3 fogHi = vec3(0.88, 0.92, 0.98);
    vec3 base = mix(fogLo, fogHi, smoothstep(-0.9, 0.75, y));

    vec2 wind = vec2(phi * sc * 5.0 + t * 0.45, y * sc * 8.0 - t * 0.25);
    float streak = 0.0;
    for (int k = 0; k < 3; k++) {
        float fk = float(k);
        vec2 w = wind * (1.1 + fk * 0.2) + vec2(fk * 19.0, fk * 7.0);
        streak += noise2(w) * (0.35 - fk * 0.08);
    }
    streak = smoothstep(0.25, 0.95, streak) * 0.22;

    vec2 fl = vec2(phi * sc * 22.0, y * 18.0) + vec2(t * 0.6, -t * 0.85);
    vec2 fid = floor(fl);
    vec2 ffr = fract(fl) - 0.5;
    float fh = hash12(fid);
    float flake = smoothstep(0.55, 0.0, length(ffr + vec2(sin(t + fh * 10.0) * 0.1, 0.0)));
    flake *= smoothstep(0.92, 0.998, fh) * 0.35;

    vec3 col = base + vec3(1.0) * streak;
    col += vec3(0.95, 0.97, 1.0) * flake;
    col += vec3(0.9, 0.95, 1.0) * pow(max(0.0, noise2(wind * 3.0)), 4.0) * 0.15;

    col *= mix(vec3(1.0), clamp(u_Color.rgb * vec3(0.95, 1.0, 1.05), 0.0, 1.0), 0.2);
    col = pow(clamp(col, 0.0, 1.0), vec3(0.98));
    gl_FragColor = vec4(col, 1.0);
}
