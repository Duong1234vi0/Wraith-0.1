#version 120

uniform sampler2D tex;
uniform float time;
uniform float alpha;
uniform vec4 color;

float pulse(float t) {
    return 0.7 + 0.3 * sin(t);
}

void main() {
    vec2 uv = gl_TexCoord[0].st;
    vec4 texColor = texture2D(tex, uv);

    float center = 1.0 - smoothstep(0.0, 1.0, abs(uv.x - 0.5) * 2.0);
    float vfade = smoothstep(0.0, 0.15, uv.y) * smoothstep(1.0, 0.85, uv.y);
    float p = pulse(time + uv.y * 6.283);

    float a = texColor.a * center * vfade * alpha * p;
    gl_FragColor = vec4(color.rgb, a * color.a);
}
