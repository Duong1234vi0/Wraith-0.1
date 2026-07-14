/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ComparisonChain
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ComparisonChain;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.g_422_i;
import lightning.product.r_4811_B;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_2610_C
implements Comparable<k_2610_C> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final g_422_i J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private boolean P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private boolean t_148_a;
    @Nullable
    private k_2610_C s_956_w;

    public k_2610_C(g_422_i potionIn) {
        this(potionIn, 0, 0);
    }

    public k_2610_C(g_422_i potionIn, int durationIn) {
        this(potionIn, durationIn, 0);
    }

    public k_2610_C(g_422_i potionIn, int durationIn, int amplifierIn) {
        this(potionIn, durationIn, amplifierIn, false, true);
    }

    public k_2610_C(g_422_i potionIn, int durationIn, int amplifierIn, boolean ambientIn, boolean showParticlesIn) {
        this(potionIn, durationIn, amplifierIn, ambientIn, showParticlesIn, showParticlesIn);
    }

    public k_2610_C(g_422_i potionIn, int durationIn, int amplifierIn, boolean ambientIn, boolean p_i48980_5_, boolean p_i48980_6_) {
        this(potionIn, durationIn, amplifierIn, ambientIn, p_i48980_5_, p_i48980_6_, null);
    }

    public k_2610_C(g_422_i p_i230050_1_, int p_i230050_2_, int p_i230050_3_, boolean p_i230050_4_, boolean p_i230050_5_, boolean p_i230050_6_, @Nullable k_2610_C p_i230050_7_) {
        this.J_1907_R = p_i230050_1_;
        this.R_4764_Y = p_i230050_2_;
        this.G_564_y = p_i230050_3_;
        this.u_1723_Y = p_i230050_4_;
        this.w_1484_f = p_i230050_5_;
        this.t_148_a = p_i230050_6_;
        this.s_956_w = p_i230050_7_;
    }

    public k_2610_C(k_2610_C other) {
        this.J_1907_R = other.J_1907_R;
        this.n_1700_B(other);
    }

    void n_1700_B(k_2610_C p_230117_1_) {
        this.R_4764_Y = p_230117_1_.R_4764_Y;
        this.G_564_y = p_230117_1_.G_564_y;
        this.u_1723_Y = p_230117_1_.u_1723_Y;
        this.w_1484_f = p_230117_1_.w_1484_f;
        this.t_148_a = p_230117_1_.t_148_a;
    }

    public boolean J_1907_R(k_2610_C other) {
        if (this.J_1907_R != other.J_1907_R) {
            n_1700_B.warn("This method should only be called for matching effects!");
        }
        boolean flag = false;
        if (other.G_564_y > this.G_564_y) {
            if (other.R_4764_Y < this.R_4764_Y) {
                k_2610_C effectinstance = this.s_956_w;
                this.s_956_w = new k_2610_C(this);
                this.s_956_w.s_956_w = effectinstance;
            }
            this.G_564_y = other.G_564_y;
            this.R_4764_Y = other.R_4764_Y;
            flag = true;
        } else if (other.R_4764_Y > this.R_4764_Y) {
            if (other.G_564_y == this.G_564_y) {
                this.R_4764_Y = other.R_4764_Y;
                flag = true;
            } else if (this.s_956_w == null) {
                this.s_956_w = new k_2610_C(other);
            } else {
                this.s_956_w.J_1907_R(other);
            }
        }
        if (!other.u_1723_Y && this.u_1723_Y || flag) {
            this.u_1723_Y = other.u_1723_Y;
            flag = true;
        }
        if (other.w_1484_f != this.w_1484_f) {
            this.w_1484_f = other.w_1484_f;
            flag = true;
        }
        if (other.t_148_a != this.t_148_a) {
            this.t_148_a = other.t_148_a;
            flag = true;
        }
        return flag;
    }

    public g_422_i n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public boolean G_564_y() {
        return this.u_1723_Y;
    }

    public boolean P_1922_E() {
        return this.w_1484_f;
    }

    public boolean u_1723_Y() {
        return this.t_148_a;
    }

    public boolean n_1700_B(r_4811_B entityIn, Runnable p_76455_2_) {
        if (this.R_4764_Y > 0) {
            if (this.J_1907_R.n_1700_B(this.R_4764_Y, this.G_564_y)) {
                this.n_1700_B(entityIn);
            }
            this.t_148_a();
            if (this.R_4764_Y == 0 && this.s_956_w != null) {
                this.n_1700_B(this.s_956_w);
                this.s_956_w = this.s_956_w.s_956_w;
                p_76455_2_.run();
            }
        }
        return this.R_4764_Y > 0;
    }

    private int t_148_a() {
        if (this.s_956_w != null) {
            this.s_956_w.t_148_a();
        }
        return --this.R_4764_Y;
    }

    public void n_1700_B(r_4811_B entityIn) {
        if (this.R_4764_Y > 0) {
            this.J_1907_R.n_1700_B(entityIn, this.G_564_y);
        }
    }

    public String v_4262_N() {
        return this.J_1907_R.R_4764_Y();
    }

    public String toString() {
        String s = this.G_564_y > 0 ? this.v_4262_N() + " x " + (this.G_564_y + 1) + ", Duration: " + this.R_4764_Y : this.v_4262_N() + ", Duration: " + this.R_4764_Y;
        if (this.P_1922_E) {
            s = s + ", Splash: true";
        }
        if (!this.w_1484_f) {
            s = s + ", Particles: false";
        }
        if (!this.t_148_a) {
            s = s + ", Show Icon: false";
        }
        return s;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof k_2610_C)) {
            return false;
        }
        k_2610_C effectinstance = (k_2610_C)p_equals_1_;
        return this.R_4764_Y == effectinstance.R_4764_Y && this.G_564_y == effectinstance.G_564_y && this.P_1922_E == effectinstance.P_1922_E && this.u_1723_Y == effectinstance.u_1723_Y && this.J_1907_R.equals(effectinstance.J_1907_R);
    }

    public int hashCode() {
        int i = this.J_1907_R.hashCode();
        i = 31 * i + this.R_4764_Y;
        i = 31 * i + this.G_564_y;
        i = 31 * i + (this.P_1922_E ? 1 : 0);
        return 31 * i + (this.u_1723_Y ? 1 : 0);
    }

    public U_2912_j n_1700_B(U_2912_j nbt) {
        nbt.n_1700_B("Id", (byte)g_422_i.n_1700_B(this.n_1700_B()));
        this.R_4764_Y(nbt);
        return nbt;
    }

    private void R_4764_Y(U_2912_j nbt) {
        nbt.n_1700_B("Amplifier", (byte)this.R_4764_Y());
        nbt.J_1907_R("Duration", this.J_1907_R());
        nbt.n_1700_B("Ambient", this.G_564_y());
        nbt.n_1700_B("ShowParticles", this.P_1922_E());
        nbt.n_1700_B("ShowIcon", this.u_1723_Y());
        if (this.s_956_w != null) {
            U_2912_j compoundnbt = new U_2912_j();
            this.s_956_w.n_1700_B(compoundnbt);
            nbt.n_1700_B("HiddenEffect", compoundnbt);
        }
    }

    public static k_2610_C J_1907_R(U_2912_j nbt) {
        byte i = nbt.u_1723_Y("Id");
        g_422_i effect = g_422_i.n_1700_B(i);
        return effect == null ? null : k_2610_C.n_1700_B(effect, nbt);
    }

    private static k_2610_C n_1700_B(g_422_i effect, U_2912_j nbt) {
        byte i = nbt.u_1723_Y("Amplifier");
        int j = nbt.w_1484_f("Duration");
        boolean flag = nbt.t_1786_h("Ambient");
        boolean flag1 = true;
        if (nbt.R_4764_Y("ShowParticles", 1)) {
            flag1 = nbt.t_1786_h("ShowParticles");
        }
        boolean flag2 = flag1;
        if (nbt.R_4764_Y("ShowIcon", 1)) {
            flag2 = nbt.t_1786_h("ShowIcon");
        }
        k_2610_C effectinstance = null;
        if (nbt.R_4764_Y("HiddenEffect", 10)) {
            effectinstance = k_2610_C.n_1700_B(effect, nbt.M_182_A("HiddenEffect"));
        }
        return new k_2610_C(effect, j, i < 0 ? (byte)0 : i, flag, flag1, flag2, effectinstance);
    }

    public void n_1700_B(boolean maxDuration) {
        this.v_4262_N = maxDuration;
    }

    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    public int R_4764_Y(k_2610_C p_compareTo_1_) {
        int i = 32147;
        return !(this.J_1907_R() > 32147 && p_compareTo_1_.J_1907_R() > 32147 || this.G_564_y() && p_compareTo_1_.G_564_y()) ? ComparisonChain.start().compare(Boolean.valueOf(this.G_564_y()), Boolean.valueOf(p_compareTo_1_.G_564_y())).compare(this.J_1907_R(), p_compareTo_1_.J_1907_R()).compare(this.n_1700_B().u_1723_Y(), p_compareTo_1_.n_1700_B().u_1723_Y()).result() : ComparisonChain.start().compare(Boolean.valueOf(this.G_564_y()), Boolean.valueOf(p_compareTo_1_.G_564_y())).compare(this.n_1700_B().u_1723_Y(), p_compareTo_1_.n_1700_B().u_1723_Y()).result();
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.R_4764_Y((k_2610_C)object);
    }
}

