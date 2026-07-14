#version 330

layout(std140) uniform BetterWorldSkyConfig {
    vec4 FogColor;
    vec4 FogParams;
    mat4 InvProjMat;
    mat4 InvViewMat;
    vec4 SkyZenith;
    vec4 SkyHorizon;
    vec4 NebColor1;
    vec4 NebColor2;
    vec4 StarColor;
};

out vec3 vRayDir;

void main() {
    vec2 positions[3] = vec2[](
        vec2(-1.0, -1.0),
        vec2(3.0, -1.0),
        vec2(-1.0, 3.0)
    );

    vec2 position = positions[gl_VertexID];
    gl_Position = vec4(position.xy, 1.0, 1.0);

    vec4 rayView = InvProjMat * vec4(position.xy, -1.0, 1.0);
    rayView = vec4(rayView.xy, -1.0, 0.0);
    vRayDir = normalize((InvViewMat * rayView).xyz);
}
