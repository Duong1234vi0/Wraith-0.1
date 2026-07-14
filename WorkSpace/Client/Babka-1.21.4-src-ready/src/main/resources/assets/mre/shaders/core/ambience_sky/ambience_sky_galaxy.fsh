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

float hash(vec2 p) {
    p = fract(p * vec2(234.34, 435.45));
    p += dot(p, p + 23.345);
    return fract(p.x * p.y);
}

vec3 hash3(vec2 p) {
    return vec3(
        hash(p),
        hash(p + 12.34),
        hash(p + 56.78)
    );
}

float stars(vec2 p, float density) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    float h = hash(i);
    float bright = smoothstep(1.0 - density, 1.0, h);
    float twinkle = 0.5 + 0.5 * sin(uTime * 2.0 + h * 6.2831);
    float d = length(f - 0.5);
    return bright * smoothstep(0.05, 0.0, d) * twinkle;
}

float smoothNoise(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    float a = hash(i.xy + i.z * 31.0);
    float b = hash(i.xy + (i.z + 1.0) * 31.0);
    return mix(a, b, f.z);
}

float fbm(vec3 p) {
    float v = 0.0;
    float a = 0.5;
    for (int i = 0; i < 5; i++) {
        v += a * smoothNoise(p);
        p *= 2.0;
        a *= 0.5;
    }
    return v;
}

float nebula(vec3 p) {
    float n = fbm(p * 0.4);
    float n2 = fbm(p * 0.8 + 1.5);
    return n * 0.6 + n2 * 0.4;
}

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 rayW = rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV;

    float t = uTime * uSpeed * 0.1;

    // ---- deep space gradient ----
    float height = rayW.y * 0.5 + 0.5;
    vec3 deepColor = mix(
        vec3(0.0, 0.0, 0.02),
        vec3(0.01, 0.0, 0.04),
        height
    );

    // ---- stars with color ----
    vec2 starUV = rayW.xy * 120.0 / (1.0 + abs(rayW.z) * 0.3);
    vec3 starColors = vec3(0.0);
    float totalBright = 0.0;

    // layer 1: dense small stars
    for (int i = 0; i < 3; i++) {
        float fi = float(i);
        float dens = 0.4 + fi * 0.15;
        float baseSize = 0.04 - fi * 0.008;
        vec2 suv = starUV * (1.0 + fi * 0.7);
        vec2 si = floor(suv);
        vec2 sf = fract(suv);
        float sh = hash(si + fi * 100.0);
        float bright = smoothstep(1.0 - dens, 1.0, sh);
        vec3 col = hash3(si + fi * 200.0);
        col = 0.5 + 0.5 * col;
        col *= mix(0.3, 1.0, sh);
        float twinkle = 0.5 + 0.5 * sin(uTime * (1.5 + fi * 0.7) + sh * 6.283);
        float d = length(sf - 0.5);
        float size = mix(baseSize * 0.5, baseSize, sh);
        float s = bright * smoothstep(size, 0.0, d) * twinkle;
        starColors += col * s * (1.0 + fi * 0.5);
        totalBright += s;
    }

    // ---- colorful nebula clouds ----
    float nebScale = uScale * 0.15;
    vec3 np = rayW * nebScale + vec3(t * 0.1, t * 0.05, 0.0);
    float n1 = nebula(np);
    float n2 = nebula(np + vec3(5.0, 3.0, 7.0));
    float n3 = nebula(np * 0.5 + vec3(2.0, 8.0, 4.0));

    vec3 nebColor1 = uColor;
    vec3 nebColor2 = uColor2;
    vec3 nebColor3 = vec3(0.9, 0.3, 0.6);
    vec3 nebulaC = vec3(0.0);
    nebulaC += nebColor1 * n1 * 1.2;
    nebulaC += nebColor2 * n2 * 0.8;
    nebulaC += nebColor3 * n3 * 0.4;
    nebulaC *= 0.8;

    // ---- cosmic glow / milky way band ----
    vec3 gDir = normalize(rayW);
    vec2 gp = vec2(gDir.x, gDir.y);
    float radius = length(gp);
    float angle = atan(gp.y, gp.x);
    float band = exp(-radius * 0.8) * (0.6 + 0.4 * sin(angle * 2.0 + radius * 5.0 + t * 0.2));
    band = band * band * 0.15;

    vec3 bandColor = mix(uColor, vec3(0.8, 0.5, 1.0), 0.5) * band;

    // ---- horizon glow ----
    float horizonGlow = exp(-abs(rayW.y) * 4.0) * 0.08;
    vec3 hColor = mix(uColor2, vec3(0.5, 0.2, 1.0), 0.3) * horizonGlow;

    // ---- shooting stars ----
    vec3 shooting = vec3(0.0);
    for (int i = 0; i < 4; i++) {
        float fi = float(i);
        float phase = fract(hash(vec2(fi, 0.0)) + t * 0.03 * (0.6 + fi * 0.15));
        float len = 0.15 + hash(vec2(fi, 5.0)) * 0.15;

        vec2 start = vec2(
            hash(vec2(fi, 1.0)) * 2.0 - 1.0,
            hash(vec2(fi, 2.0)) * 0.6 + 0.2
        );
        vec2 dir = normalize(vec2(
            hash(vec2(fi, 3.0)) * 2.0 - 1.0,
            hash(vec2(fi, 4.0)) * 0.3 - 0.5
        ));
        vec2 pos = start + dir * phase * 1.5;
        float dist = length(gp - pos);
        float head = smoothstep(0.002, 0.0, dist);
        float trail = smoothstep(len, 0.001, dist) * (1.0 - phase);
        float bright = head * 2.0 + trail * 0.5;
        vec3 sCol = vec3(1.0, 0.95, 0.8) * bright * (1.0 - phase * 0.7);
        shooting += sCol;
    }

    // ---- composition ----
    vec3 col = deepColor;
    col += starColors;
    col += nebulaC;
    col += bandColor;
    col += hColor;
    col += shooting;

    // subtle vignette
    float vig = 1.0 - 0.2 * dot(sp, sp);
    col *= vig;

    col *= uAlpha;

    float alpha = uAlpha * clamp(
        max(totalBright * 0.3,
        max(n1 * 1.5,
        max(n2 * 0.8,
        band * 3.0 + horizonGlow * 5.0))) * 0.5 + 0.3,
        0.0, 1.0
    );

    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
