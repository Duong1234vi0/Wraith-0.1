/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.Rotations;
import lightning.product.D_38_f;
import lightning.product.D_686_b;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class N_2302_G
extends q_1613_l {
    public N_2302_G(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        b_257_Y direction = context.getFace();
        if (direction == b_257_Y.n_1700_B) {
            return m_3054_I.G_564_y;
        }
        b_4507_u world = context.getWorld();
        BlockPlaceContext blockitemusecontext = new BlockPlaceContext(context);
        c_1514_x blockpos = blockitemusecontext.getPos();
        Z_1993_T itemstack = context.getItem();
        e_2866_D vector3d = e_2866_D.R_4764_Y(blockpos);
        I_4817_s axisalignedbb = t_5_h.J_1907_R.u_2550_I().n_1700_B(vector3d.n_1700_B(), vector3d.J_1907_R(), vector3d.R_4764_Y());
        if (world.a_(null, axisalignedbb, p_242390_0_ -> true) && world.n_1700_B((N_4263_v)null, axisalignedbb).isEmpty()) {
            if (world instanceof e_3591_l) {
                e_3591_l serverworld = (e_3591_l)world;
                D_686_b armorstandentity = t_5_h.J_1907_R.J_1907_R(serverworld, itemstack.Q_4569_t(), null, context.getPlayer(), blockpos, a_3160_D.P_4830_p, true, true);
                if (armorstandentity == null) {
                    return m_3054_I.G_564_y;
                }
                serverworld.n_1700_B((N_4263_v)armorstandentity);
                float f = (float)u_530_F.G_564_y((u_530_F.v_4262_N(context.getPlacementYaw() - 180.0f) + 22.5f) / 45.0f) * 45.0f;
                armorstandentity.J_1907_R(armorstandentity.O_3598_v(), armorstandentity.X_2960_b(), armorstandentity.l_2647_k(), f, 0.0f);
                this.n_1700_B(armorstandentity, world.w_1457_N);
                world.a_(armorstandentity);
                world.n_1700_B((a_3913_L)null, armorstandentity.O_3598_v(), armorstandentity.X_2960_b(), armorstandentity.l_2647_k(), SoundEvents.Z_976_R, D_38_f.P_1922_E, 0.75f, 0.8f);
            }
            itemstack.v_4262_N(1);
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.G_564_y;
    }

    private void n_1700_B(D_686_b armorStand, Random rand) {
        Rotations rotations = armorStand.M_182_A();
        float f = rand.nextFloat() * 5.0f;
        float f1 = rand.nextFloat() * 20.0f - 10.0f;
        Rotations rotations1 = new Rotations(rotations.J_1907_R() + f, rotations.R_4764_Y() + f1, rotations.G_564_y());
        armorStand.n_1700_B(rotations1);
        rotations = armorStand.multiplayerClientSuggestionProvider();
        f = rand.nextFloat() * 10.0f - 5.0f;
        rotations1 = new Rotations(rotations.J_1907_R(), rotations.R_4764_Y() + f, rotations.G_564_y());
        armorStand.J_1907_R(rotations1);
    }
}


