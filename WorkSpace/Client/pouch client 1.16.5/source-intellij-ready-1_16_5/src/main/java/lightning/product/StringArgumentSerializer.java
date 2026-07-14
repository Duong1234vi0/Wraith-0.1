/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType$StringType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import lightning.product.b_2585_i;
import lightning.product.ArgumentSerializer;

public class StringArgumentSerializer
implements ArgumentSerializer<StringArgumentType> {
    @Override
    public void n_1700_B(StringArgumentType argument, b_2585_i buffer) {
        buffer.n_1700_B((Enum<?>)argument.getType());
    }

    public StringArgumentType J_1907_R(b_2585_i buffer) {
        StringArgumentType.StringType stringtype = buffer.n_1700_B(StringArgumentType.StringType.class);
        switch (stringtype) {
            case SINGLE_WORD: {
                return StringArgumentType.word();
            }
            case QUOTABLE_PHRASE: {
                return StringArgumentType.string();
            }
        }
        return StringArgumentType.greedyString();
    }

    @Override
    public void n_1700_B(StringArgumentType p_212244_1_, JsonObject p_212244_2_) {
        switch (p_212244_1_.getType()) {
            case SINGLE_WORD: {
                p_212244_2_.addProperty("type", "word");
                break;
            }
            case QUOTABLE_PHRASE: {
                p_212244_2_.addProperty("type", "phrase");
                break;
            }
            default: {
                p_212244_2_.addProperty("type", "greedy");
            }
        }
    }

    @Override
    public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
        return this.J_1907_R(b_2585_i2);
    }
}


