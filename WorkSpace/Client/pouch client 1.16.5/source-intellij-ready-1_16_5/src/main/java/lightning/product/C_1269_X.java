/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import java.util.ArrayList;
import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.F_3572_x;
import lightning.product.F_518_D;
import lightning.product.G_624_v;
import lightning.product.I_1407_m;
import lightning.product.L_103_L;
import lightning.product.L_1362_X;
import lightning.product.L_3570_A;
import lightning.product.L_4248_u;
import lightning.product.O_1309_Q;
import lightning.product.O_2934_T;
import lightning.product.O_4761_U;
import lightning.product.P_2947_S;
import lightning.product.P_5000_x;
import lightning.product.P_925_e;
import lightning.product.S_3139_t;
import lightning.product.U_2871_b;
import lightning.product.V_118_c;
import lightning.product.V_4217_p;
import lightning.product.W_2853_p;
import lightning.product.X_4895_T;
import lightning.product.Y_776_s;
import lightning.product.Z_1567_W;
import lightning.product.Z_735_d;
import lightning.product.MinecraftAccess;
import lightning.product.ClientSuggestionProvider;
import lightning.product.j_306_t;
import lightning.product.k_2302_P;
import lightning.product.l_4088_R;
import lightning.product.n_3197_X;
import lightning.product.o_2341_D;
import lightning.product.AttackAura;
import lightning.product.t_1446_I;
import lightning.product.t_3452_g;
import lightning.product.v_1900_v;
import lightning.product.w_2705_t;
import lightning.product.x_612_B;
import lightning.product.y_4642_Y;

public class C_1269_X
implements MinecraftAccess {
    private String n_1700_B = ".";
    private final CommandDispatcher<V_4217_p> J_1907_R = new CommandDispatcher();
    private final List<o_2341_D> R_4764_Y = new ArrayList<o_2341_D>();

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(String prefix) {
        this.n_1700_B = prefix;
    }

    public CommandDispatcher<V_4217_p> J_1907_R() {
        return this.J_1907_R;
    }

    public List<o_2341_D> R_4764_Y() {
        return this.R_4764_Y;
    }

    public V_4217_p G_564_y() {
        W_2853_p conn = c_3005_b.k_2293_S();
        if (conn != null) {
            return conn.n_1700_B();
        }
        return new ClientSuggestionProvider(null, c_3005_b);
    }

    private void P_1922_E() {
        if (G_624_v.t_148_a.J_1907_R()) {
            this.n_1700_B(new L_1362_X());
            this.n_1700_B(new L_4248_u());
            this.n_1700_B(new O_1309_Q());
            this.n_1700_B(new t_1446_I());
            this.n_1700_B(new Y_776_s());
            this.n_1700_B(new X_4895_T());
            this.n_1700_B(new F_518_D());
            this.n_1700_B(new O_2934_T());
            this.n_1700_B(new I_1407_m());
            this.n_1700_B(new j_306_t());
            this.n_1700_B(new n_3197_X());
            this.n_1700_B(new x_612_B());
            this.n_1700_B(new V_118_c());
            this.n_1700_B(new S_3139_t());
            this.n_1700_B(new P_2947_S());
            this.n_1700_B(new L_3570_A());
            this.n_1700_B(new L_103_L());
            this.n_1700_B(new k_2302_P());
            this.n_1700_B(new P_5000_x());
            this.n_1700_B(new Z_735_d());
            this.n_1700_B(new O_4761_U());
            this.n_1700_B(new F_3572_x());
            this.n_1700_B(new P_925_e());
            this.n_1700_B(new w_2705_t());
            this.n_1700_B(new l_4088_R());
            this.n_1700_B(new t_3452_g());
        }
    }

    public boolean J_1907_R(String command) {
        if (command.equalsIgnoreCase("donutsmp")) {
            AttackAura.w_1457_N();
            v_1900_v.n_1700_B(new U_2871_b("sex unlocked").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
            return true;
        }
        return false;
    }

    public boolean R_4764_Y(String command) {
        String normalized;
        if (!y_4642_Y.R_4764_Y()) {
            return true;
        }
        String string = normalized = command == null ? "" : command.trim();
        if (normalized.isEmpty()) {
            return false;
        }
        String[] parts = normalized.split("\\s+");
        if (parts.length == 0) {
            return false;
        }
        String first = parts[0];
        return "self".equalsIgnoreCase(first) || "@self".equalsIgnoreCase(first);
    }

    public C_1269_X() {
        this.P_1922_E();
    }

    private void n_1700_B(o_2341_D command) {
        command.n_1700_B(this.J_1907_R);
        this.R_4764_Y.add(command);
    }
}



