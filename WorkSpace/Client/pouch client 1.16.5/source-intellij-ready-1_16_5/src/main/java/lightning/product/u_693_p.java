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
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.K_1178_t;
import lightning.product.K_4074_S;
import lightning.product.DataAccessor;
import lightning.product.Q_2241_p;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.BlockPosArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.z_1856_e;

public class u_693_p
implements DataAccessor {
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.data.block.invalid"));
    public static final Function<String, z_1856_e.n_1700_B> n_1700_B = p_218923_0_ -> new z_1856_e.n_1700_B((String)p_218923_0_){
        final /* synthetic */ String n_1700_B;
        {
            this.n_1700_B = string;
        }

        @Override
        public DataAccessor n_1700_B(CommandContext<y_2498_m> context) throws CommandSyntaxException {
            c_1514_x blockpos = BlockPosArgument.n_1700_B(context, this.n_1700_B + "Pos");
            i_2154_H tileentity = ((y_2498_m)context.getSource()).h_1847_R().getTileEntity(blockpos);
            if (tileentity == null) {
                throw J_1907_R.create();
            }
            return new u_693_p(tileentity, blockpos);
        }

        @Override
        public ArgumentBuilder<y_2498_m, ?> n_1700_B(ArgumentBuilder<y_2498_m, ?> builder, Function<ArgumentBuilder<y_2498_m, ?>, ArgumentBuilder<y_2498_m, ?>> action) {
            return builder.then(Q_2241_p.n_1700_B("block").then(action.apply((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B(this.n_1700_B + "Pos", BlockPosArgument.n_1700_B()))));
        }
    };
    private final i_2154_H R_4764_Y;
    private final c_1514_x G_564_y;

    public u_693_p(i_2154_H tileEntityIn, c_1514_x posIn) {
        this.R_4764_Y = tileEntityIn;
        this.G_564_y = posIn;
    }

    @Override
    public void n_1700_B(U_2912_j other) {
        other.J_1907_R("x", this.G_564_y.getX());
        other.J_1907_R("y", this.G_564_y.getY());
        other.J_1907_R("z", this.G_564_y.getZ());
        K_4074_S blockstate = this.R_4764_Y.c_3005_b().getBlockState(this.G_564_y);
        this.R_4764_Y.n_1700_B(blockstate, other);
        this.R_4764_Y.J_1907_R();
        this.R_4764_Y.c_3005_b().n_1700_B(this.G_564_y, blockstate, blockstate, 3);
    }

    @Override
    public U_2912_j n_1700_B() {
        return this.R_4764_Y.n_1700_B(new U_2912_j());
    }

    @Override
    public x_282_a J_1907_R() {
        return new F_2904_S("commands.data.block.modified", this.G_564_y.getX(), this.G_564_y.getY(), this.G_564_y.getZ());
    }

    @Override
    public x_282_a n_1700_B(Tag nbt) {
        return new F_2904_S("commands.data.block.query", this.G_564_y.getX(), this.G_564_y.getY(), this.G_564_y.getZ(), nbt.P_4830_p());
    }

    @Override
    public x_282_a n_1700_B(K_1178_t.v_4262_N pathIn, double scale, int value) {
        return new F_2904_S("commands.data.block.get", pathIn, this.G_564_y.getX(), this.G_564_y.getY(), this.G_564_y.getZ(), String.format(Locale.ROOT, "%.2f", scale), value);
    }
}


