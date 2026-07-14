/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;
import lightning.product.ContextAwareComponent;
import lightning.product.MutableComponent;
import lightning.product.J_2545_z;
import lightning.product.L_3144_D;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.Y_995_C;
import lightning.product.y_2498_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Y_901_G
extends L_3144_D
implements ContextAwareComponent {
    private static final Logger R_4764_Y = LogManager.getLogger();
    private final String G_564_y;
    @Nullable
    private final Y_995_C P_1922_E;

    public Y_901_G(String selectorIn) {
        this.G_564_y = selectorIn;
        Y_995_C entityselector = null;
        try {
            J_2545_z entityselectorparser = new J_2545_z(new StringReader(selectorIn));
            entityselector = entityselectorparser.w_1457_N();
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            R_4764_Y.warn("Invalid selector component: {}", (Object)selectorIn, (Object)commandsyntaxexception.getMessage());
        }
        this.P_1922_E = entityselector;
    }

    public String v_4262_N() {
        return this.G_564_y;
    }

    @Override
    public MutableComponent n_1700_B(@Nullable y_2498_m p_230535_1_, @Nullable N_4263_v p_230535_2_, int p_230535_3_) throws CommandSyntaxException {
        return p_230535_1_ != null && this.P_1922_E != null ? Y_995_C.n_1700_B(this.P_1922_E.J_1907_R(p_230535_1_)) : new U_2871_b("");
    }

    @Override
    public String J_1907_R() {
        return this.G_564_y;
    }

    public Y_901_G w_1484_f() {
        return new Y_901_G(this.G_564_y);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof Y_901_G)) {
            return false;
        }
        Y_901_G selectortextcomponent = (Y_901_G)p_equals_1_;
        return this.G_564_y.equals(selectortextcomponent.G_564_y) && super.equals(p_equals_1_);
    }

    @Override
    public String toString() {
        return "SelectorComponent{pattern='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
    }

    @Override
    public /* synthetic */ L_3144_D t_148_a() {
        return this.w_1484_f();
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.w_1484_f();
    }
}


