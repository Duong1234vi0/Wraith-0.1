#version 120

uniform vec2 size;
uniform vec2 center;
uniform float innerRadius;
uniform float outerRadius;
uniform float startAngle;
uniform float endAngle;
uniform vec4 color;

float sdRing(vec2 p, float r1, float r2) {
    return abs(length(p) - (r1 + r2) * 0.5) - (r2 - r1) * 0.5;
}

float normalizeAngle(float angle) {
    return mod(angle + 3.14159265359, 3.14159265359 * 2.0) - 3.14159265359;
}

float angleInRange(float angle, float start, float end) {
    float range = end - start;
    float absRange = abs(range);
    float twoPi = 6.283185307179586;
    
    if (absRange >= twoPi - 0.1) {
        return 1.0;
    }
    
    float normalizedRange = mod(absRange + twoPi, twoPi);
    if (normalizedRange < 0.1 || normalizedRange > twoPi - 0.1) {
        return 1.0;
    }
    
    float normAngle = normalizeAngle(angle);
    float normStart = normalizeAngle(start);
    float normEnd = normalizeAngle(end);
    
    if (abs(normEnd - normStart) < 0.1 && absRange > 6.0) {
        return 1.0;
    }
    
    if (normEnd > normStart) {
        return step(normStart, normAngle) * step(normAngle, normEnd);
    } else {
        return step(normStart, normAngle) + step(normAngle, normEnd);
    }
}

void main() {
    vec2 pixel = gl_TexCoord[0].st * size;
    vec2 p = pixel - center;
    
    float dist = sdRing(p, innerRadius, outerRadius);
    float angle = atan(p.y, p.x);
    float inAngleRange = angleInRange(angle, startAngle, endAngle);
    
    float alpha = (1.0 - smoothstep(-1.0, 1.0, dist)) * inAngleRange * color.a;
    gl_FragColor = vec4(color.rgb, alpha);
}
