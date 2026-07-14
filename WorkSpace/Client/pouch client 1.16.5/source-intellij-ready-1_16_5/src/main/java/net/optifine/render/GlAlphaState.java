/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.X_933_l;

public class GlAlphaState {
    private boolean enabled;
    private int func;
    private float ref;

    public GlAlphaState() {
        this(false, 519, 0.0f);
    }

    public GlAlphaState(boolean enabled) {
        this(enabled, 519, 0.0f);
    }

    public GlAlphaState(boolean enabled, int func, float ref) {
        this.enabled = enabled;
        this.func = func;
        this.ref = ref;
    }

    public void setState(boolean enabled, int func, float ref) {
        this.enabled = enabled;
        this.func = func;
        this.ref = ref;
    }

    public void setState(GlAlphaState state) {
        this.enabled = state.enabled;
        this.func = state.func;
        this.ref = state.ref;
    }

    public void setFuncRef(int func, float ref) {
        this.func = func;
        this.ref = ref;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEnabled() {
        this.enabled = true;
    }

    public void setDisabled() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getFunc() {
        return this.func;
    }

    public float getRef() {
        return this.ref;
    }

    public void apply() {
        if (!this.enabled) {
            X_933_l.G_564_y();
        } else {
            X_933_l.P_1922_E();
            X_933_l.n_1700_B(this.func, this.ref);
        }
    }

    public String toString() {
        return "enabled: " + this.enabled + ", func: " + this.func + ", ref: " + this.ref;
    }
}

