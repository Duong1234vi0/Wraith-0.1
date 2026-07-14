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
#define MAX_ITER 5
#define INTENSITY 0.005

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
    vec3 ray = normalize(rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV);

    vec2 skyUv = vec2(atan(ray.x, ray.z) / TAU, asin(clamp(ray.y, -1.0, 1.0)) / PI) * uScale;

    vec2 p = mod(skyUv * TAU, TAU) - 250.0;

    vec2 iterPos = p;
    float value = 1.0;
    float timeOffset = uTime * uSpeed * 0.5 + 23.0;

    for (int n = 0; n < MAX_ITER; n++) {
        float iterTime = timeOffset * (1.0 - (3.5 / float(n + 1)));

        iterPos = p + vec2(
            cos(iterTime - iterPos.x) + sin(iterTime + iterPos.y),
            sin(iterTime - iterPos.y) + cos(iterTime + iterPos.x)
        );

        float sinX = sin(iterPos.x + iterTime);
        float cosY = cos(iterPos.y + iterTime);

        float compX = p.x * INTENSITY / max(abs(sinX), 0.001);
        float compY = p.y * INTENSITY / max(abs(cosY), 0.001);
        float dist = sqrt(compX * compX + compY * compY);

        value += 1.0 / dist;
    }

    value = 1.17 - pow(value / float(MAX_ITER), 1.4);

    float pwr = value * value;
    pwr = pwr * pwr;
    pwr = pwr * pwr;

    vec3 baseColor = vec3(abs(pwr));
    vec3 enhancedColor = clamp(baseColor + vec3(0.0, 0.35, 0.5), 0.0, 1.0);
    float luminance = dot(enhancedColor, vec3(0.299, 0.587, 0.114));

    vec3 col = vec3(luminance) * mix(uColor, uColor2, luminance) * 1.4;

    float horizon = 1.0 - abs(ray.y) * 0.4;
    col *= horizon;
    col *= uAlpha;

    float alpha = uAlpha * clamp(luminance * 1.2 + 0.1, 0.0, 1.0);
    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
