/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.LongArgumentType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import lightning.product.b_2585_i;
import lightning.product.BrigadierArgumentSerializers;
import lightning.product.ArgumentSerializer;

public class j_3578_J
implements ArgumentSerializer<LongArgumentType> {
    @Override
    public void n_1700_B(LongArgumentType argument, b_2585_i buffer) {
        boolean flag = argument.getMinimum() != Long.MIN_VALUE;
        boolean flag1 = argument.getMaximum() != Long.MAX_VALUE;
        buffer.writeByte(BrigadierArgumentSerializers.n_1700_B(flag, flag1));
        if (flag) {
            buffer.writeLong(argument.getMinimum());
        }
        if (flag1) {
            buffer.writeLong(argument.getMaximum());
        }
    }

    public LongArgumentType J_1907_R(b_2585_i buffer) {
        byte b0 = buffer.readByte();
        long i = BrigadierArgumentSerializers.n_1700_B(b0) ? buffer.readLong() : Long.MIN_VALUE;
        long j = BrigadierArgumentSerializers.J_1907_R(b0) ? buffer.readLong() : Long.MAX_VALUE;
        return LongArgumentType.longArg((long)i, (long)j);
    }

    @Override
    public void n_1700_B(LongArgumentType p_212244_1_, JsonObject p_212244_2_) {
        if (p_212244_1_.getMinimum() != Long.MIN_VALUE) {
            p_212244_2_.addProperty("min", (Number)p_212244_1_.getMinimum());
        }
        if (p_212244_1_.getMaximum() != Long.MAX_VALUE) {
            p_212244_2_.addProperty("max", (Number)p_212244_1_.getMaximum());
        }
    }

    @Override
    public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
        return this.J_1907_R(b_2585_i2);
    }
}


