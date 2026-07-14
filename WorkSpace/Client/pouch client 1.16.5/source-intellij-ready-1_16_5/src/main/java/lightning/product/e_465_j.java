/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.D_2103_L;
import lightning.product.PackRepository;
import lightning.product.F_2904_S;
import lightning.product.J_4417_W;
import lightning.product.M_2935_g;
import lightning.product.R_2450_T;
import lightning.product.V_3049_B;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.AccessibilityOptionsScreen;
import lightning.product.Y_4729_x;
import lightning.product.a_3289_V;
import lightning.product.g_221_o;
import lightning.product.h_1866_W;
import lightning.product.LockIconButton;
import lightning.product.k_2603_m;
import lightning.product.ServerboundLockDifficultyPacket;
import lightning.product.l_792_r;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.ControlsScreen;
import lightning.product.u_2986_h;
import lightning.product.x_282_a;
import lightning.product.z_1886_T;

public class e_465_j
extends k_2603_m {
    private static final M_2935_g[] n_1700_B = new M_2935_g[]{M_2935_g.FOV};
    private final k_2603_m J_1907_R;
    private final V_4423_d R_4764_Y;
    private Button G_564_y;
    private LockIconButton P_1922_E;
    private R_2450_T u_1723_Y;

    public e_465_j(k_2603_m parentScreen, V_4423_d gameSettingsObj) {
        super(new F_2904_S("options.title"));
        this.J_1907_R = parentScreen;
        this.R_4764_Y = gameSettingsObj;
    }

    @Override
    protected void init() {
        int i = 0;
        for (M_2935_g abstractoption : n_1700_B) {
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 - 12 + 24 * (i >> 1);
            this.addButton(abstractoption.createWidget(this.minecraft.P_4830_p, j, k, 150));
            ++i;
        }
        if (this.minecraft.Y_601_j != null) {
            this.u_1723_Y = this.minecraft.Y_601_j.x_607_J();
            this.G_564_y = this.addButton(new Button(this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.n_1700_B(this.u_1723_Y), p_213051_1_ -> {
                this.u_1723_Y = R_2450_T.n_1700_B(this.u_1723_Y.n_1700_B() + 1);
                this.minecraft.k_2293_S().n_1700_B(new z_1886_T(this.u_1723_Y));
                this.G_564_y.setMessage(this.n_1700_B(this.u_1723_Y));
            }));
            if (this.minecraft.e_4240_b() && !this.minecraft.Y_601_j.Y_259_p().n_1700_B()) {
                this.G_564_y.setWidth(this.G_564_y.getWidth() - 20);
                this.P_1922_E = this.addButton(new LockIconButton(this.G_564_y.x + this.G_564_y.getWidth(), this.G_564_y.y, p_213054_1_ -> this.minecraft.n_1700_B(new q_3418_t(this::n_1700_B, new F_2904_S("difficulty.lock.title"), new F_2904_S("difficulty.lock.question", new F_2904_S("options.difficulty." + this.minecraft.Y_601_j.Y_259_p().u_2550_I().R_4764_Y()))))));
                this.P_1922_E.n_1700_B(this.minecraft.Y_601_j.Y_259_p().M_588_G());
                this.P_1922_E.active = !this.P_1922_E.n_1700_B();
                this.G_564_y.active = !this.P_1922_E.n_1700_B();
            } else {
                this.G_564_y.active = false;
            }
        } else {
            this.addButton(new Y_4729_x(this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, M_2935_g.REALMS_NOTIFICATIONS, M_2935_g.REALMS_NOTIFICATIONS.R_4764_Y(this.R_4764_Y), p_213057_1_ -> {
                M_2935_g.REALMS_NOTIFICATIONS.n_1700_B(this.R_4764_Y);
                this.R_4764_Y.J_1907_R();
                p_213057_1_.setMessage(M_2935_g.REALMS_NOTIFICATIONS.R_4764_Y(this.R_4764_Y));
            }));
        }
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, new F_2904_S("options.skinCustomisation"), p_213055_1_ -> this.minecraft.n_1700_B(new l_792_r(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, new F_2904_S("options.sounds"), p_213061_1_ -> this.minecraft.n_1700_B(new h_1866_W(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, new F_2904_S("options.video"), p_213059_1_ -> this.minecraft.n_1700_B(new J_4417_W(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, new F_2904_S("options.controls"), p_213052_1_ -> this.minecraft.n_1700_B(new ControlsScreen(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, new F_2904_S("options.language"), p_213053_1_ -> this.minecraft.n_1700_B(new a_3289_V((k_2603_m)this, this.R_4764_Y, this.minecraft.e_2887_G()))));
        this.addButton(new Button(this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, new F_2904_S("options.chat.title"), p_213049_1_ -> this.minecraft.n_1700_B(new u_2986_h(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, new F_2904_S("options.resourcepack"), p_213060_1_ -> this.minecraft.n_1700_B(new V_3049_B(this, this.minecraft.q_4610_l(), this::n_1700_B, this.minecraft.g_221_o(), new F_2904_S("resourcePack.title")))));
        this.addButton(new Button(this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, new F_2904_S("options.accessibility.title"), p_213058_1_ -> this.minecraft.n_1700_B(new AccessibilityOptionsScreen(this, this.R_4764_Y))));
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, CommonComponents.R_4764_Y, p_213056_1_ -> this.minecraft.n_1700_B(this.J_1907_R)));
    }

    private void n_1700_B(PackRepository p_241584_1_) {
        ImmutableList list = ImmutableList.copyOf(this.R_4764_Y.w_1484_f);
        this.R_4764_Y.w_1484_f.clear();
        this.R_4764_Y.t_148_a.clear();
        for (D_2103_L resourcepackinfo : p_241584_1_.P_1922_E()) {
            if (resourcepackinfo.v_4262_N()) continue;
            this.R_4764_Y.w_1484_f.add(resourcepackinfo.P_1922_E());
            if (resourcepackinfo.R_4764_Y().n_1700_B()) continue;
            this.R_4764_Y.t_148_a.add(resourcepackinfo.P_1922_E());
        }
        this.R_4764_Y.J_1907_R();
        ImmutableList list1 = ImmutableList.copyOf(this.R_4764_Y.w_1484_f);
        if (!list1.equals(list)) {
            this.minecraft.w_1484_f();
        }
    }

    private x_282_a n_1700_B(R_2450_T p_238630_1_) {
        return new F_2904_S("options.difficulty").n_1700_B(": ").n_1700_B(p_238630_1_.J_1907_R());
    }

    private void n_1700_B(boolean value) {
        this.minecraft.n_1700_B(this);
        if (value && this.minecraft.Y_601_j != null) {
            this.minecraft.k_2293_S().n_1700_B(new ServerboundLockDifficultyPacket(true));
            this.P_1922_E.n_1700_B(true);
            this.P_1922_E.active = false;
            this.G_564_y.active = false;
        }
    }

    @Override
    public void onClose() {
        this.R_4764_Y.J_1907_R();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        e_465_j.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


