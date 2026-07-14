/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.L_3144_D;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.x_282_a;

public class s_4082_G
extends L_3144_D {
    private static Function<String, Supplier<x_282_a>> R_4764_Y = p_193635_0_ -> () -> new U_2871_b((String)p_193635_0_);
    private final String G_564_y;
    private Supplier<x_282_a> P_1922_E;

    public s_4082_G(String keybind) {
        this.G_564_y = keybind;
    }

    public static void n_1700_B(Function<String, Supplier<x_282_a>> p_240696_0_) {
        R_4764_Y = p_240696_0_;
    }

    private x_282_a s_956_w() {
        if (this.P_1922_E == null) {
            this.P_1922_E = R_4764_Y.apply(this.G_564_y);
        }
        return this.P_1922_E.get();
    }

    @Override
    public <T> Optional<T> J_1907_R(FormattedText.J_1907_R<T> acceptor) {
        return this.s_956_w().n_1700_B(acceptor);
    }

    @Override
    public <T> Optional<T> J_1907_R(FormattedText.n_1700_B<T> acceptor, Z_1567_W style) {
        return this.s_956_w().n_1700_B(acceptor, style);
    }

    public s_4082_G v_4262_N() {
        return new s_4082_G(this.G_564_y);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof s_4082_G)) {
            return false;
        }
        s_4082_G keybindtextcomponent = (s_4082_G)p_equals_1_;
        return this.G_564_y.equals(keybindtextcomponent.G_564_y) && super.equals(p_equals_1_);
    }

    @Override
    public String toString() {
        return "KeybindComponent{keybind='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
    }

    public String w_1484_f() {
        return this.G_564_y;
    }

    @Override
    public /* synthetic */ L_3144_D t_148_a() {
        return this.v_4262_N();
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.v_4262_N();
    }
}


