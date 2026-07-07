package net.minecraft.client.gl;

import java.util.List;

public final class ShaderProgramDefinition {
    private ShaderProgramDefinition() {
    }

    public static final class Uniform {
        public Uniform(String name, String type, int count, List<Float> values) {
        }
    }

    public static final class Sampler {
        public Sampler(String name) {
        }
    }
}
