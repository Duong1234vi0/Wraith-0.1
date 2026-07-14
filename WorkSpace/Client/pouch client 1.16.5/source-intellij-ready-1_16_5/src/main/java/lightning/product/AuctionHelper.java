/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import lightning.product.AutoBuy;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.h_2367_h;
import lightning.product.Animation;
import lightning.product.n_3864_h;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;
import lightning.product.z_3427_G;

public class AuctionHelper
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "HolyWorld", "HolyWorld");
    private final h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442 \u0432\u044b\u0433\u043e\u0434\u043d\u043e\u0433\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", true, H_2506_c.n_1700_B("#53FF00"));
    private final NumberSetting kolichestvoVygodnyhPredmetovSetting = new NumberSetting("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0432\u044b\u0433\u043e\u0434\u043d\u044b\u0445 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", 3.0f, 1.0f, 5.0f, 1.0f);
    private final Animation s_956_w = new Animation(0.0f, 4.0f);
    private boolean u_2550_I = true;

    public AuctionHelper() {
        super("Auction Helper", ModuleCategory.P_1922_E);
        this.addSettings(this.rezhimMode, this.w_1484_f, this.kolichestvoVygodnyhPredmetovSetting);
    }

    @Y_1740_V
    public void n_1700_B(n_3864_h.J_1907_R e) {
        if (!(AuctionHelper.c_3005_b.Y_1740_V instanceof z_3427_G) || !this.rezhimMode.isMode("HolyWorld")) {
            return;
        }
        if (!AuctionHelper.c_3005_b.Y_1740_V.getTitle().getString().contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d")) {
            return;
        }
        float target = this.u_2550_I ? 0.0f : 1.0f;
        this.s_956_w.n_1700_B(target);
        if (this.s_956_w.R_4764_Y()) {
            this.u_2550_I = !this.u_2550_I;
        }
        int alpha = (int)(105.0f + this.s_956_w.n_1700_B() * 120.0f);
        ArrayList<n_1700_B> priced = new ArrayList<n_1700_B>();
        for (Slot slot : e.P_1922_E().P_1922_E) {
            Z_1993_T stack;
            long price;
            if (slot == null || !slot.J_1907_R() || (price = AutoBuy.n_1700_B(stack = slot.n_1700_B())) <= 0L) continue;
            priced.add(new n_1700_B(slot, price));
        }
        if (priced.isEmpty()) {
            return;
        }
        priced.sort(Comparator.comparingLong(ps -> ps.J_1907_R));
        int toHighlight = (int)Math.min((float)priced.size(), ((Float)this.kolichestvoVygodnyhPredmetovSetting.getValue()).floatValue());
        int highlightColor = H_2506_c.n_1700_B((int)((Integer)this.w_1484_f.J_1907_R()), alpha);
        for (int i = 0; i < toHighlight; ++i) {
            Slot slot = ((n_1700_B)priced.get((int)i)).n_1700_B;
            float rx = e.R_4764_Y() + slot.P_1922_E;
            float ry = e.G_564_y() + slot.u_1723_Y;
            F_489_x.n_1700_B(e.J_1907_R(), rx, ry, 16.0f, 16.0f, highlightColor);
        }
    }

    private static class n_1700_B {
        final Slot n_1700_B;
        final long J_1907_R;

        n_1700_B(Slot slot, long price) {
            this.n_1700_B = slot;
            this.J_1907_R = price;
        }
    }
}



