/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 */
package lightning.product;

import lightning.product.I_685_r;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;

public class NoPush
extends Module {
    public MultiBooleanSetting tipOptions = new MultiBooleanSetting("\u0422\u0438\u043f", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u0438", true), new BooleanSetting("\u0411\u043b\u043e\u043a\u0438", true), new BooleanSetting("\u0412\u043e\u0434\u0430", true), new BooleanSetting("\u0423\u0434\u043e\u0447\u043a\u0438", true), new BooleanSetting("\u041b\u043e\u0434\u043a\u0438", false), new BooleanSetting("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0438", false), new BooleanSetting("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432\u044b\u0435 \u043a\u043e\u043b\u043e\u043d\u043d\u044b", false));

    public NoPush() {
        super("NoPush", ModuleCategory.J_1907_R);
        this.addSettings(this.tipOptions);
    }

    @Y_1740_V
    public void n_1700_B(I_685_r e) {
        boolean cancel;
        switch (e.n_1700_B) {
            default: {
                throw new MatchException(null, null);
            }
            case n_1700_B: {
                boolean bl = this.tipOptions.isOptionEnabled("\u0411\u043b\u043e\u043a\u0438");
                break;
            }
            case J_1907_R: {
                boolean bl = this.tipOptions.isOptionEnabled("\u0412\u043e\u0434\u0430");
                break;
            }
            case R_4764_Y: {
                boolean bl = this.tipOptions.isOptionEnabled("\u0418\u0433\u0440\u043e\u043a\u0438");
                break;
            }
            case G_564_y: {
                boolean bl = this.tipOptions.isOptionEnabled("\u0423\u0434\u043e\u0447\u043a\u0438");
                break;
            }
            case P_1922_E: {
                boolean bl = this.tipOptions.isOptionEnabled("\u041b\u043e\u0434\u043a\u0438");
                break;
            }
            case u_1723_Y: {
                boolean bl = this.tipOptions.isOptionEnabled("\u0412\u0430\u0433\u043e\u043d\u0435\u0442\u043a\u0438");
                break;
            }
            case v_4262_N: {
                boolean bl = cancel = this.tipOptions.isOptionEnabled("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432\u044b\u0435 \u043a\u043e\u043b\u043e\u043d\u043d\u044b").booleanValue();
            }
        }
        if (cancel) {
            e.n_1700_B(true);
        }
    }
}


