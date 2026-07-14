/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import lightning.product.b_2585_i;
import lightning.product.BrigadierArgumentSerializers;
import lightning.product.ArgumentSerializer;

public class FloatArgumentSerializer
implements ArgumentSerializer<FloatArgumentType> {
    @Override
    public void n_1700_B(FloatArgumentType argument, b_2585_i buffer) {
        boolean flag = argument.getMinimum() != -3.4028235E38f;
        boolean flag1 = argument.getMaximum() != Float.MAX_VALUE;
        buffer.writeByte(BrigadierArgumentSerializers.n_1700_B(flag, flag1));
        if (flag) {
            buffer.writeFloat(argument.getMinimum());
        }
        if (flag1) {
            buffer.writeFloat(argument.getMaximum());
        }
    }

    public FloatArgumentType J_1907_R(b_2585_i buffer) {
        byte b0 = buffer.readByte();
        float f = BrigadierArgumentSerializers.n_1700_B(b0) ? buffer.readFloat() : -3.4028235E38f;
        float f1 = BrigadierArgumentSerializers.J_1907_R(b0) ? buffer.readFloat() : Float.MAX_VALUE;
        return FloatArgumentType.floatArg((float)f, (float)f1);
    }

    @Override
    public void n_1700_B(FloatArgumentType p_212244_1_, JsonObject p_212244_2_) {
        if (p_212244_1_.getMinimum() != -3.4028235E38f) {
            p_212244_2_.addProperty("min", (Number)Float.valueOf(p_212244_1_.getMinimum()));
        }
        if (p_212244_1_.getMaximum() != Float.MAX_VALUE) {
            p_212244_2_.addProperty("max", (Number)Float.valueOf(p_212244_1_.getMaximum()));
        }
    }

    @Override
    public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
        return this.J_1907_R(b_2585_i2);
    }
}


