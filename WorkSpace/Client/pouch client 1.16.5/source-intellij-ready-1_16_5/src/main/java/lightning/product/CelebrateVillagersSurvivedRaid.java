/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.J_3992_v;
import lightning.product.L_2225_p;
import lightning.product.MoveToSkySeeingSpot;
import lightning.product.S_50_d;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_3129_s;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.Behavior;
import lightning.product.j_3341_s;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.FireworkRocketItem;
import lightning.product.MemoryModuleType;

public class CelebrateVillagersSurvivedRaid
extends Behavior<L_2225_p> {
    @Nullable
    private b_3129_s n_1700_B;

    public CelebrateVillagersSurvivedRaid(int durationMin, int durationMax) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), durationMin, durationMax);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        c_1514_x blockpos = owner.b_2312_j();
        this.n_1700_B = worldIn.Z_875_P(blockpos);
        return this.n_1700_B != null && this.n_1700_B.P_1922_E() && MoveToSkySeeingSpot.n_1700_B(worldIn, owner, blockpos);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.n_1700_B != null && !this.n_1700_B.G_564_y();
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        this.n_1700_B = null;
        entityIn.y_1945_D().n_1700_B(worldIn.Z_976_R(), worldIn.X_933_l());
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        Random random = owner.M_3508_C();
        if (random.nextInt(100) == 0) {
            owner.V_1176_p();
        }
        if (random.nextInt(200) == 0 && MoveToSkySeeingSpot.n_1700_B(worldIn, owner, owner.b_2312_j())) {
            e_933_M dyecolor = j_3341_s.n_1700_B(e_933_M.values(), random);
            int i = random.nextInt(3);
            Z_1993_T itemstack = this.n_1700_B(dyecolor, i);
            J_3992_v fireworkrocketentity = new J_3992_v(owner.O_508_d, owner, owner.O_3598_v(), owner.X_2048_Y(), owner.l_2647_k(), itemstack);
            owner.O_508_d.a_(fireworkrocketentity);
        }
    }

    private Z_1993_T n_1700_B(e_933_M color, int flightTime) {
        Z_1993_T itemstack = new Z_1993_T(Items.FenceBlock, 1);
        Z_1993_T itemstack1 = new Z_1993_T(Items.FenceGateBlock);
        U_2912_j compoundnbt = itemstack1.n_1700_B("Explosion");
        ArrayList list = Lists.newArrayList();
        list.add(color.u_1723_Y());
        compoundnbt.n_1700_B("Colors", list);
        compoundnbt.n_1700_B("Type", (byte)FireworkRocketItem.n_1700_B.P_1922_E.n_1700_B());
        U_2912_j compoundnbt1 = itemstack.n_1700_B("Fireworks");
        q_2896_o listnbt = new q_2896_o();
        U_2912_j compoundnbt2 = itemstack1.J_1907_R("Explosion");
        if (compoundnbt2 != null) {
            listnbt.add(compoundnbt2);
        }
        compoundnbt1.n_1700_B("Flight", (byte)flightTime);
        if (!listnbt.isEmpty()) {
            compoundnbt1.n_1700_B("Explosions", listnbt);
        }
        return itemstack;
    }
}


