/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.q_4099_E;
import lightning.product.CrashReportCategory;
import lightning.product.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public abstract class i_2154_H {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final BlockEntityType<?> J_1907_R;
    @Nullable
    protected b_4507_u u_2550_I;
    protected c_1514_x M_588_G = c_1514_x.ZERO;
    protected boolean P_4830_p;
    @Nullable
    private K_4074_S R_4764_Y;
    private boolean G_564_y;

    public i_2154_H(BlockEntityType<?> tileEntityTypeIn) {
        this.J_1907_R = tileEntityTypeIn;
    }

    @Nullable
    public b_4507_u c_3005_b() {
        return this.u_2550_I;
    }

    public void J_1907_R(b_4507_u world, c_1514_x pos) {
        this.u_2550_I = world;
        this.M_588_G = pos.toImmutable();
    }

    public boolean t_4043_B() {
        return this.u_2550_I != null;
    }

    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        this.M_588_G = new c_1514_x(nbt.w_1484_f("x"), nbt.w_1484_f("y"), nbt.w_1484_f("z"));
    }

    public U_2912_j n_1700_B(U_2912_j compound) {
        return this.J_1907_R(compound);
    }

    private U_2912_j J_1907_R(U_2912_j compound) {
        g_2336_b resourcelocation = BlockEntityType.n_1700_B(this.z_1737_N());
        if (resourcelocation == null) {
            throw new RuntimeException(String.valueOf(this.getClass()) + " is missing a mapping! This is a bug!");
        }
        compound.n_1700_B("id", resourcelocation.toString());
        compound.J_1907_R("x", this.M_588_G.getX());
        compound.J_1907_R("y", this.M_588_G.getY());
        compound.J_1907_R("z", this.M_588_G.getZ());
        return compound;
    }

    @Nullable
    public static i_2154_H J_1907_R(K_4074_S state, U_2912_j nbt) {
        String s = nbt.M_588_G("id");
        return V_3137_a.X_933_l.J_1907_R(new g_2336_b(s)).map(type -> {
            try {
                return type.n_1700_B();
            }
            catch (Throwable throwable) {
                n_1700_B.error("Failed to create block entity {}", (Object)s, (Object)throwable);
                return null;
            }
        }).map(tileEntity -> {
            try {
                tileEntity.n_1700_B(state, nbt);
                return tileEntity;
            }
            catch (Throwable throwable) {
                n_1700_B.error("Failed to load data for block entity {}", (Object)s, (Object)throwable);
                return null;
            }
        }).orElseGet(() -> {
            n_1700_B.warn("Skipping BlockEntity with id {}", (Object)s);
            return null;
        });
    }

    public void J_1907_R() {
        if (this.u_2550_I != null) {
            this.R_4764_Y = this.u_2550_I.getBlockState(this.M_588_G);
            this.u_2550_I.J_1907_R(this.M_588_G, this);
            if (!this.R_4764_Y.v_4262_N()) {
                this.u_2550_I.R_4764_Y(this.M_588_G, this.R_4764_Y.J_1907_R());
            }
        }
    }

    public double t_148_a() {
        return 64.0;
    }

    public c_1514_x x_607_J() {
        return this.M_588_G;
    }

    public K_4074_S e_4240_b() {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = this.u_2550_I.getBlockState(this.M_588_G);
        }
        return this.R_4764_Y;
    }

    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return null;
    }

    public U_2912_j H_() {
        return this.J_1907_R(new U_2912_j());
    }

    public boolean n_3318_d() {
        return this.P_4830_p;
    }

    public void I_() {
        this.P_4830_p = true;
    }

    public void M_182_A() {
        this.P_4830_p = false;
    }

    public boolean a_(int id, int type) {
        return false;
    }

    public void d_2427_y() {
        this.R_4764_Y = null;
    }

    public void n_1700_B(CrashReportCategory reportCategory) {
        reportCategory.n_1700_B("Name", () -> String.valueOf(V_3137_a.X_933_l.J_1907_R(this.z_1737_N())) + " // " + this.getClass().getCanonicalName());
        if (this.u_2550_I != null) {
            CrashReportCategory.n_1700_B(reportCategory, this.M_588_G, this.e_4240_b());
            CrashReportCategory.n_1700_B(reportCategory, this.M_588_G, this.u_2550_I.getBlockState(this.M_588_G));
        }
    }

    public void R_4764_Y(c_1514_x posIn) {
        this.M_588_G = posIn.toImmutable();
    }

    public boolean K_() {
        return false;
    }

    public void J_1907_R(W_2163_m rotationIn) {
    }

    public void J_1907_R(q_4099_E mirrorIn) {
    }

    public BlockEntityType<?> z_1737_N() {
        return this.J_1907_R;
    }

    public void v_4276_D() {
        if (!this.G_564_y) {
            this.G_564_y = true;
            n_1700_B.warn("Block entity invalid: {} @ {}", new Supplier[]{() -> V_3137_a.X_933_l.J_1907_R(this.z_1737_N()), this::x_607_J});
        }
    }
}


