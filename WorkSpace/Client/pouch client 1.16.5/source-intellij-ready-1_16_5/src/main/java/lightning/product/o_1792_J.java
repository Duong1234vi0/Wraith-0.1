/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4315_y;
import lightning.product.PackRepository;
import lightning.product.F_1561_E;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.MutableComponent;
import lightning.product.H_4757_Q;
import lightning.product.I_14_v;
import lightning.product.K_1289_S;
import lightning.product.SystemToast;
import lightning.product.O_694_j;
import lightning.product.Q_2241_p;
import lightning.product.R_2450_T;
import lightning.product.GenericDirtMessageScreen;
import lightning.product.T_4652_I;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.V_3049_B;
import lightning.product.V_3137_a;
import lightning.product.Button;
import lightning.product.ServerResources;
import lightning.product.a_658_u;
import lightning.product.b_2971_z;
import lightning.product.MinecraftClient;
import lightning.product.e_4716_U;
import lightning.product.g_221_o;
import lightning.product.DataPackConfig;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.k_2603_m;
import lightning.product.PackSource;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.r_146_S;
import lightning.product.r_4097_j;
import lightning.product.r_985_l;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class o_1792_J
extends k_2603_m {
    private static final Logger G_564_y = LogManager.getLogger();
    private static final x_282_a P_1922_E = new F_2904_S("selectWorld.gameMode");
    private static final x_282_a u_1723_Y = new F_2904_S("selectWorld.enterSeed");
    private static final x_282_a v_4262_N = new F_2904_S("selectWorld.seedInfo");
    private static final x_282_a w_1484_f = new F_2904_S("selectWorld.enterName");
    private static final x_282_a t_148_a = new F_2904_S("selectWorld.resultFolder");
    private static final x_282_a s_956_w = new F_2904_S("selectWorld.allowCommands.info");
    private final k_2603_m u_2550_I;
    private O_694_j M_588_G;
    private String P_4830_p;
    private J_1907_R h_1847_R = lightning.product.o_1792_J$J_1907_R.n_1700_B;
    @Nullable
    private J_1907_R Q_4569_t;
    private R_2450_T M_182_A = R_2450_T.R_4764_Y;
    private R_2450_T t_1786_h = R_2450_T.R_4764_Y;
    private boolean multiplayerClientSuggestionProvider;
    private boolean w_1457_N;
    public boolean n_1700_B;
    protected DataPackConfig J_1907_R;
    @Nullable
    private Path Y_601_j;
    @Nullable
    private PackRepository Y_259_p;
    private boolean Q_2552_b;
    private Button C_2741_M;
    private Button k_2293_S;
    private Button q_2307_F;
    private Button Z_875_P;
    private Button c_3005_b;
    private Button H_2857_Y;
    private Button A_4115_X;
    private x_282_a Y_1740_V;
    private x_282_a t_4043_B;
    private String x_607_J;
    private A_2352_Z e_4240_b = new A_2352_Z();
    public final r_985_l R_4764_Y;

    public o_1792_J(@Nullable k_2603_m p_i242064_1_, B_4315_y p_i242064_2_, j_419_j p_i242064_3_, @Nullable Path p_i242064_4_, DataPackConfig p_i242064_5_, r_4097_j.J_1907_R p_i242064_6_) {
        this(p_i242064_1_, p_i242064_5_, new r_985_l(p_i242064_6_, p_i242064_3_, F_1561_E.n_1700_B(p_i242064_3_), OptionalLong.of(p_i242064_3_.n_1700_B())));
        this.x_607_J = p_i242064_2_.n_1700_B();
        this.multiplayerClientSuggestionProvider = p_i242064_2_.P_1922_E();
        this.w_1457_N = true;
        this.t_1786_h = this.M_182_A = p_i242064_2_.G_564_y();
        this.e_4240_b.n_1700_B(p_i242064_2_.u_1723_Y(), null);
        if (p_i242064_2_.R_4764_Y()) {
            this.h_1847_R = lightning.product.o_1792_J$J_1907_R.J_1907_R;
        } else if (p_i242064_2_.J_1907_R().u_1723_Y()) {
            this.h_1847_R = lightning.product.o_1792_J$J_1907_R.n_1700_B;
        } else if (p_i242064_2_.J_1907_R().P_1922_E()) {
            this.h_1847_R = lightning.product.o_1792_J$J_1907_R.R_4764_Y;
        }
        this.Y_601_j = p_i242064_4_;
    }

    public static o_1792_J n_1700_B(@Nullable k_2603_m p_243425_0_) {
        r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
        return new o_1792_J(p_243425_0_, DataPackConfig.n_1700_B, new r_985_l(dynamicregistries$impl, j_419_j.n_1700_B(dynamicregistries$impl.J_1907_R(V_3137_a.d_2427_y), dynamicregistries$impl.J_1907_R(V_3137_a.PlayerInfo), dynamicregistries$impl.J_1907_R(V_3137_a.e_1992_r)), Optional.of(F_1561_E.n_1700_B), OptionalLong.empty()));
    }

    private o_1792_J(@Nullable k_2603_m p_i242063_1_, DataPackConfig p_i242063_2_, r_985_l p_i242063_3_) {
        super(new F_2904_S("selectWorld.create"));
        this.u_2550_I = p_i242063_1_;
        this.x_607_J = K_1289_S.n_1700_B("selectWorld.newWorld", new Object[0]);
        this.J_1907_R = p_i242063_2_;
        this.R_4764_Y = p_i242063_3_;
    }

    @Override
    public void tick() {
        this.M_588_G.tick();
        this.R_4764_Y.tick();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.M_588_G = new O_694_j(this.font, this.width / 2 - 100, 60, 200, 20, (x_282_a)new F_2904_S("selectWorld.enterName")){

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(". ").n_1700_B(new F_2904_S("selectWorld.resultFolder")).n_1700_B(" ").n_1700_B(o_1792_J.this.P_4830_p);
            }
        };
        this.M_588_G.setText(this.x_607_J);
        this.M_588_G.setResponder(p_214319_1_ -> {
            this.x_607_J = p_214319_1_;
            this.C_2741_M.active = !this.M_588_G.getText().isEmpty();
            this.P_1922_E();
        });
        this.children.add(this.M_588_G);
        int i = this.width / 2 - 155;
        int j = this.width / 2 + 5;
        this.k_2293_S = this.addButton(new Button(i, 100, 150, 20, U_2871_b.R_4764_Y, p_214316_1_ -> {
            switch (this.h_1847_R.ordinal()) {
                case 0: {
                    this.n_1700_B(lightning.product.o_1792_J$J_1907_R.J_1907_R);
                    break;
                }
                case 1: {
                    this.n_1700_B(lightning.product.o_1792_J$J_1907_R.R_4764_Y);
                    break;
                }
                case 2: {
                    this.n_1700_B(lightning.product.o_1792_J$J_1907_R.n_1700_B);
                }
            }
            p_214316_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return new F_2904_S("options.generic_value", P_1922_E, new F_2904_S("selectWorld.gameMode." + o_1792_J.this.h_1847_R.P_1922_E));
            }

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(". ").n_1700_B(o_1792_J.this.Y_1740_V).n_1700_B(" ").n_1700_B(o_1792_J.this.t_4043_B);
            }
        });
        this.q_2307_F = this.addButton(new Button(j, 100, 150, 20, new F_2904_S("options.difficulty"), p_238956_1_ -> {
            this.t_1786_h = this.M_182_A = this.M_182_A.G_564_y();
            p_238956_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return new F_2904_S("options.difficulty").n_1700_B(": ").n_1700_B(o_1792_J.this.t_1786_h.J_1907_R());
            }
        });
        this.A_4115_X = this.addButton(new Button(i, 151, 150, 20, new F_2904_S("selectWorld.allowCommands"), p_214322_1_ -> {
            this.w_1457_N = true;
            this.multiplayerClientSuggestionProvider = !this.multiplayerClientSuggestionProvider;
            p_214322_1_.queueNarration(250);
        }){

            @Override
            public x_282_a getMessage() {
                return CommonComponents.n_1700_B(super.getMessage(), o_1792_J.this.multiplayerClientSuggestionProvider && !o_1792_J.this.n_1700_B);
            }

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(". ").n_1700_B(new F_2904_S("selectWorld.allowCommands.info"));
            }
        });
        this.H_2857_Y = this.addButton(new Button(j, 151, 150, 20, new F_2904_S("selectWorld.dataPacks"), p_214320_1_ -> this.t_148_a()));
        this.c_3005_b = this.addButton(new Button(i, 185, 150, 20, new F_2904_S("selectWorld.gameRules"), p_214312_1_ -> this.minecraft.n_1700_B(new a_658_u(this.e_4240_b.J_1907_R(), p_238946_1_ -> {
            this.minecraft.n_1700_B(this);
            p_238946_1_.ifPresent(p_238941_1_ -> {
                this.e_4240_b = p_238941_1_;
            });
        }))));
        this.R_4764_Y.n_1700_B(this, this.minecraft, this.font);
        this.Z_875_P = this.addButton(new Button(j, 185, 150, 20, new F_2904_S("selectWorld.moreWorldOptions"), p_214321_1_ -> this.v_4262_N()));
        this.C_2741_M = this.addButton(new Button(i, this.height - 28, 150, 20, new F_2904_S("selectWorld.create"), p_214318_1_ -> this.u_1723_Y()));
        this.C_2741_M.active = !this.x_607_J.isEmpty();
        this.addButton(new Button(j, this.height - 28, 150, 20, CommonComponents.G_564_y, p_214317_1_ -> this.J_1907_R()));
        this.n_1700_B();
        this.n_1700_B(this.M_588_G);
        this.n_1700_B(this.h_1847_R);
        this.P_1922_E();
    }

    private void G_564_y() {
        this.Y_1740_V = new F_2904_S("selectWorld.gameMode." + this.h_1847_R.P_1922_E + ".line1");
        this.t_4043_B = new F_2904_S("selectWorld.gameMode." + this.h_1847_R.P_1922_E + ".line2");
    }

    private void P_1922_E() {
        this.P_4830_p = this.M_588_G.getText().trim();
        if (this.P_4830_p.isEmpty()) {
            this.P_4830_p = "World";
        }
        try {
            this.P_4830_p = r_146_S.n_1700_B(this.minecraft.t_148_a().J_1907_R(), this.P_4830_p, "");
        }
        catch (Exception exception1) {
            this.P_4830_p = "World";
            try {
                this.P_4830_p = r_146_S.n_1700_B(this.minecraft.t_148_a().J_1907_R(), this.P_4830_p, "");
            }
            catch (Exception exception) {
                throw new RuntimeException("Could not create save folder", exception);
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void u_1723_Y() {
        this.minecraft.R_4764_Y(new GenericDirtMessageScreen(new F_2904_S("createWorld.preparing")));
        if (this.u_2550_I()) {
            B_4315_y worldsettings;
            this.w_1484_f();
            j_419_j dimensiongeneratorsettings = this.R_4764_Y.n_1700_B(this.n_1700_B);
            if (dimensiongeneratorsettings.v_4262_N()) {
                A_2352_Z gamerules = new A_2352_Z();
                gamerules.n_1700_B(A_2352_Z.s_956_w).n_1700_B(false, (G_564_y)null);
                worldsettings = new B_4315_y(this.M_588_G.getText().trim(), I_14_v.P_1922_E, false, R_2450_T.n_1700_B, true, gamerules, DataPackConfig.n_1700_B);
            } else {
                worldsettings = new B_4315_y(this.M_588_G.getText().trim(), this.h_1847_R.u_1723_Y, this.n_1700_B, this.t_1786_h, this.multiplayerClientSuggestionProvider && !this.n_1700_B, this.e_4240_b, this.J_1907_R);
            }
            this.minecraft.n_1700_B(this.P_4830_p, worldsettings, this.R_4764_Y.J_1907_R(), dimensiongeneratorsettings);
        }
    }

    private void v_4262_N() {
        this.n_1700_B(!this.Q_2552_b);
    }

    private void n_1700_B(J_1907_R p_228200_1_) {
        if (!this.w_1457_N) {
            boolean bl = this.multiplayerClientSuggestionProvider = p_228200_1_ == lightning.product.o_1792_J$J_1907_R.R_4764_Y;
        }
        if (p_228200_1_ == lightning.product.o_1792_J$J_1907_R.J_1907_R) {
            this.n_1700_B = true;
            this.A_4115_X.active = false;
            this.R_4764_Y.n_1700_B.active = false;
            this.t_1786_h = R_2450_T.G_564_y;
            this.q_2307_F.active = false;
        } else {
            this.n_1700_B = false;
            this.A_4115_X.active = true;
            this.R_4764_Y.n_1700_B.active = true;
            this.t_1786_h = this.M_182_A;
            this.q_2307_F.active = true;
        }
        this.h_1847_R = p_228200_1_;
        this.G_564_y();
    }

    public void n_1700_B() {
        this.n_1700_B(this.Q_2552_b);
    }

    private void n_1700_B(boolean toggle) {
        this.Q_2552_b = toggle;
        this.k_2293_S.visible = !this.Q_2552_b;
        boolean bl = this.q_2307_F.visible = !this.Q_2552_b;
        if (this.R_4764_Y.n_1700_B()) {
            this.H_2857_Y.visible = false;
            this.k_2293_S.active = false;
            if (this.Q_4569_t == null) {
                this.Q_4569_t = this.h_1847_R;
            }
            this.n_1700_B(lightning.product.o_1792_J$J_1907_R.G_564_y);
            this.A_4115_X.visible = false;
        } else {
            this.k_2293_S.active = true;
            if (this.Q_4569_t != null) {
                this.n_1700_B(this.Q_4569_t);
            }
            this.A_4115_X.visible = !this.Q_2552_b;
            this.H_2857_Y.visible = !this.Q_2552_b;
        }
        this.R_4764_Y.J_1907_R(this.Q_2552_b);
        this.M_588_G.setVisible(!this.Q_2552_b);
        if (this.Q_2552_b) {
            this.Z_875_P.setMessage(CommonComponents.R_4764_Y);
        } else {
            this.Z_875_P.setMessage(new F_2904_S("selectWorld.moreWorldOptions"));
        }
        this.c_3005_b.visible = !this.Q_2552_b;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode != 257 && keyCode != 335) {
            return false;
        }
        this.u_1723_Y();
        return true;
    }

    @Override
    public void closeScreen() {
        if (this.Q_2552_b) {
            this.n_1700_B(false);
        } else {
            this.J_1907_R();
        }
    }

    public void J_1907_R() {
        this.minecraft.n_1700_B(this.u_2550_I);
        this.w_1484_f();
    }

    private void w_1484_f() {
        if (this.Y_259_p != null) {
            this.Y_259_p.close();
        }
        this.s_956_w();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        o_1792_J.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, -1);
        if (this.Q_2552_b) {
            o_1792_J.drawString(matrixStack, this.font, u_1723_Y, this.width / 2 - 100, 47, -6250336);
            o_1792_J.drawString(matrixStack, this.font, v_4262_N, this.width / 2 - 100, 85, -6250336);
            this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        } else {
            o_1792_J.drawString(matrixStack, this.font, w_1484_f, this.width / 2 - 100, 47, -6250336);
            o_1792_J.drawString(matrixStack, this.font, new U_2871_b("").n_1700_B(t_148_a).n_1700_B(" ").n_1700_B(this.P_4830_p), this.width / 2 - 100, 85, -6250336);
            this.M_588_G.render(matrixStack, mouseX, mouseY, partialTicks);
            o_1792_J.drawString(matrixStack, this.font, this.Y_1740_V, this.width / 2 - 150, 122, -6250336);
            o_1792_J.drawString(matrixStack, this.font, this.t_4043_B, this.width / 2 - 150, 134, -6250336);
            if (this.A_4115_X.visible) {
                o_1792_J.drawString(matrixStack, this.font, s_956_w, this.width / 2 - 150, 172, -6250336);
            }
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected <T extends GuiEventListener> T addListener(T listener) {
        return super.addListener(listener);
    }

    @Override
    protected <T extends V_2511_L> T addButton(T button) {
        return super.addButton(button);
    }

    @Nullable
    protected Path R_4764_Y() {
        if (this.Y_601_j == null) {
            try {
                this.Y_601_j = Files.createTempDirectory("mcworld-", new FileAttribute[0]);
            }
            catch (IOException ioexception) {
                G_564_y.warn("Failed to create temporary dir", (Throwable)ioexception);
                SystemToast.R_4764_Y(this.minecraft, this.P_4830_p);
                this.J_1907_R();
            }
        }
        return this.Y_601_j;
    }

    private void t_148_a() {
        Pair<File, PackRepository> pair = this.M_588_G();
        if (pair != null) {
            this.minecraft.n_1700_B(new V_3049_B(this, (PackRepository)pair.getSecond(), this::n_1700_B, (File)pair.getFirst(), new F_2904_S("dataPack.title")));
        }
    }

    private void n_1700_B(PackRepository p_241621_1_) {
        ImmutableList list = ImmutableList.copyOf(p_241621_1_.G_564_y());
        List list1 = (List)p_241621_1_.J_1907_R().stream().filter(arg_0 -> o_1792_J.n_1700_B((List)list, arg_0)).collect(ImmutableList.toImmutableList());
        DataPackConfig datapackcodec = new DataPackConfig((List<String>)list, list1);
        if (list.equals(this.J_1907_R.n_1700_B())) {
            this.J_1907_R = datapackcodec;
        } else {
            this.minecraft.w_1484_f(() -> this.minecraft.n_1700_B(new GenericDirtMessageScreen(new F_2904_S("dataPack.validation.working"))));
            ServerResources.n_1700_B(p_241621_1_.u_1723_Y(), Q_2241_p.n_1700_B.R_4764_Y, 2, j_3341_s.u_1723_Y(), this.minecraft).handle((p_241623_2_, p_241623_3_) -> {
                if (p_241623_3_ != null) {
                    G_564_y.warn("Failed to validate datapack", p_241623_3_);
                    this.minecraft.w_1484_f(() -> this.minecraft.n_1700_B(new q_3418_t(p_241630_1_ -> {
                        if (p_241630_1_) {
                            this.t_148_a();
                        } else {
                            this.J_1907_R = DataPackConfig.n_1700_B;
                            this.minecraft.n_1700_B(this);
                        }
                    }, new F_2904_S("dataPack.validation.failed"), U_2871_b.R_4764_Y, new F_2904_S("dataPack.validation.back"), new F_2904_S("dataPack.validation.reset"))));
                } else {
                    this.minecraft.w_1484_f(() -> {
                        this.J_1907_R = datapackcodec;
                        this.R_4764_Y.n_1700_B((ServerResources)p_241623_2_);
                        p_241623_2_.close();
                        this.minecraft.n_1700_B(this);
                    });
                }
                return null;
            });
        }
    }

    private void s_956_w() {
        if (this.Y_601_j != null) {
            try (Stream<Path> stream = Files.walk(this.Y_601_j, new FileVisitOption[0]);){
                stream.sorted(Comparator.reverseOrder()).forEach(p_238948_0_ -> {
                    try {
                        Files.delete(p_238948_0_);
                    }
                    catch (IOException ioexception1) {
                        G_564_y.warn("Failed to remove temporary file {}", p_238948_0_, (Object)ioexception1);
                    }
                });
            }
            catch (IOException ioexception) {
                G_564_y.warn("Failed to list temporary dir {}", (Object)this.Y_601_j);
            }
            this.Y_601_j = null;
        }
    }

    private static void n_1700_B(Path p_238945_0_, Path p_238945_1_, Path p_238945_2_) {
        try {
            j_3341_s.J_1907_R(p_238945_0_, p_238945_1_, p_238945_2_);
        }
        catch (IOException ioexception) {
            G_564_y.warn("Failed to copy datapack file from {} to {}", (Object)p_238945_2_, (Object)p_238945_1_);
            throw new n_1700_B(ioexception);
        }
    }

    private boolean u_2550_I() {
        if (this.Y_601_j != null) {
            try (b_2971_z.n_1700_B saveformat$levelsave = this.minecraft.t_148_a().R_4764_Y(this.P_4830_p);
                 Stream<Path> stream = Files.walk(this.Y_601_j, new FileVisitOption[0]);){
                Path path = saveformat$levelsave.n_1700_B(H_4757_Q.v_4262_N);
                Files.createDirectories(path, new FileAttribute[0]);
                stream.filter(p_238942_1_ -> !p_238942_1_.equals(this.Y_601_j)).forEach(p_238949_2_ -> o_1792_J.n_1700_B(this.Y_601_j, path, p_238949_2_));
            }
            catch (IOException | n_1700_B ioexception) {
                G_564_y.warn("Failed to copy datapacks to world {}", (Object)this.P_4830_p, (Object)ioexception);
                SystemToast.R_4764_Y(this.minecraft, this.P_4830_p);
                this.J_1907_R();
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static Path n_1700_B(Path p_238943_0_, MinecraftClient p_238943_1_) {
        MutableObject mutableobject = new MutableObject();
        try (Stream<Path> stream = Files.walk(p_238943_0_, new FileVisitOption[0]);){
            stream.filter(p_238944_1_ -> !p_238944_1_.equals(p_238943_0_)).forEach(p_238947_2_ -> {
                Path path = (Path)mutableobject.getValue();
                if (path == null) {
                    try {
                        path = Files.createTempDirectory("mcworld-", new FileAttribute[0]);
                    }
                    catch (IOException ioexception1) {
                        G_564_y.warn("Failed to create temporary dir");
                        throw new n_1700_B(ioexception1);
                    }
                    mutableobject.setValue((Object)path);
                }
                o_1792_J.n_1700_B(p_238943_0_, path, p_238947_2_);
            });
        }
        catch (IOException | n_1700_B ioexception) {
            G_564_y.warn("Failed to copy datapacks from world {}", (Object)p_238943_0_, (Object)ioexception);
            SystemToast.R_4764_Y(p_238943_1_, p_238943_0_.toString());
            return null;
        }
        return (Path)mutableobject.getValue();
    }

    @Nullable
    private Pair<File, PackRepository> M_588_G() {
        Path path = this.R_4764_Y();
        if (path != null) {
            File file1 = path.toFile();
            if (this.Y_259_p == null) {
                this.Y_259_p = new PackRepository(new T_4652_I(), new e_4716_U(file1, PackSource.n_1700_B));
                this.Y_259_p.n_1700_B();
            }
            this.Y_259_p.n_1700_B(this.J_1907_R.n_1700_B());
            return Pair.of((Object)file1, (Object)this.Y_259_p);
        }
        return null;
    }

    private static /* synthetic */ boolean n_1700_B(List list, String p_241626_1_) {
        return !list.contains(p_241626_1_);
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("survival", I_14_v.J_1907_R);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("hardcore", I_14_v.J_1907_R);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("creative", I_14_v.R_4764_Y);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("spectator", I_14_v.P_1922_E);
        private final String P_1922_E;
        private final I_14_v u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String p_i225940_3_, I_14_v p_i225940_4_) {
            this.P_1922_E = p_i225940_3_;
            this.u_1723_Y = p_i225940_4_;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.o_1792_J$J_1907_R.n_1700_B();
        }
    }

    static class n_1700_B
    extends RuntimeException {
        public n_1700_B(Throwable p_i232309_1_) {
            super(p_i232309_1_);
        }
    }
}



