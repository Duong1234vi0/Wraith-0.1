/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.serialization.Lifecycle
 *  joptsimple.AbstractOptionSpec
 *  joptsimple.ArgumentAcceptingOptionSpec
 *  joptsimple.NonOptionArgumentSpec
 *  joptsimple.OptionParser
 *  joptsimple.OptionSet
 *  joptsimple.OptionSpec
 *  joptsimple.OptionSpecBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Lifecycle;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.OutputStream;
import java.net.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import joptsimple.AbstractOptionSpec;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.OptionSpecBuilder;
import lightning.product.A_2352_Z;
import lightning.product.B_4315_y;
import lightning.product.PackRepository;
import lightning.product.F_491_v;
import lightning.product.F_877_l;
import lightning.product.H_4757_Q;
import lightning.product.LoggerChunkProgressListener;
import lightning.product.DedicatedServerProperties;
import lightning.product.Q_2241_p;
import lightning.product.T_4652_I;
import lightning.product.U_3339_M;
import lightning.product.V_4604_M;
import lightning.product.W_1689_V;
import lightning.product.Tag;
import lightning.product.ServerResources;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.c_3102_J;
import lightning.product.e_4716_U;
import lightning.product.f_2392_k;
import lightning.product.DataPackConfig;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.l_4118_l;
import lightning.product.WorldData;
import lightning.product.n_3236_c;
import lightning.product.PackSource;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_3482_a;
import net.minecraft.server.G_564_y;
import net.minecraft.server.P_1922_E;
import net.minecraft.server.u_1723_Y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static void main(String[] p_main_0_) {
        OptionParser optionparser = new OptionParser();
        OptionSpecBuilder optionspec = optionparser.accepts("nogui");
        OptionSpecBuilder optionspec1 = optionparser.accepts("initSettings", "Initializes 'server.properties' and 'eula.txt', then quits");
        OptionSpecBuilder optionspec2 = optionparser.accepts("demo");
        OptionSpecBuilder optionspec3 = optionparser.accepts("bonusChest");
        OptionSpecBuilder optionspec4 = optionparser.accepts("forceUpgrade");
        OptionSpecBuilder optionspec5 = optionparser.accepts("eraseCache");
        OptionSpecBuilder optionspec6 = optionparser.accepts("safeMode", "Loads level with vanilla datapack only");
        AbstractOptionSpec optionspec7 = optionparser.accepts("help").forHelp();
        ArgumentAcceptingOptionSpec optionspec8 = optionparser.accepts("singleplayer").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec9 = optionparser.accepts("universe").withRequiredArg().defaultsTo((Object)".", (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec10 = optionparser.accepts("world").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec11 = optionparser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo((Object)-1, (Object[])new Integer[0]);
        ArgumentAcceptingOptionSpec optionspec12 = optionparser.accepts("serverId").withRequiredArg();
        NonOptionArgumentSpec optionspec13 = optionparser.nonOptions();
        try {
            ServerResources datapackregistries;
            OptionSet optionset = optionparser.parse(p_main_0_);
            if (optionset.has((OptionSpec)optionspec7)) {
                optionparser.printHelpOn((OutputStream)System.err);
                return;
            }
            n_3236_c.v_4262_N();
            y_3482_a.n_1700_B();
            y_3482_a.R_4764_Y();
            j_3341_s.h_1847_R();
            r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
            Path path = Paths.get("server.properties", new String[0]);
            u_1723_Y serverpropertiesprovider = new u_1723_Y(dynamicregistries$impl, path);
            serverpropertiesprovider.J_1907_R();
            Path path1 = Paths.get("eula.txt", new String[0]);
            P_1922_E servereula = new P_1922_E(path1);
            if (optionset.has((OptionSpec)optionspec1)) {
                n_1700_B.info("Initialized '{}' and '{}'", (Object)path.toAbsolutePath(), (Object)path1.toAbsolutePath());
                return;
            }
            if (!servereula.n_1700_B()) {
                n_1700_B.info("You need to agree to the EULA in order to run the server. Go to eula.txt for more info.");
                return;
            }
            File file1 = new File((String)optionset.valueOf((OptionSpec)optionspec9));
            YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(Proxy.NO_PROXY);
            MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
            GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
            W_1689_V playerprofilecache = new W_1689_V(gameprofilerepository, new File(file1, G_564_y.n_1700_B.getName()));
            String s = Optional.ofNullable((String)optionset.valueOf((OptionSpec)optionspec10)).orElse(serverpropertiesprovider.n_1700_B().h_1847_R);
            b_2971_z saveformat = b_2971_z.n_1700_B(file1.toPath());
            b_2971_z.n_1700_B saveformat$levelsave = saveformat.R_4764_Y(s);
            G_564_y.n_1700_B(saveformat$levelsave);
            DataPackConfig datapackcodec = saveformat$levelsave.P_1922_E();
            boolean flag = optionset.has((OptionSpec)optionspec6);
            if (flag) {
                n_1700_B.warn("Safe mode active, only vanilla datapack will be loaded");
            }
            PackRepository resourcepacklist = new PackRepository(new T_4652_I(), new e_4716_U(saveformat$levelsave.n_1700_B(H_4757_Q.v_4262_N).toFile(), PackSource.R_4764_Y));
            DataPackConfig datapackcodec1 = G_564_y.n_1700_B(resourcepacklist, datapackcodec == null ? DataPackConfig.n_1700_B : datapackcodec, flag);
            CompletableFuture<ServerResources> completablefuture = ServerResources.n_1700_B(resourcepacklist.u_1723_Y(), Q_2241_p.n_1700_B.J_1907_R, serverpropertiesprovider.n_1700_B().e_4240_b, j_3341_s.u_1723_Y(), Runnable::run);
            try {
                datapackregistries = completablefuture.get();
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to load datapacks, can't proceed with server load. You can either fix your datapacks or reset to vanilla with --safeMode", (Throwable)exception);
                resourcepacklist.close();
                return;
            }
            datapackregistries.t_148_a();
            F_877_l<Tag> worldsettingsimport = F_877_l.n_1700_B(l_4118_l.n_1700_B, datapackregistries.w_1484_f(), dynamicregistries$impl);
            WorldData iserverconfiguration = saveformat$levelsave.n_1700_B(worldsettingsimport, datapackcodec1);
            if (iserverconfiguration == null) {
                j_419_j dimensiongeneratorsettings;
                B_4315_y worldsettings;
                if (optionset.has((OptionSpec)optionspec2)) {
                    worldsettings = G_564_y.J_1907_R;
                    dimensiongeneratorsettings = j_419_j.n_1700_B(dynamicregistries$impl);
                } else {
                    DedicatedServerProperties serverproperties = serverpropertiesprovider.n_1700_B();
                    worldsettings = new B_4315_y(serverproperties.h_1847_R, serverproperties.P_4830_p, serverproperties.q_2307_F, serverproperties.M_588_G, false, new A_2352_Z(), datapackcodec1);
                    dimensiongeneratorsettings = optionset.has((OptionSpec)optionspec3) ? serverproperties.H_1990_U.s_956_w() : serverproperties.H_1990_U;
                }
                iserverconfiguration = new c_3102_J(worldsettings, dimensiongeneratorsettings, Lifecycle.stable());
            }
            if (optionset.has((OptionSpec)optionspec4)) {
                Main.n_1700_B(saveformat$levelsave, F_491_v.n_1700_B(), optionset.has((OptionSpec)optionspec5), () -> true, iserverconfiguration.e_4240_b().u_1723_Y());
            }
            saveformat$levelsave.n_1700_B(dynamicregistries$impl, iserverconfiguration);
            WorldData iserverconfiguration1 = iserverconfiguration;
            final V_4604_M dedicatedserver = G_564_y.n_1700_B(arg_0 -> Main.n_1700_B(dynamicregistries$impl, saveformat$levelsave, resourcepacklist, datapackregistries, iserverconfiguration1, serverpropertiesprovider, minecraftsessionservice, gameprofilerepository, playerprofilecache, optionset, (OptionSpec)optionspec8, (OptionSpec)optionspec11, (OptionSpec)optionspec2, (OptionSpec)optionspec12, (OptionSpec)optionspec, (OptionSpec)optionspec13, arg_0));
            Thread thread = new Thread("Server Shutdown Thread"){

                @Override
                public void run() {
                    dedicatedserver.n_1700_B(true);
                }
            };
            thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(n_1700_B));
            Runtime.getRuntime().addShutdownHook(thread);
        }
        catch (Exception exception1) {
            n_1700_B.fatal("Failed to start the minecraft server", (Throwable)exception1);
        }
    }

    private static void n_1700_B(b_2971_z.n_1700_B p_240761_0_, DataFixer p_240761_1_, boolean p_240761_2_, BooleanSupplier p_240761_3_, ImmutableSet<f_2392_k<b_4507_u>> p_240761_4_) {
        n_1700_B.info("Forcing world upgrade!");
        U_3339_M worldoptimizer = new U_3339_M(p_240761_0_, p_240761_1_, p_240761_4_, p_240761_2_);
        x_282_a itextcomponent = null;
        while (!worldoptimizer.J_1907_R()) {
            int i;
            x_282_a itextcomponent1 = worldoptimizer.w_1484_f();
            if (itextcomponent != itextcomponent1) {
                itextcomponent = itextcomponent1;
                n_1700_B.info(worldoptimizer.w_1484_f().getString());
            }
            if ((i = worldoptimizer.P_1922_E()) > 0) {
                int j = worldoptimizer.u_1723_Y() + worldoptimizer.v_4262_N();
                n_1700_B.info("{}% completed ({} / {} chunks)...", (Object)u_530_F.G_564_y((float)j / (float)i * 100.0f), (Object)j, (Object)i);
            }
            if (!p_240761_3_.getAsBoolean()) {
                worldoptimizer.n_1700_B();
                continue;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    private static /* synthetic */ V_4604_M n_1700_B(r_4097_j.J_1907_R dynamicregistries$impl, b_2971_z.n_1700_B saveformat$levelsave, PackRepository resourcepacklist, ServerResources datapackregistries, WorldData iserverconfiguration1, u_1723_Y serverpropertiesprovider, MinecraftSessionService minecraftsessionservice, GameProfileRepository gameprofilerepository, W_1689_V playerprofilecache, OptionSet optionset, OptionSpec optionspec8, OptionSpec optionspec11, OptionSpec optionspec2, OptionSpec optionspec12, OptionSpec optionspec, OptionSpec optionspec13, Thread p_240762_16_) {
        boolean flag1;
        V_4604_M dedicatedserver1 = new V_4604_M(p_240762_16_, dynamicregistries$impl, saveformat$levelsave, resourcepacklist, datapackregistries, iserverconfiguration1, serverpropertiesprovider, F_491_v.n_1700_B(), minecraftsessionservice, gameprofilerepository, playerprofilecache, LoggerChunkProgressListener::new);
        dedicatedserver1.P_1922_E((String)optionset.valueOf(optionspec8));
        dedicatedserver1.n_1700_B((Integer)optionset.valueOf(optionspec11));
        dedicatedserver1.R_4764_Y(optionset.has(optionspec2));
        dedicatedserver1.R_4764_Y((String)optionset.valueOf(optionspec12));
        boolean bl = flag1 = !optionset.has(optionspec) && !optionset.valuesOf(optionspec13).contains("nogui");
        if (flag1 && !GraphicsEnvironment.isHeadless()) {
            dedicatedserver1.RealmsCreateRealmScreen();
        }
        return dedicatedserver1;
    }
}


