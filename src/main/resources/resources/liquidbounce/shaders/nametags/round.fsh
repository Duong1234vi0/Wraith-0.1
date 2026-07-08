#version 330

in vec4 vColor;
flat in vec2 vLocation;
flat in vec2 vSize;
flat in vec4 vRadius;

out vec4 fragColor;

float roundedBoxSDF(vec2 center, vec2 size, vec4 radius) {
    radius.xy = (center.x > 0.0) ? radius.xy : radius.zw;
    radius.x  = (center.y > 0.0) ? radius.x : radius.y;

    vec2 q = abs(center) - size + radius.x;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - radius.x;
}

vec4 createGradient(vec2 coords, vec4 color1, vec4 color2, vec4 color3, vec4 color4) {
    vec4 color = mix(mix(color1, color2, coords.y), mix(color3, color4, coords.y), coords.x);
    color += mix(0.0019607843, -0.0019607843, fract(sin(dot(coords.xy, vec2(12.9898, 78.233))) * 43758.5453));
    return color;
}

void main() {
    float distance = roundedBoxSDF(gl_FragCoord.xy - vLocation - (vSize / 2.0), vSize / 2.0, vRadius);

    float thickness = 0.0;
    float softness = 0.0;
    vec4 outlineColor = vec4(33.0 / 255.0, 33.0 / 255.0, 33.0 / 255.0, 0.0);

    float smoothedAlpha = 1.0 - smoothstep(-1.0, thickness > 0.0 ? 1.0 : softness + 1.0, distance);
    float smoothedborderAlpha = (1.0 - smoothstep(-softness, softness, distance));

    float borderAlpha = 1.0 - smoothstep(thickness - 2.0, thickness, abs(distance));

    if (smoothedAlpha < 0.49 && thickness > 0.0) {
        fragColor = vec4(outlineColor.rgb, smoothedborderAlpha);
    } else {
        vec4 gradient = createGradient((gl_FragCoord.xy - vLocation) / vSize, vColor, vColor, vColor, vColor);
        vec4 basicColor = vec4(gradient.rgb, gradient.a * smoothedAlpha);

        fragColor = mix(vec4(gradient.rgb, 0.0), mix(basicColor, thickness > 0.0 ? outlineColor : basicColor, borderAlpha), smoothedAlpha);
    }
}
