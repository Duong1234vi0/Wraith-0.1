/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FormattedText;
import lightning.product.ImageButton;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.RecipeUpdateListener;
import lightning.product.a_408_T;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4436_c;
import lightning.product.k_902_f;
import lightning.product.AbstractFurnaceRecipeBookComponent;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;
import lightning.product.RecipeBookMenu;

public abstract class J_4924_i<T extends k_902_f>
extends z_3427_G<T>
implements RecipeUpdateListener {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/recipe_button.png");
    public final AbstractFurnaceRecipeBookComponent n_1700_B;
    private boolean R_4764_Y;
    private final g_2336_b G_564_y;

    public J_4924_i(T screenContainer, AbstractFurnaceRecipeBookComponent recipeGuiIn, W_3491_f inv, x_282_a titleIn, g_2336_b guiTextureIn) {
        super(screenContainer, inv, titleIn);
        this.n_1700_B = recipeGuiIn;
        this.G_564_y = guiTextureIn;
    }

    @Override
    public void init() {
        super.init();
        this.R_4764_Y = this.width < 379;
        this.n_1700_B.n_1700_B(this.width, this.height, this.minecraft, this.R_4764_Y, (RecipeBookMenu)this.Q_4569_t);
        this.multiplayerClientSuggestionProvider = this.n_1700_B.n_1700_B(this.R_4764_Y, this.width, this.t_148_a);
        this.addButton(new ImageButton(this.multiplayerClientSuggestionProvider + 20, this.height / 2 - 49, 20, 18, 0, 0, 19, J_1907_R, button -> {
            this.n_1700_B.n_1700_B(this.R_4764_Y);
            this.n_1700_B.P_1922_E();
            this.multiplayerClientSuggestionProvider = this.n_1700_B.n_1700_B(this.R_4764_Y, this.width, this.t_148_a);
            ((ImageButton)button).n_1700_B(this.multiplayerClientSuggestionProvider + 20, this.height / 2 - 49);
        }));
        this.u_2550_I = (this.t_148_a - this.font.n_1700_B((FormattedText)this.title)) / 2;
    }

    @Override
    public void tick() {
        super.tick();
        this.n_1700_B.v_4262_N();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.n_1700_B.u_1723_Y() && this.R_4764_Y) {
            this.n_1700_B(matrixStack, partialTicks, mouseX, mouseY);
            this.n_1700_B.render(matrixStack, mouseX, mouseY, partialTicks);
        } else {
            this.n_1700_B.render(matrixStack, mouseX, mouseY, partialTicks);
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            this.n_1700_B.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, true, partialTicks);
        }
        this.J_1907_R(matrixStack, mouseX, mouseY);
        this.n_1700_B.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(this.G_564_y);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        if (((k_902_f)this.Q_4569_t).w_1484_f()) {
            int k = ((k_902_f)this.Q_4569_t).v_4262_N();
            this.blit(matrixStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        int l = ((k_902_f)this.Q_4569_t).u_1723_Y();
        this.blit(matrixStack, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.n_1700_B.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return this.R_4764_Y && this.n_1700_B.u_1723_Y() ? true : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void n_1700_B(Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        super.n_1700_B(slotIn, slotId, mouseButton, type);
        this.n_1700_B.n_1700_B(slotIn);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.n_1700_B.keyPressed(keyCode, scanCode, modifiers) ? false : super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        boolean flag = mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
        return this.n_1700_B.n_1700_B(mouseX, mouseY, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.t_148_a, this.s_956_w, mouseButton) && flag;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return this.n_1700_B.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B.w_1484_f();
    }

    @Override
    public j_4436_c J_1907_R() {
        return this.n_1700_B;
    }

    @Override
    public void onClose() {
        this.n_1700_B.G_564_y();
        super.onClose();
    }
}


