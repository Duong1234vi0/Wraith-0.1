/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.Stats;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.q_1613_l;
import lightning.product.x_1688_C;

public class BoatItem
extends q_1613_l {
    private static final Predicate<N_4263_v> n_1700_B = I_408_V.v_4262_N.and(N_4263_v::C_290_v);
    private final g_1462_f.J_1907_R J_1907_R;

    public BoatItem(g_1462_f.J_1907_R typeIn, q_1613_l.n_1700_B properties) {
        super(properties);
        this.J_1907_R = typeIn;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        BlockHitResult raytraceresult = BoatItem.n_1700_B(worldIn, playerIn, ClipContext.J_1907_R.R_4764_Y);
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.n_1700_B) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        e_2866_D vector3d = playerIn.t_148_a(1.0f);
        double d0 = 5.0;
        List<N_4263_v> list = worldIn.J_1907_R((N_4263_v)playerIn, playerIn.i_601_W().expand(vector3d.n_1700_B(5.0)).grow(1.0), n_1700_B);
        if (!list.isEmpty()) {
            e_2866_D vector3d1 = playerIn.u_2550_I(1.0f);
            for (N_4263_v entity : list) {
                I_4817_s axisalignedbb = entity.i_601_W().grow(entity.G_424_k());
                if (!axisalignedbb.contains(vector3d1)) continue;
                return InteractionResultHolder.R_4764_Y(itemstack);
            }
        }
        if (((HitResult)raytraceresult).R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            g_1462_f boatentity = new g_1462_f(worldIn, raytraceresult.P_1922_E().J_1907_R, raytraceresult.P_1922_E().R_4764_Y, raytraceresult.P_1922_E().G_564_y);
            boatentity.n_1700_B(this.J_1907_R);
            boatentity.p_178_J = playerIn.p_178_J;
            if (!worldIn.a_(boatentity, boatentity.i_601_W().grow(-0.1))) {
                return InteractionResultHolder.G_564_y(itemstack);
            }
            if (!worldIn.Y_259_p) {
                worldIn.a_(boatentity);
                if (!playerIn.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
            }
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
            return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
        }
        return InteractionResultHolder.R_4764_Y(itemstack);
    }
}


