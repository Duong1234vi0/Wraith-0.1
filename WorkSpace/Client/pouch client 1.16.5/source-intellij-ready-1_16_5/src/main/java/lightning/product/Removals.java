/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_4720_K;
import lightning.product.h_1015_G;
import lightning.product.h_3270_j;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;

public class Removals
extends Module {
    public static MultiBooleanSetting primenyatNaOptions = new MultiBooleanSetting("\u041f\u0440\u0438\u043c\u0435\u043d\u044f\u0442\u044c \u043d\u0430", new BooleanSetting("\u0422\u0440\u044f\u0441\u043a\u0430 \u043a\u0430\u043c\u0435\u0440\u044b", true), new BooleanSetting("\u0421\u043a\u043e\u0440\u0431\u043e\u0440\u0434", true), new BooleanSetting("\u0423\u0434\u043e\u0447\u043a\u0430 \u043d\u0430 \u044d\u043a\u0440\u0430\u043d\u0435", true), new BooleanSetting("\u0411\u043e\u0441\u0441-\u0431\u0430\u0440", true), new BooleanSetting("\u0427\u0430\u0441\u0442\u0438\u0446\u044b \u0440\u0430\u0437\u0440\u0443\u0448\u0435\u043d\u0438\u044f", true), new BooleanSetting("\u0414\u043e\u0436\u0434\u044c", true), new BooleanSetting("\u041a\u0430\u043c\u0435\u0440\u0430 \u043a\u043b\u0438\u043f", true), new BooleanSetting("\u0422\u0435\u043d\u0438", true), new BooleanSetting("\u0414\u044b\u043c", true), new BooleanSetting("\u0421\u043d\u0435\u0441\u0435\u043d\u0438\u0435 \u0442\u043e\u0442\u0435\u043c\u0430", true), new BooleanSetting("\u0412\u0438\u043d\u044c\u0435\u0442\u043a\u0430", true), new BooleanSetting("\u0421\u0442\u0440\u0435\u043b\u044b \u0432 \u0438\u0433\u0440\u043e\u043a\u0435", true), new BooleanSetting("\u0413\u043e\u043b\u043e\u0433\u0440\u0430\u043c\u043c\u044b", true), new BooleanSetting("\u042d\u0444\u0444\u0435\u043a\u0442 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", true), new BooleanSetting("\u0422\u0440\u0430\u0432\u0430", true), new BooleanSetting("\u041f\u043b\u043e\u0445\u0438\u0435 \u044d\u0444\u0444\u0435\u043a\u0442\u044b", false), new BooleanSetting("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435 \u0438\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u0438", false), new BooleanSetting("\u0420\u0430\u0437\u043c\u044b\u0442\u0438\u0435 \u043f\u043e\u0434 \u0432\u043e\u0434\u043e\u0439", true), new BooleanSetting("\u041b\u0430\u0432\u0430", false), new BooleanSetting("\u041e\u0433\u043e\u043d\u044c", true), new BooleanSetting("\u0422\u0430\u0439\u0442\u043b\u044b", true), new BooleanSetting("\u0412\u0437\u0440\u044b\u0432 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", false), new BooleanSetting("\u041b\u043e\u0434\u043a\u0438", false));
    public static MultiBooleanSetting umenshitZvukOptions = new MultiBooleanSetting("\u0423\u043c\u0435\u043d\u044c\u0448\u0438\u0442\u044c \u0437\u0432\u0443\u043a", new BooleanSetting("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", false), new BooleanSetting("\u041f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u0435 \u0432\u0438\u0437\u0435\u0440\u0430", false), new BooleanSetting("\u041e\u0442\u043a\u0440\u044b\u0442\u0438\u0435 \u044d\u043d\u0434-\u043f\u043e\u0440\u0442\u0430\u043b\u0430", false), new BooleanSetting("\u041c\u0443\u0437\u044b\u043a\u0430\u043b\u044c\u043d\u044b\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438", false), new BooleanSetting("\u0411\u0438\u0442\u044c\u0435 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430", false));
    public static NumberSetting gromkostSetting = new NumberSetting("\u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c", 0.5f, 0.0f, 1.0f, 0.05f, () -> umenshitZvukOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446") != false || umenshitZvukOptions.isOptionEnabled("\u041f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u0435 \u0432\u0438\u0437\u0435\u0440\u0430") != false || umenshitZvukOptions.isOptionEnabled("\u041e\u0442\u043a\u0440\u044b\u0442\u0438\u0435 \u044d\u043d\u0434-\u043f\u043e\u0440\u0442\u0430\u043b\u0430") != false || umenshitZvukOptions.isOptionEnabled("\u041c\u0443\u0437\u044b\u043a\u0430\u043b\u044c\u043d\u044b\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438") != false || umenshitZvukOptions.isOptionEnabled("\u0411\u0438\u0442\u044c\u0435 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430") != false);
    private boolean s_956_w = false;
    private boolean u_2550_I = false;

    public Removals() {
        super("Removals", ModuleCategory.R_4764_Y);
        this.addSettings(primenyatNaOptions, umenshitZvukOptions, gromkostSetting);
    }

    @Y_1740_V
    private void n_1700_B(h_3270_j e) {
        boolean cancel;
        switch (e.n_1700_B) {
            default: {
                throw new MatchException(null, null);
            }
            case n_1700_B: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u041e\u0433\u043e\u043d\u044c");
                break;
            }
            case J_1907_R: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0411\u043e\u0441\u0441-\u0431\u0430\u0440");
                break;
            }
            case R_4764_Y: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0421\u043a\u043e\u0440\u0431\u043e\u0440\u0434");
                break;
            }
            case G_564_y: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0422\u0430\u0439\u0442\u043b\u044b");
                break;
            }
            case P_1922_E: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0421\u043d\u0435\u0441\u0435\u043d\u0438\u0435 \u0442\u043e\u0442\u0435\u043c\u0430");
                break;
            }
            case u_1723_Y: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0422\u0440\u044f\u0441\u043a\u0430 \u043a\u0430\u043c\u0435\u0440\u044b");
                break;
            }
            case C_2741_M: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u041a\u0430\u043c\u0435\u0440\u0430 \u043a\u043b\u0438\u043f");
                break;
            }
            case v_4262_N: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0423\u0434\u043e\u0447\u043a\u0430 \u043d\u0430 \u044d\u043a\u0440\u0430\u043d\u0435");
                break;
            }
            case w_1484_f: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0427\u0430\u0441\u0442\u0438\u0446\u044b \u0440\u0430\u0437\u0440\u0443\u0448\u0435\u043d\u0438\u044f");
                break;
            }
            case t_148_a: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0414\u043e\u0436\u0434\u044c");
                break;
            }
            case s_956_w: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0422\u0435\u043d\u0438");
                break;
            }
            case k_2293_S: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435 \u0438\u0433\u0440\u043e\u043a\u043e\u0432");
                break;
            }
            case u_2550_I: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0414\u044b\u043c");
                break;
            }
            case M_588_G: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0412\u0438\u043d\u044c\u0435\u0442\u043a\u0430");
                break;
            }
            case P_4830_p: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0421\u0442\u0440\u0435\u043b\u044b \u0432 \u0438\u0433\u0440\u043e\u043a\u0435");
                break;
            }
            case h_1847_R: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0413\u043e\u043b\u043e\u0433\u0440\u0430\u043c\u043c\u044b");
                break;
            }
            case Q_4569_t: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0422\u0440\u0430\u0432\u0430");
                break;
            }
            case M_182_A: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u042d\u0444\u0444\u0435\u043a\u0442 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f");
                break;
            }
            case t_1786_h: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0418\u0433\u0440\u043e\u043a\u0438");
                break;
            }
            case multiplayerClientSuggestionProvider: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u041f\u043b\u043e\u0445\u0438\u0435 \u044d\u0444\u0444\u0435\u043a\u0442\u044b");
                break;
            }
            case w_1457_N: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0420\u0430\u0437\u043c\u044b\u0442\u0438\u0435 \u043f\u043e\u0434 \u0432\u043e\u0434\u043e\u0439");
                break;
            }
            case Y_601_j: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0424\u043e\u043d \u043a\u043e\u043d\u0442\u0435\u0439\u043d\u0435\u0440\u0430");
                break;
            }
            case Q_2552_b: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u041d\u0430\u043d\u0435\u0441\u0435\u043d\u0438\u0435 \u0442\u0430\u0431\u0430");
                break;
            }
            case Y_259_p: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u041b\u0430\u0432\u0430");
                break;
            }
            case q_2307_F: {
                boolean bl = primenyatNaOptions.isOptionEnabled("\u0412\u0437\u0440\u044b\u0432 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430");
                break;
            }
            case Z_875_P: {
                boolean bl = cancel = primenyatNaOptions.isOptionEnabled("\u041b\u043e\u0434\u043a\u0438").booleanValue();
            }
        }
        if (cancel) {
            e.n_1700_B(true);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (Removals.c_3005_b.Y_601_j != null) {
            Removals.c_3005_b.u_1723_Y.P_1922_E();
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (Removals.c_3005_b.Y_601_j != null) {
            Removals.c_3005_b.u_1723_Y.P_1922_E();
        }
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        boolean grass = primenyatNaOptions.isOptionEnabled("\u0422\u0440\u0430\u0432\u0430");
        boolean lava = primenyatNaOptions.isOptionEnabled("\u041b\u0430\u0432\u0430");
        if (grass != this.s_956_w || lava != this.u_2550_I) {
            this.s_956_w = grass;
            this.u_2550_I = lava;
            if (Removals.c_3005_b.Y_601_j != null) {
                Removals.c_3005_b.u_1723_Y.P_1922_E();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Z_4720_K e) {
        boolean found;
        String name = e.J_1907_R().u_1723_Y().toString();
        boolean bl = found = umenshitZvukOptions.isOptionEnabled("\u0411\u0438\u0442\u044c\u0435 \u043f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430") != false && name.contains("minecraft:entity.experience_bottle.throw") || name.contains("minecraft:entity.experience_orb.pickup");
        if (umenshitZvukOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && name.contains("minecraft:item.trident.throw")) {
            found = true;
        }
        if (umenshitZvukOptions.isOptionEnabled("\u041f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u0435 \u0432\u0438\u0437\u0435\u0440\u0430").booleanValue() && name.contains("minecraft:entity.wither.spawn")) {
            found = true;
        }
        if (umenshitZvukOptions.isOptionEnabled("\u041e\u0442\u043a\u0440\u044b\u0442\u0438\u0435 \u044d\u043d\u0434-\u043f\u043e\u0440\u0442\u0430\u043b\u0430").booleanValue() && name.contains("minecraft:block.end_portal.spawn")) {
            found = true;
        }
        if (umenshitZvukOptions.isOptionEnabled("\u041c\u0443\u0437\u044b\u043a\u0430\u043b\u044c\u043d\u044b\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438").booleanValue() && name.contains("minecraft:item.record.play")) {
            found = true;
        }
        if (found) {
            e.n_1700_B(((Float)gromkostSetting.getValue()).floatValue());
        }
    }
}



