/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.T_1368_k;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.d_742_e;
import lightning.product.r_2555_q;
import lightning.product.ServerboundSetCommandBlockPacket;

public class CommandBlockEditScreen
extends r_2555_q {
    private final T_1368_k v_4262_N;
    private Button w_1484_f;
    private Button t_148_a;
    private Button s_956_w;
    private T_1368_k.n_1700_B u_2550_I = T_1368_k.n_1700_B.R_4764_Y;
    private boolean M_588_G;
    private boolean P_4830_p;

    public CommandBlockEditScreen(T_1368_k commandBlockIn) {
        this.v_4262_N = commandBlockIn;
    }

    @Override
    d_742_e n_1700_B() {
        return this.v_4262_N.P_1922_E();
    }

    @Override
    int J_1907_R() {
        return 135;
    }

    @Override
    protected void init() {
        super.init();
        this.w_1484_f = this.addButton(new Button(this.width / 2 - 50 - 100 - 4, 165, 100, 20, new F_2904_S("advMode.mode.sequence"), p_214191_1_ -> {
            this.v_4262_N();
            this.u_1723_Y();
        }));
        this.t_148_a = this.addButton(new Button(this.width / 2 - 50, 165, 100, 20, new F_2904_S("advMode.mode.unconditional"), p_214190_1_ -> {
            this.M_588_G = !this.M_588_G;
            this.w_1484_f();
        }));
        this.s_956_w = this.addButton(new Button(this.width / 2 + 50 + 4, 165, 100, 20, new F_2904_S("advMode.mode.redstoneTriggered"), p_214189_1_ -> {
            this.P_4830_p = !this.P_4830_p;
            this.t_148_a();
        }));
        this.R_4764_Y.active = false;
        this.P_1922_E.active = false;
        this.w_1484_f.active = false;
        this.t_148_a.active = false;
        this.s_956_w.active = false;
    }

    public void P_1922_E() {
        d_742_e commandblocklogic = this.v_4262_N.P_1922_E();
        this.n_1700_B.setText(commandblocklogic.w_1484_f());
        this.u_1723_Y = commandblocklogic.s_956_w();
        this.u_2550_I = this.v_4262_N.h_1847_R();
        this.M_588_G = this.v_4262_N.Q_4569_t();
        this.P_4830_p = this.v_4262_N.w_1484_f();
        this.R_4764_Y();
        this.u_1723_Y();
        this.w_1484_f();
        this.t_148_a();
        this.R_4764_Y.active = true;
        this.P_1922_E.active = true;
        this.w_1484_f.active = true;
        this.t_148_a.active = true;
        this.s_956_w.active = true;
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        super.resize(minecraft, width, height);
        this.R_4764_Y();
        this.u_1723_Y();
        this.w_1484_f();
        this.t_148_a();
        this.R_4764_Y.active = true;
        this.P_1922_E.active = true;
        this.w_1484_f.active = true;
        this.t_148_a.active = true;
        this.s_956_w.active = true;
    }

    @Override
    protected void n_1700_B(d_742_e commandBlockLogicIn) {
        this.minecraft.k_2293_S().n_1700_B(new ServerboundSetCommandBlockPacket(new c_1514_x(commandBlockLogicIn.R_4764_Y()), this.n_1700_B.getText(), this.u_2550_I, commandBlockLogicIn.s_956_w(), this.M_588_G, this.P_4830_p));
    }

    private void u_1723_Y() {
        switch (this.u_2550_I) {
            case n_1700_B: {
                this.w_1484_f.setMessage(new F_2904_S("advMode.mode.sequence"));
                break;
            }
            case J_1907_R: {
                this.w_1484_f.setMessage(new F_2904_S("advMode.mode.auto"));
                break;
            }
            case R_4764_Y: {
                this.w_1484_f.setMessage(new F_2904_S("advMode.mode.redstone"));
            }
        }
    }

    private void v_4262_N() {
        switch (this.u_2550_I) {
            case n_1700_B: {
                this.u_2550_I = T_1368_k.n_1700_B.J_1907_R;
                break;
            }
            case J_1907_R: {
                this.u_2550_I = T_1368_k.n_1700_B.R_4764_Y;
                break;
            }
            case R_4764_Y: {
                this.u_2550_I = T_1368_k.n_1700_B.n_1700_B;
            }
        }
    }

    private void w_1484_f() {
        if (this.M_588_G) {
            this.t_148_a.setMessage(new F_2904_S("advMode.mode.conditional"));
        } else {
            this.t_148_a.setMessage(new F_2904_S("advMode.mode.unconditional"));
        }
    }

    private void t_148_a() {
        if (this.P_4830_p) {
            this.s_956_w.setMessage(new F_2904_S("advMode.mode.autoexec.bat"));
        } else {
            this.s_956_w.setMessage(new F_2904_S("advMode.mode.redstoneTriggered"));
        }
    }
}



