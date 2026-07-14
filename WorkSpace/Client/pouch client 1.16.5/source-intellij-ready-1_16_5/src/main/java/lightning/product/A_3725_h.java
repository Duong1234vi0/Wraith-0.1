/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.C_990_G;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.V_3354_l;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;

public class A_3725_h
extends q_1613_l {
    public A_3725_h(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        double d2;
        double d1;
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (!blockstate.n_1700_B(a_3742_W.ClientBootstrap) && !blockstate.n_1700_B(a_3742_W.Z_875_P)) {
            return m_3054_I.G_564_y;
        }
        c_1514_x blockpos1 = blockpos.up();
        if (!world.u_1723_Y(blockpos1)) {
            return m_3054_I.G_564_y;
        }
        double d0 = blockpos1.getX();
        List<N_4263_v> list = world.n_1700_B((N_4263_v)null, new I_4817_s(d0, d1 = (double)blockpos1.getY(), d2 = (double)blockpos1.getZ(), d0 + 1.0, d1 + 2.0, d2 + 1.0));
        if (!list.isEmpty()) {
            return m_3054_I.G_564_y;
        }
        if (world instanceof e_3591_l) {
            V_3354_l endercrystalentity = new V_3354_l(world, d0 + 0.5, d1, d2 + 0.5);
            endercrystalentity.n_1700_B(false);
            world.a_(endercrystalentity);
            C_990_G dragonfightmanager = ((e_3591_l)world).UploadStatus();
            if (dragonfightmanager != null) {
                dragonfightmanager.P_1922_E();
            }
        }
        context.getItem().v_4262_N(1);
        return m_3054_I.n_1700_B(world.Y_259_p);
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return true;
    }
}



