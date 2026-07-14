/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.TickList;
import lightning.product.D_38_f;
import lightning.product.DifficultyInstance;
import lightning.product.LevelTimeAccess;
import lightning.product.R_2450_T;
import lightning.product.LevelData;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.SoundEvent;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.CommonLevelAccessor;
import lightning.product.Fluid;

public interface LevelAccessor
extends LevelTimeAccess,
CommonLevelAccessor {
    @Override
    default public long A_4115_X() {
        return this.k_2293_S().u_1723_Y();
    }

    public TickList<T_2915_h> u_2550_I();

    public TickList<Fluid> M_588_G();

    public LevelData k_2293_S();

    public DifficultyInstance J_1907_R(c_1514_x var1);

    default public R_2450_T x_607_J() {
        return this.k_2293_S().u_2550_I();
    }

    public ChunkSource q_2307_F();

    @Override
    default public boolean R_4764_Y(int chunkX, int chunkZ) {
        return this.q_2307_F().P_1922_E(chunkX, chunkZ);
    }

    public Random e_4240_b();

    default public void n_1700_B(c_1514_x p_230547_1_, T_2915_h p_230547_2_) {
    }

    public void n_1700_B(@Nullable a_3913_L var1, c_1514_x var2, SoundEvent var3, D_38_f var4, float var5, float var6);

    public void n_1700_B(ParticleOptions var1, double var2, double var4, double var6, double var8, double var10, double var12);

    public void n_1700_B(@Nullable a_3913_L var1, int var2, c_1514_x var3, int var4);

    default public int n_3318_d() {
        return this.G_624_v().u_2550_I();
    }

    default public void R_4764_Y(int type, c_1514_x pos, int data) {
        this.n_1700_B((a_3913_L)null, type, pos, data);
    }
}


