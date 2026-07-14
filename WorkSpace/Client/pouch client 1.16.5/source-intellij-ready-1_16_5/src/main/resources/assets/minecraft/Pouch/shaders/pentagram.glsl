#version 120

#define PI 3.14159265359

uniform vec4 u_Color;
uniform float u_Alpha;
uniform float u_HurtMix;

float sdSegment(vec2 p, vec2 a, vec2 b) {
    vec2 pa = p - a;
    vec2 ba = b - a;
    float h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);
    return length(pa - ba * h);
}

void main() {
    vec2 uv = gl_TexCoord[0].st * 2.0 - 1.0;
    float dist = length(uv);
    if (dist > 1.005) discard;

    float R = 0.90;
    vec2 p0 = R * vec2(cos(-PI / 2.0), sin(-PI / 2.0));
    vec2 p1 = R * vec2(cos(-PI / 2.0 + 2.0 * PI / 5.0), sin(-PI / 2.0 + 2.0 * PI / 5.0));
    vec2 p2 = R * vec2(cos(-PI / 2.0 + 4.0 * PI / 5.0), sin(-PI / 2.0 + 4.0 * PI / 5.0));
    vec2 p3 = R * vec2(cos(-PI / 2.0 + 6.0 * PI / 5.0), sin(-PI / 2.0 + 6.0 * PI / 5.0));
    vec2 p4 = R * vec2(cos(-PI / 2.0 + 8.0 * PI / 5.0), sin(-PI / 2.0 + 8.0 * PI / 5.0));

    float circleThick = 0.020;
    float dRing = abs(dist - R) - circleThick * 0.5;
    float circleLine = 1.0 - smoothstep(0.0, 0.038, dRing);
    float ringDist = max(0.0, abs(dist - R) - circleThick * 0.35);
    float circleGlow = exp(-ringDist * 22.0) * 0.38;

    float lineW = 0.016;
    float d0 = sdSegment(uv, p0, p2);
    float d1 = sdSegment(uv, p2, p4);
    float d2 = sdSegment(uv, p4, p1);
    float d3 = sdSegment(uv, p1, p3);
    float d4 = sdSegment(uv, p3, p0);
    float dStar = min(min(min(d0, d1), min(d2, d3)), d4);

    float starLine = 1.0 - smoothstep(0.0, 0.034, dStar - lineW * 0.5);
    float starGlow = exp(-max(0.0, dStar - lineW * 0.35) * 26.0) * 0.34;

    float flareSigma = 0.0032;
    float vertexFlare = 0.0;
    vertexFlare += exp(-dot(uv - p0, uv - p0) / flareSigma);
    vertexFlare += exp(-dot(uv - p1, uv - p1) / flareSigma);
    vertexFlare += exp(-dot(uv - p2, uv - p2) / flareSigma);
    vertexFlare += exp(-dot(uv - p3, uv - p3) / flareSigma);
    vertexFlare += exp(-dot(uv - p4, uv - p4) / flareSigma);
    vertexFlare *= 2.15;

    float edgeHaze = exp(-dStar * 32.0) * 0.07 + exp(-ringDist * 28.0) * 0.05;

    vec3 ice = vec3(0.82, 0.94, 1.0);
    vec3 white = vec3(1.0, 0.995, 1.0);
    vec3 baseCol = mix(ice, white, 0.35);
    baseCol = mix(baseCol, u_Color.rgb, 0.16);
    baseCol = mix(baseCol, vec3(1.0, 0.38, 0.42), u_HurtMix);

    vec3 col = baseCol;
    float hot = clamp(starLine + circleLine + vertexFlare * 0.45, 0.0, 1.0);
    col = mix(col, white, hot * 0.55);

    float a = circleLine + starLine + circleGlow + starGlow + vertexFlare * 0.62 + edgeHaze;
    a = clamp(a, 0.0, 1.0) * u_Alpha;

    if (dist > R + 0.035 && a < u_Alpha * 0.08) discard;
    if (a < 0.028) discard;

    gl_FragColor = vec4(col, a * u_Color.a);
}

