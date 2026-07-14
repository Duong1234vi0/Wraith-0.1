#version 120

uniform float u_time;
uniform vec3 u_color;
uniform float u_seed;

float rand(float n) {
    return fract(sin(n) * 43758.5453123);
}

void main() {
    vec2 uv = fract(gl_TexCoord[0].xy);
    
    float accum = 0.0;
    
    bool mainIsVertical = rand(u_seed * 41.3) > 0.5;
    
    for (int i = 0; i < 2; i++) {
        float basePos = rand(u_seed + float(i) * 7.3) * 0.6 + 0.2;
        float phase = rand(u_seed + float(i) * 3.7) * 6.28;
        float speed = 1.5 + rand(u_seed + float(i) * 11.1) * 2.0;
        
        float d;
        if (mainIsVertical) {
            float wave = 0.07 * sin(uv.y * 8.0 + u_time * speed + phase);
            float lineX = basePos + wave;
            d = abs(uv.x - lineX);
        } else {
            float wave = 0.07 * sin(uv.x * 8.0 + u_time * speed + phase);
            float lineY = basePos + wave;
            d = abs(uv.y - lineY);
        }
        
        float core = smoothstep(0.012, 0.0, d);
        float glow = exp(-d * 30.0) * 0.35;
        accum += core + glow;
    }
    
    {
        float basePos = rand(u_seed + 100.0) * 0.4 + 0.3; // смещение по диагонали
        float phase = rand(u_seed + 103.7) * 6.28;
        float speed = 1.5 + rand(u_seed + 111.1) * 2.0;
        
        // Диагональная координата: (x + y) / 2 или (x - y) / 2
        bool diagDir = rand(u_seed + 200.0) > 0.5;
        float diagCoord = diagDir ? (uv.x + uv.y) * 0.5 : (uv.x - uv.y + 1.0) * 0.5;
        
        // Волна вдоль перпендикуляра к диагонали
        float perpCoord = diagDir ? (uv.x - uv.y + 1.0) * 0.5 : (uv.x + uv.y) * 0.5;
        float wave = 0.06 * sin(perpCoord * 10.0 + u_time * speed + phase);
        
        float linePos = basePos + wave;
        float d = abs(diagCoord - linePos);
        
        float core = smoothstep(0.012, 0.0, d);
        float glow = exp(-d * 30.0) * 0.35;
        accum += core + glow;
    }
    
    float alpha = clamp(accum * 0.7, 0.0, 1.0);
    
    if (alpha < 0.02) discard;
    
    gl_FragColor = vec4(u_color * alpha, alpha);
}
