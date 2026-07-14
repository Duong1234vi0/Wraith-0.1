/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.LockCode;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.SoundEvent;
import lightning.product.X_1924_A;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.BeaconMenu;
import lightning.product.g_422_i;
import lightning.product.i_2154_H;
import lightning.product.k_2610_C;
import lightning.product.ContainerData;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockTags;
import lightning.product.o_869_X;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.BlockEntityType;
import lightning.product.t_3286_u;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;
import lightning.product.z_2963_s;

public class x_3974_Q
extends i_2154_H
implements X_1924_A,
t_3286_u {
    public static final g_422_i[][] n_1700_B = new g_422_i[][]{{MobEffects.n_1700_B, MobEffects.R_4764_Y}, {MobEffects.u_2550_I, MobEffects.w_1484_f}, {MobEffects.P_1922_E}, {MobEffects.s_956_w}};
    private static final Set<g_422_i> J_1907_R = Arrays.stream(n_1700_B).flatMap(Arrays::stream).collect(Collectors.toSet());
    private List<n_1700_B> R_4764_Y = Lists.newArrayList();
    private List<n_1700_B> G_564_y = Lists.newArrayList();
    private int P_1922_E;
    private int u_1723_Y = -1;
    @Nullable
    private g_422_i v_4262_N;
    @Nullable
    private g_422_i w_1484_f;
    @Nullable
    private x_282_a t_148_a;
    private LockCode s_956_w = LockCode.n_1700_B;
    private final ContainerData h_1847_R = new ContainerData(){

        @Override
        public int n_1700_B(int index) {
            switch (index) {
                case 0: {
                    return x_3974_Q.this.P_1922_E;
                }
                case 1: {
                    return g_422_i.n_1700_B(x_3974_Q.this.v_4262_N);
                }
                case 2: {
                    return g_422_i.n_1700_B(x_3974_Q.this.w_1484_f);
                }
            }
            return 0;
        }

        @Override
        public void n_1700_B(int index, int value) {
            switch (index) {
                case 0: {
                    x_3974_Q.this.P_1922_E = value;
                    break;
                }
                case 1: {
                    if (!x_3974_Q.this.u_2550_I.Y_259_p && !x_3974_Q.this.R_4764_Y.isEmpty()) {
                        x_3974_Q.this.n_1700_B(SoundEvents.V_1225_t);
                    }
                    x_3974_Q.this.v_4262_N = x_3974_Q.n_1700_B(value);
                    break;
                }
                case 2: {
                    x_3974_Q.this.w_1484_f = x_3974_Q.n_1700_B(value);
                }
            }
        }

        @Override
        public int n_1700_B() {
            return 3;
        }
    };

    public x_3974_Q() {
        super(BlockEntityType.h_1847_R);
    }

    @Override
    public void P_1922_E() {
        c_1514_x blockpos;
        int i = this.M_588_G.getX();
        int j = this.M_588_G.getY();
        int k = this.M_588_G.getZ();
        if (this.u_1723_Y < j) {
            blockpos = this.M_588_G;
            this.G_564_y = Lists.newArrayList();
            this.u_1723_Y = blockpos.getY() - 1;
        } else {
            blockpos = new c_1514_x(i, this.u_1723_Y + 1, k);
        }
        n_1700_B beacontileentity$beamsegment = this.G_564_y.isEmpty() ? null : this.G_564_y.get(this.G_564_y.size() - 1);
        int l = this.u_2550_I.n_1700_B(z_2963_s.n_1700_B.J_1907_R, i, k);
        for (int i1 = 0; i1 < 10 && blockpos.getY() <= l; ++i1) {
            K_4074_S blockstate = this.u_2550_I.getBlockState(blockpos);
            T_2915_h block = blockstate.J_1907_R();
            if (block instanceof o_869_X) {
                float[] afloat = ((o_869_X)((Object)block)).J_1907_R().G_564_y();
                if (this.G_564_y.size() <= 1) {
                    beacontileentity$beamsegment = new n_1700_B(afloat);
                    this.G_564_y.add(beacontileentity$beamsegment);
                } else if (beacontileentity$beamsegment != null) {
                    if (Arrays.equals(afloat, beacontileentity$beamsegment.n_1700_B)) {
                        beacontileentity$beamsegment.n_1700_B();
                    } else {
                        beacontileentity$beamsegment = new n_1700_B(new float[]{(beacontileentity$beamsegment.n_1700_B[0] + afloat[0]) / 2.0f, (beacontileentity$beamsegment.n_1700_B[1] + afloat[1]) / 2.0f, (beacontileentity$beamsegment.n_1700_B[2] + afloat[2]) / 2.0f});
                        this.G_564_y.add(beacontileentity$beamsegment);
                    }
                }
            } else {
                if (beacontileentity$beamsegment == null || blockstate.J_1907_R((BlockGetter)this.u_2550_I, blockpos) >= 15 && block != a_3742_W.Z_875_P) {
                    this.G_564_y.clear();
                    this.u_1723_Y = l;
                    break;
                }
                beacontileentity$beamsegment.n_1700_B();
            }
            blockpos = blockpos.up();
            ++this.u_1723_Y;
        }
        int j1 = this.P_1922_E;
        if (this.u_2550_I.X_933_l() % 80L == 0L) {
            if (!this.R_4764_Y.isEmpty()) {
                this.n_1700_B(i, j, k);
            }
            if (this.P_1922_E > 0 && !this.R_4764_Y.isEmpty()) {
                this.s_956_w();
                this.n_1700_B(SoundEvents.V_1446_Y);
            }
        }
        if (this.u_1723_Y >= l) {
            this.u_1723_Y = -1;
            boolean flag = j1 > 0;
            this.R_4764_Y = this.G_564_y;
            if (!this.u_2550_I.Y_259_p) {
                boolean flag1;
                boolean bl = flag1 = this.P_1922_E > 0;
                if (!flag && flag1) {
                    this.n_1700_B(SoundEvents.t_4219_U);
                    for (B_4088_l serverplayerentity : this.u_2550_I.n_1700_B(B_4088_l.class, new I_4817_s(i, j, k, i, j - 4, k).grow(10.0, 5.0, 10.0))) {
                        U_3554_Q.M_588_G.n_1700_B(serverplayerentity, this);
                    }
                } else if (flag && !flag1) {
                    this.n_1700_B(SoundEvents.PlayerInfo);
                }
            }
        }
    }

    private void n_1700_B(int beaconXIn, int beaconYIn, int beaconZIn) {
        int j;
        this.P_1922_E = 0;
        int i = 1;
        while (i <= 4 && (j = beaconYIn - i) >= 0) {
            boolean flag = true;
            block1: for (int k = beaconXIn - i; k <= beaconXIn + i && flag; ++k) {
                for (int l = beaconZIn - i; l <= beaconZIn + i; ++l) {
                    if (this.u_2550_I.getBlockState(new c_1514_x(k, j, l)).n_1700_B(BlockTags.D_60_a)) continue;
                    flag = false;
                    continue block1;
                }
            }
            if (!flag) break;
            this.P_1922_E = i++;
        }
    }

    @Override
    public void I_() {
        this.n_1700_B(SoundEvents.PlayerInfo);
        super.I_();
    }

    private void s_956_w() {
        if (!this.u_2550_I.Y_259_p && this.v_4262_N != null) {
            double d0 = this.P_1922_E * 10 + 10;
            int i = 0;
            if (this.P_1922_E >= 4 && this.v_4262_N == this.w_1484_f) {
                i = 1;
            }
            int j = (9 + this.P_1922_E * 2) * 20;
            I_4817_s axisalignedbb = new I_4817_s(this.M_588_G).grow(d0).expand(0.0, this.u_2550_I.c_3005_b(), 0.0);
            List<a_3913_L> list = this.u_2550_I.n_1700_B(a_3913_L.class, axisalignedbb);
            for (a_3913_L playerentity : list) {
                playerentity.n_1700_B(new k_2610_C(this.v_4262_N, j, i, true, true));
            }
            if (this.P_1922_E >= 4 && this.v_4262_N != this.w_1484_f && this.w_1484_f != null) {
                for (a_3913_L playerentity1 : list) {
                    playerentity1.n_1700_B(new k_2610_C(this.w_1484_f, j, 0, true, true));
                }
            }
        }
    }

    public void n_1700_B(SoundEvent sound) {
        this.u_2550_I.n_1700_B((a_3913_L)null, this.M_588_G, sound, D_38_f.P_1922_E, 1.0f, 1.0f);
    }

    public List<n_1700_B> v_4262_N() {
        return this.P_1922_E == 0 ? ImmutableList.of() : this.R_4764_Y;
    }

    public int w_1484_f() {
        return this.P_1922_E;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 3, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    @Override
    public double t_148_a() {
        return 256.0;
    }

    @Nullable
    private static g_422_i n_1700_B(int effectId) {
        g_422_i effect = g_422_i.n_1700_B(effectId);
        return J_1907_R.contains(effect) ? effect : null;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.v_4262_N = x_3974_Q.n_1700_B(nbt.w_1484_f("Primary"));
        this.w_1484_f = x_3974_Q.n_1700_B(nbt.w_1484_f("Secondary"));
        if (nbt.R_4764_Y("CustomName", 8)) {
            this.t_148_a = x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("CustomName"));
        }
        this.s_956_w = LockCode.J_1907_R(nbt);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Primary", g_422_i.n_1700_B(this.v_4262_N));
        compound.J_1907_R("Secondary", g_422_i.n_1700_B(this.w_1484_f));
        compound.J_1907_R("Levels", this.P_1922_E);
        if (this.t_148_a != null) {
            compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(this.t_148_a));
        }
        this.s_956_w.n_1700_B(compound);
        return compound;
    }

    public void n_1700_B(@Nullable x_282_a aname) {
        this.t_148_a = aname;
    }

    @Override
    @Nullable
    public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
        return BaseContainerBlockEntity.n_1700_B(p_createMenu_3_, this.s_956_w, this.c_()) ? new BeaconMenu(p_createMenu_1_, p_createMenu_2_, this.h_1847_R, ContainerLevelAccess.n_1700_B(this.u_2550_I, this.x_607_J())) : null;
    }

    @Override
    public x_282_a c_() {
        return this.t_148_a != null ? this.t_148_a : new F_2904_S("container.beacon");
    }

    public static class n_1700_B {
        private final float[] n_1700_B;
        private int J_1907_R;

        public n_1700_B(float[] colorsIn) {
            this.n_1700_B = colorsIn;
            this.J_1907_R = 1;
        }

        protected void n_1700_B() {
            ++this.J_1907_R;
        }

        public float[] J_1907_R() {
            return this.n_1700_B;
        }

        public int R_4764_Y() {
            return this.J_1907_R;
        }
    }
}


