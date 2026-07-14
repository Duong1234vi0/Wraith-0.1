/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.ItemCombinerScreen;
import lightning.product.O_694_j;
import lightning.product.P_1520_s;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.AnvilMenu;
import lightning.product.a_2900_S;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;

public class AnvilScreen
extends ItemCombinerScreen<AnvilMenu> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/anvil.png");
    private static final x_282_a J_1907_R = new F_2904_S("container.repair.expensive");
    private O_694_j R_4764_Y;

    public AnvilScreen(AnvilMenu container, W_3491_f playerInventory, x_282_a title) {
        super(container, playerInventory, title, n_1700_B);
        this.u_2550_I = 60;
    }

    @Override
    public void tick() {
        super.tick();
        this.R_4764_Y.tick();
    }

    @Override
    protected void n_1700_B() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.R_4764_Y = new O_694_j(this.font, i + 62, j + 24, 103, 12, new F_2904_S("container.repair"));
        this.R_4764_Y.setCanLoseFocus(false);
        this.R_4764_Y.setTextColor(-1);
        this.R_4764_Y.setDisabledTextColour(-1);
        this.R_4764_Y.setEnableBackgroundDrawing(false);
        this.R_4764_Y.setMaxStringLength(35);
        this.R_4764_Y.setResponder(this::n_1700_B);
        this.children.add(this.R_4764_Y);
        this.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.R_4764_Y.getText();
        this.init(minecraft, width, height);
        this.R_4764_Y.setText(s);
    }

    @Override
    public void onClose() {
        super.onClose();
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.Y_259_p.P_1922_E();
        }
        return !this.R_4764_Y.keyPressed(keyCode, scanCode, modifiers) && !this.R_4764_Y.canWrite() ? super.keyPressed(keyCode, scanCode, modifiers) : true;
    }

    private void n_1700_B(String name) {
        if (!name.isEmpty()) {
            String s = name;
            Slot slot = ((AnvilMenu)this.Q_4569_t).n_1700_B(0);
            if (slot != null && slot.J_1907_R() && !slot.n_1700_B().Y_601_j() && name.equals(slot.n_1700_B().multiplayerClientSuggestionProvider().getString())) {
                s = "";
            }
            ((AnvilMenu)this.Q_4569_t).n_1700_B(s);
            this.minecraft.Y_259_p.n_1700_B.n_1700_B(new P_1520_s(s));
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        c_4037_x.Y_259_p();
        super.n_1700_B(matrixStack, x, y);
        int i = ((AnvilMenu)this.Q_4569_t).J_1907_R();
        if (i > 0) {
            x_282_a itextcomponent;
            int j = 8453920;
            if (i >= 40 && !this.minecraft.Y_259_p.C_415_h.G_564_y) {
                itextcomponent = J_1907_R;
                j = 0xFF6060;
            } else if (!((AnvilMenu)this.Q_4569_t).n_1700_B(2).J_1907_R()) {
                itextcomponent = null;
            } else {
                itextcomponent = new F_2904_S("container.repair.cost", i);
                if (!((AnvilMenu)this.Q_4569_t).n_1700_B(2).n_1700_B(this.M_182_A.P_1922_E)) {
                    j = 0xFF6060;
                }
            }
            if (itextcomponent != null) {
                int k = this.t_148_a - 8 - this.font.n_1700_B((FormattedText)itextcomponent) - 2;
                int l = 69;
                AnvilScreen.fill(matrixStack, k - 2, 67, this.t_148_a - 8, 79, 0x4F000000);
                this.font.n_1700_B(matrixStack, itextcomponent, (float)k, 69.0f, j);
            }
        }
    }

    @Override
    public void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
        if (slotInd == 0) {
            this.R_4764_Y.setText(stack.n_1700_B() ? "" : stack.multiplayerClientSuggestionProvider().getString());
            this.R_4764_Y.setEnabled(!stack.n_1700_B());
            this.setListener(this.R_4764_Y);
        }
    }
}



