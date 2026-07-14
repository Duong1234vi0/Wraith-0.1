/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.ContextAwareComponent;
import lightning.product.MutableComponent;
import lightning.product.J_2545_z;
import lightning.product.L_3144_D;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.Objective;
import lightning.product.Y_995_C;
import lightning.product.ServerScoreboard;
import lightning.product.i_4556_r;
import lightning.product.v_4839_y;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class K_2271_Q
extends L_3144_D
implements ContextAwareComponent {
    private final String R_4764_Y;
    @Nullable
    private final Y_995_C G_564_y;
    private final String P_1922_E;

    @Nullable
    private static Y_995_C G_564_y(String p_240707_0_) {
        try {
            return new J_2545_z(new StringReader(p_240707_0_)).w_1457_N();
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            return null;
        }
    }

    public K_2271_Q(String nameIn, String objectiveIn) {
        this(nameIn, K_2271_Q.G_564_y(nameIn), objectiveIn);
    }

    private K_2271_Q(String p_i232569_1_, @Nullable Y_995_C p_i232569_2_, String p_i232569_3_) {
        this.R_4764_Y = p_i232569_1_;
        this.G_564_y = p_i232569_2_;
        this.P_1922_E = p_i232569_3_;
    }

    public String v_4262_N() {
        return this.R_4764_Y;
    }

    public String w_1484_f() {
        return this.P_1922_E;
    }

    private String n_1700_B(y_2498_m p_240705_1_) throws CommandSyntaxException {
        List<? extends N_4263_v> list;
        if (this.G_564_y != null && !(list = this.G_564_y.J_1907_R(p_240705_1_)).isEmpty()) {
            if (list.size() != 1) {
                throw i_4556_r.n_1700_B.create();
            }
            return list.get(0).L_3570_A();
        }
        return this.R_4764_Y;
    }

    private String n_1700_B(String p_240706_1_, y_2498_m p_240706_2_) {
        Objective scoreobjective;
        ServerScoreboard scoreboard;
        G_564_y minecraftserver = p_240706_2_.w_1457_N();
        if (minecraftserver != null && (scoreboard = minecraftserver.S_4022_R()).n_1700_B(p_240706_1_, scoreobjective = scoreboard.R_4764_Y(this.P_1922_E))) {
            v_4839_y score = scoreboard.J_1907_R(p_240706_1_, scoreobjective);
            return Integer.toString(score.J_1907_R());
        }
        return "";
    }

    public K_2271_Q s_956_w() {
        return new K_2271_Q(this.R_4764_Y, this.G_564_y, this.P_1922_E);
    }

    @Override
    public MutableComponent n_1700_B(@Nullable y_2498_m p_230535_1_, @Nullable N_4263_v p_230535_2_, int p_230535_3_) throws CommandSyntaxException {
        if (p_230535_1_ == null) {
            return new U_2871_b("");
        }
        String s = this.n_1700_B(p_230535_1_);
        String s1 = p_230535_2_ != null && s.equals("*") ? p_230535_2_.L_3570_A() : s;
        return new U_2871_b(this.n_1700_B(s1, p_230535_1_));
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof K_2271_Q)) {
            return false;
        }
        K_2271_Q scoretextcomponent = (K_2271_Q)p_equals_1_;
        return this.R_4764_Y.equals(scoretextcomponent.R_4764_Y) && this.P_1922_E.equals(scoretextcomponent.P_1922_E) && super.equals(p_equals_1_);
    }

    @Override
    public String toString() {
        return "ScoreComponent{name='" + this.R_4764_Y + "'objective='" + this.P_1922_E + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
    }

    @Override
    public /* synthetic */ L_3144_D t_148_a() {
        return this.s_956_w();
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.s_956_w();
    }
}


