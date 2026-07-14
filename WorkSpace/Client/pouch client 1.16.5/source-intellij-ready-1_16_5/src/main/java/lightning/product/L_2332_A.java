/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.Q_2241_p;
import lightning.product.ProfileResults;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class L_2332_A {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.debug.notRunning"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.debug.alreadyRunning"));
    @Nullable
    private static final FileSystemProvider G_564_y = FileSystemProvider.installedProviders().stream().filter(p_225386_0_ -> p_225386_0_.getScheme().equalsIgnoreCase("jar")).findFirst().orElse(null);

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("debug").requires(p_198332_0_ -> p_198332_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("start").executes(p_198329_0_ -> L_2332_A.n_1700_B((y_2498_m)p_198329_0_.getSource())))).then(Q_2241_p.n_1700_B("stop").executes(p_198333_0_ -> L_2332_A.J_1907_R((y_2498_m)p_198333_0_.getSource())))).then(Q_2241_p.n_1700_B("report").executes(p_225388_0_ -> L_2332_A.R_4764_Y((y_2498_m)p_225388_0_.getSource()))));
    }

    private static int n_1700_B(y_2498_m source) throws CommandSyntaxException {
        G_564_y minecraftserver = source.w_1457_N();
        if (minecraftserver.j_2266_I()) {
            throw R_4764_Y.create();
        }
        minecraftserver.S_980_j();
        source.n_1700_B(new F_2904_S("commands.debug.started", "Started the debug profiler. Type '/debug stop' to stop it."), true);
        return 0;
    }

    private static int J_1907_R(y_2498_m source) throws CommandSyntaxException {
        G_564_y minecraftserver = source.w_1457_N();
        if (!minecraftserver.j_2266_I()) {
            throw J_1907_R.create();
        }
        ProfileResults iprofileresult = minecraftserver.R_3077_Z();
        File file1 = new File(minecraftserver.G_564_y("debug"), "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt");
        iprofileresult.n_1700_B(file1);
        float f = (float)iprofileresult.u_1723_Y() / 1.0E9f;
        float f1 = (float)iprofileresult.P_1922_E() / f;
        source.n_1700_B(new F_2904_S("commands.debug.stopped", String.format(Locale.ROOT, "%.2f", Float.valueOf(f)), iprofileresult.P_1922_E(), String.format("%.2f", Float.valueOf(f1))), true);
        return u_530_F.G_564_y(f1);
    }

    private static int R_4764_Y(y_2498_m p_225389_0_) {
        G_564_y minecraftserver = p_225389_0_.w_1457_N();
        String s = "debug-report-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
        try {
            Path path1 = minecraftserver.G_564_y("debug").toPath();
            Files.createDirectories(path1, new FileAttribute[0]);
            if (!SharedConstants.G_564_y && G_564_y != null) {
                Path path2 = path1.resolve(s + ".zip");
                try (FileSystem filesystem = G_564_y.newFileSystem(path2, (Map<String, ?>)ImmutableMap.of((Object)"create", (Object)"true"));){
                    minecraftserver.n_1700_B(filesystem.getPath("/", new String[0]));
                }
            } else {
                Path path = path1.resolve(s);
                minecraftserver.n_1700_B(path);
            }
            p_225389_0_.n_1700_B(new F_2904_S("commands.debug.reportSaved", s), false);
            return 1;
        }
        catch (IOException ioexception) {
            n_1700_B.error("Failed to save debug dump", (Throwable)ioexception);
            p_225389_0_.n_1700_B(new F_2904_S("commands.debug.reportFailed"));
            return 0;
        }
    }
}


