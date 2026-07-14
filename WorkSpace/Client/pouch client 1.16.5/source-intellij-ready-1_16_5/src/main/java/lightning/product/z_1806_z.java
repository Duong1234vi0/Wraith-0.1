/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lightning.product.C_2701_A;
import lightning.product.RecipeCollection;
import lightning.product.GuiEventListener;
import lightning.product.Widget;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.k_902_f;
import lightning.product.PlaceRecipe;
import lightning.product.u_530_F;
import lightning.product.RecipeBookMenu;

public class z_1806_z
extends C_2701_A
implements GuiEventListener,
Widget {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/recipe_book.png");
    private final List<J_1907_R> J_1907_R = Lists.newArrayList();
    private boolean R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private MinecraftClient u_1723_Y;
    private RecipeCollection v_4262_N;
    private Recipe<?> w_1484_f;
    private float t_148_a;
    private boolean s_956_w;

    public void n_1700_B(MinecraftClient p_201703_1_, RecipeCollection p_201703_2_, int p_201703_3_, int p_201703_4_, int p_201703_5_, int p_201703_6_, float p_201703_7_) {
        float f5;
        float f4;
        float f3;
        float f2;
        float f1;
        this.u_1723_Y = p_201703_1_;
        this.v_4262_N = p_201703_2_;
        if (p_201703_1_.Y_259_p.H_1873_g instanceof k_902_f) {
            this.s_956_w = true;
        }
        boolean flag = p_201703_1_.Y_259_p.M_182_A().n_1700_B((RecipeBookMenu)p_201703_1_.Y_259_p.H_1873_g);
        List<Recipe<?>> list = p_201703_2_.J_1907_R(true);
        List list1 = flag ? Collections.emptyList() : p_201703_2_.J_1907_R(false);
        int i = list.size();
        int j = i + list1.size();
        int k = j <= 16 ? 4 : 5;
        int l = (int)Math.ceil((float)j / (float)k);
        this.G_564_y = p_201703_3_;
        this.P_1922_E = p_201703_4_;
        int i1 = 25;
        float f = this.G_564_y + Math.min(j, k) * 25;
        if (f > (f1 = (float)(p_201703_5_ + 50))) {
            this.G_564_y = (int)((float)this.G_564_y - p_201703_7_ * (float)((int)((f - f1) / p_201703_7_)));
        }
        if ((f2 = (float)(this.P_1922_E + l * 25)) > (f3 = (float)(p_201703_6_ + 50))) {
            this.P_1922_E = (int)((float)this.P_1922_E - p_201703_7_ * (float)u_530_F.u_1723_Y((f2 - f3) / p_201703_7_));
        }
        if ((f4 = (float)this.P_1922_E) < (f5 = (float)(p_201703_6_ - 100))) {
            this.P_1922_E = (int)((float)this.P_1922_E - p_201703_7_ * (float)u_530_F.u_1723_Y((f4 - f5) / p_201703_7_));
        }
        this.R_4764_Y = true;
        this.J_1907_R.clear();
        for (int j1 = 0; j1 < j; ++j1) {
            boolean flag1 = j1 < i;
            Recipe irecipe = flag1 ? list.get(j1) : (Recipe)list1.get(j1 - i);
            int k1 = this.G_564_y + 4 + 25 * (j1 % k);
            int l1 = this.P_1922_E + 5 + 25 * (j1 / k);
            if (this.s_956_w) {
                this.J_1907_R.add(new n_1700_B(this, k1, l1, irecipe, flag1));
                continue;
            }
            this.J_1907_R.add(new J_1907_R(k1, l1, irecipe, flag1));
        }
        this.w_1484_f = null;
    }

    @Override
    public boolean changeFocus(boolean focus) {
        return false;
    }

    public RecipeCollection n_1700_B() {
        return this.v_4262_N;
    }

    public Recipe<?> J_1907_R() {
        return this.w_1484_f;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        for (J_1907_R recipeoverlaygui$recipebuttonwidget : this.J_1907_R) {
            if (!recipeoverlaygui$recipebuttonwidget.mouseClicked(mouseX, mouseY, button)) continue;
            this.w_1484_f = recipeoverlaygui$recipebuttonwidget.R_4764_Y;
            return true;
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.R_4764_Y) {
            this.t_148_a += partialTicks;
            c_4037_x.Y_601_j();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.u_1723_Y.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(0.0f, 0.0f, 170.0f);
            int i = this.J_1907_R.size() <= 16 ? 4 : 5;
            int j = Math.min(this.J_1907_R.size(), i);
            int k = u_530_F.u_1723_Y((float)this.J_1907_R.size() / (float)i);
            int l = 24;
            int i1 = 4;
            int j1 = 82;
            int k1 = 208;
            this.n_1700_B(matrixStack, j, k, 24, 4, 82, 208);
            c_4037_x.Y_259_p();
            for (J_1907_R recipeoverlaygui$recipebuttonwidget : this.J_1907_R) {
                recipeoverlaygui$recipebuttonwidget.render(matrixStack, mouseX, mouseY, partialTicks);
            }
            c_4037_x.d_2461_k();
        }
    }

    private void n_1700_B(g_221_o p_238923_1_, int p_238923_2_, int p_238923_3_, int p_238923_4_, int p_238923_5_, int p_238923_6_, int p_238923_7_) {
        this.blit(p_238923_1_, this.G_564_y, this.P_1922_E, p_238923_6_, p_238923_7_, p_238923_5_, p_238923_5_);
        this.blit(p_238923_1_, this.G_564_y + p_238923_5_ * 2 + p_238923_2_ * p_238923_4_, this.P_1922_E, p_238923_6_ + p_238923_4_ + p_238923_5_, p_238923_7_, p_238923_5_, p_238923_5_);
        this.blit(p_238923_1_, this.G_564_y, this.P_1922_E + p_238923_5_ * 2 + p_238923_3_ * p_238923_4_, p_238923_6_, p_238923_7_ + p_238923_4_ + p_238923_5_, p_238923_5_, p_238923_5_);
        this.blit(p_238923_1_, this.G_564_y + p_238923_5_ * 2 + p_238923_2_ * p_238923_4_, this.P_1922_E + p_238923_5_ * 2 + p_238923_3_ * p_238923_4_, p_238923_6_ + p_238923_4_ + p_238923_5_, p_238923_7_ + p_238923_4_ + p_238923_5_, p_238923_5_, p_238923_5_);
        for (int i = 0; i < p_238923_2_; ++i) {
            this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + i * p_238923_4_, this.P_1922_E, p_238923_6_ + p_238923_5_, p_238923_7_, p_238923_4_, p_238923_5_);
            this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + (i + 1) * p_238923_4_, this.P_1922_E, p_238923_6_ + p_238923_5_, p_238923_7_, p_238923_5_, p_238923_5_);
            for (int j = 0; j < p_238923_3_; ++j) {
                if (i == 0) {
                    this.blit(p_238923_1_, this.G_564_y, this.P_1922_E + p_238923_5_ + j * p_238923_4_, p_238923_6_, p_238923_7_ + p_238923_5_, p_238923_5_, p_238923_4_);
                    this.blit(p_238923_1_, this.G_564_y, this.P_1922_E + p_238923_5_ + (j + 1) * p_238923_4_, p_238923_6_, p_238923_7_ + p_238923_5_, p_238923_5_, p_238923_5_);
                }
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + i * p_238923_4_, this.P_1922_E + p_238923_5_ + j * p_238923_4_, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_4_, p_238923_4_);
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + (i + 1) * p_238923_4_, this.P_1922_E + p_238923_5_ + j * p_238923_4_, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_5_, p_238923_4_);
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + i * p_238923_4_, this.P_1922_E + p_238923_5_ + (j + 1) * p_238923_4_, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_4_, p_238923_5_);
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + (i + 1) * p_238923_4_ - 1, this.P_1922_E + p_238923_5_ + (j + 1) * p_238923_4_ - 1, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_5_ + 1, p_238923_5_ + 1);
                if (i != p_238923_2_ - 1) continue;
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ * 2 + p_238923_2_ * p_238923_4_, this.P_1922_E + p_238923_5_ + j * p_238923_4_, p_238923_6_ + p_238923_4_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_5_, p_238923_4_);
                this.blit(p_238923_1_, this.G_564_y + p_238923_5_ * 2 + p_238923_2_ * p_238923_4_, this.P_1922_E + p_238923_5_ + (j + 1) * p_238923_4_, p_238923_6_ + p_238923_4_ + p_238923_5_, p_238923_7_ + p_238923_5_, p_238923_5_, p_238923_5_);
            }
            this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + i * p_238923_4_, this.P_1922_E + p_238923_5_ * 2 + p_238923_3_ * p_238923_4_, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_4_ + p_238923_5_, p_238923_4_, p_238923_5_);
            this.blit(p_238923_1_, this.G_564_y + p_238923_5_ + (i + 1) * p_238923_4_, this.P_1922_E + p_238923_5_ * 2 + p_238923_3_ * p_238923_4_, p_238923_6_ + p_238923_5_, p_238923_7_ + p_238923_4_ + p_238923_5_, p_238923_5_, p_238923_5_);
        }
    }

    public void n_1700_B(boolean p_192999_1_) {
        this.R_4764_Y = p_192999_1_;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    class n_1700_B
    extends J_1907_R {
        public n_1700_B(z_1806_z this$0, int p_i48747_2_, int p_i48747_3_, Recipe<?> p_i48747_4_, boolean p_i48747_5_) {
            super(p_i48747_2_, p_i48747_3_, p_i48747_4_, p_i48747_5_);
        }

        @Override
        protected void n_1700_B(Recipe<?> p_201505_1_) {
            Z_1993_T[] aitemstack = p_201505_1_.n_1700_B().get(0).n_1700_B();
            this.n_1700_B.add(new J_1907_R.n_1700_B(this, 10, 10, aitemstack));
        }
    }

    class J_1907_R
    extends V_2511_L
    implements PlaceRecipe<b_3278_X> {
        private final Recipe<?> R_4764_Y;
        private final boolean G_564_y;
        protected final List<n_1700_B> n_1700_B;

        public J_1907_R(int p_i47594_2_, int p_i47594_3_, Recipe<?> p_i47594_4_, boolean p_i47594_5_) {
            super(p_i47594_2_, p_i47594_3_, 200, 20, U_2871_b.R_4764_Y);
            this.n_1700_B = Lists.newArrayList();
            this.width = 24;
            this.height = 24;
            this.R_4764_Y = p_i47594_4_;
            this.G_564_y = p_i47594_5_;
            this.n_1700_B(p_i47594_4_);
        }

        protected void n_1700_B(Recipe<?> p_201505_1_) {
            this.n_1700_B(3, 3, -1, p_201505_1_, p_201505_1_.n_1700_B().iterator(), 0);
        }

        @Override
        public void n_1700_B(Iterator<b_3278_X> ingredients, int slotIn, int maxAmount, int y, int x) {
            Z_1993_T[] aitemstack = ingredients.next().n_1700_B();
            if (aitemstack.length != 0) {
                this.n_1700_B.add(new n_1700_B(this, 3 + x * 7, 3 + y * 7, aitemstack));
            }
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            int j;
            c_4037_x.M_588_G();
            z_1806_z.this.u_1723_Y.G_624_v().n_1700_B(n_1700_B);
            int i = 152;
            if (!this.G_564_y) {
                i += 26;
            }
            int n = j = z_1806_z.this.s_956_w ? 130 : 78;
            if (this.isHovered()) {
                j += 26;
            }
            this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
            for (n_1700_B recipeoverlaygui$recipebuttonwidget$child : this.n_1700_B) {
                c_4037_x.v_4276_D();
                float f = 0.42f;
                int k = (int)((float)(this.x + recipeoverlaygui$recipebuttonwidget$child.J_1907_R) / 0.42f - 3.0f);
                int l = (int)((float)(this.y + recipeoverlaygui$recipebuttonwidget$child.R_4764_Y) / 0.42f - 3.0f);
                c_4037_x.J_1907_R(0.42f, 0.42f, 1.0f);
                z_1806_z.this.u_1723_Y.r_715_M().J_1907_R(recipeoverlaygui$recipebuttonwidget$child.n_1700_B[u_530_F.G_564_y(z_1806_z.this.t_148_a / 30.0f) % recipeoverlaygui$recipebuttonwidget$child.n_1700_B.length], k, l);
                c_4037_x.d_2461_k();
            }
            c_4037_x.u_2550_I();
        }

        public class n_1700_B {
            public final Z_1993_T[] n_1700_B;
            public final int J_1907_R;
            public final int R_4764_Y;

            public n_1700_B(J_1907_R this$1, int p_i48748_2_, int p_i48748_3_, Z_1993_T[] p_i48748_4_) {
                this.J_1907_R = p_i48748_2_;
                this.R_4764_Y = p_i48748_3_;
                this.n_1700_B = p_i48748_4_;
            }
        }
    }
}



