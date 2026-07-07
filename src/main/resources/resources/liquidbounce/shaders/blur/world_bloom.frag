#version 410 core

in vec2 fragTexCoord;
out vec4 fragColor;

uniform sampler2D texture0;
uniform float radius;
uniform float intensity;

const vec2 DIRECTIONS[8] = vec2[](
    vec2(1.0, 0.0),
    vec2(-1.0, 0.0),
    vec2(0.0, 1.0),
    vec2(0.0, -1.0),
    vec2(0.7071, 0.7071),
    vec2(-0.7071, 0.7071),
    vec2(0.7071, -0.7071),
    vec2(-0.7071, -0.7071)
);

void main() {
    vec2 texelSize = vec2(1.0) / textureSize(texture0, 0).xy;
    vec3 base = texture(texture0, fragTexCoord).rgb;
    vec3 bloom = vec3(0.0);
    float total = 0.0;

    for (int direction = 0; direction < 8; direction++) {
        for (float step = 1.0; step <= radius; step += 1.0) {
            vec3 sampleColor = texture(texture0, fragTexCoord + DIRECTIONS[direction] * texelSize * step).rgb;
            float luminance = dot(sampleColor, vec3(0.2126, 0.7152, 0.0722));
            float bright = smoothstep(0.35, 0.95, luminance);
            float weight = 1.0 - step / (radius + 1.0);

            bloom += sampleColor * bright * weight;
            total += weight;
        }
    }

    vec3 bloomColor = total <= 0.0 ? vec3(0.0) : bloom / total;
    fragColor = vec4(min(base + bloomColor * intensity, vec3(1.0)), 1.0);
}
