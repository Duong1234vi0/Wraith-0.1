/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.K_1178_t;
import lightning.product.DataAccessor;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.a_3913_L;
import lightning.product.h_2396_v;
import lightning.product.i_4556_r;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.z_1856_e;

public class EntityDataAccessor
implements DataAccessor {
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.data.entity.invalid"));
    public static final Function<String, z_1856_e.n_1700_B> n_1700_B = p_218922_0_ -> new z_1856_e.n_1700_B((String)p_218922_0_){
        final /* synthetic */ String n_1700_B;
        {
            this.n_1700_B = string;
        }

        @Override
        public DataAccessor n_1700_B(CommandContext<y_2498_m> context) throws CommandSyntaxException {
            return new EntityDataAccessor(i_4556_r.n_1700_B(context, this.n_1700_B));
        }

        @Override
        public ArgumentBuilder<y_2498_m, ?> n_1700_B(ArgumentBuilder<y_2498_m, ?> builder, Function<ArgumentBuilder<y_2498_m, ?>, ArgumentBuilder<y_2498_m, ?>> action) {
            return builder.then(Q_2241_p.n_1700_B("entity").then(action.apply((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B(this.n_1700_B, i_4556_r.n_1700_B()))));
        }
    };
    private final N_4263_v R_4764_Y;

    public EntityDataAccessor(N_4263_v entityIn) {
        this.R_4764_Y = entityIn;
    }

    @Override
    public void n_1700_B(U_2912_j other) throws CommandSyntaxException {
        if (this.R_4764_Y instanceof a_3913_L) {
            throw J_1907_R.create();
        }
        UUID uuid = this.R_4764_Y.w_2705_t();
        this.R_4764_Y.u_1723_Y(other);
        this.R_4764_Y.a_(uuid);
    }

    @Override
    public U_2912_j n_1700_B() {
        return h_2396_v.J_1907_R(this.R_4764_Y);
    }

    @Override
    public x_282_a J_1907_R() {
        return new F_2904_S("commands.data.entity.modified", this.R_4764_Y.c_());
    }

    @Override
    public x_282_a n_1700_B(Tag nbt) {
        return new F_2904_S("commands.data.entity.query", this.R_4764_Y.c_(), nbt.P_4830_p());
    }

    @Override
    public x_282_a n_1700_B(K_1178_t.v_4262_N pathIn, double scale, int value) {
        return new F_2904_S("commands.data.entity.get", pathIn, this.R_4764_Y.c_(), String.format(Locale.ROOT, "%.2f", scale), value);
    }
}


