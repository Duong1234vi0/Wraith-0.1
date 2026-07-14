/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Ordering
 */
package lightning.product;

import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import lightning.product.B_3871_I;
import lightning.product.J_1907_R;
import lightning.product.K_1289_S;
import lightning.product.MobEffectUtil;
import lightning.product.W_3491_f;
import lightning.product.X_4340_E;
import lightning.product.a_2900_S;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.n_1700_B;
import lightning.product.x_282_a;
import lightning.product.MobEffectTextureManager;
import lightning.product.z_3427_G;

public abstract class t_3127_w<T extends a_2900_S>
extends z_3427_G<T> {
    protected boolean n_1700_B;

    public t_3127_w(T screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.o_();
    }

    protected void o_() {
        X_4340_E player;
        n_1700_B bot1 = null;
        for (n_1700_B bot : J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E x_4340_E = player = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
        if (player.I_3457_f().isEmpty()) {
            this.multiplayerClientSuggestionProvider = (this.width - this.t_148_a) / 2;
            this.n_1700_B = false;
        } else {
            this.multiplayerClientSuggestionProvider = 160 + (this.width - this.t_148_a - 200) / 2;
            this.n_1700_B = true;
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.n_1700_B) {
            this.n_1700_B(matrixStack);
        }
    }

    private void n_1700_B(g_221_o p_238811_1_) {
        int i = this.multiplayerClientSuggestionProvider - 124;
        n_1700_B bot1 = null;
        for (n_1700_B bot : J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E player = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
        Collection<k_2610_C> collection = player.I_3457_f();
        if (!collection.isEmpty()) {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int j = 33;
            if (collection.size() > 5) {
                j = 132 / (collection.size() - 1);
            }
            List iterable = Ordering.natural().sortedCopy(collection);
            this.n_1700_B(p_238811_1_, i, j, iterable);
            this.J_1907_R(p_238811_1_, i, j, iterable);
            this.R_4764_Y(p_238811_1_, i, j, iterable);
        }
    }

    private void n_1700_B(g_221_o p_238810_1_, int p_238810_2_, int p_238810_3_, Iterable<k_2610_C> p_238810_4_) {
        this.minecraft.G_624_v().n_1700_B(w_1484_f);
        int i = this.w_1457_N;
        for (k_2610_C effectinstance : p_238810_4_) {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.blit(p_238810_1_, p_238810_2_, i, 0, 166, 140, 32);
            i += p_238810_3_;
        }
    }

    private void J_1907_R(g_221_o p_238812_1_, int p_238812_2_, int p_238812_3_, Iterable<k_2610_C> p_238812_4_) {
        MobEffectTextureManager potionspriteuploader = this.minecraft.V_1446_Y();
        int i = this.w_1457_N;
        for (k_2610_C effectinstance : p_238812_4_) {
            g_422_i effect = effectinstance.n_1700_B();
            B_3871_I textureatlassprite = potionspriteuploader.n_1700_B(effect);
            this.minecraft.G_624_v().n_1700_B(textureatlassprite.u_2550_I().R_4764_Y());
            t_3127_w.blit(p_238812_1_, p_238812_2_ + 6, i + 7, this.getBlitOffset(), 18, 18, textureatlassprite);
            i += p_238812_3_;
        }
    }

    private void R_4764_Y(g_221_o p_238813_1_, int p_238813_2_, int p_238813_3_, Iterable<k_2610_C> p_238813_4_) {
        int i = this.w_1457_N;
        for (k_2610_C effectinstance : p_238813_4_) {
            Object s = K_1289_S.n_1700_B(effectinstance.n_1700_B().R_4764_Y(), new Object[0]);
            if (effectinstance.R_4764_Y() >= 1 && effectinstance.R_4764_Y() <= 9) {
                s = (String)s + " " + K_1289_S.n_1700_B("enchantment.level." + (effectinstance.R_4764_Y() + 1), new Object[0]);
            }
            this.font.n_1700_B(p_238813_1_, (String)s, (float)(p_238813_2_ + 10 + 18), (float)(i + 6), 0xFFFFFF);
            String s1 = MobEffectUtil.n_1700_B(effectinstance, 1.0f);
            this.font.n_1700_B(p_238813_1_, s1, (float)(p_238813_2_ + 10 + 18), (float)(i + 6 + 10), 0x7F7F7F);
            i += p_238813_3_;
        }
    }
}


