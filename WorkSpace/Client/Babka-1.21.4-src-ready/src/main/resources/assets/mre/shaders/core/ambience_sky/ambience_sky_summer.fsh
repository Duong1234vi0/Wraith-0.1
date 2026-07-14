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

#define TAU 6.28318530718
#define PI  3.14159265359

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

float sk_hash(vec3 p) {
    return fract(sin(dot(p, vec3(127.1, 311.7, 74.7))) * 43758.5453123);
}

float sk_noise(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    vec3 w3 = f * f * (3.0 - 2.0 * f);
    float n000 = sk_hash(i);
    float n100 = sk_hash(i + vec3(1.0, 0.0, 0.0));
    float n010 = sk_hash(i + vec3(0.0, 1.0, 0.0));
    float n110 = sk_hash(i + vec3(1.0, 1.0, 0.0));
    float n001 = sk_hash(i + vec3(0.0, 0.0, 1.0));
    float n101 = sk_hash(i + vec3(1.0, 0.0, 1.0));
    float n011 = sk_hash(i + vec3(0.0, 1.0, 1.0));
    float n111 = sk_hash(i + vec3(1.0, 1.0, 1.0));
    float x00 = mix(n000, n100, w3.x);
    float x10 = mix(n010, n110, w3.x);
    float x01 = mix(n001, n101, w3.x);
    float x11 = mix(n011, n111, w3.x);
    float y0 = mix(x00, x10, w3.y);
    float y1 = mix(x01, x11, w3.y);
    return mix(y0, y1, w3.z);
}

float sk_fbm(vec3 p) {
    float s = 0.0;
    float a = 0.5;
    for (int i = 0; i < 5; i++) {
        s += a * sk_noise(p);
        p *= 2.07;
        a *= 0.5;
    }
    return s;
}

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 rd = normalize(rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV);

    float t = uTime * uSpeed * 0.3;
    float n = 0.0;

    float breathe = 0.93 + 0.07 * sin(t * 0.26);
    float y = rd.y;

    float hazeMask = smoothstep(0.2, -0.38, y);
    hazeMask *= hazeMask;
    vec3 hazeOff = vec3(
        sin(t * 2.15 + dot(rd, vec3(1.0, 0.55, 0.35)) * 12.0),
        cos(t * 1.95 + dot(rd, vec3(-0.45, 1.0, 0.5)) * 11.5),
        sin(t * 2.35 + dot(rd, vec3(0.25, -0.4, 1.0)) * 13.0)
    ) * (0.0055 * hazeMask * uScale);

    vec3 rsum = rd + hazeOff;
    float rlen = length(rsum);
    vec3 rs = (rlen > 1.0e-4) ? (rsum / rlen) : rd;

    float sc = uScale;
    vec3 drift = vec3(t * 0.042, t * 0.024, t * 0.033);

    vec3 zenith = vec3(uColor.r * 0.4, uColor.g * 0.6, uColor.b * 0.9);
    vec3 horizonLight = uColor2;
    vec3 grassHint = vec3(0.1, 0.35, 0.2);

    float gradMix = smoothstep(-0.38, 0.68, y);
    vec3 skyBase = mix(horizonLight, zenith, gradMix);
    float nearGround = (1.0 - gradMix) * smoothstep(-0.55, 0.18, y);
    skyBase = mix(skyBase, mix(skyBase, grassHint, 0.4), nearGround * 0.55);

    vec3 pC = rs * sc * 1.85 + drift;
    float boil = sk_fbm(pC * 2.35 + vec3(t * 0.11));
    float cLow = sk_fbm(pC + vec3(boil * 0.5, -boil * 0.38, boil * 0.22));
    float cHi = sk_fbm(pC * 1.35 - drift * 0.55 + vec3(17.3, 84.1, 31.7));
    float clouds = cLow * 0.58 + cHi * 0.42;
    clouds = pow(clamp(clouds, 0.0, 1.0), 0.82);
    float puffCore = smoothstep(0.5, 0.84, clouds);
    float puffEdge = smoothstep(0.36, 0.9, clouds) * (1.0 - puffCore) * 0.45;
    vec3 cloudTint = vec3(1.0, 0.99, 0.97);
    vec3 col = mix(skyBase, cloudTint, puffCore);
    col = mix(col, mix(skyBase, cloudTint, 0.88), puffEdge);
    col += vec3(1.0, 0.92, 0.75) * puffEdge * 0.12 * breathe;

    vec3 gBase = rs * sc * 1.25 + drift * 0.85 + vec3(0.0, sin(t * 0.28) * 0.12, 0.0);
    float g1 = sk_fbm(gBase + sk_fbm(gBase * 1.65 + vec3(t * 0.095)) * 0.55);
    float g2 = sk_fbm(gBase * 2.2 - vec3(t * 0.05, t * 0.03, t * 0.04));
    float goldMask = smoothstep(0.52, 0.8, g1 * 0.55 + g2 * 0.45);
    goldMask *= smoothstep(-0.15, 0.58, y);
    goldMask *= 0.55 + 0.45 * sin(dot(rs, vec3(1.15, 2.05, 0.75)) * 3.0 + t * 0.7);
    vec3 goldA = vec3(1.0, 0.843, 0.0);
    vec3 goldB = vec3(1.0, 0.58, 0.15);
    vec3 goldCol = mix(goldA, goldB, sin(t * 0.45 + dot(rs, vec3(0.55, 1.95, 1.05)) * 4.0) * 0.5 + 0.5);
    col += goldCol * goldMask * 0.5 * breathe;

    float airLight = pow(max(0.0, 1.0 - abs(y) - 0.04), 2.15);
    col = mix(col, col * 1.06 + vec3(0.06, 0.07, 0.03), airLight * 0.38);

    col *= uAlpha;

    float alpha = uAlpha * clamp(puffCore * 0.8 + puffEdge * 0.5 + 0.3, 0.0, 1.0);
    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
