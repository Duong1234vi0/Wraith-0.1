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

#define MAX_ITER 4

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

float stars(vec2 p, float density) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    float h = hash(i);
    float bright = smoothstep(1.0 - density, 1.0, h);
    float twinkle = 0.5 + 0.5 * sin(uTime * 2.0 + h * 6.2831);
    float size = smoothstep(0.8, 1.0, hash(i + 50.0));
    float d = length(f - 0.5);
    return bright * twinkle * smoothstep(0.04 * size, 0.0, d);
}

// 3D noise from the world ray direction — seamless on the full sphere
float noise3D(vec3 p) {
    vec3 i = p;
    float c = 1.0;
    float inten = 0.015;

    for (int n = 0; n < MAX_ITER; n++) {
        float t = uTime * uSpeed * (1.0 - (3.0 / float(n + 1)));
        i = p + vec3(
            cos(t - i.x) + sin(t + i.y),
            sin(t - i.y) + cos(t + i.z),
            cos(t - i.z) + sin(t + i.x)
        );
        c += 1.0 / length(vec3(
            p.x / (sin(i.x + t) / inten),
            p.y / (cos(i.y + t) / inten),
            p.z / (sin(i.z + t) / inten)
        ));
    }

    c /= float(MAX_ITER);
    return 1.5 - sqrt(c);
}

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    // ray direction in world space
    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 rayW = rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV;

    // star field
    vec2 starUV = rayW.xy * 80.0 / (1.0 + abs(rayW.z) * 0.5);
    float st = stars(starUV, 0.55);
    st += stars(starUV * 2.0, 0.25) * 0.5;

    // nebula / 3D noise (caustic-style)
    float noiseVal = noise3D(rayW * uScale);
    float brightness = noiseVal * noiseVal * noiseVal * noiseVal;

    // aurora band near horizon
    float horizon = smoothstep(0.0, 0.4, abs(rayW.y));
    float aurora = max(0.0, (1.0 - abs(rayW.y * 2.0)) * 0.6 * (0.5 + 0.5 * sin(rayW.x * 4.0 + uTime * 0.5)));

    vec3 col = mix(uColor, uColor2, clamp(brightness * 1.5, 0.0, 1.0));
    col = col * brightness * 1.5 + col * 0.2;
    col += uColor * st;
    col += mix(uColor, uColor2, 0.7) * aurora;
    col *= (0.6 + 0.4 * horizon);

    float alpha = uAlpha * clamp(brightness * 0.8 + st * 0.5 + aurora * 0.4 + 0.1, 0.0, 1.0);

    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
