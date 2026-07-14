/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.W_3464_O;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.s_1671_u;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J_4256_G
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("mco.configure.world.invite.profile.name");
    private static final x_282_a R_4764_Y = new F_2904_S("mco.configure.world.players.error");
    private O_694_j G_564_y;
    private final q_1982_R P_1922_E;
    private final W_3464_O u_1723_Y;
    private final k_2603_m v_4262_N;
    @Nullable
    private x_282_a w_1484_f;

    public J_4256_G(W_3464_O p_i232207_1_, k_2603_m p_i232207_2_, q_1982_R p_i232207_3_) {
        this.u_1723_Y = p_i232207_1_;
        this.v_4262_N = p_i232207_2_;
        this.P_1922_E = p_i232207_3_;
    }

    @Override
    public void tick() {
        this.G_564_y.tick();
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.G_564_y = new O_694_j(this.minecraft.t_148_a, this.width / 2 - 100, J_4256_G.G_564_y(2), 200, 20, null, new F_2904_S("mco.configure.world.invite.profile.name"));
        this.addListener(this.G_564_y);
        this.n_1700_B(this.G_564_y);
        this.addButton(new Button(this.width / 2 - 100, J_4256_G.G_564_y(10), 200, 20, new F_2904_S("mco.configure.world.buttons.invite"), p_237844_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 - 100, J_4256_G.G_564_y(12), 200, 20, CommonComponents.G_564_y, p_237843_1_ -> this.minecraft.n_1700_B(this.v_4262_N)));
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void n_1700_B() {
        block5: {
            p_178_J realmsclient = p_178_J.n_1700_B();
            if (this.G_564_y.getText() != null && !this.G_564_y.getText().isEmpty()) {
                try {
                    q_1982_R realmsserver = realmsclient.J_1907_R(this.P_1922_E.n_1700_B, this.G_564_y.getText().trim());
                    if (realmsserver != null) {
                        this.P_1922_E.w_1484_f = realmsserver.w_1484_f;
                        this.minecraft.n_1700_B(new s_1671_u(this.u_1723_Y, this.P_1922_E));
                        break block5;
                    }
                    this.n_1700_B(R_4764_Y);
                }
                catch (Exception exception) {
                    n_1700_B.error("Couldn't invite user");
                    this.n_1700_B(R_4764_Y);
                }
            } else {
                this.n_1700_B(R_4764_Y);
            }
        }
    }

    private void n_1700_B(x_282_a p_224209_1_) {
        this.w_1484_f = p_224209_1_;
        NarrationHelper.n_1700_B(p_224209_1_.getString());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.v_4262_N);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.font.J_1907_R(matrixStack, J_1907_R, (float)(this.width / 2 - 100), (float)J_4256_G.G_564_y(1), 0xA0A0A0);
        if (this.w_1484_f != null) {
            J_4256_G.drawCenteredString(matrixStack, this.font, this.w_1484_f, this.width / 2, J_4256_G.G_564_y(5), 0xFF0000);
        }
        this.G_564_y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


