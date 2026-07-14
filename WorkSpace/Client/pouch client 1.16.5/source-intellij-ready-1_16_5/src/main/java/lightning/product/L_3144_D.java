/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.MutableComponent;
import lightning.product.Z_1567_W;
import lightning.product.FormattedCharSequence;
import lightning.product.l_4033_W;
import lightning.product.x_282_a;

public abstract class L_3144_D
implements MutableComponent {
    protected final List<x_282_a> u_1723_Y = Lists.newArrayList();
    private FormattedCharSequence R_4764_Y = FormattedCharSequence.n_1700_B;
    @Nullable
    private l_4033_W G_564_y;
    private Z_1567_W P_1922_E = Z_1567_W.n_1700_B;

    @Override
    public MutableComponent n_1700_B(x_282_a sibling) {
        this.u_1723_Y.add(sibling);
        return this;
    }

    @Override
    public String J_1907_R() {
        return "";
    }

    @Override
    public List<x_282_a> R_4764_Y() {
        return this.u_1723_Y;
    }

    @Override
    public MutableComponent n_1700_B(Z_1567_W style) {
        this.P_1922_E = style;
        return this;
    }

    @Override
    public Z_1567_W n_1700_B() {
        return this.P_1922_E;
    }

    public abstract L_3144_D t_148_a();

    @Override
    public final MutableComponent P_1922_E() {
        L_3144_D textcomponent = this.t_148_a();
        textcomponent.u_1723_Y.addAll(this.u_1723_Y);
        textcomponent.n_1700_B(this.P_1922_E);
        return textcomponent;
    }

    @Override
    public FormattedCharSequence u_1723_Y() {
        l_4033_W languagemap = l_4033_W.R_4764_Y();
        if (this.G_564_y != languagemap) {
            this.R_4764_Y = languagemap.n_1700_B(this);
            this.G_564_y = languagemap;
        }
        return this.R_4764_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof L_3144_D)) {
            return false;
        }
        L_3144_D textcomponent = (L_3144_D)p_equals_1_;
        return this.u_1723_Y.equals(textcomponent.u_1723_Y) && Objects.equals(this.n_1700_B(), textcomponent.n_1700_B());
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B(), this.u_1723_Y);
    }

    public String toString() {
        return "BaseComponent{style=" + String.valueOf(this.P_1922_E) + ", siblings=" + String.valueOf(this.u_1723_Y) + "}";
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.t_148_a();
    }
}


