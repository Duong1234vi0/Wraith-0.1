/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import lightning.product.b_2585_i;
import lightning.product.BrigadierArgumentSerializers;
import lightning.product.ArgumentSerializer;

public class h_3896_O
implements ArgumentSerializer<DoubleArgumentType> {
    @Override
    public void n_1700_B(DoubleArgumentType argument, b_2585_i buffer) {
        boolean flag = argument.getMinimum() != -1.7976931348623157E308;
        boolean flag1 = argument.getMaximum() != Double.MAX_VALUE;
        buffer.writeByte(BrigadierArgumentSerializers.n_1700_B(flag, flag1));
        if (flag) {
            buffer.writeDouble(argument.getMinimum());
        }
        if (flag1) {
            buffer.writeDouble(argument.getMaximum());
        }
    }

    public DoubleArgumentType J_1907_R(b_2585_i buffer) {
        byte b0 = buffer.readByte();
        double d0 = BrigadierArgumentSerializers.n_1700_B(b0) ? buffer.readDouble() : -1.7976931348623157E308;
        double d1 = BrigadierArgumentSerializers.J_1907_R(b0) ? buffer.readDouble() : Double.MAX_VALUE;
        return DoubleArgumentType.doubleArg((double)d0, (double)d1);
    }

    @Override
    public void n_1700_B(DoubleArgumentType p_212244_1_, JsonObject p_212244_2_) {
        if (p_212244_1_.getMinimum() != -1.7976931348623157E308) {
            p_212244_2_.addProperty("min", (Number)p_212244_1_.getMinimum());
        }
        if (p_212244_1_.getMaximum() != Double.MAX_VALUE) {
            p_212244_2_.addProperty("max", (Number)p_212244_1_.getMaximum());
        }
    }

    @Override
    public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
        return this.J_1907_R(b_2585_i2);
    }
}


