/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.MutableComponent;
import lightning.product.J_2020_G;
import lightning.product.U_2912_j;
import lightning.product.c_1514_x;
import lightning.product.e_933_M;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.r_4889_F;
import lightning.product.x_282_a;

public class z_555_N {
    private final c_1514_x n_1700_B;
    private final e_933_M J_1907_R;
    @Nullable
    private final x_282_a R_4764_Y;

    public z_555_N(c_1514_x pos, e_933_M color, @Nullable x_282_a name) {
        this.n_1700_B = pos;
        this.J_1907_R = color;
        this.R_4764_Y = name;
    }

    public static z_555_N n_1700_B(U_2912_j nbt) {
        c_1514_x blockpos = n_3832_I.J_1907_R(nbt.M_182_A("Pos"));
        e_933_M dyecolor = e_933_M.n_1700_B(nbt.M_588_G("Color"), e_933_M.n_1700_B);
        MutableComponent itextcomponent = nbt.P_1922_E("Name") ? x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("Name")) : null;
        return new z_555_N(blockpos, dyecolor, itextcomponent);
    }

    @Nullable
    public static z_555_N n_1700_B(BlockGetter reader, c_1514_x pos) {
        i_2154_H tileentity = reader.getTileEntity(pos);
        if (tileentity instanceof r_4889_F) {
            r_4889_F bannertileentity = (r_4889_F)tileentity;
            e_933_M dyecolor = bannertileentity.n_1700_B(() -> reader.getBlockState(pos));
            x_282_a itextcomponent = bannertileentity.t_3452_g() ? bannertileentity.k_2302_P() : null;
            return new z_555_N(pos, dyecolor, itextcomponent);
        }
        return null;
    }

    public c_1514_x n_1700_B() {
        return this.n_1700_B;
    }

    public J_2020_G.n_1700_B J_1907_R() {
        switch (this.J_1907_R) {
            case n_1700_B: {
                return J_2020_G.n_1700_B.u_2550_I;
            }
            case J_1907_R: {
                return J_2020_G.n_1700_B.M_588_G;
            }
            case R_4764_Y: {
                return J_2020_G.n_1700_B.P_4830_p;
            }
            case G_564_y: {
                return J_2020_G.n_1700_B.h_1847_R;
            }
            case P_1922_E: {
                return J_2020_G.n_1700_B.Q_4569_t;
            }
            case u_1723_Y: {
                return J_2020_G.n_1700_B.M_182_A;
            }
            case v_4262_N: {
                return J_2020_G.n_1700_B.t_1786_h;
            }
            case w_1484_f: {
                return J_2020_G.n_1700_B.multiplayerClientSuggestionProvider;
            }
            case t_148_a: {
                return J_2020_G.n_1700_B.w_1457_N;
            }
            case s_956_w: {
                return J_2020_G.n_1700_B.Y_601_j;
            }
            case u_2550_I: {
                return J_2020_G.n_1700_B.Y_259_p;
            }
            case M_588_G: {
                return J_2020_G.n_1700_B.Q_2552_b;
            }
            case P_4830_p: {
                return J_2020_G.n_1700_B.C_2741_M;
            }
            case h_1847_R: {
                return J_2020_G.n_1700_B.k_2293_S;
            }
            case Q_4569_t: {
                return J_2020_G.n_1700_B.q_2307_F;
            }
        }
        return J_2020_G.n_1700_B.Z_875_P;
    }

    @Nullable
    public x_282_a R_4764_Y() {
        return this.R_4764_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            z_555_N mapbanner = (z_555_N)p_equals_1_;
            return Objects.equals(this.n_1700_B, mapbanner.n_1700_B) && this.J_1907_R == mapbanner.J_1907_R && Objects.equals(this.R_4764_Y, mapbanner.R_4764_Y);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }

    public U_2912_j G_564_y() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Pos", n_3832_I.n_1700_B(this.n_1700_B));
        compoundnbt.n_1700_B("Color", this.J_1907_R.R_4764_Y());
        if (this.R_4764_Y != null) {
            compoundnbt.n_1700_B("Name", x_282_a.n_1700_B.n_1700_B(this.R_4764_Y));
        }
        return compoundnbt;
    }

    public String P_1922_E() {
        return "banner-" + this.n_1700_B.getX() + "," + this.n_1700_B.getY() + "," + this.n_1700_B.getZ();
    }
}


