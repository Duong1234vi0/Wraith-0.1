/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.CommandSource;
import lightning.product.MutableComponent;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.Z_1567_W;
import lightning.product.a_3913_L;
import lightning.product.FormattedCharSequence;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.i_2154_H;
import lightning.product.i_2909_p;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class A_4313_D
extends i_2154_H {
    private final x_282_a[] n_1700_B = new x_282_a[]{U_2871_b.R_4764_Y, U_2871_b.R_4764_Y, U_2871_b.R_4764_Y, U_2871_b.R_4764_Y};
    private boolean J_1907_R = true;
    private a_3913_L R_4764_Y;
    private final FormattedCharSequence[] G_564_y = new FormattedCharSequence[4];
    private e_933_M P_1922_E = e_933_M.M_182_A;

    public A_4313_D() {
        super(BlockEntityType.w_1484_f);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        for (int i = 0; i < 4; ++i) {
            String s = x_282_a.n_1700_B.n_1700_B(this.n_1700_B[i]);
            compound.n_1700_B("Text" + (i + 1), s);
        }
        compound.n_1700_B("Color", this.P_1922_E.R_4764_Y());
        return compound;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        this.J_1907_R = false;
        super.n_1700_B(state, nbt);
        this.P_1922_E = e_933_M.n_1700_B(nbt.M_588_G("Color"), e_933_M.M_182_A);
        for (int i = 0; i < 4; ++i) {
            String s = nbt.M_588_G("Text" + (i + 1));
            MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(s.isEmpty() ? "\"\"" : s);
            if (this.u_2550_I instanceof e_3591_l) {
                try {
                    this.n_1700_B[i] = ComponentUtils.n_1700_B(this.n_1700_B((B_4088_l)null), itextcomponent, (N_4263_v)null, 0);
                }
                catch (CommandSyntaxException commandsyntaxexception) {
                    this.n_1700_B[i] = itextcomponent;
                }
            } else {
                this.n_1700_B[i] = itextcomponent;
            }
            this.G_564_y[i] = null;
        }
    }

    public x_282_a n_1700_B(int line) {
        return this.n_1700_B[line];
    }

    public void n_1700_B(int line, x_282_a signText) {
        this.n_1700_B[line] = signText;
        this.G_564_y[line] = null;
    }

    @Nullable
    public FormattedCharSequence n_1700_B(int p_242686_1_, Function<x_282_a, FormattedCharSequence> p_242686_2_) {
        if (this.G_564_y[p_242686_1_] == null && this.n_1700_B[p_242686_1_] != null) {
            this.G_564_y[p_242686_1_] = p_242686_2_.apply(this.n_1700_B[p_242686_1_]);
        }
        return this.G_564_y[p_242686_1_];
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 9, this.H_());
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    @Override
    public boolean K_() {
        return true;
    }

    public boolean P_1922_E() {
        return this.J_1907_R;
    }

    public void n_1700_B(boolean isEditableIn) {
        this.J_1907_R = isEditableIn;
        if (!isEditableIn) {
            this.R_4764_Y = null;
        }
    }

    public void n_1700_B(a_3913_L playerIn) {
        this.R_4764_Y = playerIn;
    }

    public a_3913_L v_4262_N() {
        return this.R_4764_Y;
    }

    public boolean J_1907_R(a_3913_L playerIn) {
        for (x_282_a itextcomponent : this.n_1700_B) {
            i_2909_p clickevent;
            Z_1567_W style;
            Z_1567_W z_1567_W = style = itextcomponent == null ? null : itextcomponent.n_1700_B();
            if (style == null || style.w_1484_f() == null || (clickevent = style.w_1484_f()).n_1700_B() != i_2909_p.n_1700_B.R_4764_Y) continue;
            playerIn.f_1574_f().H_1083_k().n_1700_B(this.n_1700_B((B_4088_l)playerIn), clickevent.J_1907_R());
        }
        return true;
    }

    public y_2498_m n_1700_B(@Nullable B_4088_l playerIn) {
        String s = playerIn == null ? "Sign" : playerIn.O_1309_Q().getString();
        x_282_a itextcomponent = playerIn == null ? new U_2871_b("Sign") : playerIn.c_();
        return new y_2498_m(CommandSource.T_3594_S, e_2866_D.n_1700_B(this.M_588_G), P_3504_Q.n_1700_B, (e_3591_l)this.u_2550_I, 2, s, itextcomponent, this.u_2550_I.T_2506_i(), playerIn);
    }

    public e_933_M w_1484_f() {
        return this.P_1922_E;
    }

    public boolean n_1700_B(e_933_M newColor) {
        if (newColor != this.w_1484_f()) {
            this.P_1922_E = newColor;
            this.J_1907_R();
            this.u_2550_I.n_1700_B(this.x_607_J(), this.e_4240_b(), this.e_4240_b(), 3);
            return true;
        }
        return false;
    }
}


