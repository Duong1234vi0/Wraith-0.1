/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lightning.product.RecipeCollection;
import lightning.product.F_2904_S;
import lightning.product.I_4724_t;
import lightning.product.U_1907_s;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.RecipeBookMenu;

public class RecipeButton
extends V_2511_L {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/recipe_book.png");
    private static final x_282_a J_1907_R = new F_2904_S("gui.recipebook.moreRecipes");
    private RecipeBookMenu<?> R_4764_Y;
    private U_1907_s G_564_y;
    private RecipeCollection P_1922_E;
    private float u_1723_Y;
    private float v_4262_N;
    private int w_1484_f;

    public RecipeButton() {
        super(0, 0, 25, 25, U_2871_b.R_4764_Y);
    }

    public void n_1700_B(RecipeCollection p_203400_1_, I_4724_t p_203400_2_) {
        this.P_1922_E = p_203400_1_;
        this.R_4764_Y = (RecipeBookMenu)p_203400_2_.G_564_y().Y_259_p.H_1873_g;
        this.G_564_y = p_203400_2_.P_1922_E();
        List<Recipe<?>> list = p_203400_1_.n_1700_B(this.G_564_y.n_1700_B(this.R_4764_Y));
        for (Recipe<?> irecipe : list) {
            if (!this.G_564_y.G_564_y(irecipe)) continue;
            p_203400_2_.n_1700_B(list);
            this.v_4262_N = 15.0f;
            break;
        }
    }

    public RecipeCollection n_1700_B() {
        return this.P_1922_E;
    }

    public void n_1700_B(int p_191770_1_, int p_191770_2_) {
        this.x = p_191770_1_;
        this.y = p_191770_2_;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        boolean flag;
        if (!k_2603_m.hasControlDown()) {
            this.u_1723_Y += partialTicks;
        }
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = 29;
        if (!this.P_1922_E.J_1907_R()) {
            i += 25;
        }
        int j = 206;
        if (this.P_1922_E.n_1700_B(this.G_564_y.n_1700_B(this.R_4764_Y)).size() > 1) {
            j += 25;
        }
        boolean bl = flag = this.v_4262_N > 0.0f;
        if (flag) {
            float f = 1.0f + 0.1f * (float)Math.sin(this.v_4262_N / 15.0f * (float)Math.PI);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)(this.x + 8), (float)(this.y + 12), 0.0f);
            c_4037_x.J_1907_R(f, f, 1.0f);
            c_4037_x.R_4764_Y((float)(-(this.x + 8)), (float)(-(this.y + 12)), 0.0f);
            this.v_4262_N -= partialTicks;
        }
        this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
        List<Recipe<?>> list = this.G_564_y();
        this.w_1484_f = u_530_F.G_564_y(this.u_1723_Y / 30.0f) % list.size();
        Z_1993_T itemstack = list.get(this.w_1484_f).R_4764_Y();
        int k = 4;
        if (this.P_1922_E.P_1922_E() && this.G_564_y().size() > 1) {
            minecraft.r_715_M().J_1907_R(itemstack, this.x + k + 1, this.y + k + 1);
            --k;
        }
        minecraft.r_715_M().R_4764_Y(itemstack, this.x + k, this.y + k);
        if (flag) {
            c_4037_x.d_2461_k();
        }
    }

    private List<Recipe<?>> G_564_y() {
        List<Recipe<?>> list = this.P_1922_E.J_1907_R(true);
        if (!this.G_564_y.n_1700_B(this.R_4764_Y)) {
            list.addAll(this.P_1922_E.J_1907_R(false));
        }
        return list;
    }

    public boolean J_1907_R() {
        return this.G_564_y().size() == 1;
    }

    public Recipe<?> R_4764_Y() {
        List<Recipe<?>> list = this.G_564_y();
        return list.get(this.w_1484_f);
    }

    public List<x_282_a> n_1700_B(k_2603_m p_191772_1_) {
        Z_1993_T itemstack = this.G_564_y().get(this.w_1484_f).R_4764_Y();
        ArrayList list = Lists.newArrayList(p_191772_1_.getTooltipFromItem(itemstack));
        if (this.P_1922_E.n_1700_B(this.G_564_y.n_1700_B(this.R_4764_Y)).size() > 1) {
            list.add(J_1907_R);
        }
        return list;
    }

    @Override
    public int getWidth() {
        return 25;
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return button == 0 || button == 1;
    }
}



