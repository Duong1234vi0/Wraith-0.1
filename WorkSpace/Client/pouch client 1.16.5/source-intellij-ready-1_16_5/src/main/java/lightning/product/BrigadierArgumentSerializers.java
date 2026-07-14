/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.LongArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 */
package lightning.product;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import lightning.product.A_958_X;
import lightning.product.EmptyArgumentSerializer;
import lightning.product.StringArgumentSerializer;
import lightning.product.IntegerArgumentSerializer;
import lightning.product.h_3896_O;
import lightning.product.FloatArgumentSerializer;
import lightning.product.j_3578_J;

public class BrigadierArgumentSerializers {
    public static void n_1700_B() {
        A_958_X.n_1700_B("brigadier:bool", BoolArgumentType.class, new EmptyArgumentSerializer<BoolArgumentType>(BoolArgumentType::bool));
        A_958_X.n_1700_B("brigadier:float", FloatArgumentType.class, new FloatArgumentSerializer());
        A_958_X.n_1700_B("brigadier:double", DoubleArgumentType.class, new h_3896_O());
        A_958_X.n_1700_B("brigadier:integer", IntegerArgumentType.class, new IntegerArgumentSerializer());
        A_958_X.n_1700_B("brigadier:long", LongArgumentType.class, new j_3578_J());
        A_958_X.n_1700_B("brigadier:string", StringArgumentType.class, new StringArgumentSerializer());
    }

    public static byte n_1700_B(boolean min, boolean max) {
        byte b0 = 0;
        if (min) {
            b0 = (byte)(b0 | 1);
        }
        if (max) {
            b0 = (byte)(b0 | 2);
        }
        return b0;
    }

    public static boolean n_1700_B(byte flags) {
        return (flags & 1) != 0;
    }

    public static boolean J_1907_R(byte flags) {
        return (flags & 2) != 0;
    }
}


