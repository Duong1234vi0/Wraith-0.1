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
import lightning.product.VillagerProfession;
import lightning.product.R_3043_n;
import lightning.product.V_3137_a;

public class VillagerData {
    private static final int[] J_1907_R = new int[]{0, 10, 70, 150, 250};
    public static final Codec<VillagerData> n_1700_B = RecordCodecBuilder.create(dataInstance -> dataInstance.group((App)V_3137_a.O_508_d.fieldOf("type").orElseGet(() -> R_3043_n.R_4764_Y).forGetter(data -> data.R_4764_Y), (App)V_3137_a.r_715_M.fieldOf("profession").orElseGet(() -> VillagerProfession.n_1700_B).forGetter(data -> data.G_564_y), (App)Codec.INT.fieldOf("level").orElse((Object)1).forGetter(data -> data.P_1922_E)).apply((Applicative)dataInstance, VillagerData::new));
    private final R_3043_n R_4764_Y;
    private final VillagerProfession G_564_y;
    private final int P_1922_E;

    public VillagerData(R_3043_n type, VillagerProfession profession, int level) {
        this.R_4764_Y = type;
        this.G_564_y = profession;
        this.P_1922_E = Math.max(1, level);
    }

    public R_3043_n n_1700_B() {
        return this.R_4764_Y;
    }

    public VillagerProfession J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return this.P_1922_E;
    }

    public VillagerData n_1700_B(R_3043_n typeIn) {
        return new VillagerData(typeIn, this.G_564_y, this.P_1922_E);
    }

    public VillagerData n_1700_B(VillagerProfession professionIn) {
        return new VillagerData(this.R_4764_Y, professionIn, this.P_1922_E);
    }

    public VillagerData n_1700_B(int levelIn) {
        return new VillagerData(this.R_4764_Y, this.G_564_y, levelIn);
    }

    public static int J_1907_R(int level) {
        return VillagerData.G_564_y(level) ? J_1907_R[level - 1] : 0;
    }

    public static int R_4764_Y(int level) {
        return VillagerData.G_564_y(level) ? J_1907_R[level] : 0;
    }

    public static boolean G_564_y(int level) {
        return level >= 1 && level < 5;
    }
}


