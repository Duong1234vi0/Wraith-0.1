package net.minecraft.client.gl;

import java.util.List;

public class ShaderProgram {
    protected final int program;

    public ShaderProgram(int program) {
        this.program = program;
    }

    public void set(List<ShaderProgramDefinition.Uniform> uniforms, List<ShaderProgramDefinition.Sampler> samplers) {
    }
}
