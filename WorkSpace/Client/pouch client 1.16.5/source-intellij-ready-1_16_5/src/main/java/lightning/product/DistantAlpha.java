/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class DistantAlpha
extends Module {
    private final NumberSetting minalphaSetting = new NumberSetting("MinAlpha", 0.5f, 0.0f, 1.0f, 0.1f);
    private final NumberSetting startdistanceSetting = new NumberSetting("StartDistance", 1.5f, 1.0f, 2.0f, 0.1f);
    private final NumberSetting killdistanceSetting = new NumberSetting("KillDistance", 0.5f, 0.0f, 1.0f, 0.01f);

    public DistantAlpha() {
        super("DistantAlpha", ModuleCategory.R_4764_Y);
        this.addSettings(this.minalphaSetting, this.startdistanceSetting, this.killdistanceSetting);
    }

    public float n_1700_B(r_4811_B entity) {
        if (!this.w_1484_f() || entity == null || DistantAlpha.c_3005_b.Y_259_p == null) {
            return 1.0f;
        }
        if (entity == DistantAlpha.c_3005_b.Y_259_p) {
            return 1.0f;
        }
        double distance = DistantAlpha.c_3005_b.Y_259_p.R_4764_Y(entity);
        if (distance <= (double)((Float)this.killdistanceSetting.getValue()).floatValue()) {
            return ((Float)this.minalphaSetting.getValue()).floatValue();
        }
        if (distance <= (double)((Float)this.startdistanceSetting.getValue()).floatValue()) {
            float progress = (float)((distance - (double)((Float)this.killdistanceSetting.getValue()).floatValue()) / (double)(((Float)this.startdistanceSetting.getValue()).floatValue() - ((Float)this.killdistanceSetting.getValue()).floatValue()));
            return u_530_F.v_4262_N(progress, ((Float)this.minalphaSetting.getValue()).floatValue(), 1.0f);
        }
        return 1.0f;
    }
}


