/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lightning.product.LootContextParams;
import lightning.product.MobEffects;
import lightning.product.L_2225_p;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.Z_1993_T;
import lightning.product.a_3236_r;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.Behavior;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class R_1722_J
extends Behavior<L_2225_p> {
    private static final Map<VillagerProfession, g_2336_b> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), giftMap -> {
        giftMap.put(VillagerProfession.J_1907_R, o_4810_o.RealmsClientConfig);
        giftMap.put(VillagerProfession.R_4764_Y, o_4810_o.f_4016_n);
        giftMap.put(VillagerProfession.G_564_y, o_4810_o.j_276_v);
        giftMap.put(VillagerProfession.P_1922_E, o_4810_o.UploadStatus);
        giftMap.put(VillagerProfession.u_1723_Y, o_4810_o.e_1992_r);
        giftMap.put(VillagerProfession.v_4262_N, o_4810_o.D_60_a);
        giftMap.put(VillagerProfession.w_1484_f, o_4810_o.k_3961_g);
        giftMap.put(VillagerProfession.t_148_a, o_4810_o.Ops);
        giftMap.put(VillagerProfession.s_956_w, o_4810_o.h_4320_q);
        giftMap.put(VillagerProfession.u_2550_I, o_4810_o.t_4219_U);
        giftMap.put(VillagerProfession.P_4830_p, o_4810_o.V_1446_Y);
        giftMap.put(VillagerProfession.h_1847_R, o_4810_o.PlayerInfo);
        giftMap.put(VillagerProfession.Q_4569_t, o_4810_o.V_1225_t);
    });
    private int R_4764_Y = 600;
    private boolean G_564_y;
    private long P_1922_E;

    public R_1722_J(int duration) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.u_2550_I, (Object)((Object)S_50_d.n_1700_B)), duration);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        if (!this.J_1907_R(owner)) {
            return false;
        }
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
            return false;
        }
        return true;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        this.G_564_y = false;
        this.P_1922_E = gameTimeIn;
        a_3913_L playerentity = this.R_4764_Y(entityIn).get();
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.t_1786_h, playerentity);
        a_3236_r.n_1700_B((r_4811_B)entityIn, (r_4811_B)playerentity);
    }

    protected boolean J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.J_1907_R(entityIn) && !this.G_564_y;
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        a_3913_L playerentity = this.R_4764_Y(owner).get();
        a_3236_r.n_1700_B((r_4811_B)owner, (r_4811_B)playerentity);
        if (this.n_1700_B(owner, playerentity)) {
            if (gameTime - this.P_1922_E > 20L) {
                this.n_1700_B(owner, (r_4811_B)playerentity);
                this.G_564_y = true;
            }
        } else {
            a_3236_r.n_1700_B((r_4811_B)owner, playerentity, 0.5f, 5);
        }
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        this.R_4764_Y = R_1722_J.n_1700_B(worldIn);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.t_1786_h);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.h_1847_R);
    }

    private void n_1700_B(L_2225_p villager, r_4811_B hero) {
        for (Z_1993_T itemstack : this.n_1700_B(villager)) {
            a_3236_r.n_1700_B((r_4811_B)villager, itemstack, hero.s_4990_V());
        }
    }

    private List<Z_1993_T> n_1700_B(L_2225_p villager) {
        if (villager.d_()) {
            return ImmutableList.of((Object)new Z_1993_T(Items.w_728_N));
        }
        VillagerProfession villagerprofession = villager.c_2086_l().J_1907_R();
        if (n_1700_B.containsKey(villagerprofession)) {
            p_4985_U loottable = villager.O_508_d.T_2506_i().F_2624_D().n_1700_B(n_1700_B.get(villagerprofession));
            q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)villager.O_508_d).n_1700_B(LootContextParams.u_1723_Y, villager.s_4990_V()).n_1700_B(LootContextParams.n_1700_B, villager).n_1700_B(villager.M_3508_C());
            return loottable.n_1700_B(lootcontext$builder.n_1700_B(f_1402_I.v_4262_N));
        }
        return ImmutableList.of((Object)new Z_1993_T(Items.G_4691_Q));
    }

    private boolean J_1907_R(L_2225_p villager) {
        return this.R_4764_Y(villager).isPresent();
    }

    private Optional<a_3913_L> R_4764_Y(L_2225_p villager) {
        return villager.y_1945_D().R_4764_Y(MemoryModuleType.u_2550_I).filter(this::n_1700_B);
    }

    private boolean n_1700_B(a_3913_L player) {
        return player.J_1907_R(MobEffects.x_607_J);
    }

    private boolean n_1700_B(L_2225_p villager, a_3913_L hero) {
        c_1514_x blockpos = hero.b_2312_j();
        c_1514_x blockpos1 = villager.b_2312_j();
        return blockpos1.withinDistance(blockpos, 5.0);
    }

    private static int n_1700_B(e_3591_l world) {
        return 600 + world.w_1457_N.nextInt(6001);
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        return this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


