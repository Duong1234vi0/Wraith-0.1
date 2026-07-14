/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.Features;
import lightning.product.X_1924_A;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Feature;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.n_3832_I;
import lightning.product.BlockEntityType;
import lightning.product.u_530_F;
import lightning.product.w_2989_N;
import lightning.product.z_3539_x;
import lightning.product.EndGatewayConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_4605_O
extends TheEndPortalBlockEntity
implements X_1924_A {
    private static final Logger n_1700_B = LogManager.getLogger();
    private long J_1907_R;
    private int R_4764_Y;
    @Nullable
    private c_1514_x G_564_y;
    private boolean P_1922_E;

    public A_4605_O() {
        super(BlockEntityType.Y_259_p);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Age", this.J_1907_R);
        if (this.G_564_y != null) {
            compound.n_1700_B("ExitPortal", n_3832_I.n_1700_B(this.G_564_y));
        }
        if (this.P_1922_E) {
            compound.n_1700_B("ExactTeleport", this.P_1922_E);
        }
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.J_1907_R = nbt.t_148_a("Age");
        if (nbt.R_4764_Y("ExitPortal", 10)) {
            this.G_564_y = n_3832_I.J_1907_R(nbt.M_182_A("ExitPortal"));
        }
        this.P_1922_E = nbt.t_1786_h("ExactTeleport");
    }

    @Override
    public double t_148_a() {
        return 256.0;
    }

    @Override
    public void P_1922_E() {
        boolean flag = this.v_4262_N();
        boolean flag1 = this.w_1484_f();
        ++this.J_1907_R;
        if (flag1) {
            --this.R_4764_Y;
        } else if (!this.u_2550_I.Y_259_p) {
            List<N_4263_v> list = this.u_2550_I.n_1700_B(N_4263_v.class, new I_4817_s(this.x_607_J()), A_4605_O::n_1700_B);
            if (!list.isEmpty()) {
                this.J_1907_R(list.get(this.u_2550_I.w_1457_N.nextInt(list.size())));
            }
            if (this.J_1907_R % 2400L == 0L) {
                this.s_956_w();
            }
        }
        if (flag != this.v_4262_N() || flag1 != this.w_1484_f()) {
            this.J_1907_R();
        }
    }

    public static boolean n_1700_B(N_4263_v p_242690_0_) {
        return I_408_V.v_4262_N.test(p_242690_0_) && !p_242690_0_.d_3244_b().V_1225_t();
    }

    public boolean v_4262_N() {
        return this.J_1907_R < 200L;
    }

    public boolean w_1484_f() {
        return this.R_4764_Y > 0;
    }

    public float n_1700_B(float partialTicks) {
        return u_530_F.n_1700_B(((float)this.J_1907_R + partialTicks) / 200.0f, 0.0f, 1.0f);
    }

    public float J_1907_R(float partialTicks) {
        return 1.0f - u_530_F.n_1700_B(((float)this.R_4764_Y - partialTicks) / 40.0f, 0.0f, 1.0f);
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 8, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public void s_956_w() {
        if (!this.u_2550_I.Y_259_p) {
            this.R_4764_Y = 40;
            this.u_2550_I.n_1700_B(this.x_607_J(), this.e_4240_b().J_1907_R(), 1, 0);
            this.J_1907_R();
        }
    }

    @Override
    public boolean a_(int id, int type) {
        if (id == 1) {
            this.R_4764_Y = 40;
            return true;
        }
        return super.a_(id, type);
    }

    public void J_1907_R(N_4263_v entityIn) {
        if (this.u_2550_I instanceof e_3591_l && !this.w_1484_f()) {
            this.R_4764_Y = 100;
            if (this.G_564_y == null && this.u_2550_I.g_2268_R() == b_4507_u.w_1484_f) {
                this.n_1700_B((e_3591_l)this.u_2550_I);
            }
            if (this.G_564_y != null) {
                N_4263_v entity;
                c_1514_x blockpos;
                c_1514_x c_1514_x2 = blockpos = this.P_1922_E ? this.G_564_y : this.M_588_G();
                if (entityIn instanceof w_2989_N) {
                    N_4263_v entity1 = ((w_2989_N)entityIn).Y_601_j();
                    if (entity1 instanceof B_4088_l) {
                        U_3554_Q.G_564_y.n_1700_B((B_4088_l)entity1, this.u_2550_I.getBlockState(this.x_607_J()));
                    }
                    if (entity1 != null) {
                        entity = entity1;
                        entityIn.Ops();
                    } else {
                        entity = entityIn;
                    }
                } else {
                    entity = entityIn.d_3244_b();
                }
                entity.PlayerInfo();
                entity.M_588_G((double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5);
            }
            this.s_956_w();
        }
    }

    private c_1514_x M_588_G() {
        c_1514_x blockpos = A_4605_O.n_1700_B(this.u_2550_I, this.G_564_y.add(0, 2, 0), 5, false);
        n_1700_B.debug("Best exit position for portal at {} is {}", (Object)this.G_564_y, (Object)blockpos);
        return blockpos.up();
    }

    private void n_1700_B(e_3591_l p_227015_1_) {
        e_2866_D vector3d = new e_2866_D(this.x_607_J().getX(), 0.0, this.x_607_J().getZ()).G_564_y();
        e_2866_D vector3d1 = vector3d.n_1700_B(1024.0);
        int i = 16;
        while (A_4605_O.n_1700_B(p_227015_1_, vector3d1).s_956_w() > 0 && i-- > 0) {
            n_1700_B.debug("Skipping backwards past nonempty chunk at {}", (Object)vector3d1);
            vector3d1 = vector3d1.P_1922_E(vector3d.n_1700_B(-16.0));
        }
        int j = 16;
        while (A_4605_O.n_1700_B(p_227015_1_, vector3d1).s_956_w() == 0 && j-- > 0) {
            n_1700_B.debug("Skipping forward past empty chunk at {}", (Object)vector3d1);
            vector3d1 = vector3d1.P_1922_E(vector3d.n_1700_B(16.0));
        }
        n_1700_B.debug("Found chunk at {}", (Object)vector3d1);
        H_1748_a chunk = A_4605_O.n_1700_B(p_227015_1_, vector3d1);
        this.G_564_y = A_4605_O.n_1700_B(chunk);
        if (this.G_564_y == null) {
            this.G_564_y = new c_1514_x(vector3d1.J_1907_R + 0.5, 75.0, vector3d1.G_564_y + 0.5);
            n_1700_B.debug("Failed to find suitable block, settling on {}", (Object)this.G_564_y);
            Features.P_1922_E.n_1700_B(p_227015_1_, p_227015_1_.Y_259_p().t_148_a(), new Random(this.G_564_y.toLong()), this.G_564_y);
        } else {
            n_1700_B.debug("Found block at {}", (Object)this.G_564_y);
        }
        this.G_564_y = A_4605_O.n_1700_B(p_227015_1_, this.G_564_y, 16, true);
        n_1700_B.debug("Creating portal at {}", (Object)this.G_564_y);
        this.G_564_y = this.G_564_y.up(10);
        this.n_1700_B(p_227015_1_, this.G_564_y);
        this.J_1907_R();
    }

    private static c_1514_x n_1700_B(BlockGetter worldIn, c_1514_x posIn, int radius, boolean allowBedrock) {
        z_3539_x blockpos = null;
        for (int i = -radius; i <= radius; ++i) {
            block1: for (int j = -radius; j <= radius; ++j) {
                if (i == 0 && j == 0 && !allowBedrock) continue;
                for (int k = 255; k > (blockpos == null ? 0 : blockpos.getY()); --k) {
                    c_1514_x blockpos1 = new c_1514_x(posIn.getX() + i, k, posIn.getZ() + j);
                    K_4074_S blockstate = worldIn.getBlockState(blockpos1);
                    if (!blockstate.multiplayerClientSuggestionProvider(worldIn, blockpos1) || !allowBedrock && blockstate.n_1700_B(a_3742_W.Z_875_P)) continue;
                    blockpos = blockpos1;
                    continue block1;
                }
            }
        }
        return blockpos == null ? posIn : blockpos;
    }

    private static H_1748_a n_1700_B(b_4507_u worldIn, e_2866_D vec3) {
        return worldIn.u_1723_Y(u_530_F.R_4764_Y(vec3.J_1907_R / 16.0), u_530_F.R_4764_Y(vec3.G_564_y / 16.0));
    }

    @Nullable
    private static c_1514_x n_1700_B(H_1748_a chunkIn) {
        Y_1387_d chunkpos = chunkIn.getPos();
        c_1514_x blockpos = new c_1514_x(chunkpos.J_1907_R(), 30, chunkpos.R_4764_Y());
        int i = chunkIn.s_956_w() + 16 - 1;
        c_1514_x blockpos1 = new c_1514_x(chunkpos.G_564_y(), i, chunkpos.P_1922_E());
        c_1514_x blockpos2 = null;
        double d0 = 0.0;
        for (c_1514_x blockpos3 : c_1514_x.getAllInBoxMutable(blockpos, blockpos1)) {
            K_4074_S blockstate = chunkIn.getBlockState(blockpos3);
            c_1514_x blockpos4 = blockpos3.up();
            c_1514_x blockpos5 = blockpos3.up(2);
            if (!blockstate.n_1700_B(a_3742_W.e_1231_S) || chunkIn.getBlockState(blockpos4).multiplayerClientSuggestionProvider(chunkIn, blockpos4) || chunkIn.getBlockState(blockpos5).multiplayerClientSuggestionProvider(chunkIn, blockpos5)) continue;
            double d1 = blockpos3.distanceSq(0.0, 0.0, 0.0, true);
            if (blockpos2 != null && !(d1 < d0)) continue;
            blockpos2 = blockpos3;
            d0 = d1;
        }
        return blockpos2;
    }

    private void n_1700_B(e_3591_l p_227016_1_, c_1514_x p_227016_2_) {
        Feature.Y_1740_V.J_1907_R(EndGatewayConfiguration.n_1700_B(this.x_607_J(), false)).n_1700_B(p_227016_1_, p_227016_1_.Y_259_p().t_148_a(), new Random(), p_227016_2_);
    }

    @Override
    public boolean n_1700_B(b_257_Y face) {
        return T_2915_h.R_4764_Y(this.e_4240_b(), this.u_2550_I, this.x_607_J(), face);
    }

    public int u_2550_I() {
        int i = 0;
        for (b_257_Y direction : b_257_Y.values()) {
            i += this.n_1700_B(direction) ? 1 : 0;
        }
        return i;
    }

    public void n_1700_B(c_1514_x exitPortalIn, boolean p_195489_2_) {
        this.P_1922_E = p_195489_2_;
        this.G_564_y = exitPortalIn;
    }
}


