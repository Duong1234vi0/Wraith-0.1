/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.TheEndBiomeSource;
import lightning.product.G_156_T;
import lightning.product.V_3137_a;
import lightning.product.Z_3903_F;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.n_395_H;
import lightning.product.n_880_h;
import lightning.product.v_1758_J;
import lightning.product.z_1753_f;

public final class LevelStem {
    public static final Codec<LevelStem> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)Z_3903_F.h_1847_R.fieldOf("type").forGetter(LevelStem::n_1700_B), (App)z_1753_f.n_1700_B.fieldOf("generator").forGetter(LevelStem::R_4764_Y)).apply((Applicative)builder, builder.stable(LevelStem::new)));
    public static final f_2392_k<LevelStem> J_1907_R = f_2392_k.n_1700_B(V_3137_a.v_4276_D, new g_2336_b("overworld"));
    public static final f_2392_k<LevelStem> R_4764_Y = f_2392_k.n_1700_B(V_3137_a.v_4276_D, new g_2336_b("the_nether"));
    public static final f_2392_k<LevelStem> G_564_y = f_2392_k.n_1700_B(V_3137_a.v_4276_D, new g_2336_b("the_end"));
    private static final LinkedHashSet<f_2392_k<LevelStem>> P_1922_E = Sets.newLinkedHashSet((Iterable)ImmutableList.of(J_1907_R, R_4764_Y, G_564_y));
    private final Supplier<Z_3903_F> u_1723_Y;
    private final z_1753_f v_4262_N;

    public LevelStem(Supplier<Z_3903_F> dimensionTypeSupplier, z_1753_f chunkGenerator) {
        this.u_1723_Y = dimensionTypeSupplier;
        this.v_4262_N = chunkGenerator;
    }

    public Supplier<Z_3903_F> n_1700_B() {
        return this.u_1723_Y;
    }

    public Z_3903_F J_1907_R() {
        return this.u_1723_Y.get();
    }

    public z_1753_f R_4764_Y() {
        return this.v_4262_N;
    }

    public static v_1758_J<LevelStem> n_1700_B(v_1758_J<LevelStem> registry) {
        v_1758_J<LevelStem> simpleregistry = new v_1758_J<LevelStem>(V_3137_a.v_4276_D, Lifecycle.experimental());
        for (f_2392_k f_2392_k2 : P_1922_E) {
            LevelStem dimension = registry.n_1700_B(f_2392_k2);
            if (dimension == null) continue;
            simpleregistry.n_1700_B(f_2392_k2, dimension, registry.G_564_y(dimension));
        }
        for (Map.Entry entry : registry.P_1922_E()) {
            f_2392_k registrykey1 = (f_2392_k)entry.getKey();
            if (P_1922_E.contains(registrykey1)) continue;
            simpleregistry.n_1700_B(registrykey1, (LevelStem)entry.getValue(), registry.G_564_y((LevelStem)entry.getValue()));
        }
        return simpleregistry;
    }

    public static boolean n_1700_B(long seed, v_1758_J<LevelStem> registry) {
        ArrayList list = Lists.newArrayList(registry.P_1922_E());
        if (list.size() != P_1922_E.size()) {
            return false;
        }
        Map.Entry entry = (Map.Entry)list.get(0);
        Map.Entry entry1 = (Map.Entry)list.get(1);
        Map.Entry entry2 = (Map.Entry)list.get(2);
        if (entry.getKey() == J_1907_R && entry1.getKey() == R_4764_Y && entry2.getKey() == G_564_y) {
            if (!((LevelStem)entry.getValue()).J_1907_R().n_1700_B(Z_3903_F.t_148_a) && ((LevelStem)entry.getValue()).J_1907_R() != Z_3903_F.P_4830_p) {
                return false;
            }
            if (!((LevelStem)entry1.getValue()).J_1907_R().n_1700_B(Z_3903_F.s_956_w)) {
                return false;
            }
            if (!((LevelStem)entry2.getValue()).J_1907_R().n_1700_B(Z_3903_F.u_2550_I)) {
                return false;
            }
            if (((LevelStem)entry1.getValue()).R_4764_Y() instanceof n_395_H && ((LevelStem)entry2.getValue()).R_4764_Y() instanceof n_395_H) {
                n_395_H noisechunkgenerator = (n_395_H)((LevelStem)entry1.getValue()).R_4764_Y();
                n_395_H noisechunkgenerator1 = (n_395_H)((LevelStem)entry2.getValue()).R_4764_Y();
                if (!noisechunkgenerator.n_1700_B(seed, G_156_T.P_1922_E)) {
                    return false;
                }
                if (!noisechunkgenerator1.n_1700_B(seed, G_156_T.u_1723_Y)) {
                    return false;
                }
                if (!(noisechunkgenerator.G_564_y() instanceof n_880_h)) {
                    return false;
                }
                n_880_h netherbiomeprovider = (n_880_h)noisechunkgenerator.G_564_y();
                if (!netherbiomeprovider.J_1907_R(seed)) {
                    return false;
                }
                if (!(noisechunkgenerator1.G_564_y() instanceof TheEndBiomeSource)) {
                    return false;
                }
                TheEndBiomeSource endbiomeprovider = (TheEndBiomeSource)noisechunkgenerator1.G_564_y();
                return endbiomeprovider.J_1907_R(seed);
            }
            return false;
        }
        return false;
    }
}


