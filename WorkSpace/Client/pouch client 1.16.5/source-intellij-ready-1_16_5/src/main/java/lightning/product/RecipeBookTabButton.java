/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.RecipeCollection;
import lightning.product.H_3330_w;
import lightning.product.Z_1993_T;
import lightning.product.c_1070_s;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.StateSwitchingButton;
import lightning.product.Recipe;
import lightning.product.j_4436_c;
import lightning.product.n_4974_X;
import lightning.product.RecipeBookMenu;

public class RecipeBookTabButton
extends StateSwitchingButton {
    private final n_4974_X v_4262_N;
    private float w_1484_f;

    public RecipeBookTabButton(n_4974_X p_i51075_1_) {
        super(0, 0, 35, 27, false);
        this.v_4262_N = p_i51075_1_;
        this.n_1700_B(153, 2, 35, 0, j_4436_c.n_1700_B);
    }

    public void n_1700_B(MinecraftClient p_193918_1_) {
        c_1070_s clientrecipebook = p_193918_1_.Y_259_p.M_182_A();
        List<RecipeCollection> list = clientrecipebook.n_1700_B(this.v_4262_N);
        if (p_193918_1_.Y_259_p.H_1873_g instanceof RecipeBookMenu) {
            for (RecipeCollection recipelist : list) {
                for (Recipe<?> irecipe : recipelist.n_1700_B(clientrecipebook.n_1700_B((RecipeBookMenu)p_193918_1_.Y_259_p.H_1873_g))) {
                    if (!clientrecipebook.G_564_y(irecipe)) continue;
                    this.w_1484_f = 15.0f;
                    return;
                }
            }
        }
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.w_1484_f > 0.0f) {
            float f = 1.0f + 0.1f * (float)Math.sin(this.w_1484_f / 15.0f * (float)Math.PI);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)(this.x + 8), (float)(this.y + 12), 0.0f);
            c_4037_x.J_1907_R(1.0f, f, 1.0f);
            c_4037_x.R_4764_Y((float)(-(this.x + 8)), (float)(-(this.y + 12)), 0.0f);
        }
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.G_624_v().n_1700_B(this.n_1700_B);
        c_4037_x.t_1786_h();
        int i = this.R_4764_Y;
        int j = this.G_564_y;
        if (this.J_1907_R) {
            i += this.P_1922_E;
        }
        if (this.isHovered()) {
            j += this.u_1723_Y;
        }
        int k = this.x;
        if (this.J_1907_R) {
            k -= 2;
        }
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.blit(matrixStack, k, this.y, i, j, this.width, this.height);
        c_4037_x.multiplayerClientSuggestionProvider();
        this.n_1700_B(minecraft.r_715_M());
        if (this.w_1484_f > 0.0f) {
            c_4037_x.d_2461_k();
            this.w_1484_f -= partialTicks;
        }
    }

    private void n_1700_B(H_3330_w p_193920_1_) {
        int i;
        List<Z_1993_T> list = this.v_4262_N.n_1700_B();
        int n = i = this.J_1907_R ? -2 : 0;
        if (list.size() == 1) {
            p_193920_1_.R_4764_Y(list.get(0), this.x + 9 + i, this.y + 5);
        } else if (list.size() == 2) {
            p_193920_1_.R_4764_Y(list.get(0), this.x + 3 + i, this.y + 5);
            p_193920_1_.R_4764_Y(list.get(1), this.x + 14 + i, this.y + 5);
        }
    }

    public n_4974_X n_1700_B() {
        return this.v_4262_N;
    }

    public boolean n_1700_B(c_1070_s p_199500_1_) {
        List<RecipeCollection> list = p_199500_1_.n_1700_B(this.v_4262_N);
        this.visible = false;
        if (list != null) {
            for (RecipeCollection recipelist : list) {
                if (!recipelist.n_1700_B() || !recipelist.R_4764_Y()) continue;
                this.visible = true;
                break;
            }
        }
        return this.visible;
    }
}



