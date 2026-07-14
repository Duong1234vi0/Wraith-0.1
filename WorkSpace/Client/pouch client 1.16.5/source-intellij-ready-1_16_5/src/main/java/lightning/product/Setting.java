/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import lombok.Generated;

public class Setting<Value> {
    Value n_1700_B;
    String J_1907_R;
    private Runnable G_564_y;
    public boolean R_4764_Y = true;
    private Supplier<Boolean> P_1922_E = null;

    public Setting(String name, Value defaultVal) {
        this.J_1907_R = name;
        this.n_1700_B = defaultVal;
    }

    public String getName() {
        return this.J_1907_R;
    }

    public void setValue(Value value) {
        this.n_1700_B = value;
        if (this.G_564_y != null) {
            this.G_564_y.run();
        }
    }

    public Value getValue() {
        return this.n_1700_B;
    }

    public Setting<?> visibleWhen(Supplier<Boolean> bool) {
        this.P_1922_E = bool;
        return this;
    }

    public Setting<?> visibleWhen(BooleanSupplier bool) {
        this.P_1922_E = bool::getAsBoolean;
        return this;
    }

    public boolean isVisible() {
        return this.P_1922_E != null ? Boolean.TRUE.equals(this.P_1922_E.get()) : this.R_4764_Y;
    }

    @Generated
    public void J_1907_R(Value defaultVal) {
        this.n_1700_B = defaultVal;
    }

    @Generated
    public void n_1700_B(String settingName) {
        this.J_1907_R = settingName;
    }

    @Generated
    public void n_1700_B(Runnable onChange) {
        this.G_564_y = onChange;
    }

    @Generated
    public void n_1700_B(boolean visible) {
        this.R_4764_Y = visible;
    }

    @Generated
    public void J_1907_R(Supplier<Boolean> visibleSupplier) {
        this.P_1922_E = visibleSupplier;
    }

    @Generated
    public Value G_564_y() {
        return this.n_1700_B;
    }

    @Generated
    public String P_1922_E() {
        return this.J_1907_R;
    }

    @Generated
    public Runnable u_1723_Y() {
        return this.G_564_y;
    }

    @Generated
    public Supplier<Boolean> v_4262_N() {
        return this.P_1922_E;
    }
}

