#version 120

varying vec3 vWorldDir;

uniform vec4 u_Color;
uniform float u_Scale;
uniform float u_Time;

float hash31(vec3 p) {
    return fract(sin(dot(p, vec3(127.1, 311.7, 74.7))) * 43758.5453);
}

float noise3(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    vec3 w = f * f * (3.0 - 2.0 * f);
    float n000 = hash31(i);
    float n100 = hash31(i + vec3(1.0, 0.0, 0.0));
    float n010 = hash31(i + vec3(0.0, 1.0, 0.0));
    float n110 = hash31(i + vec3(1.0, 1.0, 0.0));
    float n001 = hash31(i + vec3(0.0, 0.0, 1.0));
    float n101 = hash31(i + vec3(1.0, 0.0, 1.0));
    float n011 = hash31(i + vec3(0.0, 1.0, 1.0));
    float n111 = hash31(i + vec3(1.0, 1.0, 1.0));
    float x00 = mix(n000, n100, w.x);
    float x10 = mix(n010, n110, w.x);
    float x01 = mix(n001, n101, w.x);
    float x11 = mix(n011, n111, w.x);
    float y0 = mix(x00, x10, w.y);
    float y1 = mix(x01, x11, w.y);
    return mix(y0, y1, w.z);
}

float fbm3(vec3 p) {
    float s = 0.0;
    float a = 0.5;
    for (int i = 0; i < 5; i++) {
        s += a * noise3(p);
        p *= 2.02;
        a *= 0.5;
    }
    return s;
}

void main() {
    vec3 rd = normalize(vWorldDir);
    float sc = max(0.35, u_Scale);
    float t = u_Time;

    float y = rd.y;
    vec3 a = vec3(0.65, 0.45, 0.85);
    vec3 b = vec3(0.95, 0.65, 0.85);
    vec3 c = vec3(0.55, 0.85, 0.92);
    vec3 d = vec3(0.45, 0.55, 0.95);
    float w1 = smoothstep(-0.9, 0.2, y);
    float w2 = smoothstep(-0.3, 0.95, y);
    vec3 base = mix(mix(a, b, w1), mix(c, d, w2), 0.5 + 0.5 * smoothstep(-0.5, 0.8, y));

    vec3 silk = rd * (1.9 * sc) + vec3(sin(t * 0.07), cos(t * 0.05), sin(t * 0.06)) * 0.5;
    float flow = fbm3(silk + vec3(t * 0.04, t * 0.03, -t * 0.035));
    float flow2 = fbm3(silk * 1.6 + vec3(8.0, 2.0, 4.0));
    float silkMask = smoothstep(0.25, 0.92, flow * 0.55 + flow2 * 0.45);

    vec3 silkCol = mix(vec3(0.85, 0.75, 1.0), vec3(0.75, 1.0, 0.95), flow2);
    vec3 col = mix(base, base + silkCol * 0.35, silkMask * 0.55);

    vec3 bokehP = rd * 22.0 * sc + vec3(t * 0.06, t * 0.04, t * 0.05);
    vec3 bid = floor(bokehP);
    vec3 bfr = fract(bokehP) - 0.5;
    float bh = hash31(bid);
    float bokeh = smoothstep(0.42, 0.0, length(bfr)) * smoothstep(0.92, 0.995, bh);
    col += vec3(1.0, 0.92, 1.0) * bokeh * 0.25 * (0.6 + 0.4 * sin(t * 1.2 + bh * 20.0));

    float vign = 0.85 + 0.15 * smoothstep(0.2, 1.0, abs(rd.x) + abs(rd.z));
    col *= vign;

    col *= mix(vec3(1.0), clamp(u_Color.rgb, 0.0, 1.0), 0.35);
    col = pow(clamp(col, 0.0, 1.0), vec3(0.94));
    gl_FragColor = vec4(col, 1.0);
}
