/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.bridge.game.GameVersion
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  io.netty.util.ResourceLeakDetector
 *  io.netty.util.ResourceLeakDetector$Level
 */
package lightning.product;

import com.mojang.bridge.game.GameVersion;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.netty.util.ResourceLeakDetector;
import java.time.Duration;
import lightning.product.H_3790_v;
import lightning.product.BrigadierExceptions;

public class SharedConstants {
    public static final ResourceLeakDetector.Level n_1700_B = ResourceLeakDetector.Level.DISABLED;
    public static final long J_1907_R = Duration.ofMillis(300L).toNanos();
    public static boolean R_4764_Y = true;
    public static boolean G_564_y;
    public static final char[] P_1922_E;
    private static GameVersion u_1723_Y;

    public static boolean n_1700_B(char character) {
        return character != '\u00a7' && character >= ' ' && character != '\u007f';
    }

    public static String n_1700_B(String input) {
        StringBuilder stringbuilder = new StringBuilder();
        for (char c0 : input.toCharArray()) {
            if (!SharedConstants.n_1700_B(c0)) continue;
            stringbuilder.append(c0);
        }
        return stringbuilder.toString();
    }

    public static GameVersion n_1700_B() {
        if (u_1723_Y == null) {
            u_1723_Y = H_3790_v.n_1700_B();
        }
        return u_1723_Y;
    }

    public static int J_1907_R() {
        return 754;
    }

    static {
        P_1922_E = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};
        ResourceLeakDetector.setLevel((ResourceLeakDetector.Level)n_1700_B);
        CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES = false;
        CommandSyntaxException.BUILT_IN_EXCEPTIONS = new BrigadierExceptions();
    }
}


