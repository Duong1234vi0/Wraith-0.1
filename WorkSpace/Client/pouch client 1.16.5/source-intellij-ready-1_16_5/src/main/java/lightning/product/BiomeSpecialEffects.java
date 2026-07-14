/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import lightning.product.AmbientParticleSettings;
import lightning.product.E_4700_p;
import lightning.product.AmbientMoodSettings;
import lightning.product.SoundEvent;
import lightning.product.Music;
import lightning.product.AmbientAdditionsSettings;
import lightning.product.k_594_Q;

public class BiomeSpecialEffects {
    public static final Codec<BiomeSpecialEffects> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)Codec.INT.fieldOf("fog_color").forGetter(ambience -> ambience.J_1907_R), (App)Codec.INT.fieldOf("water_color").forGetter(ambience -> ambience.R_4764_Y), (App)Codec.INT.fieldOf("water_fog_color").forGetter(ambience -> ambience.G_564_y), (App)Codec.INT.fieldOf("sky_color").forGetter(ambience -> ambience.P_1922_E), (App)Codec.INT.optionalFieldOf("foliage_color").forGetter(ambience -> ambience.u_1723_Y), (App)Codec.INT.optionalFieldOf("grass_color").forGetter(p_244426_0_ -> p_244426_0_.v_4262_N), (App)lightning.product.BiomeSpecialEffects$J_1907_R.G_564_y.optionalFieldOf("grass_color_modifier", (Object)lightning.product.BiomeSpecialEffects$J_1907_R.n_1700_B).forGetter(ambience -> ambience.w_1484_f), (App)AmbientParticleSettings.n_1700_B.optionalFieldOf("particle").forGetter(ambience -> ambience.t_148_a), (App)SoundEvent.n_1700_B.optionalFieldOf("ambient_sound").forGetter(ambience -> ambience.s_956_w), (App)AmbientMoodSettings.n_1700_B.optionalFieldOf("mood_sound").forGetter(ambience -> ambience.u_2550_I), (App)AmbientAdditionsSettings.n_1700_B.optionalFieldOf("additions_sound").forGetter(ambience -> ambience.M_588_G), (App)Music.n_1700_B.optionalFieldOf("music").forGetter(ambience -> ambience.P_4830_p)).apply((Applicative)builder, BiomeSpecialEffects::new));
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final Optional<Integer> u_1723_Y;
    private final Optional<Integer> v_4262_N;
    private final J_1907_R w_1484_f;
    private final Optional<AmbientParticleSettings> t_148_a;
    private final Optional<SoundEvent> s_956_w;
    private final Optional<AmbientMoodSettings> u_2550_I;
    private final Optional<AmbientAdditionsSettings> M_588_G;
    private final Optional<Music> P_4830_p;

    private BiomeSpecialEffects(int fogColor, int waterColor, int waterFogColor, int skyColor, Optional<Integer> foliageColor, Optional<Integer> grassColor, J_1907_R grassColorModifier, Optional<AmbientParticleSettings> particle, Optional<SoundEvent> ambientSound, Optional<AmbientMoodSettings> moodSound, Optional<AmbientAdditionsSettings> additionsSound, Optional<Music> music) {
        this.J_1907_R = fogColor;
        this.R_4764_Y = waterColor;
        this.G_564_y = waterFogColor;
        this.P_1922_E = skyColor;
        this.u_1723_Y = foliageColor;
        this.v_4262_N = grassColor;
        this.w_1484_f = grassColorModifier;
        this.t_148_a = particle;
        this.s_956_w = ambientSound;
        this.u_2550_I = moodSound;
        this.M_588_G = additionsSound;
        this.P_4830_p = music;
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public int G_564_y() {
        return this.P_1922_E;
    }

    public Optional<Integer> P_1922_E() {
        return this.u_1723_Y;
    }

    public Optional<Integer> u_1723_Y() {
        return this.v_4262_N;
    }

    public J_1907_R v_4262_N() {
        return this.w_1484_f;
    }

    public Optional<AmbientParticleSettings> w_1484_f() {
        return this.t_148_a;
    }

    public Optional<SoundEvent> t_148_a() {
        return this.s_956_w;
    }

    public Optional<AmbientMoodSettings> s_956_w() {
        return this.u_2550_I;
    }

    public Optional<AmbientAdditionsSettings> u_2550_I() {
        return this.M_588_G;
    }

    public Optional<Music> M_588_G() {
        return this.P_4830_p;
    }

    public static abstract sealed class J_1907_R
    extends Enum<J_1907_R>
    implements E_4700_p {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("none"){

            @Override
            public int n_1700_B(double x, double z, int grassColor) {
                return grassColor;
            }
        };
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("dark_forest"){

            @Override
            public int n_1700_B(double x, double z, int grassColor) {
                return (grassColor & 0xFEFEFE) + 2634762 >> 1;
            }
        };
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("swamp"){

            @Override
            public int n_1700_B(double x, double z, int grassColor) {
                double d0 = k_594_Q.u_1723_Y.n_1700_B(x * 0.0225, z * 0.0225, false);
                return d0 < -0.1 ? 5011004 : 6975545;
            }
        };
        private final String P_1922_E;
        public static final Codec<J_1907_R> G_564_y;
        private static final Map<String, J_1907_R> u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        public abstract int n_1700_B(double var1, double var3, int var5);

        private J_1907_R(String name) {
            this.P_1922_E = name;
        }

        public String J_1907_R() {
            return this.P_1922_E;
        }

        @Override
        public String n_1700_B() {
            return this.P_1922_E;
        }

        public static J_1907_R n_1700_B(String name) {
            return u_1723_Y.get(name);
        }

        private static /* synthetic */ J_1907_R[] R_4764_Y() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            v_4262_N = lightning.product.BiomeSpecialEffects$J_1907_R.R_4764_Y();
            G_564_y = E_4700_p.n_1700_B(J_1907_R::values, J_1907_R::n_1700_B);
            u_1723_Y = Arrays.stream(lightning.product.BiomeSpecialEffects$J_1907_R.values()).collect(Collectors.toMap(J_1907_R::J_1907_R, modifier -> modifier));
        }
    }

    public static class n_1700_B {
        private OptionalInt n_1700_B = OptionalInt.empty();
        private OptionalInt J_1907_R = OptionalInt.empty();
        private OptionalInt R_4764_Y = OptionalInt.empty();
        private OptionalInt G_564_y = OptionalInt.empty();
        private Optional<Integer> P_1922_E = Optional.empty();
        private Optional<Integer> u_1723_Y = Optional.empty();
        private J_1907_R v_4262_N = lightning.product.BiomeSpecialEffects$J_1907_R.n_1700_B;
        private Optional<AmbientParticleSettings> w_1484_f = Optional.empty();
        private Optional<SoundEvent> t_148_a = Optional.empty();
        private Optional<AmbientMoodSettings> s_956_w = Optional.empty();
        private Optional<AmbientAdditionsSettings> u_2550_I = Optional.empty();
        private Optional<Music> M_588_G = Optional.empty();

        public n_1700_B n_1700_B(int fogColor) {
            this.n_1700_B = OptionalInt.of(fogColor);
            return this;
        }

        public n_1700_B J_1907_R(int waterColor) {
            this.J_1907_R = OptionalInt.of(waterColor);
            return this;
        }

        public n_1700_B R_4764_Y(int waterFogColor) {
            this.R_4764_Y = OptionalInt.of(waterFogColor);
            return this;
        }

        public n_1700_B G_564_y(int skyColor) {
            this.G_564_y = OptionalInt.of(skyColor);
            return this;
        }

        public n_1700_B P_1922_E(int foliageColor) {
            this.P_1922_E = Optional.of(foliageColor);
            return this;
        }

        public n_1700_B u_1723_Y(int grassColor) {
            this.u_1723_Y = Optional.of(grassColor);
            return this;
        }

        public n_1700_B n_1700_B(J_1907_R grassColorModifier) {
            this.v_4262_N = grassColorModifier;
            return this;
        }

        public n_1700_B n_1700_B(AmbientParticleSettings particle) {
            this.w_1484_f = Optional.of(particle);
            return this;
        }

        public n_1700_B n_1700_B(SoundEvent ambientSound) {
            this.t_148_a = Optional.of(ambientSound);
            return this;
        }

        public n_1700_B n_1700_B(AmbientMoodSettings moodSound) {
            this.s_956_w = Optional.of(moodSound);
            return this;
        }

        public n_1700_B n_1700_B(AmbientAdditionsSettings additionsSound) {
            this.u_2550_I = Optional.of(additionsSound);
            return this;
        }

        public n_1700_B n_1700_B(Music music) {
            this.M_588_G = Optional.of(music);
            return this;
        }

        public BiomeSpecialEffects n_1700_B() {
            return new BiomeSpecialEffects(this.n_1700_B.orElseThrow(() -> new IllegalStateException("Missing 'fog' color.")), this.J_1907_R.orElseThrow(() -> new IllegalStateException("Missing 'water' color.")), this.R_4764_Y.orElseThrow(() -> new IllegalStateException("Missing 'water fog' color.")), this.G_564_y.orElseThrow(() -> new IllegalStateException("Missing 'sky' color.")), this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
        }
    }
}


