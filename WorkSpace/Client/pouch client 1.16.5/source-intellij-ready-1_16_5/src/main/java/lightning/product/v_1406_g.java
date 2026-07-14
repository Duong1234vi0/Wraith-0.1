/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3871_I;
import lightning.product.F_2904_S;
import lightning.product.AbstractButton;
import lightning.product.MobEffects;
import lightning.product.P_4526_H;
import lightning.product.NonNullList;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.ContainerListener;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.d_1428_k;
import lightning.product.BeaconMenu;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.Items;
import lightning.product.x_282_a;
import lightning.product.x_3974_Q;
import lightning.product.z_3427_G;

public class v_1406_g
extends z_3427_G<BeaconMenu> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/beacon.png");
    private static final x_282_a J_1907_R = new F_2904_S("block.minecraft.beacon.primary");
    private static final x_282_a R_4764_Y = new F_2904_S("block.minecraft.beacon.secondary");
    private R_4764_Y G_564_y;
    private boolean P_1922_E;
    private g_422_i u_1723_Y;
    private g_422_i v_4262_N;

    public v_1406_g(final BeaconMenu container, W_3491_f playerInventory, x_282_a title) {
        super(container, playerInventory, title);
        this.t_148_a = 230;
        this.s_956_w = 219;
        container.n_1700_B(new ContainerListener(){

            @Override
            public void n_1700_B(a_2900_S containerToSend, NonNullList<Z_1993_T> itemsList) {
            }

            @Override
            public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
            }

            @Override
            public void n_1700_B(a_2900_S containerIn, int varToUpdate, int newValue) {
                v_1406_g.this.u_1723_Y = container.J_1907_R();
                v_1406_g.this.v_4262_N = container.R_4764_Y();
                v_1406_g.this.P_1922_E = true;
            }
        });
    }

    @Override
    protected void init() {
        super.init();
        this.G_564_y = this.addButton(new R_4764_Y(this.multiplayerClientSuggestionProvider + 164, this.w_1457_N + 107));
        this.addButton(new J_1907_R(this.multiplayerClientSuggestionProvider + 190, this.w_1457_N + 107));
        this.P_1922_E = true;
        this.G_564_y.active = false;
    }

    @Override
    public void tick() {
        super.tick();
        int i = ((BeaconMenu)this.Q_4569_t).n_1700_B();
        if (this.P_1922_E && i >= 0) {
            this.P_1922_E = false;
            for (int j = 0; j <= 2; ++j) {
                int k = x_3974_Q.n_1700_B[j].length;
                int l = k * 22 + (k - 1) * 2;
                for (int i1 = 0; i1 < k; ++i1) {
                    g_422_i effect = x_3974_Q.n_1700_B[j][i1];
                    G_564_y beaconscreen$powerbutton = new G_564_y(this.multiplayerClientSuggestionProvider + 76 + i1 * 24 - l / 2, this.w_1457_N + 22 + j * 25, effect, true);
                    this.addButton(beaconscreen$powerbutton);
                    if (j >= i) {
                        beaconscreen$powerbutton.active = false;
                        continue;
                    }
                    if (effect != this.u_1723_Y) continue;
                    beaconscreen$powerbutton.n_1700_B(true);
                }
            }
            int j1 = 3;
            int k1 = x_3974_Q.n_1700_B[3].length + 1;
            int l1 = k1 * 22 + (k1 - 1) * 2;
            for (int i2 = 0; i2 < k1 - 1; ++i2) {
                g_422_i effect1 = x_3974_Q.n_1700_B[3][i2];
                G_564_y beaconscreen$powerbutton2 = new G_564_y(this.multiplayerClientSuggestionProvider + 167 + i2 * 24 - l1 / 2, this.w_1457_N + 47, effect1, false);
                this.addButton(beaconscreen$powerbutton2);
                if (3 >= i) {
                    beaconscreen$powerbutton2.active = false;
                    continue;
                }
                if (effect1 != this.v_4262_N) continue;
                beaconscreen$powerbutton2.n_1700_B(true);
            }
            if (this.u_1723_Y != null) {
                G_564_y beaconscreen$powerbutton1 = new G_564_y(this.multiplayerClientSuggestionProvider + 167 + (k1 - 1) * 24 - l1 / 2, this.w_1457_N + 47, this.u_1723_Y, false);
                this.addButton(beaconscreen$powerbutton1);
                if (3 >= i) {
                    beaconscreen$powerbutton1.active = false;
                } else if (this.u_1723_Y == this.v_4262_N) {
                    beaconscreen$powerbutton1.n_1700_B(true);
                }
            }
        }
        this.G_564_y.active = ((BeaconMenu)this.Q_4569_t).G_564_y() && this.u_1723_Y != null;
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        v_1406_g.drawCenteredString(matrixStack, this.font, J_1907_R, 62, 10, 0xE0E0E0);
        v_1406_g.drawCenteredString(matrixStack, this.font, R_4764_Y, 169, 10, 0xE0E0E0);
        for (V_2511_L widget : this.buttons) {
            if (!widget.isHovered()) continue;
            widget.renderToolTip(matrixStack, x - this.multiplayerClientSuggestionProvider, y - this.w_1457_N);
            break;
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        this.itemRenderer.J_1907_R = 100.0f;
        this.itemRenderer.J_1907_R(new Z_1993_T(Items.q_4124_m), i + 20, j + 109);
        this.itemRenderer.J_1907_R(new Z_1993_T(Items.Y_2905_A), i + 41, j + 109);
        this.itemRenderer.J_1907_R(new Z_1993_T(Items.k_2273_q), i + 41 + 22, j + 109);
        this.itemRenderer.J_1907_R(new Z_1993_T(Items.ServerHandshakePacketListener), i + 42 + 44, j + 109);
        this.itemRenderer.J_1907_R(new Z_1993_T(Items.D_1621_L), i + 42 + 66, j + 109);
        this.itemRenderer.J_1907_R = 0.0f;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    class R_4764_Y
    extends P_1922_E {
        public R_4764_Y(int x, int y) {
            super(x, y, 90, 220);
        }

        @Override
        public void onPress() {
            v_1406_g.this.minecraft.k_2293_S().n_1700_B(new d_1428_k(g_422_i.n_1700_B(v_1406_g.this.u_1723_Y), g_422_i.n_1700_B(v_1406_g.this.v_4262_N)));
            ((v_1406_g)v_1406_g.this).minecraft.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(((v_1406_g)v_1406_g.this).minecraft.Y_259_p.H_1873_g.u_1723_Y));
            v_1406_g.this.minecraft.n_1700_B((k_2603_m)null);
        }

        @Override
        public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
            v_1406_g.this.renderTooltip(matrixStack, CommonComponents.R_4764_Y, mouseX, mouseY);
        }
    }

    class J_1907_R
    extends P_1922_E {
        public J_1907_R(int x, int y) {
            super(x, y, 112, 220);
        }

        @Override
        public void onPress() {
            ((v_1406_g)v_1406_g.this).minecraft.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(((v_1406_g)v_1406_g.this).minecraft.Y_259_p.H_1873_g.u_1723_Y));
            v_1406_g.this.minecraft.n_1700_B((k_2603_m)null);
        }

        @Override
        public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
            v_1406_g.this.renderTooltip(matrixStack, CommonComponents.G_564_y, mouseX, mouseY);
        }
    }

    class G_564_y
    extends n_1700_B {
        private final g_422_i J_1907_R;
        private final B_3871_I R_4764_Y;
        private final boolean G_564_y;
        private final x_282_a P_1922_E;

        public G_564_y(int x, int y, g_422_i p_i50827_4_, boolean p_i50827_5_) {
            super(x, y);
            this.J_1907_R = p_i50827_4_;
            this.R_4764_Y = MinecraftClient.A_4115_X().V_1446_Y().n_1700_B(p_i50827_4_);
            this.G_564_y = p_i50827_5_;
            this.P_1922_E = this.n_1700_B(p_i50827_4_, p_i50827_5_);
        }

        private x_282_a n_1700_B(g_422_i p_243337_1_, boolean p_243337_2_) {
            F_2904_S iformattabletextcomponent = new F_2904_S(p_243337_1_.R_4764_Y());
            if (!p_243337_2_ && p_243337_1_ != MobEffects.s_956_w) {
                iformattabletextcomponent.n_1700_B(" II");
            }
            return iformattabletextcomponent;
        }

        @Override
        public void onPress() {
            if (!this.n_1700_B()) {
                if (this.G_564_y) {
                    v_1406_g.this.u_1723_Y = this.J_1907_R;
                } else {
                    v_1406_g.this.v_4262_N = this.J_1907_R;
                }
                v_1406_g.this.buttons.clear();
                v_1406_g.this.children.clear();
                v_1406_g.this.init();
                v_1406_g.this.tick();
            }
        }

        @Override
        public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
            v_1406_g.this.renderTooltip(matrixStack, this.P_1922_E, mouseX, mouseY);
        }

        @Override
        protected void n_1700_B(g_221_o p_230454_1_) {
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(this.R_4764_Y.u_2550_I().R_4764_Y());
            lightning.product.v_1406_g$G_564_y.blit(p_230454_1_, this.x + 2, this.y + 2, this.getBlitOffset(), 18, 18, this.R_4764_Y);
        }
    }

    static abstract class P_1922_E
    extends n_1700_B {
        private final int n_1700_B;
        private final int J_1907_R;

        protected P_1922_E(int x, int y, int u, int v) {
            super(x, y);
            this.n_1700_B = u;
            this.J_1907_R = v;
        }

        @Override
        protected void n_1700_B(g_221_o p_230454_1_) {
            this.blit(p_230454_1_, this.x + 2, this.y + 2, this.n_1700_B, this.J_1907_R, 18, 18);
        }
    }

    static abstract class n_1700_B
    extends AbstractButton {
        private boolean n_1700_B;

        protected n_1700_B(int x, int y) {
            super(x, y, 22, 22, U_2871_b.R_4764_Y);
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(n_1700_B);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int i = 219;
            int j = 0;
            if (!this.active) {
                j += this.width * 2;
            } else if (this.n_1700_B) {
                j += this.width * 1;
            } else if (this.isHovered()) {
                j += this.width * 3;
            }
            this.blit(matrixStack, this.x, this.y, j, 219, this.width, this.height);
            this.n_1700_B(matrixStack);
        }

        protected abstract void n_1700_B(g_221_o var1);

        public boolean n_1700_B() {
            return this.n_1700_B;
        }

        public void n_1700_B(boolean selectedIn) {
            this.n_1700_B = selectedIn;
        }
    }
}



