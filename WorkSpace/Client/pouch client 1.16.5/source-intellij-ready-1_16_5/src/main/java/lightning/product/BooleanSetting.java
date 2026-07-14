/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Module;
import lightning.product.Setting;
import lombok.Generated;

public class BooleanSetting
extends Setting<Boolean> {
    public boolean G_564_y;
    private int P_1922_E = -100;
    private Module.n_1700_B u_1723_Y = Module.n_1700_B.n_1700_B;
    private boolean v_4262_N = true;
    private Supplier<Boolean> w_1484_f = null;

    public BooleanSetting(String name, Boolean defaultVal) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
    }

    public BooleanSetting(String name, Boolean defaultVal, Supplier<Boolean> visible) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
        this.n_1700_B(visible);
    }

    public BooleanSetting forceEnabledWhen(Supplier<Boolean> supplier) {
        this.w_1484_f = supplier;
        return this;
    }

    public boolean isForcedEnabled() {
        return this.w_1484_f != null && this.w_1484_f.get() != false;
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
        this.G_564_y = value;
    }

    public Boolean isEnabled() {
        if (this.isForcedEnabled()) {
            return true;
        }
        return (Boolean)super.getValue();
    }

    @Override
    @Generated
    public void J_1907_R(boolean defaultVal) {
        this.G_564_y = defaultVal;
    }

    @Override
    @Generated
    public void n_1700_B(int bind) {
        this.P_1922_E = bind;
    }

    @Override
    @Generated
    public void n_1700_B(Module.n_1700_B toggleMode) {
        this.u_1723_Y = toggleMode;
    }

    @Generated
    public void R_4764_Y(boolean Keybindvisible) {
        this.v_4262_N = Keybindvisible;
    }

    @Generated
    public void G_564_y(Supplier<Boolean> lockedSupplier) {
        this.w_1484_f = lockedSupplier;
    }

    @Generated
    public boolean s_956_w() {
        return this.G_564_y;
    }

    @Generated
    public int u_2550_I() {
        return this.P_1922_E;
    }

    @Generated
    public Module.n_1700_B M_588_G() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean P_4830_p() {
        return this.v_4262_N;
    }

    @Generated
    public Supplier<Boolean> h_1847_R() {
        return this.w_1484_f;
    }

    @Override
    public /* synthetic */ Object J_1907_R() {
        return this.t_148_a();
    }
}


