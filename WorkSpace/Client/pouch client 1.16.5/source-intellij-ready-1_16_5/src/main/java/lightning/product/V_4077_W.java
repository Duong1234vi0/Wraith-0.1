/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.ObjectSelectionList;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.Button;
import lightning.product.f_2392_k;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.k_594_Q;
import lightning.product.l_4033_W;
import lightning.product.WritableRegistry;
import lightning.product.o_2488_o;
import lightning.product.CommonComponents;
import lightning.product.r_4097_j;
import lightning.product.x_282_a;

public class V_4077_W
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("createWorld.customize.buffet.biome");
    private final k_2603_m J_1907_R;
    private final Consumer<k_594_Q> R_4764_Y;
    private final WritableRegistry<k_594_Q> G_564_y;
    private n_1700_B P_1922_E;
    private k_594_Q u_1723_Y;
    private Button v_4262_N;

    public V_4077_W(k_2603_m p_i242054_1_, r_4097_j p_i242054_2_, Consumer<k_594_Q> p_i242054_3_, k_594_Q p_i242054_4_) {
        super(new F_2904_S("createWorld.customize.buffet.title"));
        this.J_1907_R = p_i242054_1_;
        this.R_4764_Y = p_i242054_3_;
        this.u_1723_Y = p_i242054_4_;
        this.G_564_y = p_i242054_2_.J_1907_R(V_3137_a.PlayerInfo);
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.J_1907_R);
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.P_1922_E = new n_1700_B();
        this.children.add(this.P_1922_E);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 155, this.height - 28, 150, 20, CommonComponents.R_4764_Y, p_241579_1_ -> {
            this.R_4764_Y.accept(this.u_1723_Y);
            this.minecraft.n_1700_B(this.J_1907_R);
        }));
        this.addButton(new Button(this.width / 2 + 5, this.height - 28, 150, 20, CommonComponents.G_564_y, p_213015_1_ -> this.minecraft.n_1700_B(this.J_1907_R)));
        this.P_1922_E.n_1700_B(this.P_1922_E.getEventListeners().stream().filter(p_241578_1_ -> Objects.equals(p_241578_1_.J_1907_R, this.u_1723_Y)).findFirst().orElse(null));
    }

    private void n_1700_B() {
        this.v_4262_N.active = this.P_1922_E.getSelected() != null;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        V_4077_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        V_4077_W.drawCenteredString(matrixStack, this.font, n_1700_B, this.width / 2, 28, 0xA0A0A0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    class lightning.product.V_4077_W$n_1700_B
    extends ObjectSelectionList<n_1700_B> {
        private lightning.product.V_4077_W$n_1700_B() {
            super(V_4077_W.this.minecraft, V_4077_W.this.width, V_4077_W.this.height, 40, V_4077_W.this.height - 37, 16);
            V_4077_W.this.G_564_y.P_1922_E().stream().sorted(Comparator.comparing(p_238598_0_ -> ((f_2392_k)p_238598_0_.getKey()).n_1700_B().toString())).forEach(p_238597_1_ -> this.addEntry(new n_1700_B((k_594_Q)p_238597_1_.getValue())));
        }

        @Override
        protected boolean isFocused() {
            return V_4077_W.this.getListener() == this;
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            super.setSelected(entry);
            if (entry != null) {
                V_4077_W.this.u_1723_Y = entry.J_1907_R;
                I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", V_4077_W.this.G_564_y.J_1907_R(entry.J_1907_R)).getString());
            }
            V_4077_W.this.n_1700_B();
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }

        class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            private final k_594_Q J_1907_R;
            private final x_282_a R_4764_Y;

            public n_1700_B(k_594_Q p_i232272_2_) {
                this.J_1907_R = p_i232272_2_;
                g_2336_b resourcelocation = V_4077_W.this.G_564_y.J_1907_R(p_i232272_2_);
                String s = "biome." + resourcelocation.R_4764_Y() + "." + resourcelocation.J_1907_R();
                this.R_4764_Y = l_4033_W.R_4764_Y().J_1907_R(s) ? new F_2904_S(s) : new U_2871_b(resourcelocation.toString());
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                C_2701_A.drawString(p_230432_1_, V_4077_W.this.font, this.R_4764_Y, p_230432_4_ + 5, p_230432_3_ + 2, 0xFFFFFF);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                if (button == 0) {
                    n_1700_B.this.n_1700_B(this);
                    return true;
                }
                return false;
            }
        }
    }
}


