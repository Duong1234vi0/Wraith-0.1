#version 120

uniform sampler2D originalTexture;
uniform sampler2D blurredTexture;
uniform vec4 multiplier;
uniform vec2 resolution;
uniform float mixFactor;

void main() {
    vec2 texCoord = gl_TexCoord[0].xy;
    vec4 srcColor = texture2D(originalTexture, texCoord);
    vec2 blurredUV = gl_FragCoord.xy / resolution;
    blurredUV.y = 1.0 - blurredUV.y;
    vec4 blurTinted = texture2D(blurredTexture, blurredUV) * multiplier * srcColor.a;
    gl_FragColor = mix(srcColor, blurTinted, mixFactor);
}
