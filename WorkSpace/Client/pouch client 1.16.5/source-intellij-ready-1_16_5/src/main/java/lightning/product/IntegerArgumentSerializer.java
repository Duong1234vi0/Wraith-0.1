/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import lightning.product.b_2585_i;
import lightning.product.BrigadierArgumentSerializers;
import lightning.product.ArgumentSerializer;

public class IntegerArgumentSerializer
implements ArgumentSerializer<IntegerArgumentType> {
    @Override
    public void n_1700_B(IntegerArgumentType argument, b_2585_i buffer) {
        boolean flag = argument.getMinimum() != Integer.MIN_VALUE;
        boolean flag1 = argument.getMaximum() != Integer.MAX_VALUE;
        buffer.writeByte(BrigadierArgumentSerializers.n_1700_B(flag, flag1));
        if (flag) {
            buffer.writeInt(argument.getMinimum());
        }
        if (flag1) {
            buffer.writeInt(argument.getMaximum());
        }
    }

    public IntegerArgumentType J_1907_R(b_2585_i buffer) {
        byte b0 = buffer.readByte();
        int i = BrigadierArgumentSerializers.n_1700_B(b0) ? buffer.readInt() : Integer.MIN_VALUE;
        int j = BrigadierArgumentSerializers.J_1907_R(b0) ? buffer.readInt() : Integer.MAX_VALUE;
        return IntegerArgumentType.integer((int)i, (int)j);
    }

    @Override
    public void n_1700_B(IntegerArgumentType p_212244_1_, JsonObject p_212244_2_) {
        if (p_212244_1_.getMinimum() != Integer.MIN_VALUE) {
            p_212244_2_.addProperty("min", (Number)p_212244_1_.getMinimum());
        }
        if (p_212244_1_.getMaximum() != Integer.MAX_VALUE) {
            p_212244_2_.addProperty("max", (Number)p_212244_1_.getMaximum());
        }
    }

    @Override
    public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
        return this.J_1907_R(b_2585_i2);
    }
}


