#version 120

uniform vec2 size;
uniform vec2 center;
uniform float progress;
uniform float radius;
uniform vec4 color;

void main() {
    vec2 uv = gl_TexCoord[0].st * size;
    vec2 p = uv - center;
    float dist = length(p);

    float sweep = 6.2831853 * (0.35 + progress * 0.9);
    float ang = atan(p.y, p.x);
    float start = progress * 5.2;
    float delta = mod(ang - start + 6.2831853, 6.2831853);
    float inSweep = step(delta, sweep);

    float ringW = 2.0 + (1.0 - progress) * 3.2;
    float d = abs(dist - radius);
    float ring = (1.0 - smoothstep(ringW * 0.4, ringW, d)) * inSweep;

    float core = exp(-abs(dist - radius) * 0.18) * 0.18;
    float spark = pow(max(cos(delta * 2.0), 0.0), 14.0) * exp(-abs(dist - radius) * 0.12) * 0.45;

    float a = (ring + core + spark) * color.a;
    if (a < 0.01) discard;

    vec3 c = mix(color.rgb * 0.7, color.rgb, ring);
    c += vec3(1.0) * spark * 0.12;
    gl_FragColor = vec4(c, clamp(a, 0.0, 1.0));
}
