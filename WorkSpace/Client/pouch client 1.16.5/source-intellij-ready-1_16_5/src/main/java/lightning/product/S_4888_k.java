/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 */
package lightning.product;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.Locale;
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.J_4848_t;
import lightning.product.K_1178_t;
import lightning.product.DataAccessor;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.V_4217_p;
import lightning.product.Tag;
import lightning.product.g_2336_b;
import lightning.product.ResourceLocationArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.z_1856_e;

public class S_4888_k
implements DataAccessor {
    private static final SuggestionProvider<y_2498_m> J_1907_R = (p_229838_0_, p_229838_1_) -> V_4217_p.n_1700_B(S_4888_k.n_1700_B((CommandContext<y_2498_m>)p_229838_0_).n_1700_B(), p_229838_1_);
    public static final Function<String, z_1856_e.n_1700_B> n_1700_B = p_229839_0_ -> new z_1856_e.n_1700_B((String)p_229839_0_){
        final /* synthetic */ String n_1700_B;
        {
            this.n_1700_B = string;
        }

        @Override
        public DataAccessor n_1700_B(CommandContext<y_2498_m> context) {
            return new S_4888_k(S_4888_k.n_1700_B(context), ResourceLocationArgument.P_1922_E(context, this.n_1700_B));
        }

        @Override
        public ArgumentBuilder<y_2498_m, ?> n_1700_B(ArgumentBuilder<y_2498_m, ?> builder, Function<ArgumentBuilder<y_2498_m, ?>, ArgumentBuilder<y_2498_m, ?>> action) {
            return builder.then(Q_2241_p.n_1700_B("storage").then(action.apply((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B(this.n_1700_B, ResourceLocationArgument.n_1700_B()).suggests(J_1907_R))));
        }
    };
    private final J_4848_t R_4764_Y;
    private final g_2336_b G_564_y;

    private static J_4848_t n_1700_B(CommandContext<y_2498_m> p_229840_0_) {
        return ((y_2498_m)p_229840_0_.getSource()).w_1457_N().l_4537_E();
    }

    private S_4888_k(J_4848_t p_i226092_1_, g_2336_b p_i226092_2_) {
        this.R_4764_Y = p_i226092_1_;
        this.G_564_y = p_i226092_2_;
    }

    @Override
    public void n_1700_B(U_2912_j other) {
        this.R_4764_Y.n_1700_B(this.G_564_y, other);
    }

    @Override
    public U_2912_j n_1700_B() {
        return this.R_4764_Y.n_1700_B(this.G_564_y);
    }

    @Override
    public x_282_a J_1907_R() {
        return new F_2904_S("commands.data.storage.modified", this.G_564_y);
    }

    @Override
    public x_282_a n_1700_B(Tag nbt) {
        return new F_2904_S("commands.data.storage.query", this.G_564_y, nbt.P_4830_p());
    }

    @Override
    public x_282_a n_1700_B(K_1178_t.v_4262_N pathIn, double scale, int value) {
        return new F_2904_S("commands.data.storage.get", pathIn, this.G_564_y, String.format(Locale.ROOT, "%.2f", scale), value);
    }
}


