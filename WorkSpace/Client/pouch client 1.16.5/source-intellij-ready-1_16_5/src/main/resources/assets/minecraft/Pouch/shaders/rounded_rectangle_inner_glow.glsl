#version 120

uniform vec2 size;
uniform vec4 radius;
uniform vec4 color;
uniform vec4 glowColor;
uniform float glowRadius;
uniform float alpha;

float signedDistanceField(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
    r.x = (p.y > 0.0) ? r.x : r.y;

    vec2 q = abs(p) - b + r.x;

    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r.x;
}

void main() {
    vec2 halfSize = size * 0.5;
    vec2 pos = gl_TexCoord[0].st * size;
    
    // Расстояние до края прямоугольника
    float sdf = signedDistanceField(halfSize - pos, halfSize, radius);
    
    // Основной прямоугольник (внутри границ)
    float rectAlpha = (1.0 - smoothstep(0.0, 1.0, sdf)) * color.a;
    
    // Внутреннее свечение (от края к центру)
    // Инвертируем sdf для получения расстояния от края внутрь
    float innerGlow = smoothstep(-glowRadius, 0.0, sdf) * glowColor.a * rectAlpha;
    
    // Смешиваем основной цвет с цветом свечения
    vec3 finalColor = mix(color.rgb, glowColor.rgb, innerGlow / max(color.a, 0.001));
    float finalAlpha = max(rectAlpha, 0.0) * alpha;
    
    gl_FragColor = vec4(finalColor, finalAlpha);
}

