#version 150

uniform float uTime;
uniform vec2 uResolution;
uniform vec3 uColor;
uniform vec3 uColor2;
uniform float uAlpha;
uniform float uSpeed;
uniform float uScale;
uniform vec2 uCameraDir;
uniform float uFov;

out vec4 fragColor;

mat3 rotX(float a) {
    float c = cos(a), s = sin(a);
    return mat3(1.0, 0.0, 0.0,
                0.0,   c,   s,
                0.0,  -s,   c);
}
mat3 rotY(float a) {
    float c = cos(a), s = sin(a);
    return mat3(  c, 0.0,   s,
                0.0, 1.0, 0.0,
                 -s, 0.0,   c);
}

float hash21(vec2 p) {
    p = fract(p * vec2(234.34, 435.45));
    p += dot(p, p + 23.345);
    return fract(p.x * p.y);
}

float noise3D(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    float a = hash21(i.xy + i.z * 31.0);
    float b = hash21(i.xy + (i.z + 1.0) * 31.0);
    return mix(a, b, f.z);
}

vec3 fireColor(float t) {
    vec3 c1 = vec3(1.0, 0.95, 0.6);
    vec3 c2 = vec3(1.0, 0.5, 0.05);
    vec3 c3 = vec3(0.9, 0.05, 0.0);
    vec3 c4 = vec3(0.2, 0.0, 0.0);
    vec3 col = mix(c1, c2, smoothstep(0.0, 0.3, t));
    col = mix(col, c3, smoothstep(0.3, 0.6, t));
    col = mix(col, c4, smoothstep(0.6, 0.9, t));
    return col;
}

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 rayW = rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV;

    float scale = uScale * 0.5;
    float t = uTime * uSpeed * 0.3;

    vec3 p = rayW * scale + vec3(0.0, t * 0.5, 0.0);
    float n = 0.0;
    float amp = 1.0;
    float freq = 1.0;
    for (int i = 0; i < 5; i++) {
        vec3 q = p * freq + vec3(t * freq * 0.3, t * 0.2, t * freq * 0.1);
        n += noise3D(q) * amp;
        amp *= 0.5;
        freq *= 2.0;
    }
    n = n * 0.5 + 0.2;

    float horizon = 1.0 - abs(rayW.y) * 0.5;
    float heatHaze = sin(p.y * 10.0 + t * 3.0) * 0.1;

    vec3 col = fireColor(n + heatHaze);
    col *= (1.0 + 0.3 * n) * horizon;

    float embers = 0.0;
    for (int i = 0; i < 8; i++) {
        float fi = float(i);
        vec2 ep = vec2(
            sin(fi * 2.399 + t * 0.5 + fi * 0.7) * 0.8,
            fract(fi * 0.137 + t * 0.15) * 2.0 - 1.0
        );
        vec2 er = rayW.xy * 0.5;
        float d = length(er - ep);
        embers += 0.4 * smoothstep(0.08, 0.0, d) * (1.0 - abs(ep.y)) * (0.5 + 0.5 * sin(t * 4.0 + fi));
    }

    col += vec3(1.0, 0.6, 0.1) * embers;
    col *= uAlpha;

    float alpha = uAlpha * clamp(max(n, embers * 2.0), 0.0, 1.0);
    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
