/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.StrongholdConfiguration;
import lightning.product.StructureFeature;
import lightning.product.V_3137_a;
import lightning.product.V_4739_Y;

public class StructureSettings {
    public static final Codec<StructureSettings> n_1700_B = RecordCodecBuilder.create(p_236198_0_ -> p_236198_0_.group((App)StrongholdConfiguration.n_1700_B.optionalFieldOf("stronghold").forGetter(p_236200_0_ -> Optional.ofNullable(p_236200_0_.P_1922_E)), (App)Codec.simpleMap(V_3137_a.M_1641_O, V_4739_Y.n_1700_B, V_3137_a.M_1641_O).fieldOf("structures").forGetter(p_236196_0_ -> p_236196_0_.G_564_y)).apply((Applicative)p_236198_0_, StructureSettings::new));
    public static final ImmutableMap<StructureFeature<?>, V_4739_Y> J_1907_R = ImmutableMap.builder().put(StructureFeature.t_1786_h, (Object)new V_4739_Y(32, 8, 10387312)).put(StructureFeature.u_1723_Y, (Object)new V_4739_Y(32, 8, 14357617)).put(StructureFeature.v_4262_N, (Object)new V_4739_Y(32, 8, 14357618)).put(StructureFeature.P_1922_E, (Object)new V_4739_Y(32, 8, 14357619)).put((Object)StructureFeature.s_956_w, (Object)new V_4739_Y(32, 8, 14357620)).put(StructureFeature.J_1907_R, (Object)new V_4739_Y(32, 8, 165745296)).put(StructureFeature.u_2550_I, (Object)new V_4739_Y(1, 0, 0)).put(StructureFeature.M_588_G, (Object)new V_4739_Y(32, 5, 10387313)).put(StructureFeature.Q_4569_t, (Object)new V_4739_Y(20, 11, 10387313)).put(StructureFeature.G_564_y, (Object)new V_4739_Y(80, 20, 10387319)).put(StructureFeature.M_182_A, (Object)new V_4739_Y(1, 0, 0)).put(StructureFeature.R_4764_Y, (Object)new V_4739_Y(1, 0, 0)).put(StructureFeature.w_1484_f, (Object)new V_4739_Y(40, 15, 34222645)).put(StructureFeature.t_148_a, (Object)new V_4739_Y(24, 4, 165745295)).put(StructureFeature.P_4830_p, (Object)new V_4739_Y(20, 8, 14357621)).put(StructureFeature.w_1457_N, (Object)new V_4739_Y(27, 4, 30084232)).put(StructureFeature.h_1847_R, (Object)new V_4739_Y(27, 4, 30084232)).put(StructureFeature.multiplayerClientSuggestionProvider, (Object)new V_4739_Y(2, 1, 14357921)).build();
    public static final StrongholdConfiguration R_4764_Y;
    private final Map<StructureFeature<?>, V_4739_Y> G_564_y;
    @Nullable
    private final StrongholdConfiguration P_1922_E;

    public StructureSettings(Optional<StrongholdConfiguration> p_i231912_1_, Map<StructureFeature<?>, V_4739_Y> p_i231912_2_) {
        this.P_1922_E = p_i231912_1_.orElse(null);
        this.G_564_y = p_i231912_2_;
    }

    public StructureSettings(boolean p_i231913_1_) {
        this.G_564_y = Maps.newHashMap(J_1907_R);
        this.P_1922_E = p_i231913_1_ ? R_4764_Y : null;
    }

    public Map<StructureFeature<?>, V_4739_Y> n_1700_B() {
        return this.G_564_y;
    }

    @Nullable
    public V_4739_Y n_1700_B(StructureFeature<?> p_236197_1_) {
        return this.G_564_y.get(p_236197_1_);
    }

    @Nullable
    public StrongholdConfiguration J_1907_R() {
        return this.P_1922_E;
    }

    static {
        for (StructureFeature f_661_m : V_3137_a.M_1641_O) {
            if (J_1907_R.containsKey((Object)f_661_m)) continue;
            throw new IllegalStateException("Structure feature without default settings: " + String.valueOf(V_3137_a.M_1641_O.J_1907_R(f_661_m)));
        }
        R_4764_Y = new StrongholdConfiguration(32, 3, 128);
    }
}


