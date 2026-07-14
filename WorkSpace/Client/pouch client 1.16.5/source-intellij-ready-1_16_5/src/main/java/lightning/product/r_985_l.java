/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonIOException
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DataResult$PartialResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  com.mojang.serialization.Lifecycle
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.PointerBuffer
 *  org.lwjgl.util.tinyfd.TinyFileDialogs
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lightning.product.PackRepository;
import lightning.product.F_1561_E;
import lightning.product.F_2904_S;
import lightning.product.F_877_l;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.RegistryWriteOps;
import lightning.product.N_2445_q;
import lightning.product.SystemToast;
import lightning.product.O_694_j;
import lightning.product.Q_2241_p;
import lightning.product.Widget;
import lightning.product.T_4652_I;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.Y_4083_F;
import lightning.product.ServerResources;
import lightning.product.MinecraftClient;
import lightning.product.e_1813_Z;
import lightning.product.e_4716_U;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.k_2603_m;
import lightning.product.o_1792_J;
import lightning.product.PackSource;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.r_4097_j;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.PointerBuffer;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

public class r_985_l
implements Widget,
e_1813_Z {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final x_282_a R_4764_Y = new F_2904_S("generator.custom");
    private static final x_282_a G_564_y = new F_2904_S("generator.amplified.info");
    private static final x_282_a P_1922_E = new F_2904_S("selectWorld.mapFeatures.info");
    private N_2445_q u_1723_Y = N_2445_q.n_1700_B;
    private Y_4083_F v_4262_N;
    private int w_1484_f;
    private O_694_j t_148_a;
    private Button s_956_w;
    public Button n_1700_B;
    private Button u_2550_I;
    private Button M_588_G;
    private Button P_4830_p;
    private r_4097_j.J_1907_R h_1847_R;
    private j_419_j Q_4569_t;
    private Optional<F_1561_E> M_182_A;
    private OptionalLong t_1786_h;

    public r_985_l(r_4097_j.J_1907_R p_i242065_1_, j_419_j p_i242065_2_, Optional<F_1561_E> p_i242065_3_, OptionalLong p_i242065_4_) {
        this.h_1847_R = p_i242065_1_;
        this.Q_4569_t = p_i242065_2_;
        this.M_182_A = p_i242065_3_;
        this.t_1786_h = p_i242065_4_;
    }

    public void n_1700_B(final o_1792_J p_239048_1_, MinecraftClient p_239048_2_, Y_4083_F p_239048_3_) {
        this.v_4262_N = p_239048_3_;
        this.w_1484_f = p_239048_1_.width;
        this.t_148_a = new O_694_j(this.v_4262_N, this.w_1484_f / 2 - 100, 60, 200, 20, new F_2904_S("selectWorld.enterSeed"));
        this.t_148_a.setText(r_985_l.n_1700_B(this.t_1786_h));
        this.t_148_a.setResponder(p_239058_1_ -> {
            this.t_1786_h = this.R_4764_Y();
        });
        p_239048_1_.addListener(this.t_148_a);
        int i = this.w_1484_f / 2 - 155;
        int j = this.w_1484_f / 2 + 5;
        this.s_956_w = p_239048_1_.addButton(new Button(i, 100, 150, 20, new F_2904_S("selectWorld.mapFeatures"), p_239056_1_ -> {
            this.Q_4569_t = this.Q_4569_t.u_2550_I();
            p_239056_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return CommonComponents.n_1700_B(super.getMessage(), r_985_l.this.Q_4569_t.J_1907_R());
            }

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(". ").n_1700_B(new F_2904_S("selectWorld.mapFeatures.info"));
            }
        });
        this.s_956_w.visible = false;
        this.u_2550_I = p_239048_1_.addButton(new Button(j, 100, 150, 20, new F_2904_S("selectWorld.mapType"), p_239050_2_ -> {
            while (this.M_182_A.isPresent()) {
                int k = F_1561_E.R_4764_Y.indexOf(this.M_182_A.get()) + 1;
                if (k >= F_1561_E.R_4764_Y.size()) {
                    k = 0;
                }
                F_1561_E biomegeneratortypescreens = F_1561_E.R_4764_Y.get(k);
                this.M_182_A = Optional.of(biomegeneratortypescreens);
                this.Q_4569_t = biomegeneratortypescreens.n_1700_B(this.h_1847_R, this.Q_4569_t.n_1700_B(), this.Q_4569_t.J_1907_R(), this.Q_4569_t.R_4764_Y());
                if (this.Q_4569_t.v_4262_N() && !k_2603_m.hasShiftDown()) continue;
            }
            p_239048_1_.n_1700_B();
            p_239050_2_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return super.getMessage().P_1922_E().n_1700_B(" ").n_1700_B(r_985_l.this.M_182_A.map(F_1561_E::n_1700_B).orElse(R_4764_Y));
            }

            @Override
            protected MutableComponent getNarrationMessage() {
                return Objects.equals(r_985_l.this.M_182_A, Optional.of(F_1561_E.J_1907_R)) ? super.getNarrationMessage().n_1700_B(". ").n_1700_B(G_564_y) : super.getNarrationMessage();
            }
        });
        this.u_2550_I.visible = false;
        this.u_2550_I.active = this.M_182_A.isPresent();
        this.M_588_G = p_239048_1_.addButton(new Button(j, 120, 150, 20, new F_2904_S("selectWorld.customizeType"), p_239044_3_ -> {
            F_1561_E.n_1700_B biomegeneratortypescreens$ifactory = F_1561_E.G_564_y.get(this.M_182_A);
            if (biomegeneratortypescreens$ifactory != null) {
                p_239048_2_.n_1700_B(biomegeneratortypescreens$ifactory.createEditScreen(p_239048_1_, this.Q_4569_t));
            }
        }));
        this.M_588_G.visible = false;
        this.n_1700_B = p_239048_1_.addButton(new Button(i, 151, 150, 20, new F_2904_S("selectWorld.bonusItems"), p_239047_1_ -> {
            this.Q_4569_t = this.Q_4569_t.M_588_G();
            p_239047_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return CommonComponents.n_1700_B(super.getMessage(), r_985_l.this.Q_4569_t.R_4764_Y() && !p_239048_1_.n_1700_B);
            }
        });
        this.n_1700_B.visible = false;
        this.P_4830_p = p_239048_1_.addButton(new Button(i, 185, 150, 20, new F_2904_S("selectWorld.import_worldgen_settings"), p_239049_3_ -> {
            F_2904_S translationtextcomponent = new F_2904_S("selectWorld.import_worldgen_settings.select_file");
            String s = TinyFileDialogs.tinyfd_openFileDialog((CharSequence)translationtextcomponent.getString(), (CharSequence)null, (PointerBuffer)null, (CharSequence)null, (boolean)false);
            if (s != null) {
                DataResult dataresult;
                ServerResources datapackregistries;
                r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
                PackRepository resourcepacklist = new PackRepository(new T_4652_I(), new e_4716_U(p_239048_1_.R_4764_Y().toFile(), PackSource.R_4764_Y));
                try {
                    net.minecraft.server.G_564_y.n_1700_B(resourcepacklist, p_239048_1_.J_1907_R, false);
                    CompletableFuture<ServerResources> completablefuture = ServerResources.n_1700_B(resourcepacklist.u_1723_Y(), Q_2241_p.n_1700_B.R_4764_Y, 2, j_3341_s.u_1723_Y(), p_239048_2_);
                    p_239048_2_.R_4764_Y(completablefuture::isDone);
                    datapackregistries = completablefuture.get();
                }
                catch (InterruptedException | ExecutionException interruptedexception) {
                    J_1907_R.error("Error loading data packs when importing world settings", (Throwable)interruptedexception);
                    F_2904_S itextcomponent = new F_2904_S("selectWorld.import_worldgen_settings.failure");
                    U_2871_b itextcomponent1 = new U_2871_b(interruptedexception.getMessage());
                    p_239048_2_.e_1992_r().n_1700_B(SystemToast.n_1700_B(p_239048_2_, SystemToast.n_1700_B.G_564_y, (x_282_a)itextcomponent, (x_282_a)itextcomponent1));
                    resourcepacklist.close();
                    return;
                }
                F_877_l worldsettingsimport = F_877_l.n_1700_B(JsonOps.INSTANCE, datapackregistries.w_1484_f(), dynamicregistries$impl);
                JsonParser jsonparser = new JsonParser();
                try (BufferedReader bufferedreader = Files.newBufferedReader(Paths.get(s, new String[0]));){
                    JsonElement jsonelement = jsonparser.parse((Reader)bufferedreader);
                    dataresult = j_419_j.n_1700_B.parse(worldsettingsimport, (Object)jsonelement);
                }
                catch (JsonIOException | JsonSyntaxException | IOException ioexception) {
                    dataresult = DataResult.error((String)("Failed to parse file: " + ioexception.getMessage()));
                }
                if (dataresult.error().isPresent()) {
                    F_2904_S itextcomponent2 = new F_2904_S("selectWorld.import_worldgen_settings.failure");
                    String s1 = ((DataResult.PartialResult)dataresult.error().get()).message();
                    J_1907_R.error("Error parsing world settings: {}", (Object)s1);
                    U_2871_b itextcomponent3 = new U_2871_b(s1);
                    p_239048_2_.e_1992_r().n_1700_B(SystemToast.n_1700_B(p_239048_2_, SystemToast.n_1700_B.G_564_y, (x_282_a)itextcomponent2, (x_282_a)itextcomponent3));
                }
                datapackregistries.close();
                Lifecycle lifecycle = dataresult.lifecycle();
                dataresult.resultOrPartial(arg_0 -> ((Logger)J_1907_R).error(arg_0)).ifPresent(p_239046_5_ -> {
                    BooleanConsumer booleanconsumer = p_239045_5_ -> {
                        p_239048_2_.n_1700_B(p_239048_1_);
                        if (p_239045_5_) {
                            this.n_1700_B(dynamicregistries$impl, (j_419_j)p_239046_5_);
                        }
                    };
                    if (lifecycle == Lifecycle.stable()) {
                        this.n_1700_B(dynamicregistries$impl, (j_419_j)p_239046_5_);
                    } else if (lifecycle == Lifecycle.experimental()) {
                        p_239048_2_.n_1700_B(new q_3418_t(booleanconsumer, new F_2904_S("selectWorld.import_worldgen_settings.experimental.title"), new F_2904_S("selectWorld.import_worldgen_settings.experimental.question")));
                    } else {
                        p_239048_2_.n_1700_B(new q_3418_t(booleanconsumer, new F_2904_S("selectWorld.import_worldgen_settings.deprecated.title"), new F_2904_S("selectWorld.import_worldgen_settings.deprecated.question")));
                    }
                });
            }
        }));
        this.P_4830_p.visible = false;
        this.u_1723_Y = N_2445_q.n_1700_B(p_239048_3_, (FormattedText)G_564_y, this.u_2550_I.getWidth());
    }

    private void n_1700_B(r_4097_j.J_1907_R p_239052_1_, j_419_j p_239052_2_) {
        this.h_1847_R = p_239052_1_;
        this.Q_4569_t = p_239052_2_;
        this.M_182_A = F_1561_E.n_1700_B(p_239052_2_);
        this.t_1786_h = OptionalLong.of(p_239052_2_.n_1700_B());
        this.t_148_a.setText(r_985_l.n_1700_B(this.t_1786_h));
        this.u_2550_I.active = this.M_182_A.isPresent();
    }

    @Override
    public void tick() {
        this.t_148_a.tick();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.s_956_w.visible) {
            this.v_4262_N.n_1700_B(matrixStack, P_1922_E, (float)(this.w_1484_f / 2 - 150), 122.0f, -6250336);
        }
        this.t_148_a.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.M_182_A.equals(Optional.of(F_1561_E.J_1907_R))) {
            this.u_1723_Y.J_1907_R(matrixStack, this.u_2550_I.x + 2, this.u_2550_I.y + 22, 9, 0xA0A0A0);
        }
    }

    protected void n_1700_B(j_419_j p_239043_1_) {
        this.Q_4569_t = p_239043_1_;
    }

    private static String n_1700_B(OptionalLong p_243445_0_) {
        return p_243445_0_.isPresent() ? Long.toString(p_243445_0_.getAsLong()) : "";
    }

    private static OptionalLong n_1700_B(String p_239053_0_) {
        try {
            return OptionalLong.of(Long.parseLong(p_239053_0_));
        }
        catch (NumberFormatException numberformatexception) {
            return OptionalLong.empty();
        }
    }

    public j_419_j n_1700_B(boolean p_239054_1_) {
        OptionalLong optionallong = this.R_4764_Y();
        return this.Q_4569_t.n_1700_B(p_239054_1_, optionallong);
    }

    private OptionalLong R_4764_Y() {
        OptionalLong optionallong1;
        String s = this.t_148_a.getText();
        OptionalLong optionallong = StringUtils.isEmpty((CharSequence)s) ? OptionalLong.empty() : ((optionallong1 = r_985_l.n_1700_B(s)).isPresent() && optionallong1.getAsLong() != 0L ? optionallong1 : OptionalLong.of(s.hashCode()));
        return optionallong;
    }

    public boolean n_1700_B() {
        return this.Q_4569_t.v_4262_N();
    }

    public void J_1907_R(boolean p_239059_1_) {
        this.u_2550_I.visible = p_239059_1_;
        if (this.Q_4569_t.v_4262_N()) {
            this.s_956_w.visible = false;
            this.n_1700_B.visible = false;
            this.M_588_G.visible = false;
            this.P_4830_p.visible = false;
        } else {
            this.s_956_w.visible = p_239059_1_;
            this.n_1700_B.visible = p_239059_1_;
            this.M_588_G.visible = p_239059_1_ && F_1561_E.G_564_y.containsKey(this.M_182_A);
            this.P_4830_p.visible = p_239059_1_;
        }
        this.t_148_a.setVisible(p_239059_1_);
    }

    public r_4097_j.J_1907_R J_1907_R() {
        return this.h_1847_R;
    }

    void n_1700_B(ServerResources p_243447_1_) {
        r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
        RegistryWriteOps worldgensettingsexport = RegistryWriteOps.n_1700_B(JsonOps.INSTANCE, this.h_1847_R);
        F_877_l worldsettingsimport = F_877_l.n_1700_B(JsonOps.INSTANCE, p_243447_1_.w_1484_f(), dynamicregistries$impl);
        DataResult dataresult = j_419_j.n_1700_B.encodeStart(worldgensettingsexport, (Object)this.Q_4569_t).flatMap(p_243446_1_ -> j_419_j.n_1700_B.parse((DynamicOps)worldsettingsimport, p_243446_1_));
        dataresult.resultOrPartial(j_3341_s.n_1700_B("Error parsing worldgen settings after loading data packs: ", arg_0 -> ((Logger)J_1907_R).error(arg_0))).ifPresent(p_243448_2_ -> {
            this.Q_4569_t = p_243448_2_;
            this.h_1847_R = dynamicregistries$impl;
        });
    }
}



