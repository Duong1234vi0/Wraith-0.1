/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.Setting;
import lombok.Generated;

public class SoundEventRegistration
extends Setting<Boolean> {
    public boolean G_564_y;
    public final String P_1922_E;
    public final String u_1723_Y;

    public SoundEventRegistration(String name, Boolean defaultVal, String textOn, String textOff) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
        this.u_1723_Y = textOff;
        this.P_1922_E = textOn;
    }

    public SoundEventRegistration(String name, Boolean defaultVal, String textOn, String textOff, Supplier<Boolean> visible) {
        super(name, defaultVal);
        this.G_564_y = defaultVal;
        this.u_1723_Y = textOff;
        this.P_1922_E = textOn;
        this.n_1700_B(visible);
    }

    @Generated
    public boolean w_1484_f() {
        return this.G_564_y;
    }

    @Generated
    public String t_148_a() {
        return this.P_1922_E;
    }

    @Generated
    public String s_956_w() {
        return this.u_1723_Y;
    }
}


