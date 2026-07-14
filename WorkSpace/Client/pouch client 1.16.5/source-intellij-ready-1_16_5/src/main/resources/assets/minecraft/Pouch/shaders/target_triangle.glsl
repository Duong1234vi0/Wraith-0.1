#version 120

varying vec2 TexCoord;
varying vec4 VertexColor;

uniform float Time;
uniform float Alpha;
uniform float Hurt;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453123);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    vec2 u = f * f * (3.0 - 2.0 * f);

    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);
}

float fbm(vec2 p) {
    float value = 0.0;
    float amplitude = 0.55;
    for (int i = 0; i < 4; i++) {
        value += noise(p) * amplitude;
        p = p * 2.04 + vec2(6.7, 3.9);
        amplitude *= 0.5;
    }
    return value;
}

void main() {
    vec2 uv = TexCoord;
    float baseY = 0.055;
    float apexY = 0.98;
    float triangleProgress = clamp((uv.y - baseY) / (apexY - baseY), 0.0, 1.0);
    float halfWidth = mix(0.47, 0.0, triangleProgress);
    float sideDistance = halfWidth - abs(uv.x - 0.5);
    float topDistance = uv.y - baseY;
    float bottomDistance = apexY - uv.y;
    float inside = min(min(sideDistance, topDistance), bottomDistance);
    float mask = smoothstep(0.0, 0.035, inside);

    if (mask <= 0.002) discard;

    float edge = 1.0 - smoothstep(0.0, 0.07, inside);
    float waves = fbm(uv * vec2(13.0, 18.0) + vec2(Time * 0.72, -Time * 1.05));
    float grains = hash(floor(uv * vec2(88.0, 124.0)) + floor(Time * 26.0));
    float pulse = 0.5 + 0.5 * sin(Time * 5.2 + uv.y * 7.4);
    float scan = pow(max(0.0, sin((uv.y - Time * 0.68) * 42.0)), 14.0);

    vec3 hurtColor = vec3(1.0, 0.06, 0.025);
    vec3 baseColor = mix(VertexColor.rgb, hurtColor, Hurt);
    vec3 litColor = mix(baseColor, vec3(1.0), edge * 0.48 + scan * 0.18);
    litColor *= 0.82 + waves * 0.26 + edge * 0.34 + scan * 0.22;

    float alpha = VertexColor.a * Alpha * mask;
    alpha *= 0.42 + waves * 0.48 + pulse * 0.10;
    alpha += edge * VertexColor.a * Alpha * 0.38;
    alpha *= 0.92 + grains * 0.08;

    gl_FragColor = vec4(litColor, alpha);
}
