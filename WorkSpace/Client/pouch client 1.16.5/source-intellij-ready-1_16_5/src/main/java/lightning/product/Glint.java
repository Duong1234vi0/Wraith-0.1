/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.Module;
import lightning.product.h_2367_h;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class Glint
extends Module {
    private static Glint s_956_w;
    public final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u0422\u0435\u043c\u0430", "\u0422\u0435\u043c\u0430", "\u0421\u0432\u043e\u0439 \u0446\u0432\u0435\u0442", "\u0420\u0430\u0434\u0443\u0433\u0430");
    public final h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442", true, new Color(148, 0, 211).getRGB(), () -> ((String)this.rezhimMode.getValue()).equals("\u0421\u0432\u043e\u0439 \u0446\u0432\u0435\u0442"));
    public final NumberSetting skorostRadugiSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0440\u0430\u0434\u0443\u0433\u0438", 3.0f, 1.0f, 10.0f, 0.5f, () -> ((String)this.rezhimMode.getValue()).equals("\u0420\u0430\u0434\u0443\u0433\u0430"));

    public Glint() {
        super("Glint", ModuleCategory.R_4764_Y);
        s_956_w = this;
        this.addSettings(this.rezhimMode, this.w_1484_f, this.skorostRadugiSetting);
    }

    public static Glint h_1847_R() {
        return s_956_w;
    }

    public static int Q_4569_t() {
        if (s_956_w == null || !s_956_w.w_1484_f()) {
            return -1;
        }
        return switch ((String)Glint.s_956_w.v_4262_N.J_1907_R()) {
            case "\u0422\u0435\u043c\u0430" -> q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            case "\u0421\u0432\u043e\u0439 \u0446\u0432\u0435\u0442" -> (Integer)Glint.s_956_w.w_1484_f.J_1907_R();
            case "\u0420\u0430\u0434\u0443\u0433\u0430" -> Glint.t_1786_h();
            default -> -1;
        };
    }

    private static int t_1786_h() {
        float speed = s_956_w != null ? ((Float)Glint.s_956_w.t_148_a.J_1907_R()).floatValue() : 3.0f;
        float hue = (float)(System.currentTimeMillis() % (long)(3000.0f / speed)) / (3000.0f / speed);
        return Color.HSBtoRGB(hue, 0.8f, 1.0f);
    }

    public static float[] M_182_A() {
        int colorInt = Glint.Q_4569_t();
        if (colorInt == -1) {
            return null;
        }
        Color c = new Color(colorInt, true);
        return new float[]{(float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 1.0f};
    }
}


