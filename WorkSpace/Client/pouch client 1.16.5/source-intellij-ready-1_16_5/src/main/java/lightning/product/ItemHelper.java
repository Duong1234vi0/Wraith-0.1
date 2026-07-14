/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.A_229_v;
import lightning.product.B_3091_S;
import lightning.product.D_4024_W;
import lightning.product.D_908_R;
import lightning.product.F_489_x;
import lightning.product.StringTag;
import lightning.product.LongArrayTag;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.MobEffects;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.X_290_I;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.a_969_m;
import lightning.product.b_2037_V;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.Animation;
import lightning.product.ByteArrayTag;
import lightning.product.l_2647_k;
import lightning.product.n_3864_h;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.ShulkerPreview;
import lightning.product.IntTag;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import lightning.product.ModuleCategory;
import lightning.product.y_6_Q;

public class ItemHelper
extends Module {
    private final b_2037_V v_4262_N = new b_2037_V("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
    private final BooleanSetting podsvechivatZeleIsceleniyaEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0437\u0435\u043b\u044c\u0435 \u0438\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u044f", false);
    private final h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0437\u0435\u043b\u044c\u044f \u0438\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u044f", true, H_2506_c.n_1700_B("#FF2AB9"), this.podsvechivatZeleIsceleniyaEnabled::isEnabled);
    private final BooleanSetting podsvechivatCharkiEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0447\u0430\u0440\u043a\u0438", false);
    private final h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0447\u0430\u0440\u043a\u0438", true, H_2506_c.n_1700_B("#FFAC93"), this.podsvechivatCharkiEnabled::isEnabled);
    private final BooleanSetting podsvechivatZolotyeYablokiEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0437\u043e\u043b\u043e\u0442\u044b\u0435 \u044f\u0431\u043b\u043e\u043a\u0438", false);
    private final h_2367_h P_4830_p = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0437\u043e\u043b\u043e\u0442\u043e\u0433\u043e \u044f\u0431\u043b\u043e\u043a\u0430", true, H_2506_c.n_1700_B("#E7EB56"), this.podsvechivatZolotyeYablokiEnabled::isEnabled);
    private final b_2037_V h_1847_R = new b_2037_V("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
    private final BooleanSetting podsvechivatTotemyEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0442\u043e\u0442\u0435\u043c\u044b", false);
    private final h_2367_h M_182_A = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430", true, H_2506_c.n_1700_B("#FFD700"), this.podsvechivatTotemyEnabled::isEnabled);
    private final BooleanSetting podsvechivatXpButylkiEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c XP \u0431\u0443\u0442\u044b\u043b\u043a\u0438", false);
    private final h_2367_h multiplayerClientSuggestionProvider = new h_2367_h("\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 XP \u0431\u0443\u0442\u044b\u043b\u043a\u0438", true, H_2506_c.n_1700_B("#7CFC00"), this.podsvechivatXpButylkiEnabled::isEnabled);
    private final b_2037_V w_1457_N = new b_2037_V("\u041e\u0441\u0442\u0430\u043b\u044c\u043d\u043e\u0435");
    private final BooleanSetting umenshatZaderzhkuNaPredmetyEnabled = new BooleanSetting("\u0423\u043c\u0435\u043d\u044c\u0448\u0430\u0442\u044c \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0443 \u043d\u0430 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", false);
    private final BooleanSetting podsvechivatTolkoChtoPodnyatyePredmetyEnabled = new BooleanSetting("\u041f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0447\u0442\u043e \u043f\u043e\u0434\u043d\u044f\u0442\u044b\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", false);
    private final BooleanSetting otobrazhatNbtPredmetovEnabled = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c nbt \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", false);
    private final Animation C_2741_M = new Animation(0.0f, 4.0f);

    public ItemHelper() {
        super("ItemHelper", ModuleCategory.P_1922_E);
        this.addSettings(this.v_4262_N, this.podsvechivatZeleIsceleniyaEnabled, this.t_148_a, this.podsvechivatCharkiEnabled, this.u_2550_I, this.podsvechivatZolotyeYablokiEnabled, this.P_4830_p, this.h_1847_R, this.podsvechivatTotemyEnabled, this.M_182_A, this.podsvechivatXpButylkiEnabled, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.umenshatZaderzhkuNaPredmetyEnabled, this.otobrazhatNbtPredmetovEnabled);
    }

    @Y_1740_V
    public void n_1700_B(X_290_I e) {
        q_1613_l item = e.J_1907_R();
        Z_1993_T itemStack = new Z_1993_T(item);
        if (this.umenshatZaderzhkuNaPredmetyEnabled.isEnabled().booleanValue() && itemStack.x_607_J()) {
            int reduction = item.Q_2552_b().P_1922_E() ? 16 : 32;
            int originalTicks = e.R_4764_Y();
            if (originalTicks > reduction && itemStack.J_1907_R() != Items.MinMaxBounds) {
                e.n_1700_B(originalTicks - reduction);
                MutableComponent message = new U_2871_b("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043d\u0430 ").n_1700_B(new U_2871_b(item.h_1847_R().getString())).n_1700_B(new U_2871_b(" \u0443\u043c\u0435\u043d\u044c\u0448\u0435\u043d\u0430 \u043d\u0430 ~" + (double)reduction / 20.0 + " \u0441\u0435\u043a\u0443\u043d\u0434\u044b")).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(message, new Object[0]);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(n_3864_h.J_1907_R e) {
        if (ItemHelper.c_3005_b.Y_1740_V instanceof B_3091_S || !(e.P_1922_E() instanceof y_6_Q)) {
            return;
        }
        for (Slot slot : e.P_1922_E().P_1922_E) {
            if (slot == null || !slot.J_1907_R()) continue;
            this.n_1700_B(e.J_1907_R(), slot.n_1700_B(), e.R_4764_Y() + slot.P_1922_E, e.G_564_y() + slot.u_1723_Y);
        }
    }

    @Y_1740_V
    public void n_1700_B(l_2647_k e) {
        if (ItemHelper.c_3005_b.Y_1740_V instanceof B_3091_S) {
            return;
        }
        int centerX = c_3005_b.RealmsServerPing().Q_4569_t() / 2;
        int baseY = c_3005_b.RealmsServerPing().M_182_A() - 16 - 3;
        for (int i = 0; i < 9; ++i) {
            Z_1993_T stack = ItemHelper.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(i);
            if (stack.n_1700_B()) continue;
            this.n_1700_B(e.J_1907_R(), stack, centerX - 90 + i * 20 + 2, baseY);
        }
    }

    private void n_1700_B(g_221_o matrixStack, Z_1993_T stack, float x, float y) {
        int color = this.n_1700_B(stack);
        if (color != 0) {
            float norm = (this.C_2741_M.n_1700_B() + 0.75f) / 1.5f;
            int alpha = (int)(75.0f + norm * 100.0f);
            F_489_x.n_1700_B(matrixStack, x, y, 16.0f, 16.0f, H_2506_c.n_1700_B(color, alpha));
        }
    }

    private int n_1700_B(Z_1993_T stack) {
        if (this.podsvechivatCharkiEnabled.isEnabled().booleanValue() && stack.J_1907_R() == Items.E_4612_l) {
            return (Integer)this.u_2550_I.J_1907_R();
        }
        if (this.podsvechivatZolotyeYablokiEnabled.isEnabled().booleanValue() && stack.J_1907_R() == Items.p_863_D) {
            return (Integer)this.P_4830_p.J_1907_R();
        }
        if (this.podsvechivatZeleIsceleniyaEnabled.isEnabled().booleanValue() && u_1934_K.n_1700_B(stack, true, false, false, MobEffects.u_1723_Y)) {
            return (Integer)this.t_148_a.J_1907_R();
        }
        if (this.podsvechivatTotemyEnabled.isEnabled().booleanValue() && stack.J_1907_R() == Items.N_81_X) {
            return (Integer)this.M_182_A.J_1907_R();
        }
        if (this.podsvechivatXpButylkiEnabled.isEnabled().booleanValue() && stack.J_1907_R() == Items.s_3084_y) {
            return (Integer)this.multiplayerClientSuggestionProvider.J_1907_R();
        }
        return 0;
    }

    @Y_1740_V
    public void n_1700_B(A_229_v e) {
        if (ShulkerPreview.n_1700_B(e.J_1907_R)) {
            return;
        }
        if (!this.otobrazhatNbtPredmetovEnabled.isEnabled().booleanValue() || !e.J_1907_R.h_1847_R()) {
            return;
        }
        e.n_1700_B(true);
        List<x_282_a> tooltip = ItemHelper.c_3005_b.Y_1740_V.getTooltipFromItem(e.J_1907_R);
        tooltip.add(U_2871_b.R_4764_Y);
        this.n_1700_B(tooltip, e.J_1907_R.Q_4569_t(), 0);
        ItemHelper.c_3005_b.Y_1740_V.func_243308_b(e.n_1700_B, tooltip, e.R_4764_Y, e.G_564_y);
    }

    private void n_1700_B(List<x_282_a> tooltip, U_2912_j tag, int depth) {
        String indent = "  ".repeat(depth);
        for (String key : tag.G_564_y()) {
            Tag base = tag.R_4764_Y(key);
            String type = this.n_1700_B(base);
            tooltip.add(new U_2871_b(indent + "- " + type + ": " + key).n_1700_B(D_4024_W.t_148_a));
        }
    }

    private String n_1700_B(Tag nbt) {
        if (nbt instanceof L_3985_e) {
            return "byte";
        }
        if (nbt instanceof a_969_m) {
            return "short";
        }
        if (nbt instanceof IntTag) {
            return "int";
        }
        if (nbt instanceof q_2567_I) {
            return "long";
        }
        if (nbt instanceof T_2717_K) {
            return "float";
        }
        if (nbt instanceof D_908_R) {
            return "double";
        }
        if (nbt instanceof StringTag) {
            return "string";
        }
        if (nbt instanceof ByteArrayTag) {
            return "byte[]";
        }
        if (nbt instanceof IntArrayTag) {
            return "int[]";
        }
        if (nbt instanceof LongArrayTag) {
            return "long[]";
        }
        if (nbt instanceof q_2896_o) {
            return "list";
        }
        if (nbt instanceof U_2912_j) {
            return "compound";
        }
        return "unknown";
    }
}



