#version 120

uniform vec2 location, rectSize;
uniform sampler2D tex;
uniform vec4 color1, color2, color3, color4;
uniform float alpha;

#define NOISE .5/255.0

vec4 createGradient(vec2 coords, vec4 color1, vec4 color2, vec4 color3, vec4 color4) {
    vec4 color = mix(mix(color1, color2, coords.y), mix(color3, color4, coords.y), coords.x);
    color.rgb += mix(NOISE, -NOISE, fract(sin(dot(coords.xy, vec2(12.9898,78.233))) * 43758.5453));
    return color;
}

void main() {
    vec2 coords = (gl_FragCoord.xy - location) / rectSize;
    vec2 clampedCoords = clamp(coords, 0.0, 1.0);

    vec4 gradientColor = createGradient(clampedCoords, color1, color2, color3, color4);
    vec4 texColor = texture2D(tex, gl_TexCoord[0].st);

    float finalAlpha = gradientColor.a * texColor.a * alpha;

    gl_FragColor = vec4(gradientColor.rgb, finalAlpha);
}
