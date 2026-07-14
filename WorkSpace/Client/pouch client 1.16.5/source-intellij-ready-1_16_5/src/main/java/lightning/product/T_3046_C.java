/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.J_2538_C;
import lightning.product.SimpleSoundInstance;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_3265_k;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.e_933_M;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.o_3091_w;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4889_F;
import lightning.product.u_530_F;
import lightning.product.w_1471_F;
import lightning.product.BannerRenderer;
import lightning.product.x_2414_j;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class T_3046_C
extends z_3427_G<w_1471_F> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/loom.png");
    private static final int J_1907_R = (J_2538_C.z_4693_k - J_2538_C.g_221_o - 1 + 4 - 1) / 4;
    private final e_4189_z R_4764_Y;
    @Nullable
    private List<Pair<J_2538_C, e_933_M>> G_564_y;
    private Z_1993_T P_1922_E = Z_1993_T.J_1907_R;
    private Z_1993_T u_1723_Y = Z_1993_T.J_1907_R;
    private Z_1993_T v_4262_N = Z_1993_T.J_1907_R;
    private boolean Q_2552_b;
    private boolean C_2741_M;
    private boolean k_2293_S;
    private float q_2307_F;
    private boolean Z_875_P;
    private int c_3005_b = 1;

    public T_3046_C(w_1471_F container, W_3491_f playerInventory, x_282_a textComponent) {
        super(container, playerInventory, textComponent);
        this.R_4764_Y = BannerRenderer.n_1700_B();
        container.n_1700_B(this::n_1700_B);
        this.M_588_G -= 2;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        Slot slot = ((w_1471_F)this.Q_4569_t).J_1907_R();
        Slot slot1 = ((w_1471_F)this.Q_4569_t).R_4764_Y();
        Slot slot2 = ((w_1471_F)this.Q_4569_t).G_564_y();
        Slot slot3 = ((w_1471_F)this.Q_4569_t).P_1922_E();
        if (!slot.J_1907_R()) {
            this.blit(matrixStack, i + slot.P_1922_E, j + slot.u_1723_Y, this.t_148_a, 0, 16, 16);
        }
        if (!slot1.J_1907_R()) {
            this.blit(matrixStack, i + slot1.P_1922_E, j + slot1.u_1723_Y, this.t_148_a + 16, 0, 16, 16);
        }
        if (!slot2.J_1907_R()) {
            this.blit(matrixStack, i + slot2.P_1922_E, j + slot2.u_1723_Y, this.t_148_a + 32, 0, 16, 16);
        }
        int k = (int)(41.0f * this.q_2307_F);
        this.blit(matrixStack, i + 119, j + 13 + k, 232 + (this.Q_2552_b ? 0 : 12), 0, 12, 15);
        W_3265_k.R_4764_Y();
        if (this.G_564_y != null && !this.k_2293_S) {
            o_3091_w.n_1700_B irendertypebuffer$impl = this.minecraft.j_1564_a().J_1907_R();
            matrixStack.n_1700_B();
            matrixStack.n_1700_B((double)(i + 139), (double)(j + 52), 0.0);
            matrixStack.n_1700_B(24.0f, -24.0f, 1.0f);
            matrixStack.n_1700_B(0.5, 0.5, 0.5);
            float f = 0.6666667f;
            matrixStack.n_1700_B(0.6666667f, -0.6666667f, -0.6666667f);
            this.R_4764_Y.u_1723_Y = 0.0f;
            this.R_4764_Y.G_564_y = -32.0f;
            BannerRenderer.n_1700_B(matrixStack, irendertypebuffer$impl, 0xF000F0, Z_3224_L.n_1700_B, this.R_4764_Y, g_2561_p.u_1723_Y, true, this.G_564_y);
            matrixStack.J_1907_R();
            irendertypebuffer$impl.J_1907_R();
        } else if (this.k_2293_S) {
            this.blit(matrixStack, i + slot3.P_1922_E - 2, j + slot3.u_1723_Y - 2, this.t_148_a, 17, 17, 16);
        }
        if (this.Q_2552_b) {
            int j2 = i + 60;
            int l2 = j + 13;
            int l = this.c_3005_b + 16;
            for (int i1 = this.c_3005_b; i1 < l && i1 < J_2538_C.z_4693_k - J_2538_C.g_221_o; ++i1) {
                int j1 = i1 - this.c_3005_b;
                int k1 = j2 + j1 % 4 * 14;
                int l1 = l2 + j1 / 4 * 14;
                this.minecraft.G_624_v().n_1700_B(n_1700_B);
                int i2 = this.s_956_w;
                if (i1 == ((w_1471_F)this.Q_4569_t).n_1700_B()) {
                    i2 += 14;
                } else if (x >= k1 && y >= l1 && x < k1 + 14 && y < l1 + 14) {
                    i2 += 28;
                }
                this.blit(matrixStack, k1, l1, 0, i2, 14, 14);
                this.n_1700_B(i1, k1, l1);
            }
        } else if (this.C_2741_M) {
            int k2 = i + 60;
            int i3 = j + 13;
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            this.blit(matrixStack, k2, i3, 0, this.s_956_w, 14, 14);
            int j3 = ((w_1471_F)this.Q_4569_t).n_1700_B();
            this.n_1700_B(j3, k2, i3);
        }
        W_3265_k.G_564_y();
    }

    private void n_1700_B(int p_228190_1_, int p_228190_2_, int p_228190_3_) {
        Z_1993_T itemstack = new Z_1993_T(Items.JukeboxBlock);
        U_2912_j compoundnbt = itemstack.n_1700_B("BlockEntityTag");
        q_2896_o listnbt = new J_2538_C.n_1700_B().n_1700_B(J_2538_C.n_1700_B, e_933_M.w_1484_f).n_1700_B(J_2538_C.values()[p_228190_1_], e_933_M.n_1700_B).n_1700_B();
        compoundnbt.n_1700_B("Patterns", listnbt);
        g_221_o matrixstack = new g_221_o();
        matrixstack.n_1700_B();
        matrixstack.n_1700_B((double)((float)p_228190_2_ + 0.5f), (double)(p_228190_3_ + 16), 0.0);
        matrixstack.n_1700_B(6.0f, -6.0f, 1.0f);
        matrixstack.n_1700_B(0.5, 0.5, 0.0);
        matrixstack.n_1700_B(0.5, 0.5, 0.5);
        float f = 0.6666667f;
        matrixstack.n_1700_B(0.6666667f, -0.6666667f, -0.6666667f);
        o_3091_w.n_1700_B irendertypebuffer$impl = this.minecraft.j_1564_a().J_1907_R();
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.R_4764_Y.G_564_y = -32.0f;
        List<Pair<J_2538_C, e_933_M>> list = r_4889_F.n_1700_B(e_933_M.w_1484_f, r_4889_F.n_1700_B(itemstack));
        BannerRenderer.n_1700_B(matrixstack, irendertypebuffer$impl, 0xF000F0, Z_3224_L.n_1700_B, this.R_4764_Y, g_2561_p.u_1723_Y, true, list);
        matrixstack.J_1907_R();
        irendertypebuffer$impl.J_1907_R();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.Z_875_P = false;
        if (this.Q_2552_b) {
            int i = this.multiplayerClientSuggestionProvider + 60;
            int j = this.w_1457_N + 13;
            int k = this.c_3005_b + 16;
            for (int l = this.c_3005_b; l < k; ++l) {
                int i1 = l - this.c_3005_b;
                double d0 = mouseX - (double)(i + i1 % 4 * 14);
                double d1 = mouseY - (double)(j + i1 / 4 * 14);
                if (!(d0 >= 0.0) || !(d1 >= 0.0) || !(d0 < 14.0) || !(d1 < 14.0) || !((w_1471_F)this.Q_4569_t).J_1907_R((a_3913_L)this.minecraft.Y_259_p, l)) continue;
                MinecraftClient.A_4115_X().Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HoneyBlock, 1.0f));
                this.minecraft.w_1457_N.sendEnchantPacket(((w_1471_F)this.Q_4569_t).u_1723_Y, l);
                return true;
            }
            i = this.multiplayerClientSuggestionProvider + 119;
            j = this.w_1457_N + 9;
            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 56)) {
                this.Z_875_P = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.Z_875_P && this.Q_2552_b) {
            int i = this.w_1457_N + 13;
            int j = i + 56;
            this.q_2307_F = ((float)mouseY - (float)i - 7.5f) / ((float)(j - i) - 15.0f);
            this.q_2307_F = u_530_F.n_1700_B(this.q_2307_F, 0.0f, 1.0f);
            int k = J_1907_R - 4;
            int l = (int)((double)(this.q_2307_F * (float)k) + 0.5);
            if (l < 0) {
                l = 0;
            }
            this.c_3005_b = 1 + l * 4;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (this.Q_2552_b) {
            int i = J_1907_R - 4;
            this.q_2307_F = (float)((double)this.q_2307_F - delta / (double)i);
            this.q_2307_F = u_530_F.n_1700_B(this.q_2307_F, 0.0f, 1.0f);
            this.c_3005_b = 1 + (int)((double)(this.q_2307_F * (float)i) + 0.5) * 4;
        }
        return true;
    }

    @Override
    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        return mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
    }

    private void n_1700_B() {
        Z_1993_T itemstack = ((w_1471_F)this.Q_4569_t).P_1922_E().n_1700_B();
        this.G_564_y = itemstack.n_1700_B() ? null : r_4889_F.n_1700_B(((x_2414_j)itemstack.J_1907_R()).R_4764_Y(), r_4889_F.n_1700_B(itemstack));
        Z_1993_T itemstack1 = ((w_1471_F)this.Q_4569_t).J_1907_R().n_1700_B();
        Z_1993_T itemstack2 = ((w_1471_F)this.Q_4569_t).R_4764_Y().n_1700_B();
        Z_1993_T itemstack3 = ((w_1471_F)this.Q_4569_t).G_564_y().n_1700_B();
        U_2912_j compoundnbt = itemstack1.n_1700_B("BlockEntityTag");
        boolean bl = this.k_2293_S = compoundnbt.R_4764_Y("Patterns", 9) && !itemstack1.n_1700_B() && compoundnbt.G_564_y("Patterns", 10).size() >= 6;
        if (this.k_2293_S) {
            this.G_564_y = null;
        }
        if (!(Z_1993_T.J_1907_R(itemstack1, this.P_1922_E) && Z_1993_T.J_1907_R(itemstack2, this.u_1723_Y) && Z_1993_T.J_1907_R(itemstack3, this.v_4262_N))) {
            this.Q_2552_b = !itemstack1.n_1700_B() && !itemstack2.n_1700_B() && itemstack3.n_1700_B() && !this.k_2293_S;
            this.C_2741_M = !this.k_2293_S && !itemstack3.n_1700_B() && !itemstack1.n_1700_B() && !itemstack2.n_1700_B();
        }
        this.P_1922_E = itemstack1.t_148_a();
        this.u_1723_Y = itemstack2.t_148_a();
        this.v_4262_N = itemstack3.t_148_a();
    }
}



