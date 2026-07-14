/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lightning.product.PackRepository;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.WorldData;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReloadCommand {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static void n_1700_B(Collection<String> p_241062_0_, y_2498_m p_241062_1_) {
        p_241062_1_.w_1457_N().n_1700_B(p_241062_0_).exceptionally(p_241061_1_ -> {
            n_1700_B.warn("Failed to execute reload", p_241061_1_);
            p_241062_1_.n_1700_B(new F_2904_S("commands.reload.failure"));
            return null;
        });
    }

    private static Collection<String> n_1700_B(PackRepository p_241058_0_, WorldData p_241058_1_, Collection<String> p_241058_2_) {
        p_241058_0_.n_1700_B();
        ArrayList collection = Lists.newArrayList(p_241058_2_);
        List<String> collection1 = p_241058_1_.k_2293_S().J_1907_R();
        for (String s : p_241058_0_.J_1907_R()) {
            if (collection1.contains(s) || collection.contains(s)) continue;
            collection.add(s);
        }
        return collection;
    }

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("reload").requires(p_198599_0_ -> p_198599_0_.n_1700_B(2))).executes(p_198598_0_ -> {
            y_2498_m commandsource = (y_2498_m)p_198598_0_.getSource();
            G_564_y minecraftserver = commandsource.w_1457_N();
            PackRepository resourcepacklist = minecraftserver.RegionPingResult();
            WorldData iserverconfiguration = minecraftserver.c_132_F();
            Collection<String> collection = resourcepacklist.G_564_y();
            Collection<String> collection1 = ReloadCommand.n_1700_B(resourcepacklist, iserverconfiguration, collection);
            commandsource.n_1700_B(new F_2904_S("commands.reload.success"), true);
            ReloadCommand.n_1700_B(collection1, commandsource);
            return 0;
        }));
    }
}


