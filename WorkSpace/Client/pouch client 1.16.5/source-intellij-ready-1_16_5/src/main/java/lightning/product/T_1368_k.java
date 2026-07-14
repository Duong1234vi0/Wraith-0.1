/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.P_3504_Q;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.d_742_e;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.CommandBlock;
import lightning.product.i_2154_H;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;
import lightning.product.y_2498_m;

public class T_1368_k
extends i_2154_H {
    private boolean n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private final d_742_e P_1922_E = new d_742_e(){

        @Override
        public void n_1700_B(String command) {
            super.n_1700_B(command);
            T_1368_k.this.J_1907_R();
        }

        @Override
        public e_3591_l n_1700_B() {
            return (e_3591_l)T_1368_k.this.u_2550_I;
        }

        @Override
        public void J_1907_R() {
            K_4074_S blockstate = T_1368_k.this.u_2550_I.getBlockState(T_1368_k.this.M_588_G);
            this.n_1700_B().n_1700_B(T_1368_k.this.M_588_G, blockstate, blockstate, 3);
        }

        @Override
        public e_2866_D R_4764_Y() {
            return e_2866_D.n_1700_B(T_1368_k.this.M_588_G);
        }

        @Override
        public y_2498_m P_1922_E() {
            return new y_2498_m(this, e_2866_D.n_1700_B(T_1368_k.this.M_588_G), P_3504_Q.n_1700_B, this.n_1700_B(), 2, this.t_148_a().getString(), this.t_148_a(), this.n_1700_B().T_2506_i(), null);
        }
    };

    public T_1368_k() {
        super(BlockEntityType.Q_2552_b);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.P_1922_E.n_1700_B(compound);
        compound.n_1700_B("powered", this.v_4262_N());
        compound.n_1700_B("conditionMet", this.u_2550_I());
        compound.n_1700_B("auto", this.w_1484_f());
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.P_1922_E.J_1907_R(nbt);
        this.n_1700_B = nbt.t_1786_h("powered");
        this.R_4764_Y = nbt.t_1786_h("conditionMet");
        this.J_1907_R(nbt.t_1786_h("auto"));
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        if (this.P_4830_p()) {
            this.R_4764_Y(false);
            U_2912_j compoundnbt = this.n_1700_B(new U_2912_j());
            return new ClientboundBlockEntityDataPacket(this.M_588_G, 2, compoundnbt);
        }
        return null;
    }

    @Override
    public boolean K_() {
        return true;
    }

    public d_742_e P_1922_E() {
        return this.P_1922_E;
    }

    public void n_1700_B(boolean poweredIn) {
        this.n_1700_B = poweredIn;
    }

    public boolean v_4262_N() {
        return this.n_1700_B;
    }

    public boolean w_1484_f() {
        return this.J_1907_R;
    }

    public void J_1907_R(boolean autoIn) {
        boolean flag = this.J_1907_R;
        this.J_1907_R = autoIn;
        if (!flag && autoIn && !this.n_1700_B && this.u_2550_I != null && this.h_1847_R() != lightning.product.T_1368_k$n_1700_B.n_1700_B) {
            this.t_1786_h();
        }
    }

    public void s_956_w() {
        n_1700_B commandblocktileentity$mode = this.h_1847_R();
        if (commandblocktileentity$mode == lightning.product.T_1368_k$n_1700_B.J_1907_R && (this.n_1700_B || this.J_1907_R) && this.u_2550_I != null) {
            this.t_1786_h();
        }
    }

    private void t_1786_h() {
        T_2915_h block = this.e_4240_b().J_1907_R();
        if (block instanceof CommandBlock) {
            this.M_588_G();
            this.u_2550_I.u_2550_I().n_1700_B(this.M_588_G, block, 1);
        }
    }

    public boolean u_2550_I() {
        return this.R_4764_Y;
    }

    public boolean M_588_G() {
        this.R_4764_Y = true;
        if (this.Q_4569_t()) {
            i_2154_H tileentity;
            c_1514_x blockpos = this.M_588_G.offset(this.u_2550_I.getBlockState(this.M_588_G).R_4764_Y(CommandBlock.P_4830_p).u_1723_Y());
            this.R_4764_Y = this.u_2550_I.getBlockState(blockpos).J_1907_R() instanceof CommandBlock ? (tileentity = this.u_2550_I.getTileEntity(blockpos)) instanceof T_1368_k && ((T_1368_k)tileentity).P_1922_E().u_1723_Y() > 0 : false;
        }
        return this.R_4764_Y;
    }

    public boolean P_4830_p() {
        return this.G_564_y;
    }

    public void R_4764_Y(boolean p_184252_1_) {
        this.G_564_y = p_184252_1_;
    }

    public n_1700_B h_1847_R() {
        K_4074_S blockstate = this.e_4240_b();
        if (blockstate.n_1700_B(a_3742_W.N_260_m)) {
            return lightning.product.T_1368_k$n_1700_B.R_4764_Y;
        }
        if (blockstate.n_1700_B(a_3742_W.ItemScroller)) {
            return lightning.product.T_1368_k$n_1700_B.J_1907_R;
        }
        return blockstate.n_1700_B(a_3742_W.ItemsCooldown) ? lightning.product.T_1368_k$n_1700_B.n_1700_B : lightning.product.T_1368_k$n_1700_B.R_4764_Y;
    }

    public boolean Q_4569_t() {
        K_4074_S blockstate = this.u_2550_I.getBlockState(this.x_607_J());
        return blockstate.J_1907_R() instanceof CommandBlock ? blockstate.R_4764_Y(CommandBlock.h_1847_R) : false;
    }

    @Override
    public void M_182_A() {
        this.d_2427_y();
        super.M_182_A();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.T_1368_k$n_1700_B.n_1700_B();
        }
    }
}



