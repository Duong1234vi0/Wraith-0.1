/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.T_3952_j;
import lightning.product.Button;
import lightning.product.W_2853_p;
import lightning.product.h_4412_P;

public class InBedChatScreen
extends h_4412_P {
    public InBedChatScreen() {
        super("");
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 100, this.height - 40, 200, 20, new F_2904_S("multiplayer.stopSleeping"), p_212998_1_ -> this.n_1700_B()));
    }

    @Override
    public void closeScreen() {
        this.n_1700_B();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.n_1700_B();
        } else if (keyCode == 257 || keyCode == 335) {
            String s = this.inputField.getText().trim();
            if (!s.isEmpty()) {
                this.sendMessage(s);
            }
            this.inputField.setText("");
            this.minecraft.M_588_G.R_4764_Y().R_4764_Y();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void n_1700_B() {
        W_2853_p clientplaynethandler = this.minecraft.Y_259_p.n_1700_B;
        clientplaynethandler.n_1700_B(new T_3952_j(this.minecraft.Y_259_p, T_3952_j.n_1700_B.R_4764_Y));
    }
}


