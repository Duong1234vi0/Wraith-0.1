#version 330

uniform sampler2D Sampler0;

in vec4 vColor;
flat in vec2 vLocation;
flat in vec2 vSize;
flat in vec4 vRound;

out vec4 fragColor;

float roundedBoxSDF(vec2 center, vec2 size, vec4 radius) {
    radius.xy = (center.x > 0.0) ? radius.xy : radius.zw;
    radius.x  = (center.y > 0.0) ? radius.x : radius.y;

    vec2 q = abs(center) - size + radius.x;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - radius.x;
}

void main() {
    vec2 texSize = vec2(textureSize(Sampler0, 0));
    vec2 uv = gl_FragCoord.xy / texSize;
    vec4 c = texture(Sampler0, uv);

    float dis = roundedBoxSDF(gl_FragCoord.xy - vLocation - (vSize / 2.0), vSize / 2.0, vRound);
    float alpha = 1.0 - smoothstep(-1.0, 1.0, dis);

    fragColor = vec4(c.rgb + vColor.rgb, c.a * alpha * vColor.a);
}
