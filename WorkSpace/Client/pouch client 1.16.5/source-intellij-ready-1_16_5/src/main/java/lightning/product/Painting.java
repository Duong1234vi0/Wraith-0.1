/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.N_4263_v;
import lightning.product.P_2973_E;
import lightning.product.ClientboundAddPaintingPacket;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.Motive;

public class Painting
extends P_2973_E {
    public Motive G_564_y;

    public Painting(t_5_h<? extends Painting> type, b_4507_u worldIn) {
        super((t_5_h<? extends P_2973_E>)type, worldIn);
    }

    public Painting(b_4507_u worldIn, c_1514_x pos, b_257_Y facing) {
        super(t_5_h.l_1233_K, worldIn, pos);
        ArrayList list = Lists.newArrayList();
        int i = 0;
        Iterator iterator = V_3137_a.Z_976_R.iterator();
        while (iterator.hasNext()) {
            Motive paintingtype;
            this.G_564_y = paintingtype = (Motive)iterator.next();
            this.n_1700_B(facing);
            if (!this.u_1723_Y()) continue;
            list.add(paintingtype);
            int j = paintingtype.n_1700_B() * paintingtype.J_1907_R();
            if (j <= i) continue;
            i = j;
        }
        if (!list.isEmpty()) {
            Iterator iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                Motive paintingtype1 = (Motive)iterator2.next();
                if (paintingtype1.n_1700_B() * paintingtype1.J_1907_R() >= i) continue;
                iterator2.remove();
            }
            this.G_564_y = (Motive)list.get(this.RealmsWorldOptions.nextInt(list.size()));
        }
        this.n_1700_B(facing);
    }

    public Painting(b_4507_u worldIn, c_1514_x pos, b_257_Y facing, Motive artIn) {
        this(worldIn, pos, facing);
        this.G_564_y = artIn;
        this.n_1700_B(facing);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Motive", V_3137_a.Z_976_R.J_1907_R(this.G_564_y).toString());
        compound.n_1700_B("Facing", (byte)this.R_4764_Y.G_564_y());
        super.n_1700_B(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.G_564_y = V_3137_a.Z_976_R.n_1700_B(g_2336_b.J_1907_R(compound.M_588_G("Motive")));
        this.R_4764_Y = b_257_Y.J_1907_R(compound.u_1723_Y("Facing"));
        super.J_1907_R(compound);
        this.n_1700_B(this.R_4764_Y);
    }

    @Override
    public int v_4262_N() {
        return this.G_564_y == null ? 1 : this.G_564_y.n_1700_B();
    }

    @Override
    public int w_1484_f() {
        return this.G_564_y == null ? 1 : this.G_564_y.J_1907_R();
    }

    @Override
    public void n_1700_B(@Nullable N_4263_v brokenEntity) {
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            this.n_1700_B(SoundEvents.Trails, 1.0f, 1.0f);
            if (brokenEntity instanceof a_3913_L) {
                a_3913_L playerentity = (a_3913_L)brokenEntity;
                if (playerentity.C_415_h.G_564_y) {
                    return;
                }
            }
            this.n_1700_B(Items.q_3115_L);
        }
    }

    @Override
    public void t_148_a() {
        this.n_1700_B(SoundEvents.Trajectory, 1.0f, 1.0f);
    }

    @Override
    public void J_1907_R(double x, double y, double z, float yaw, float pitch) {
        this.J_1907_R(x, y, z);
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        c_1514_x blockpos = this.J_1907_R.add(x - this.O_3598_v(), y - this.X_2960_b(), z - this.l_2647_k());
        this.J_1907_R(blockpos.getX(), blockpos.getY(), blockpos.getZ());
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddPaintingPacket(this);
    }
}



