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
#define PI 3.14159265

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

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 dir = normalize(rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV);

    vec2 wc = vec2(atan(dir.x, dir.z), asin(clamp(dir.y, -1.0, 1.0))) * uScale;
    vec2 p = wc * 0.05;
    float pLen = length(p);
    float angle = atan(p.y, p.x + 1e-6) + uTime * uSpeed * 0.2 + 302.2 - 20.0 * (0.5 * pLen + 0.5);
    p = vec2(pLen * cos(angle), pLen * sin(angle)) * 30.0;
    float spd = uTime * uSpeed * 0.5;
    vec2 p2 = p;
    for (int i = 0; i < 5; i++) {
        p2 += sin(max(p.x, p.y)) + p;
        p += 0.5 * vec2(cos(5.1123314 + 0.353 * p2.y + spd * 0.131121), sin(p2.x - 0.113 * spd));
        p -= cos(p.x + p.y) - sin(p.x * 0.711 - p.y);
    }
    float contrast = 1.5;
    float contrastMod = 0.375 + 1.45;
    float paintRes = clamp(length(p) * 0.035 * contrastMod, 0.0, 2.0);
    float c1p = max(0.0, 1.0 - contrastMod * abs(1.0 - paintRes));
    float c2p = max(0.0, 1.0 - contrastMod * abs(paintRes));
    float c3p = 1.0 - min(1.0, c1p + c2p);
    float light = 0.5 * max(c1p * 5.0 - 4.0, 0.0) + 0.7 * max(c2p * 5.0 - 4.0, 0.0);
    vec3 col1 = uColor;
    vec3 col2 = uColor * vec3(0.5, 1.2, 0.8);
    vec3 col3 = uColor * vec3(1.2, 0.6, 1.1);
    vec3 col = (0.13 * col1) + (0.87 * (col1 * c1p + col2 * c2p + col3 * c3p)) + light;

    float horizon = 1.0 - abs(dir.y) * 0.3;
    col *= horizon;
    col *= uAlpha;

    float alpha = uAlpha * clamp(c1p + c2p * 0.5 + 0.1, 0.0, 1.0);
    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
