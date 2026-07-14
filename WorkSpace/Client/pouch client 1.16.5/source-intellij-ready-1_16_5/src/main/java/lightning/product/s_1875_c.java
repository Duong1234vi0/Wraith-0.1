/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonIOException
 *  com.google.gson.stream.JsonWriter
 *  com.mojang.datafixers.util.Function4
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DataResult$PartialResult
 *  com.mojang.serialization.JsonOps
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.G_3105_A;
import lightning.product.H_4757_Q;
import lightning.product.RegistryWriteOps;
import lightning.product.J_2011_a;
import lightning.product.SystemToast;
import lightning.product.O_3892_W;
import lightning.product.O_694_j;
import lightning.product.ResourceManager;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.b_2971_z;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.DataPackConfig;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.k_2603_m;
import lightning.product.WorldData;
import lightning.product.CommonComponents;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_1875_c
extends k_2603_m {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    private static final x_282_a R_4764_Y = new F_2904_S("selectWorld.enterName");
    private Button G_564_y;
    private final BooleanConsumer P_1922_E;
    private O_694_j u_1723_Y;
    private final b_2971_z.n_1700_B v_4262_N;

    public s_1875_c(BooleanConsumer p_i232318_1_, b_2971_z.n_1700_B p_i232318_2_) {
        super(new F_2904_S("selectWorld.edit.title"));
        this.P_1922_E = p_i232318_1_;
        this.v_4262_N = p_i232318_2_;
    }

    @Override
    public void tick() {
        this.u_1723_Y.tick();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        Button button = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 0 + 5, 200, 20, new F_2904_S("selectWorld.edit.resetIcon"), p_214309_1_ -> {
            FileUtils.deleteQuietly((File)this.v_4262_N.u_1723_Y());
            p_214309_1_.active = false;
        }));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 24 + 5, 200, 20, new F_2904_S("selectWorld.edit.openFolder"), p_214303_1_ -> j_3341_s.t_148_a().n_1700_B(this.v_4262_N.n_1700_B(H_4757_Q.t_148_a).toFile())));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 48 + 5, 200, 20, new F_2904_S("selectWorld.edit.backup"), p_214304_1_ -> {
            boolean flag = s_1875_c.n_1700_B(this.v_4262_N);
            this.P_1922_E.accept(!flag);
        }));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 72 + 5, 200, 20, new F_2904_S("selectWorld.edit.backupFolder"), p_214302_1_ -> {
            b_2971_z saveformat = this.minecraft.t_148_a();
            Path path = saveformat.R_4764_Y();
            try {
                Files.createDirectories(Files.exists(path, new LinkOption[0]) ? path.toRealPath(new LinkOption[0]) : path, new FileAttribute[0]);
            }
            catch (IOException ioexception) {
                throw new RuntimeException(ioexception);
            }
            j_3341_s.t_148_a().n_1700_B(path.toFile());
        }));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 96 + 5, 200, 20, new F_2904_S("selectWorld.edit.optimize"), p_214310_1_ -> this.minecraft.n_1700_B(new O_3892_W(this, (p_214305_1_, p_214305_2_) -> {
            if (p_214305_1_) {
                s_1875_c.n_1700_B(this.v_4262_N);
            }
            this.minecraft.n_1700_B(G_3105_A.n_1700_B(this.minecraft, this.P_1922_E, this.minecraft.p_178_J(), this.v_4262_N, p_214305_2_));
        }, new F_2904_S("optimizeWorld.confirm.title"), new F_2904_S("optimizeWorld.confirm.description"), true))));
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 5, 200, 20, new F_2904_S("selectWorld.edit.export_worldgen_settings"), p_239023_1_ -> {
            DataResult dataresult;
            r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
            try (MinecraftClient.n_1700_B minecraft$packmanager = this.minecraft.n_1700_B(dynamicregistries$impl, MinecraftClient::n_1700_B, (Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData>)((Function4)MinecraftClient::n_1700_B), false, this.v_4262_N);){
                RegistryWriteOps dynamicops = RegistryWriteOps.n_1700_B(JsonOps.INSTANCE, dynamicregistries$impl);
                DataResult dataresult1 = j_419_j.n_1700_B.encodeStart(dynamicops, (Object)minecraft$packmanager.R_4764_Y().e_4240_b());
                dataresult = dataresult1.flatMap(p_239017_1_ -> {
                    Path path = this.v_4262_N.n_1700_B(H_4757_Q.t_148_a).resolve("worldgen_settings_export.json");
                    try (JsonWriter jsonwriter = J_1907_R.newJsonWriter((Writer)Files.newBufferedWriter(path, StandardCharsets.UTF_8, new OpenOption[0]));){
                        J_1907_R.toJson(p_239017_1_, jsonwriter);
                    }
                    catch (JsonIOException | IOException ioexception) {
                        return DataResult.error((String)("Error writing file: " + ioexception.getMessage()));
                    }
                    return DataResult.success((Object)path.toString());
                });
            }
            catch (InterruptedException | ExecutionException interruptedexception) {
                dataresult = DataResult.error((String)"Could not parse level data!");
            }
            U_2871_b itextcomponent = new U_2871_b((String)dataresult.get().map(Function.identity(), DataResult.PartialResult::message));
            F_2904_S itextcomponent1 = new F_2904_S(dataresult.result().isPresent() ? "selectWorld.edit.export_worldgen_settings.success" : "selectWorld.edit.export_worldgen_settings.failure");
            dataresult.error().ifPresent(p_239018_0_ -> n_1700_B.error("Error exporting world settings: {}", p_239018_0_));
            this.minecraft.e_1992_r().n_1700_B(SystemToast.n_1700_B(this.minecraft, SystemToast.n_1700_B.G_564_y, (x_282_a)itextcomponent1, (x_282_a)itextcomponent));
        }));
        this.G_564_y = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 144 + 5, 98, 20, new F_2904_S("selectWorld.edit.save"), p_214308_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 + 2, this.height / 4 + 144 + 5, 98, 20, CommonComponents.G_564_y, p_214306_1_ -> this.P_1922_E.accept(false)));
        button.active = this.v_4262_N.u_1723_Y().isFile();
        J_2011_a worldsummary = this.v_4262_N.G_564_y();
        String s = worldsummary == null ? "" : worldsummary.J_1907_R();
        this.u_1723_Y = new O_694_j(this.font, this.width / 2 - 100, 38, 200, 20, new F_2904_S("selectWorld.enterName"));
        this.u_1723_Y.setText(s);
        this.u_1723_Y.setResponder(p_214301_1_ -> {
            this.G_564_y.active = !p_214301_1_.trim().isEmpty();
        });
        this.children.add(this.u_1723_Y);
        this.n_1700_B(this.u_1723_Y);
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.u_1723_Y.getText();
        this.init(minecraft, width, height);
        this.u_1723_Y.setText(s);
    }

    @Override
    public void closeScreen() {
        this.P_1922_E.accept(false);
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void n_1700_B() {
        try {
            this.v_4262_N.n_1700_B(this.u_1723_Y.getText().trim());
            this.P_1922_E.accept(true);
        }
        catch (IOException ioexception) {
            n_1700_B.error("Failed to access world '{}'", (Object)this.v_4262_N.n_1700_B(), (Object)ioexception);
            SystemToast.n_1700_B(this.minecraft, this.v_4262_N.n_1700_B());
            this.P_1922_E.accept(true);
        }
    }

    public static void n_1700_B(b_2971_z p_241651_0_, String p_241651_1_) {
        boolean flag = false;
        try (b_2971_z.n_1700_B saveformat$levelsave = p_241651_0_.R_4764_Y(p_241651_1_);){
            flag = true;
            s_1875_c.n_1700_B(saveformat$levelsave);
        }
        catch (IOException ioexception) {
            if (!flag) {
                SystemToast.n_1700_B(MinecraftClient.A_4115_X(), p_241651_1_);
            }
            n_1700_B.warn("Failed to create backup of level {}", (Object)p_241651_1_, (Object)ioexception);
        }
    }

    public static boolean n_1700_B(b_2971_z.n_1700_B p_239019_0_) {
        long i = 0L;
        IOException ioexception = null;
        try {
            i = p_239019_0_.w_1484_f();
        }
        catch (IOException ioexception1) {
            ioexception = ioexception1;
        }
        if (ioexception != null) {
            F_2904_S itextcomponent2 = new F_2904_S("selectWorld.edit.backupFailed");
            U_2871_b itextcomponent3 = new U_2871_b(ioexception.getMessage());
            MinecraftClient.A_4115_X().e_1992_r().n_1700_B(new SystemToast(SystemToast.n_1700_B.R_4764_Y, itextcomponent2, itextcomponent3));
            return false;
        }
        F_2904_S itextcomponent = new F_2904_S("selectWorld.edit.backupCreated", p_239019_0_.n_1700_B());
        F_2904_S itextcomponent1 = new F_2904_S("selectWorld.edit.backupSize", u_530_F.P_1922_E((double)i / 1048576.0));
        MinecraftClient.A_4115_X().e_1992_r().n_1700_B(new SystemToast(SystemToast.n_1700_B.R_4764_Y, itextcomponent, itextcomponent1));
        return true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        s_1875_c.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        s_1875_c.drawString(matrixStack, this.font, R_4764_Y, this.width / 2 - 100, 24, 0xA0A0A0);
        this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



