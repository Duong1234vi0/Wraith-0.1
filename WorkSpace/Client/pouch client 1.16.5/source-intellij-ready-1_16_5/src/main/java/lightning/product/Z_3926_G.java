/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.C_3304_p;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.ServerboundSeenAdvancementsPacket;
import lightning.product.P_430_o;
import lightning.product.W_2853_p;
import lightning.product.ClientAdvancements;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.m_2494_X;
import lightning.product.x_282_a;

public class Z_3926_G
extends k_2603_m
implements ClientAdvancements.n_1700_B {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/advancements/window.png");
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/advancements/tabs.png");
    private static final x_282_a R_4764_Y = new F_2904_S("advancements.sad_label");
    private static final x_282_a G_564_y = new F_2904_S("advancements.empty");
    private static final x_282_a P_1922_E = new F_2904_S("gui.advancements");
    private final ClientAdvancements u_1723_Y;
    private final Map<A_2629_w, P_430_o> v_4262_N = Maps.newLinkedHashMap();
    private P_430_o w_1484_f;
    private boolean t_148_a;

    public Z_3926_G(ClientAdvancements clientAdvancementManager) {
        super(I_1084_e.n_1700_B);
        this.u_1723_Y = clientAdvancementManager;
    }

    @Override
    protected void init() {
        this.v_4262_N.clear();
        this.w_1484_f = null;
        this.u_1723_Y.n_1700_B(this);
        if (this.w_1484_f == null && !this.v_4262_N.isEmpty()) {
            this.u_1723_Y.n_1700_B(this.v_4262_N.values().iterator().next().n_1700_B(), true);
        } else {
            this.u_1723_Y.n_1700_B(this.w_1484_f == null ? null : this.w_1484_f.n_1700_B(), true);
        }
    }

    @Override
    public void onClose() {
        this.u_1723_Y.n_1700_B((ClientAdvancements.n_1700_B)null);
        W_2853_p clientplaynethandler = this.minecraft.k_2293_S();
        if (clientplaynethandler != null) {
            clientplaynethandler.n_1700_B(ServerboundSeenAdvancementsPacket.J_1907_R());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int i = (this.width - 252) / 2;
            int j = (this.height - 140) / 2;
            for (P_430_o advancementtabgui : this.v_4262_N.values()) {
                if (!advancementtabgui.n_1700_B(i, j, mouseX, mouseY)) continue;
                this.u_1723_Y.n_1700_B(advancementtabgui.n_1700_B(), true);
                break;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.minecraft.P_4830_p.w_612_n.n_1700_B(keyCode, scanCode)) {
            this.minecraft.n_1700_B((k_2603_m)null);
            this.minecraft.h_1847_R.w_1484_f();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        int i = (this.width - 252) / 2;
        int j = (this.height - 140) / 2;
        this.renderBackground(matrixStack);
        this.n_1700_B(matrixStack, mouseX, mouseY, i, j);
        this.n_1700_B(matrixStack, i, j);
        this.J_1907_R(matrixStack, mouseX, mouseY, i, j);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button != 0) {
            this.t_148_a = false;
            return false;
        }
        if (!this.t_148_a) {
            this.t_148_a = true;
        } else if (this.w_1484_f != null) {
            this.w_1484_f.n_1700_B(dragX, dragY);
        }
        return true;
    }

    private void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, int offsetX, int offsetY) {
        P_430_o advancementtabgui = this.w_1484_f;
        if (advancementtabgui == null) {
            Z_3926_G.fill(matrixStack, offsetX + 9, offsetY + 18, offsetX + 9 + 234, offsetY + 18 + 113, -16777216);
            int i = offsetX + 9 + 117;
            Z_3926_G.drawCenteredString(matrixStack, this.font, G_564_y, i, offsetY + 18 + 56 - 4, -1);
            Z_3926_G.drawCenteredString(matrixStack, this.font, R_4764_Y, i, offsetY + 18 + 113 - 9, -1);
        } else {
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)(offsetX + 9), (float)(offsetY + 18), 0.0f);
            advancementtabgui.n_1700_B(matrixStack);
            c_4037_x.d_2461_k();
            c_4037_x.J_1907_R(515);
            c_4037_x.t_1786_h();
        }
    }

    public void n_1700_B(g_221_o matrixStack, int offsetX, int offsetY) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.Y_601_j();
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        this.blit(matrixStack, offsetX, offsetY, 0, 0, 252, 140);
        if (this.v_4262_N.size() > 1) {
            this.minecraft.G_624_v().n_1700_B(J_1907_R);
            for (P_430_o advancementtabgui : this.v_4262_N.values()) {
                advancementtabgui.n_1700_B(matrixStack, offsetX, offsetY, advancementtabgui == this.w_1484_f);
            }
            c_4037_x.n_3318_d();
            c_4037_x.s_2632_s();
            for (P_430_o advancementtabgui1 : this.v_4262_N.values()) {
                advancementtabgui1.n_1700_B(offsetX, offsetY, this.itemRenderer);
            }
            c_4037_x.Y_259_p();
        }
        this.font.J_1907_R(matrixStack, P_1922_E, (float)(offsetX + 8), (float)(offsetY + 6), 0x404040);
    }

    private void J_1907_R(g_221_o matrixStack, int mouseX, int mouseY, int offsetX, int offsetY) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.w_1484_f != null) {
            c_4037_x.v_4276_D();
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.R_4764_Y((float)(offsetX + 9), (float)(offsetY + 18), 400.0f);
            this.w_1484_f.n_1700_B(matrixStack, mouseX - offsetX - 9, mouseY - offsetY - 18, offsetX, offsetY);
            c_4037_x.t_1786_h();
            c_4037_x.d_2461_k();
        }
        if (this.v_4262_N.size() > 1) {
            for (P_430_o advancementtabgui : this.v_4262_N.values()) {
                if (!advancementtabgui.n_1700_B(offsetX, offsetY, (double)mouseX, mouseY)) continue;
                this.renderTooltip(matrixStack, advancementtabgui.J_1907_R(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void n_1700_B(A_2629_w advancementIn) {
        P_430_o advancementtabgui = P_430_o.n_1700_B(this.minecraft, this, this.v_4262_N.size(), advancementIn);
        if (advancementtabgui != null) {
            this.v_4262_N.put(advancementIn, advancementtabgui);
        }
    }

    @Override
    public void J_1907_R(A_2629_w advancementIn) {
    }

    @Override
    public void R_4764_Y(A_2629_w advancementIn) {
        P_430_o advancementtabgui = this.v_4262_N(advancementIn);
        if (advancementtabgui != null) {
            advancementtabgui.n_1700_B(advancementIn);
        }
    }

    @Override
    public void G_564_y(A_2629_w advancementIn) {
    }

    @Override
    public void n_1700_B(A_2629_w advancementIn, C_3304_p progress) {
        m_2494_X advancemententrygui = this.u_1723_Y(advancementIn);
        if (advancemententrygui != null) {
            advancemententrygui.n_1700_B(progress);
        }
    }

    @Override
    public void P_1922_E(@Nullable A_2629_w advancementIn) {
        this.w_1484_f = this.v_4262_N.get(advancementIn);
    }

    @Override
    public void n_1700_B() {
        this.v_4262_N.clear();
        this.w_1484_f = null;
    }

    @Nullable
    public m_2494_X u_1723_Y(A_2629_w advancement) {
        P_430_o advancementtabgui = this.v_4262_N(advancement);
        return advancementtabgui == null ? null : advancementtabgui.J_1907_R(advancement);
    }

    @Nullable
    private P_430_o v_4262_N(A_2629_w advancement) {
        while (advancement.J_1907_R() != null) {
            advancement = advancement.J_1907_R();
        }
        return this.v_4262_N.get(advancement);
    }
}


