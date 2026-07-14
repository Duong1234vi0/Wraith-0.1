/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;
import lightning.product.WeighedRandom;
import lightning.product.g_2336_b;

public class SpawnData
extends WeighedRandom.n_1700_B {
    private final U_2912_j n_1700_B;

    public SpawnData() {
        super(1);
        this.n_1700_B = new U_2912_j();
        this.n_1700_B.n_1700_B("id", "minecraft:pig");
    }

    public SpawnData(U_2912_j nbtIn) {
        this(nbtIn.R_4764_Y("Weight", 99) ? nbtIn.w_1484_f("Weight") : 1, nbtIn.M_182_A("Entity"));
    }

    public SpawnData(int itemWeightIn, U_2912_j nbtIn) {
        super(itemWeightIn);
        this.n_1700_B = nbtIn;
        g_2336_b resourcelocation = g_2336_b.J_1907_R(nbtIn.M_588_G("id"));
        if (resourcelocation != null) {
            nbtIn.n_1700_B("id", resourcelocation.toString());
        } else {
            nbtIn.n_1700_B("id", "minecraft:pig");
        }
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Entity", this.n_1700_B);
        compoundnbt.J_1907_R("Weight", this.R_4764_Y);
        return compoundnbt;
    }

    public U_2912_j J_1907_R() {
        return this.n_1700_B;
    }
}


