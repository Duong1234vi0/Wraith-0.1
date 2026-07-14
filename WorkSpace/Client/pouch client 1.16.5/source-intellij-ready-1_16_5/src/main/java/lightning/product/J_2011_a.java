/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import java.io.File;
import javax.annotation.Nullable;
import lightning.product.B_4315_y;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.H_1033_y;
import lightning.product.H_1468_N;
import lightning.product.MutableComponent;
import lightning.product.I_14_v;
import lightning.product.SharedConstants;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;

public class J_2011_a
implements Comparable<J_2011_a> {
    private final B_4315_y n_1700_B;
    private final H_1033_y J_1907_R;
    private final String R_4764_Y;
    private final boolean G_564_y;
    private final boolean P_1922_E;
    private final File u_1723_Y;
    @Nullable
    private x_282_a v_4262_N;

    public J_2011_a(B_4315_y settings, H_1033_y versionData, String directoryName, boolean requiresConversion, boolean locked, File iconFile) {
        this.n_1700_B = settings;
        this.J_1907_R = versionData;
        this.R_4764_Y = directoryName;
        this.P_1922_E = locked;
        this.u_1723_Y = iconFile;
        this.G_564_y = requiresConversion;
    }

    public String n_1700_B() {
        return this.R_4764_Y;
    }

    public String J_1907_R() {
        return StringUtils.isEmpty((CharSequence)this.n_1700_B.n_1700_B()) ? this.R_4764_Y : this.n_1700_B.n_1700_B();
    }

    public File R_4764_Y() {
        return this.u_1723_Y;
    }

    public boolean G_564_y() {
        return this.G_564_y;
    }

    public long P_1922_E() {
        return this.J_1907_R.J_1907_R();
    }

    public int n_1700_B(J_2011_a p_compareTo_1_) {
        if (this.J_1907_R.J_1907_R() < p_compareTo_1_.J_1907_R.J_1907_R()) {
            return 1;
        }
        return this.J_1907_R.J_1907_R() > p_compareTo_1_.J_1907_R.J_1907_R() ? -1 : this.R_4764_Y.compareTo(p_compareTo_1_.R_4764_Y);
    }

    public I_14_v u_1723_Y() {
        return this.n_1700_B.J_1907_R();
    }

    public boolean v_4262_N() {
        return this.n_1700_B.R_4764_Y();
    }

    public boolean w_1484_f() {
        return this.n_1700_B.P_1922_E();
    }

    public MutableComponent t_148_a() {
        return H_1468_N.J_1907_R(this.J_1907_R.R_4764_Y()) ? new F_2904_S("selectWorld.versionUnknown") : new U_2871_b(this.J_1907_R.R_4764_Y());
    }

    public H_1033_y s_956_w() {
        return this.J_1907_R;
    }

    public boolean u_2550_I() {
        return this.M_588_G() || !SharedConstants.n_1700_B().isStable() && !this.J_1907_R.P_1922_E() || this.P_4830_p();
    }

    public boolean M_588_G() {
        return this.J_1907_R.G_564_y() > SharedConstants.n_1700_B().getWorldVersion();
    }

    public boolean P_4830_p() {
        return this.J_1907_R.G_564_y() < SharedConstants.n_1700_B().getWorldVersion();
    }

    public boolean h_1847_R() {
        return this.P_1922_E;
    }

    public x_282_a Q_4569_t() {
        if (this.v_4262_N == null) {
            this.v_4262_N = this.M_182_A();
        }
        return this.v_4262_N;
    }

    private x_282_a M_182_A() {
        F_2904_S iformattabletextcomponent;
        if (this.h_1847_R()) {
            return new F_2904_S("selectWorld.locked").n_1700_B(D_4024_W.P_4830_p);
        }
        if (this.G_564_y()) {
            return new F_2904_S("selectWorld.conversion");
        }
        MutableComponent h_2671_n = iformattabletextcomponent = this.v_4262_N() ? new U_2871_b("").n_1700_B(new F_2904_S("gameMode.hardcore").n_1700_B(D_4024_W.P_1922_E)) : new F_2904_S("gameMode." + this.u_1723_Y().J_1907_R());
        if (this.w_1484_f()) {
            iformattabletextcomponent.n_1700_B(", ").n_1700_B(new F_2904_S("selectWorld.cheats"));
        }
        MutableComponent iformattabletextcomponent1 = this.t_148_a();
        MutableComponent iformattabletextcomponent2 = new U_2871_b(", ").n_1700_B(new F_2904_S("selectWorld.version")).n_1700_B(" ");
        if (this.u_2550_I()) {
            iformattabletextcomponent2.n_1700_B(iformattabletextcomponent1.n_1700_B(this.M_588_G() ? D_4024_W.P_4830_p : D_4024_W.Y_259_p));
        } else {
            iformattabletextcomponent2.n_1700_B(iformattabletextcomponent1);
        }
        iformattabletextcomponent.n_1700_B(iformattabletextcomponent2);
        return iformattabletextcomponent;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((J_2011_a)object);
    }
}


