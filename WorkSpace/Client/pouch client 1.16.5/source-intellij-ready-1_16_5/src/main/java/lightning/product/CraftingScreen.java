/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingMenu;
import lightning.product.ImageButton;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.RecipeUpdateListener;
import lightning.product.a_408_T;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4436_c;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;
import lightning.product.RecipeBookMenu;

public class CraftingScreen
extends z_3427_G<CraftingMenu>
implements RecipeUpdateListener {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/crafting_table.png");
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/recipe_button.png");
    private final j_4436_c R_4764_Y = new j_4436_c();
    private boolean G_564_y;

    public CraftingScreen(CraftingMenu screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.G_564_y = this.width < 379;
        this.R_4764_Y.n_1700_B(this.width, this.height, this.minecraft, this.G_564_y, (RecipeBookMenu)this.Q_4569_t);
        this.multiplayerClientSuggestionProvider = this.R_4764_Y.n_1700_B(this.G_564_y, this.width, this.t_148_a);
        this.children.add(this.R_4764_Y);
        this.n_1700_B(this.R_4764_Y);
        this.addButton(new ImageButton(this.multiplayerClientSuggestionProvider + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, J_1907_R, button -> {
            this.R_4764_Y.n_1700_B(this.G_564_y);
            this.R_4764_Y.P_1922_E();
            this.multiplayerClientSuggestionProvider = this.R_4764_Y.n_1700_B(this.G_564_y, this.width, this.t_148_a);
            ((ImageButton)button).n_1700_B(this.multiplayerClientSuggestionProvider + 5, this.height / 2 - 49);
        }));
        this.u_2550_I = 29;
    }

    @Override
    public void tick() {
        super.tick();
        this.R_4764_Y.v_4262_N();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.R_4764_Y.u_1723_Y() && this.G_564_y) {
            this.n_1700_B(matrixStack, partialTicks, mouseX, mouseY);
            this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        } else {
            this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            this.R_4764_Y.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, true, partialTicks);
        }
        this.J_1907_R(matrixStack, mouseX, mouseY);
        this.R_4764_Y.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = this.multiplayerClientSuggestionProvider;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
    }

    @Override
    protected boolean n_1700_B(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.G_564_y || !this.R_4764_Y.u_1723_Y()) && super.n_1700_B(x, y, width, height, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.R_4764_Y.mouseClicked(mouseX, mouseY, button)) {
            this.setListener(this.R_4764_Y);
            return true;
        }
        return this.G_564_y && this.R_4764_Y.u_1723_Y() ? true : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        boolean flag = mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
        return this.R_4764_Y.n_1700_B(mouseX, mouseY, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.t_148_a, this.s_956_w, mouseButton) && flag;
    }

    @Override
    protected void n_1700_B(Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        super.n_1700_B(slotIn, slotId, mouseButton, type);
        this.R_4764_Y.n_1700_B(slotIn);
    }

    @Override
    public void n_1700_B() {
        this.R_4764_Y.w_1484_f();
    }

    @Override
    public void onClose() {
        this.R_4764_Y.G_564_y();
        super.onClose();
    }

    @Override
    public j_4436_c J_1907_R() {
        return this.R_4764_Y;
    }
}


