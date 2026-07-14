/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.c_973_a;
import lightning.product.i_4895_l;
import lightning.product.o_3050_h;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class PlayerTeam
extends o_3050_h {
    private final i_4895_l n_1700_B;
    private final String J_1907_R;
    private final Set<String> R_4764_Y = Sets.newHashSet();
    private x_282_a G_564_y;
    private x_282_a P_1922_E = U_2871_b.R_4764_Y;
    private x_282_a u_1723_Y = U_2871_b.R_4764_Y;
    private boolean v_4262_N = true;
    private boolean w_1484_f = true;
    private o_3050_h.J_1907_R t_148_a = o_3050_h.J_1907_R.n_1700_B;
    private o_3050_h.J_1907_R s_956_w = o_3050_h.J_1907_R.n_1700_B;
    private D_4024_W u_2550_I = D_4024_W.Q_2552_b;
    private o_3050_h.n_1700_B M_588_G = o_3050_h.n_1700_B.n_1700_B;
    private final Z_1567_W P_4830_p;

    public PlayerTeam(i_4895_l scoreboardIn, String name) {
        this.n_1700_B = scoreboardIn;
        this.J_1907_R = name;
        this.G_564_y = new U_2871_b(name);
        this.P_4830_p = Z_1567_W.n_1700_B.n_1700_B(name).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b(name)));
    }

    @Override
    public String n_1700_B() {
        return this.J_1907_R;
    }

    public x_282_a J_1907_R() {
        return this.G_564_y;
    }

    public MutableComponent R_4764_Y() {
        MutableComponent iformattabletextcomponent = ComponentUtils.n_1700_B(this.G_564_y.P_1922_E().J_1907_R(this.P_4830_p));
        D_4024_W textformatting = this.P_4830_p();
        if (textformatting != D_4024_W.Q_2552_b) {
            iformattabletextcomponent.n_1700_B(textformatting);
        }
        return iformattabletextcomponent;
    }

    public void n_1700_B(x_282_a name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.G_564_y = name;
        this.n_1700_B.R_4764_Y(this);
    }

    public void J_1907_R(@Nullable x_282_a p_207408_1_) {
        this.P_1922_E = p_207408_1_ == null ? U_2871_b.R_4764_Y : p_207408_1_;
        this.n_1700_B.R_4764_Y(this);
    }

    public x_282_a G_564_y() {
        return this.P_1922_E;
    }

    public void R_4764_Y(@Nullable x_282_a p_207409_1_) {
        this.u_1723_Y = p_207409_1_ == null ? U_2871_b.R_4764_Y : p_207409_1_;
        this.n_1700_B.R_4764_Y(this);
    }

    public x_282_a P_1922_E() {
        return this.u_1723_Y;
    }

    @Override
    public Collection<String> u_1723_Y() {
        return this.R_4764_Y;
    }

    @Override
    public MutableComponent G_564_y(x_282_a p_230427_1_) {
        MutableComponent iformattabletextcomponent = new U_2871_b("").n_1700_B(this.P_1922_E).n_1700_B(p_230427_1_).n_1700_B(this.u_1723_Y);
        D_4024_W textformatting = this.P_4830_p();
        if (textformatting != D_4024_W.Q_2552_b) {
            iformattabletextcomponent.n_1700_B(textformatting);
        }
        return iformattabletextcomponent;
    }

    public static MutableComponent n_1700_B(@Nullable o_3050_h p_237500_0_, x_282_a p_237500_1_) {
        return p_237500_0_ == null ? p_237500_1_.P_1922_E() : p_237500_0_.G_564_y(p_237500_1_);
    }

    @Override
    public boolean v_4262_N() {
        return this.v_4262_N;
    }

    public void n_1700_B(boolean friendlyFire) {
        this.v_4262_N = friendlyFire;
        this.n_1700_B.R_4764_Y(this);
    }

    @Override
    public boolean w_1484_f() {
        return this.w_1484_f;
    }

    public void J_1907_R(boolean friendlyInvisibles) {
        this.w_1484_f = friendlyInvisibles;
        this.n_1700_B.R_4764_Y(this);
    }

    @Override
    public o_3050_h.J_1907_R t_148_a() {
        return this.t_148_a;
    }

    @Override
    public o_3050_h.J_1907_R s_956_w() {
        return this.s_956_w;
    }

    public void n_1700_B(o_3050_h.J_1907_R visibility) {
        this.t_148_a = visibility;
        this.n_1700_B.R_4764_Y(this);
    }

    public void J_1907_R(o_3050_h.J_1907_R visibility) {
        this.s_956_w = visibility;
        this.n_1700_B.R_4764_Y(this);
    }

    @Override
    public o_3050_h.n_1700_B u_2550_I() {
        return this.M_588_G;
    }

    public void n_1700_B(o_3050_h.n_1700_B rule) {
        this.M_588_G = rule;
        this.n_1700_B.R_4764_Y(this);
    }

    public int M_588_G() {
        int i = 0;
        if (this.v_4262_N()) {
            i |= 1;
        }
        if (this.w_1484_f()) {
            i |= 2;
        }
        return i;
    }

    public void n_1700_B(int flags) {
        this.n_1700_B((flags & 1) > 0);
        this.J_1907_R((flags & 2) > 0);
    }

    public void n_1700_B(D_4024_W color) {
        this.u_2550_I = color;
        this.n_1700_B.R_4764_Y(this);
    }

    @Override
    public D_4024_W P_4830_p() {
        return this.u_2550_I;
    }
}


