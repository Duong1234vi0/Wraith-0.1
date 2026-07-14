#version 120

uniform sampler2D texture;
uniform float alpha;

void main() {
    vec2 tex = gl_TexCoord[0].st;
    vec4 color = texture2D(texture, tex);
    color.a *= alpha;
    gl_FragColor = color;
}
