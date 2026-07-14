/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.I_1084_e;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.K_4074_S;
import lightning.product.T_3851_R;
import lightning.product.Button;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_4037_x;
import lightning.product.c_4892_C;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.o_1792_J;
import lightning.product.o_2488_o;
import lightning.product.q_1613_l;
import lightning.product.CommonComponents;
import lightning.product.Items;
import lightning.product.x_282_a;
import lightning.product.z_2376_a;

public class G_212_i
extends k_2603_m {
    protected final o_1792_J n_1700_B;
    private final Consumer<z_2376_a> J_1907_R;
    private z_2376_a R_4764_Y;
    private x_282_a G_564_y;
    private x_282_a P_1922_E;
    private n_1700_B u_1723_Y;
    private Button v_4262_N;

    public G_212_i(o_1792_J p_i242055_1_, Consumer<z_2376_a> p_i242055_2_, z_2376_a p_i242055_3_) {
        super(new F_2904_S("createWorld.customize.flat.title"));
        this.n_1700_B = p_i242055_1_;
        this.J_1907_R = p_i242055_2_;
        this.R_4764_Y = p_i242055_3_;
    }

    public z_2376_a n_1700_B() {
        return this.R_4764_Y;
    }

    public void n_1700_B(z_2376_a p_238602_1_) {
        this.R_4764_Y = p_238602_1_;
    }

    @Override
    protected void init() {
        this.G_564_y = new F_2904_S("createWorld.customize.flat.tile");
        this.P_1922_E = new F_2904_S("createWorld.customize.flat.height");
        this.u_1723_Y = new n_1700_B();
        this.children.add(this.u_1723_Y);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 155, this.height - 52, 150, 20, new F_2904_S("createWorld.customize.flat.removeLayer"), p_213007_1_ -> {
            if (this.R_4764_Y()) {
                List<T_3851_R> list = this.R_4764_Y.u_1723_Y();
                int i = this.u_1723_Y.getEventListeners().indexOf(this.u_1723_Y.getSelected());
                int j = list.size() - i - 1;
                list.remove(j);
                this.u_1723_Y.n_1700_B(list.isEmpty() ? null : (n_1700_B.n_1700_B)this.u_1723_Y.getEventListeners().get(Math.min(i, list.size() - 1)));
                this.R_4764_Y.w_1484_f();
                this.u_1723_Y.n_1700_B();
                this.J_1907_R();
            }
        }));
        this.addButton(new Button(this.width / 2 + 5, this.height - 52, 150, 20, new F_2904_S("createWorld.customize.presets"), p_213011_1_ -> {
            this.minecraft.n_1700_B(new c_4892_C(this));
            this.R_4764_Y.w_1484_f();
            this.J_1907_R();
        }));
        this.addButton(new Button(this.width / 2 - 155, this.height - 28, 150, 20, CommonComponents.R_4764_Y, p_213010_1_ -> {
            this.J_1907_R.accept(this.R_4764_Y);
            this.minecraft.n_1700_B(this.n_1700_B);
            this.R_4764_Y.w_1484_f();
        }));
        this.addButton(new Button(this.width / 2 + 5, this.height - 28, 150, 20, CommonComponents.G_564_y, p_213009_1_ -> {
            this.minecraft.n_1700_B(this.n_1700_B);
            this.R_4764_Y.w_1484_f();
        }));
        this.R_4764_Y.w_1484_f();
        this.J_1907_R();
    }

    private void J_1907_R() {
        this.v_4262_N.active = this.R_4764_Y();
    }

    private boolean R_4764_Y() {
        return this.u_1723_Y.getSelected() != null;
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.n_1700_B);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        G_212_i.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        int i = this.width / 2 - 92 - 16;
        G_212_i.drawString(matrixStack, this.font, this.G_564_y, i, 32, 0xFFFFFF);
        G_212_i.drawString(matrixStack, this.font, this.P_1922_E, i + 2 + 213 - this.font.n_1700_B((FormattedText)this.P_1922_E), 32, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    class lightning.product.G_212_i$n_1700_B
    extends ObjectSelectionList<n_1700_B> {
        public lightning.product.G_212_i$n_1700_B() {
            super(G_212_i.this.minecraft, G_212_i.this.width, G_212_i.this.height, 43, G_212_i.this.height - 60, 24);
            for (int i = 0; i < G_212_i.this.R_4764_Y.u_1723_Y().size(); ++i) {
                this.addEntry(new n_1700_B());
            }
        }

        public void n_1700_B(@Nullable n_1700_B entry) {
            T_3851_R flatlayerinfo;
            q_1613_l item;
            super.setSelected(entry);
            if (entry != null && (item = (flatlayerinfo = G_212_i.this.R_4764_Y.u_1723_Y().get(G_212_i.this.R_4764_Y.u_1723_Y().size() - this.getEventListeners().indexOf(entry) - 1)).J_1907_R().J_1907_R().u_1723_Y()) != Items.n_1700_B) {
                I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", item.w_1484_f(new Z_1993_T(item))).getString());
            }
            G_212_i.this.J_1907_R();
        }

        @Override
        protected boolean isFocused() {
            return G_212_i.this.getListener() == this;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width - 70;
        }

        public void n_1700_B() {
            int i = this.getEventListeners().indexOf(this.getSelected());
            this.clearEntries();
            for (int j = 0; j < G_212_i.this.R_4764_Y.u_1723_Y().size(); ++j) {
                this.addEntry(new n_1700_B());
            }
            List list = this.getEventListeners();
            if (i >= 0 && i < list.size()) {
                this.n_1700_B((n_1700_B)list.get(i));
            }
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.n_1700_B((n_1700_B)n_1700_B2);
        }

        class n_1700_B
        extends ObjectSelectionList.n_1700_B<n_1700_B> {
            private n_1700_B() {
            }

            @Override
            public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
                T_3851_R flatlayerinfo = G_212_i.this.R_4764_Y.u_1723_Y().get(G_212_i.this.R_4764_Y.u_1723_Y().size() - p_230432_2_ - 1);
                K_4074_S blockstate = flatlayerinfo.J_1907_R();
                q_1613_l item = blockstate.J_1907_R().u_1723_Y();
                if (item == Items.n_1700_B) {
                    if (blockstate.n_1700_B(a_3742_W.c_3005_b)) {
                        item = Items.W_2770_z;
                    } else if (blockstate.n_1700_B(a_3742_W.H_2857_Y)) {
                        item = Items.u_1934_K;
                    }
                }
                Z_1993_T itemstack = new Z_1993_T(item);
                this.n_1700_B(p_230432_1_, p_230432_4_, p_230432_3_, itemstack);
                G_212_i.this.font.J_1907_R(p_230432_1_, item.w_1484_f(itemstack), (float)(p_230432_4_ + 18 + 5), (float)(p_230432_3_ + 3), 0xFFFFFF);
                String s = p_230432_2_ == 0 ? K_1289_S.n_1700_B("createWorld.customize.flat.layer.top", flatlayerinfo.n_1700_B()) : (p_230432_2_ == G_212_i.this.R_4764_Y.u_1723_Y().size() - 1 ? K_1289_S.n_1700_B("createWorld.customize.flat.layer.bottom", flatlayerinfo.n_1700_B()) : K_1289_S.n_1700_B("createWorld.customize.flat.layer", flatlayerinfo.n_1700_B()));
                G_212_i.this.font.J_1907_R(p_230432_1_, s, (float)(p_230432_4_ + 2 + 213 - G_212_i.this.font.J_1907_R(s)), (float)(p_230432_3_ + 3), 0xFFFFFF);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                if (button == 0) {
                    n_1700_B.this.n_1700_B(this);
                    return true;
                }
                return false;
            }

            private void n_1700_B(g_221_o p_238605_1_, int p_238605_2_, int p_238605_3_, Z_1993_T p_238605_4_) {
                this.n_1700_B(p_238605_1_, p_238605_2_ + 1, p_238605_3_ + 1);
                c_4037_x.n_3318_d();
                if (!p_238605_4_.n_1700_B()) {
                    G_212_i.this.itemRenderer.n_1700_B(p_238605_4_, p_238605_2_ + 2, p_238605_3_ + 2);
                }
                c_4037_x.d_2427_y();
            }

            private void n_1700_B(g_221_o p_238604_1_, int p_238604_2_, int p_238604_3_) {
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                n_1700_B.this.minecraft.G_624_v().n_1700_B(C_2701_A.STATS_ICON_LOCATION);
                C_2701_A.blit(p_238604_1_, p_238604_2_, p_238604_3_, G_212_i.this.getBlitOffset(), 0.0f, 0.0f, 18, 18, 128, 128);
            }
        }
    }
}


