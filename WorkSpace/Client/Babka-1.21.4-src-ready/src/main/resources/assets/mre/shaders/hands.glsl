#version 150 core

in vec2 fragCoord;

uniform sampler2D uOriginal;
uniform sampler2D uBlurred;
uniform vec4 multiplier;
uniform vec2 resolution;
uniform float mixFactor;

out vec4 fragColor;

void main() {
    vec4 srcColor = texture(uOriginal, fragCoord);
    if (srcColor.a == 0.0) discard;
    vec2 uv = gl_FragCoord.xy / resolution;
    uv.y = 1.0 - uv.y;
    vec4 blurredColor = texture(uBlurred, uv);
    vec3 tintedGlass = blurredColor.rgb * multiplier.rgb;
    vec3 finalRGB = mix(tintedGlass, srcColor.rgb, mixFactor);
    fragColor = vec4(finalRGB, srcColor.a);
}
