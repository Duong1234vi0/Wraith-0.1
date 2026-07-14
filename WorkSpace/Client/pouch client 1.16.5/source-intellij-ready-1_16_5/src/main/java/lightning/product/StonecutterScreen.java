/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.StonecutterRecipe;
import lightning.product.R_4599_y;
import lightning.product.SimpleSoundInstance;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class StonecutterScreen
extends z_3427_G<R_4599_y> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/stonecutter.png");
    private float J_1907_R;
    private boolean R_4764_Y;
    private int G_564_y;
    private boolean P_1922_E;

    public StonecutterScreen(R_4599_y containerIn, W_3491_f playerInv, x_282_a titleIn) {
        super(containerIn, playerInv, titleIn);
        containerIn.n_1700_B(this::u_1723_Y);
        --this.M_588_G;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        int k = (int)(41.0f * this.J_1907_R);
        this.blit(matrixStack, i + 119, j + 15 + k, 176 + (this.P_1922_E() ? 0 : 12), 0, 12, 15);
        int l = this.multiplayerClientSuggestionProvider + 52;
        int i1 = this.w_1457_N + 14;
        int j1 = this.G_564_y + 12;
        this.n_1700_B(matrixStack, x, y, l, i1, j1);
        this.n_1700_B(l, i1, j1);
    }

    @Override
    protected void J_1907_R(g_221_o matrixStack, int x, int y) {
        super.J_1907_R(matrixStack, x, y);
        if (this.P_1922_E) {
            int i = this.multiplayerClientSuggestionProvider + 52;
            int j = this.w_1457_N + 14;
            int k = this.G_564_y + 12;
            List<StonecutterRecipe> list = ((R_4599_y)this.Q_4569_t).J_1907_R();
            for (int l = this.G_564_y; l < k && l < ((R_4599_y)this.Q_4569_t).R_4764_Y(); ++l) {
                int i1 = l - this.G_564_y;
                int j1 = i + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if (x < j1 || x >= j1 + 16 || y < k1 || y >= k1 + 18) continue;
                this.renderTooltip(matrixStack, list.get(l).R_4764_Y(), x, y);
            }
        }
    }

    private void n_1700_B(g_221_o matrixStack, int x, int y, int p_238853_4_, int p_238853_5_, int p_238853_6_) {
        for (int i = this.G_564_y; i < p_238853_6_ && i < ((R_4599_y)this.Q_4569_t).R_4764_Y(); ++i) {
            int j = i - this.G_564_y;
            int k = p_238853_4_ + j % 4 * 16;
            int l = j / 4;
            int i1 = p_238853_5_ + l * 18 + 2;
            int j1 = this.s_956_w;
            if (i == ((R_4599_y)this.Q_4569_t).n_1700_B()) {
                j1 += 18;
            } else if (x >= k && y >= i1 && x < k + 16 && y < i1 + 18) {
                j1 += 36;
            }
            this.blit(matrixStack, k, i1 - 1, 0, j1, 16, 18);
        }
    }

    private void n_1700_B(int left, int top, int recipeIndexOffsetMax) {
        List<StonecutterRecipe> list = ((R_4599_y)this.Q_4569_t).J_1907_R();
        for (int i = this.G_564_y; i < recipeIndexOffsetMax && i < ((R_4599_y)this.Q_4569_t).R_4764_Y(); ++i) {
            int j = i - this.G_564_y;
            int k = left + j % 4 * 16;
            int l = j / 4;
            int i1 = top + l * 18 + 2;
            this.minecraft.r_715_M().J_1907_R(list.get(i).R_4764_Y(), k, i1);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.R_4764_Y = false;
        if (this.P_1922_E) {
            int i = this.multiplayerClientSuggestionProvider + 52;
            int j = this.w_1457_N + 14;
            int k = this.G_564_y + 12;
            for (int l = this.G_564_y; l < k; ++l) {
                int i1 = l - this.G_564_y;
                double d0 = mouseX - (double)(i + i1 % 4 * 16);
                double d1 = mouseY - (double)(j + i1 / 4 * 18);
                if (!(d0 >= 0.0) || !(d1 >= 0.0) || !(d0 < 16.0) || !(d1 < 18.0) || !((R_4599_y)this.Q_4569_t).J_1907_R((a_3913_L)this.minecraft.Y_259_p, l)) continue;
                MinecraftClient.A_4115_X().Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.s_3698_N, 1.0f));
                this.minecraft.w_1457_N.sendEnchantPacket(((R_4599_y)this.Q_4569_t).u_1723_Y, l);
                return true;
            }
            i = this.multiplayerClientSuggestionProvider + 119;
            j = this.w_1457_N + 9;
            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 54)) {
                this.R_4764_Y = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.R_4764_Y && this.P_1922_E()) {
            int i = this.w_1457_N + 14;
            int j = i + 54;
            this.J_1907_R = ((float)mouseY - (float)i - 7.5f) / ((float)(j - i) - 15.0f);
            this.J_1907_R = u_530_F.n_1700_B(this.J_1907_R, 0.0f, 1.0f);
            this.G_564_y = (int)((double)(this.J_1907_R * (float)this.n_1700_B()) + 0.5) * 4;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (this.P_1922_E()) {
            int i = this.n_1700_B();
            this.J_1907_R = (float)((double)this.J_1907_R - delta / (double)i);
            this.J_1907_R = u_530_F.n_1700_B(this.J_1907_R, 0.0f, 1.0f);
            this.G_564_y = (int)((double)(this.J_1907_R * (float)i) + 0.5) * 4;
        }
        return true;
    }

    private boolean P_1922_E() {
        return this.P_1922_E && ((R_4599_y)this.Q_4569_t).R_4764_Y() > 12;
    }

    protected int n_1700_B() {
        return (((R_4599_y)this.Q_4569_t).R_4764_Y() + 4 - 1) / 4 - 3;
    }

    private void u_1723_Y() {
        this.P_1922_E = ((R_4599_y)this.Q_4569_t).G_564_y();
        if (!this.P_1922_E) {
            this.J_1907_R = 0.0f;
            this.G_564_y = 0;
        }
    }
}



