#version 330

uniform sampler2D BeforeDepth;
uniform sampler2D AfterDepth;
uniform sampler2D PreviousMask;

layout(std140) uniform IrisExactHandMaskConfig {
    ivec4 Flags0;
};

in vec2 TexCoord;
out vec4 fragColor;

#define MergePrevious Flags0.x
#define IRIS_MASK_MIN 0.0000005
#define IRIS_MASK_MAX 0.00005

float phaseMask(vec2 uv) {
    float beforeDepth = texture(BeforeDepth, uv).r;
    float afterDepth = texture(AfterDepth, uv).r;

    if (beforeDepth != beforeDepth || afterDepth != afterDepth) {
        return 0.0;
    }
    if (
        beforeDepth < 0.0 ||
        beforeDepth > 1.0 ||
        afterDepth < 0.0 ||
        afterDepth > 1.0
    ) {
        return 0.0;
    }

    float delta = beforeDepth - afterDepth;
    if (delta <= 0.0) {
        return 0.0;
    }

    return smoothstep(IRIS_MASK_MIN, IRIS_MASK_MAX, delta);
}

void main() {
    vec2 uv = TexCoord;
    float oldMask = MergePrevious == 1 ? texture(PreviousMask, uv).r : 0.0;
    float combined = max(oldMask, phaseMask(uv));
    fragColor = vec4(combined, combined, combined, combined);
}
