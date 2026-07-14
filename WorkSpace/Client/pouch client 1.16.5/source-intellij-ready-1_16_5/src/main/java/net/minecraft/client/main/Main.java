/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.mojang.authlib.properties.PropertyMap
 *  com.mojang.authlib.properties.PropertyMap$Serializer
 *  javax.annotation.Nullable
 *  joptsimple.ArgumentAcceptingOptionSpec
 *  joptsimple.NonOptionArgumentSpec
 *  joptsimple.OptionParser
 *  joptsimple.OptionSet
 *  joptsimple.OptionSpec
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.client.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;
import java.util.OptionalInt;
import javax.annotation.Nullable;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import lightning.product.GameConfig;
import lightning.product.G_624_v;
import lightning.product.R_3197_Z;
import lightning.product.T_2506_i;
import lightning.product.V_4423_d;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.LanguageManager;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import lightning.product.n_3236_c;
import lightning.product.q_1272_r;
import lightning.product.q_570_v;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.u_3100_Q;
import lightning.product.w_2223_C;
import lightning.product.y_3482_a;
import lightning.product.z_4693_k;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @w_2223_C
    public static void main(String[] p_main_0_) {
        Thread thread1;
        MinecraftClient minecraft;
        if (!G_624_v.t_148_a.J_1907_R()) {
            G_624_v.t_148_a.n_1700_B();
        }
        OptionParser optionparser = new OptionParser();
        optionparser.allowsUnrecognizedOptions();
        optionparser.accepts("demo");
        optionparser.accepts("disableMultiplayer");
        optionparser.accepts("disableChat");
        optionparser.accepts("fullscreen");
        optionparser.accepts("checkGlErrors");
        ArgumentAcceptingOptionSpec optionspec = optionparser.accepts("server").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec1 = optionparser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo((Object)25565, (Object[])new Integer[0]);
        ArgumentAcceptingOptionSpec optionspec2 = optionparser.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("."), (Object[])new File[0]);
        ArgumentAcceptingOptionSpec optionspec3 = optionparser.accepts("assetsDir").withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec optionspec4 = optionparser.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec optionspec5 = optionparser.accepts("dataPackDir").withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec optionspec6 = optionparser.accepts("proxyHost").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec7 = optionparser.accepts("proxyPort").withRequiredArg().defaultsTo((Object)"8080", (Object[])new String[0]).ofType(Integer.class);
        ArgumentAcceptingOptionSpec optionspec8 = optionparser.accepts("proxyUser").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec9 = optionparser.accepts("proxyPass").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec10 = optionparser.accepts("username").withRequiredArg().defaultsTo((Object)("Player" + j_3341_s.J_1907_R() % 1000L), (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec11 = optionparser.accepts("uuid").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec12 = optionparser.accepts("accessToken").withRequiredArg().required();
        ArgumentAcceptingOptionSpec optionspec13 = optionparser.accepts("version").withRequiredArg().required();
        ArgumentAcceptingOptionSpec optionspec14 = optionparser.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo((Object)854, (Object[])new Integer[0]);
        ArgumentAcceptingOptionSpec optionspec15 = optionparser.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo((Object)480, (Object[])new Integer[0]);
        ArgumentAcceptingOptionSpec optionspec16 = optionparser.accepts("fullscreenWidth").withRequiredArg().ofType(Integer.class);
        ArgumentAcceptingOptionSpec optionspec17 = optionparser.accepts("fullscreenHeight").withRequiredArg().ofType(Integer.class);
        ArgumentAcceptingOptionSpec optionspec18 = optionparser.accepts("userProperties").withRequiredArg().defaultsTo((Object)"{}", (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec19 = optionparser.accepts("profileProperties").withRequiredArg().defaultsTo((Object)"{}", (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec20 = optionparser.accepts("assetIndex").withRequiredArg();
        ArgumentAcceptingOptionSpec optionspec21 = optionparser.accepts("userType").withRequiredArg().defaultsTo((Object)"legacy", (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec22 = optionparser.accepts("versionType").withRequiredArg().defaultsTo((Object)"release", (Object[])new String[0]);
        NonOptionArgumentSpec optionspec23 = optionparser.nonOptions();
        OptionSet optionset = optionparser.parse(p_main_0_);
        List list = optionset.valuesOf((OptionSpec)optionspec23);
        if (!list.isEmpty()) {
            System.out.println("Completely ignored arguments: " + String.valueOf(list));
        }
        T_2506_i.n_1700_B();
        String s = (String)Main.getValue(optionset, optionspec6);
        Proxy proxy = Proxy.NO_PROXY;
        if (s != null) {
            try {
                proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(s, (int)((Integer)Main.getValue(optionset, optionspec7))));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        final String s1 = (String)Main.getValue(optionset, optionspec8);
        final String s2 = (String)Main.getValue(optionset, optionspec9);
        if (!proxy.equals(Proxy.NO_PROXY) && Main.isNotEmpty(s1) && Main.isNotEmpty(s2)) {
            Authenticator.setDefault(new Authenticator(){

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(s1, s2.toCharArray());
                }
            });
        }
        int i = (Integer)Main.getValue(optionset, optionspec14);
        int j = (Integer)Main.getValue(optionset, optionspec15);
        OptionalInt optionalint = Main.toOptionalInt((Integer)Main.getValue(optionset, optionspec16));
        OptionalInt optionalint1 = Main.toOptionalInt((Integer)Main.getValue(optionset, optionspec17));
        boolean flag = optionset.has("fullscreen");
        boolean flag1 = optionset.has("demo");
        boolean flag2 = optionset.has("disableMultiplayer");
        boolean flag3 = optionset.has("disableChat");
        String s3 = (String)Main.getValue(optionset, optionspec13);
        Gson gson = new GsonBuilder().registerTypeAdapter(PropertyMap.class, (Object)new PropertyMap.Serializer()).create();
        PropertyMap propertymap = i_4431_W.n_1700_B(gson, (String)Main.getValue(optionset, optionspec18), PropertyMap.class);
        PropertyMap propertymap1 = i_4431_W.n_1700_B(gson, (String)Main.getValue(optionset, optionspec19), PropertyMap.class);
        String s4 = (String)Main.getValue(optionset, optionspec22);
        File file1 = (File)Main.getValue(optionset, optionspec2);
        File file2 = optionset.has((OptionSpec)optionspec3) ? (File)Main.getValue(optionset, optionspec3) : new File(file1, "assets/");
        File file3 = optionset.has((OptionSpec)optionspec4) ? (File)Main.getValue(optionset, optionspec4) : new File(file1, "resourcepacks/");
        String s5 = optionset.has((OptionSpec)optionspec11) ? (String)optionspec11.value(optionset) : a_3913_L.u_1723_Y((String)optionspec10.value(optionset)).toString();
        String s6 = optionset.has((OptionSpec)optionspec20) ? (String)optionspec20.value(optionset) : null;
        String s7 = (String)Main.getValue(optionset, optionspec);
        Integer integer = (Integer)Main.getValue(optionset, optionspec1);
        n_3236_c.v_4262_N();
        y_3482_a.n_1700_B();
        y_3482_a.R_4764_Y();
        j_3341_s.h_1847_R();
        u_3100_Q session = new u_3100_Q((String)optionspec10.value(optionset), s5, (String)optionspec12.value(optionset), (String)optionspec21.value(optionset));
        GameConfig gameconfiguration = new GameConfig(new GameConfig.G_564_y(session, propertymap, propertymap1, proxy), new q_570_v(i, j, optionalint, optionalint1, flag), new GameConfig.n_1700_B(file1, file3, file2, s6), new GameConfig.J_1907_R(flag1, s3, s4, flag2, flag3), new GameConfig.R_4764_Y(s7, integer));
        Thread thread = new Thread("Client Shutdown Thread"){

            @Override
            public void run() {
                R_3197_Z integratedserver;
                MinecraftClient minecraft1 = MinecraftClient.A_4115_X();
                if (minecraft1 != null && (integratedserver = minecraft1.n_3318_d()) != null) {
                    integratedserver.n_1700_B(true);
                }
            }
        };
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
        Runtime.getRuntime().addShutdownHook(thread);
        new z_4693_k();
        try {
            Thread.currentThread().setName("Render thread");
            c_4037_x.n_1700_B();
            c_4037_x.g_2268_R();
            minecraft = new MinecraftClient(gameconfiguration);
            c_4037_x.T_3594_S();
        }
        catch (q_1272_r undeclaredexception) {
            LOGGER.warn("Failed to create window: ", (Throwable)undeclaredexception);
            return;
        }
        catch (Throwable throwable1) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Initializing game");
            crashreport.n_1700_B("Initialization");
            MinecraftClient.n_1700_B((LanguageManager)null, gameconfiguration.G_564_y.J_1907_R, (V_4423_d)null, crashreport);
            MinecraftClient.J_1907_R(crashreport);
            return;
        }
        if (minecraft.w_612_n()) {
            thread1 = new Thread("Game thread"){

                @Override
                public void run() {
                    try {
                        c_4037_x.n_1700_B(true);
                        minecraft.R_4764_Y();
                    }
                    catch (Throwable throwable2) {
                        LOGGER.error("Exception in client thread", throwable2);
                    }
                }
            };
            thread1.start();
            while (minecraft.Q_4569_t()) {
            }
        } else {
            thread1 = null;
            try {
                c_4037_x.n_1700_B(false);
                minecraft.R_4764_Y();
            }
            catch (Throwable throwable) {
                LOGGER.error("Unhandled game exception", throwable);
            }
        }
        try {
            minecraft.h_1847_R();
            if (thread1 != null) {
                thread1.join();
            }
        }
        catch (InterruptedException interruptedexception) {
            LOGGER.error("Exception during client thread shutdown", (Throwable)interruptedexception);
        }
        finally {
            minecraft.s_956_w();
        }
    }

    private static OptionalInt toOptionalInt(@Nullable Integer value) {
        return value != null ? OptionalInt.of(value) : OptionalInt.empty();
    }

    @Nullable
    private static <T> T getValue(OptionSet set, OptionSpec<T> option) {
        try {
            return (T)set.valueOf(option);
        }
        catch (Throwable throwable) {
            ArgumentAcceptingOptionSpec argumentacceptingoptionspec;
            List list;
            if (option instanceof ArgumentAcceptingOptionSpec && !(list = (argumentacceptingoptionspec = (ArgumentAcceptingOptionSpec)option).defaultValues()).isEmpty()) {
                return (T)list.get(0);
            }
            throw throwable;
        }
    }

    private static boolean isNotEmpty(@Nullable String str) {
        return str != null && !str.isEmpty();
    }

    static {
        System.setProperty("java.awt.headless", "true");
    }
}



