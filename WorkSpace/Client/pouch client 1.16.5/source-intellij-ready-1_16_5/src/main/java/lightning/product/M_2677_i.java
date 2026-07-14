/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import java.util.Objects;
import lightning.product.C_2701_A;
import lightning.product.D_60_a;
import lightning.product.ObjectSelectionList;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.f_1043_S;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class M_2677_i
extends RealmsScreen {
    private final k_2603_m n_1700_B;
    private final D_60_a J_1907_R;
    private J_1907_R R_4764_Y;

    public M_2677_i(k_2603_m p_i232197_1_, D_60_a p_i232197_2_) {
        this.n_1700_B = p_i232197_1_;
        this.J_1907_R = p_i232197_2_;
    }

    @Override
    public void tick() {
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 24, 200, 20, CommonComponents.w_1484_f, p_237731_1_ -> this.minecraft.n_1700_B(this.n_1700_B)));
        this.R_4764_Y = new J_1907_R(this.minecraft);
        this.addListener(this.R_4764_Y);
        this.J_1907_R(this.R_4764_Y);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.n_1700_B);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        M_2677_i.drawCenteredString(matrixStack, this.font, "Changes from last backup", this.width / 2, 10, 0xFFFFFF);
        this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private x_282_a n_1700_B(String p_237733_1_, String p_237733_2_) {
        String s = p_237733_1_.toLowerCase(Locale.ROOT);
        if (s.contains("game") && s.contains("mode")) {
            return this.J_1907_R(p_237733_2_);
        }
        return s.contains("game") && s.contains("difficulty") ? this.n_1700_B(p_237733_2_) : new U_2871_b(p_237733_2_);
    }

    private x_282_a n_1700_B(String p_237732_1_) {
        try {
            return f_1043_S.n_1700_B[Integer.parseInt(p_237732_1_)];
        }
        catch (Exception exception) {
            return new U_2871_b("UNKNOWN");
        }
    }

    private x_282_a J_1907_R(String p_237735_1_) {
        try {
            return f_1043_S.J_1907_R[Integer.parseInt(p_237735_1_)];
        }
        catch (Exception exception) {
            return new U_2871_b("UNKNOWN");
        }
    }

    class J_1907_R
    extends ObjectSelectionList<n_1700_B> {
        public J_1907_R(MinecraftClient p_i232198_2_) {
            super(p_i232198_2_, M_2677_i.this.width, M_2677_i.this.height, 32, M_2677_i.this.height - 64, 36);
            this.setRenderSelection(false);
            if (M_2677_i.this.J_1907_R.P_1922_E != null) {
                M_2677_i.this.J_1907_R.P_1922_E.forEach((p_237736_1_, p_237736_2_) -> {
                    M_2677_i m_2677_i = M_2677_i.this;
                    Objects.requireNonNull(m_2677_i);
                    this.addEntry(m_2677_i.new n_1700_B((String)p_237736_1_, (String)p_237736_2_));
                });
            }
        }
    }

    class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B> {
        private final String J_1907_R;
        private final String R_4764_Y;

        public n_1700_B(String p_i232199_2_, String p_i232199_3_) {
            this.J_1907_R = p_i232199_2_;
            this.R_4764_Y = p_i232199_3_;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            Y_4083_F fontrenderer = ((M_2677_i)M_2677_i.this).minecraft.t_148_a;
            C_2701_A.drawString(p_230432_1_, fontrenderer, this.J_1907_R, p_230432_4_, p_230432_3_, 0xA0A0A0);
            C_2701_A.drawString(p_230432_1_, fontrenderer, M_2677_i.this.n_1700_B(this.J_1907_R, this.R_4764_Y), p_230432_4_, p_230432_3_ + 12, 0xFFFFFF);
        }
    }
}



