#version 330

uniform sampler2D Sampler0;

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

in vec2 vTexCoord;
in vec2 vLocalPos;
in vec4 vColor;

out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, float r) {
    vec2 q = abs(p) - b + r;
    return min(max(q.x, q.y), 0.0) + length(max(q, vec2(0.0))) - r;
}

void main() {
    vec4 tex = texture(Sampler0, vTexCoord);
    if (tex.a < 0.001 || vColor.a < 0.001) {
        discard;
    }

    // vColor.r encodes radius as normalized SDF units:
    // 0.0 = square, 1.0 = fully circular for a square quad.
    float radius = clamp(vColor.r, 0.0, 1.0);
    float d = sdRoundBox(vLocalPos, vec2(1.0), radius);
    float aa = max(fwidth(d), 1e-4);
    float mask = smoothstep(0.0, -aa, d);

    float alpha = tex.a * vColor.a * ColorModulator.a * mask;
    if (alpha < 0.001) {
        discard;
    }

    fragColor = vec4(tex.rgb, alpha);
}
