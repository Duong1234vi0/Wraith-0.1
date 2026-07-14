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

void main() {
    vec2 uv = gl_FragCoord.xy / uResolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = uResolution.x / uResolution.y;

    float tanV = tan(radians(uFov) * 0.5);
    vec3 rayV = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 rayW = rotY(uCameraDir.x) * rotX(uCameraDir.y) * rayV;

    float t = uTime * uSpeed * 0.2;
    float scale = uScale * 2.0;

    vec2 gp = rayW.xy * scale;
    vec2 gp2 = rayW.xy * scale * 0.5 + t * 0.5;

    // main grid
    vec2 grid = abs(fract(gp + 0.5) - 0.5) / fwidth(gp);
    float lineX = 1.0 - min(grid.x, 1.0);
    float lineY = 1.0 - min(grid.y, 1.0);
    float line = max(lineX, lineY);

    // secondary grid (rotated)
    vec2 rp = vec2(gp2.x * 0.7 - gp2.y * 0.7, gp2.x * 0.7 + gp2.y * 0.7);
    vec2 grid2 = abs(fract(rp + 0.5) - 0.5) / fwidth(rp);
    float line2 = max(1.0 - min(grid2.x, 1.0), 1.0 - min(grid2.y, 1.0)) * 0.4;

    // scanlines
    float scan = 0.5 + 0.5 * sin(gp.y * 30.0 + gp.x * 10.0 + t * 5.0);

    // horizon fade
    float horizon = 1.0 - abs(rayW.y) * 1.2;
    horizon = clamp(horizon, 0.0, 1.0);

    // grid pulse
    float pulse = 0.5 + 0.5 * sin(gp.x * 3.0 + gp.y * 3.0 + t * 2.0);

    // color mixing
    vec3 col1 = uColor * pulse;
    vec3 col2 = uColor2 * (1.0 - pulse);
    vec3 gridColor = mix(col1, col2, 0.3 + 0.3 * scan);

    vec3 col = vec3(0.0);
    col += gridColor * line * 0.8;
    col += gridColor * line2 * 0.5;
    col += mix(uColor, uColor2, 0.5) * line * scan * 0.3;
    col *= horizon * 0.9;

    // glowing nodes at intersections
    vec2 nodeUV = fract(gp + 0.5);
    float nodeDist = length(nodeUV - 0.5);
    float node = smoothstep(0.15, 0.0, nodeDist);
    float nodePulse = 0.5 + 0.5 * sin(t * 1.5 + gp.x * 5.0 + gp.y * 5.0);
    col += mix(uColor, uColor2, nodePulse) * node * 0.6;

    // falling digital rain
    for (int i = 0; i < 6; i++) {
        float fi = float(i);
        vec2 rainPos = vec2(
            fract(hash(vec2(fi, 0.0)) + t * 0.08 * (0.5 + fi * 0.1)) * 2.0 - 1.0,
            fract(hash(vec2(fi, 1.0)) + t * 0.12 * (0.3 + fi * 0.15)) * 2.0 - 1.0
        );
        vec2 diff = gp * 0.15 - rainPos;
        float rain = smoothstep(0.03, 0.0, length(diff));
        col += vec3(0.0, 1.0, 0.5) * rain * 0.5 * (0.5 + 0.5 * sin(t * 3.0 + fi));
    }

    col *= uAlpha;

    float alpha = uAlpha * clamp((line + line2) * 0.6 + node * 0.3, 0.0, 1.0);
    if (alpha <= 0.001) discard;
    fragColor = vec4(col, alpha);
}
