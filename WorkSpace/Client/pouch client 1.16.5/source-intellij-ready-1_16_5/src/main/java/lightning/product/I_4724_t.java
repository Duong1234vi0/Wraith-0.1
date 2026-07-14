/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.RecipeCollection;
import lightning.product.G_304_t;
import lightning.product.U_1907_s;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.StateSwitchingButton;
import lightning.product.Recipe;
import lightning.product.j_4436_c;
import lightning.product.RecipeButton;
import lightning.product.z_1806_z;

public class I_4724_t {
    private final List<RecipeButton> n_1700_B = Lists.newArrayListWithCapacity((int)20);
    private RecipeButton J_1907_R;
    private final z_1806_z R_4764_Y = new z_1806_z();
    private MinecraftClient G_564_y;
    private final List<G_304_t> P_1922_E = Lists.newArrayList();
    private List<RecipeCollection> u_1723_Y;
    private StateSwitchingButton v_4262_N;
    private StateSwitchingButton w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private U_1907_s u_2550_I;
    private Recipe<?> M_588_G;
    private RecipeCollection P_4830_p;

    public I_4724_t() {
        for (int i = 0; i < 20; ++i) {
            this.n_1700_B.add(new RecipeButton());
        }
    }

    public void n_1700_B(MinecraftClient p_194194_1_, int p_194194_2_, int p_194194_3_) {
        this.G_564_y = p_194194_1_;
        this.u_2550_I = p_194194_1_.Y_259_p.M_182_A();
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            this.n_1700_B.get(i).n_1700_B(p_194194_2_ + 11 + 25 * (i % 5), p_194194_3_ + 31 + 25 * (i / 5));
        }
        this.v_4262_N = new StateSwitchingButton(p_194194_2_ + 93, p_194194_3_ + 137, 12, 17, false);
        this.v_4262_N.n_1700_B(1, 208, 13, 18, j_4436_c.n_1700_B);
        this.w_1484_f = new StateSwitchingButton(p_194194_2_ + 38, p_194194_3_ + 137, 12, 17, true);
        this.w_1484_f.n_1700_B(1, 208, 13, 18, j_4436_c.n_1700_B);
    }

    public void n_1700_B(j_4436_c p_193732_1_) {
        this.P_1922_E.remove(p_193732_1_);
        this.P_1922_E.add(p_193732_1_);
    }

    public void n_1700_B(List<RecipeCollection> p_194192_1_, boolean p_194192_2_) {
        this.u_1723_Y = p_194192_1_;
        this.t_148_a = (int)Math.ceil((double)p_194192_1_.size() / 20.0);
        if (this.t_148_a <= this.s_956_w || p_194192_2_) {
            this.s_956_w = 0;
        }
        this.u_1723_Y();
    }

    private void u_1723_Y() {
        int i = 20 * this.s_956_w;
        for (int j = 0; j < this.n_1700_B.size(); ++j) {
            RecipeButton recipewidget = this.n_1700_B.get(j);
            if (i + j < this.u_1723_Y.size()) {
                RecipeCollection recipelist = this.u_1723_Y.get(i + j);
                recipewidget.n_1700_B(recipelist, this);
                recipewidget.visible = true;
                continue;
            }
            recipewidget.visible = false;
        }
        this.v_4262_N();
    }

    private void v_4262_N() {
        this.v_4262_N.visible = this.t_148_a > 1 && this.s_956_w < this.t_148_a - 1;
        this.w_1484_f.visible = this.t_148_a > 1 && this.s_956_w > 0;
    }

    public void n_1700_B(g_221_o p_238927_1_, int p_238927_2_, int p_238927_3_, int p_238927_4_, int p_238927_5_, float p_238927_6_) {
        if (this.t_148_a > 1) {
            String s = this.s_956_w + 1 + "/" + this.t_148_a;
            int i = this.G_564_y.t_148_a.J_1907_R(s);
            this.G_564_y.t_148_a.J_1907_R(p_238927_1_, s, (float)(p_238927_2_ - i / 2 + 73), (float)(p_238927_3_ + 141), -1);
        }
        this.J_1907_R = null;
        for (RecipeButton recipewidget : this.n_1700_B) {
            recipewidget.render(p_238927_1_, p_238927_4_, p_238927_5_, p_238927_6_);
            if (!recipewidget.visible || !recipewidget.isHovered()) continue;
            this.J_1907_R = recipewidget;
        }
        this.w_1484_f.render(p_238927_1_, p_238927_4_, p_238927_5_, p_238927_6_);
        this.v_4262_N.render(p_238927_1_, p_238927_4_, p_238927_5_, p_238927_6_);
        this.R_4764_Y.render(p_238927_1_, p_238927_4_, p_238927_5_, p_238927_6_);
    }

    public void n_1700_B(g_221_o p_238926_1_, int p_238926_2_, int p_238926_3_) {
        if (this.G_564_y.Y_1740_V != null && this.J_1907_R != null && !this.R_4764_Y.R_4764_Y()) {
            this.G_564_y.Y_1740_V.func_243308_b(p_238926_1_, this.J_1907_R.n_1700_B(this.G_564_y.Y_1740_V), p_238926_2_, p_238926_3_);
        }
    }

    @Nullable
    public Recipe<?> n_1700_B() {
        return this.M_588_G;
    }

    @Nullable
    public RecipeCollection J_1907_R() {
        return this.P_4830_p;
    }

    public void R_4764_Y() {
        this.R_4764_Y.n_1700_B(false);
    }

    public boolean n_1700_B(double p_198955_1_, double p_198955_3_, int p_198955_5_, int p_198955_6_, int p_198955_7_, int p_198955_8_, int p_198955_9_) {
        this.M_588_G = null;
        this.P_4830_p = null;
        if (this.R_4764_Y.R_4764_Y()) {
            if (this.R_4764_Y.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
                this.M_588_G = this.R_4764_Y.J_1907_R();
                this.P_4830_p = this.R_4764_Y.n_1700_B();
            } else {
                this.R_4764_Y.n_1700_B(false);
            }
            return true;
        }
        if (this.v_4262_N.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
            ++this.s_956_w;
            this.u_1723_Y();
            return true;
        }
        if (this.w_1484_f.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
            --this.s_956_w;
            this.u_1723_Y();
            return true;
        }
        for (RecipeButton recipewidget : this.n_1700_B) {
            if (!recipewidget.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) continue;
            if (p_198955_5_ == 0) {
                this.M_588_G = recipewidget.R_4764_Y();
                this.P_4830_p = recipewidget.n_1700_B();
            } else if (p_198955_5_ == 1 && !this.R_4764_Y.R_4764_Y() && !recipewidget.J_1907_R()) {
                this.R_4764_Y.n_1700_B(this.G_564_y, recipewidget.n_1700_B(), recipewidget.x, recipewidget.y, p_198955_6_ + p_198955_8_ / 2, p_198955_7_ + 13 + p_198955_9_ / 2, (float)recipewidget.getWidth());
            }
            return true;
        }
        return false;
    }

    public void n_1700_B(List<Recipe<?>> p_194195_1_) {
        for (G_304_t irecipeupdatelistener : this.P_1922_E) {
            irecipeupdatelistener.n_1700_B(p_194195_1_);
        }
    }

    public MinecraftClient G_564_y() {
        return this.G_564_y;
    }

    public U_1907_s P_1922_E() {
        return this.u_2550_I;
    }
}



