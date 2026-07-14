/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.E_3343_g;
import lightning.product.NumberSetting;
import lightning.product.N_3268_u;
import lightning.product.Q_2753_H;
import lightning.product.S_4035_N;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class AirStuck
extends Module {
    private final BooleanSetting izmenyatDistanciyuKillauryEnabled = new BooleanSetting("\u0418\u0437\u043c\u0435\u043d\u044f\u0442\u044c \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u044b", false);
    private final NumberSetting distanciyaKillaurySetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u044b", 3.5f, 2.0f, 6.0f, 0.1f, this.izmenyatDistanciyuKillauryEnabled::isEnabled);
    private final BooleanSetting lovitMomentEnabled = new BooleanSetting("\u041b\u043e\u0432\u0438\u0442\u044c \u043c\u043e\u043c\u0435\u043d\u0442", true);
    private double s_956_w = Double.NaN;
    private boolean u_2550_I = false;

    public AirStuck() {
        super("AirStuck", ModuleCategory.J_1907_R);
        this.addSettings(this.izmenyatDistanciyuKillauryEnabled, this.distanciyaKillaurySetting, this.lovitMomentEnabled);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (this.lovitMomentEnabled.isEnabled().booleanValue()) {
            this.s_956_w = AirStuck.c_3005_b.Y_259_p != null && !AirStuck.c_3005_b.Y_259_p.M_1641_O() ? AirStuck.c_3005_b.Y_259_p.X_2960_b() : Double.NaN;
            this.u_2550_I = false;
        } else {
            this.u_2550_I = true;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.u_2550_I = false;
    }

    public float h_1847_R() {
        if (this.w_1484_f() && this.izmenyatDistanciyuKillauryEnabled.isEnabled().booleanValue()) {
            return ((Float)this.distanciyaKillaurySetting.getValue()).floatValue();
        }
        return -1.0f;
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (this.u_2550_I && e.G_564_y() instanceof N_3268_u) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.R_4764_Y();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (AirStuck.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.lovitMomentEnabled.isEnabled().booleanValue()) {
            double currentY = AirStuck.c_3005_b.Y_259_p.X_2960_b();
            if (AirStuck.c_3005_b.Y_259_p.M_1641_O()) {
                this.s_956_w = Double.NaN;
                this.u_2550_I = false;
            } else if (!this.u_2550_I) {
                if (Double.isNaN(this.s_956_w)) {
                    this.s_956_w = currentY;
                } else if (currentY > this.s_956_w) {
                    this.s_956_w = currentY;
                } else if (currentY < this.s_956_w) {
                    this.u_2550_I = true;
                }
            }
        }
        if (this.u_2550_I) {
            e.J_1907_R(0.0);
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(S_4035_N e) {
        if (this.u_2550_I && e.J_1907_R() == AirStuck.c_3005_b.Y_259_p) {
            e.n_1700_B(true);
        }
    }

    @Generated
    public BooleanSetting Q_4569_t() {
        return this.izmenyatDistanciyuKillauryEnabled;
    }

    @Generated
    public NumberSetting M_182_A() {
        return this.distanciyaKillaurySetting;
    }

    @Generated
    public BooleanSetting t_1786_h() {
        return this.lovitMomentEnabled;
    }

    @Generated
    public double multiplayerClientSuggestionProvider() {
        return this.s_956_w;
    }

    @Generated
    public boolean w_1457_N() {
        return this.u_2550_I;
    }
}



