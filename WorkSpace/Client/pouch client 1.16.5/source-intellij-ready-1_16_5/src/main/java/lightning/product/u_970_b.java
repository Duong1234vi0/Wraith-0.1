/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.hash.Hashing
 *  com.mojang.datafixers.util.Function4
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.mojang.datafixers.util.Function4;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.B_4315_y;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.H_4757_Q;
import lightning.product.I_1084_e;
import lightning.product.SharedConstants;
import lightning.product.J_2011_a;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.SystemToast;
import lightning.product.O_3892_W;
import lightning.product.R_4398_I;
import lightning.product.ErrorScreen;
import lightning.product.GenericDirtMessageScreen;
import lightning.product.ResourceManager;
import lightning.product.T_1114_L;
import lightning.product.SimpleSoundInstance;
import lightning.product.U_2871_b;
import lightning.product.SoundEvents;
import lightning.product.b_2971_z;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.DataPackConfig;
import lightning.product.i_2518_W;
import lightning.product.i_3199_H;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.WorldData;
import lightning.product.o_1792_J;
import lightning.product.o_2488_o;
import lightning.product.AlertScreen;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.r_4097_j;
import lightning.product.s_1875_c;
import lightning.product.u_4650_L;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class u_970_b
extends ObjectSelectionList<n_1700_B> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final DateFormat J_1907_R = new SimpleDateFormat();
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/misc/unknown_server.png");
    private static final g_2336_b G_564_y = new g_2336_b("textures/gui/world_selection.png");
    private static final x_282_a P_1922_E = new F_2904_S("selectWorld.tooltip.fromNewerVersion1").n_1700_B(D_4024_W.P_4830_p);
    private static final x_282_a u_1723_Y = new F_2904_S("selectWorld.tooltip.fromNewerVersion2").n_1700_B(D_4024_W.P_4830_p);
    private static final x_282_a v_4262_N = new F_2904_S("selectWorld.tooltip.snapshot1").n_1700_B(D_4024_W.v_4262_N);
    private static final x_282_a w_1484_f = new F_2904_S("selectWorld.tooltip.snapshot2").n_1700_B(D_4024_W.v_4262_N);
    private static final x_282_a t_148_a = new F_2904_S("selectWorld.locked").n_1700_B(D_4024_W.P_4830_p);
    private final R_4398_I s_956_w;
    @Nullable
    private List<J_2011_a> u_2550_I;

    public u_970_b(R_4398_I p_i49846_1_, MinecraftClient p_i49846_2_, int p_i49846_3_, int p_i49846_4_, int p_i49846_5_, int p_i49846_6_, int p_i49846_7_, Supplier<String> p_i49846_8_, @Nullable u_970_b p_i49846_9_) {
        super(p_i49846_2_, p_i49846_3_, p_i49846_4_, p_i49846_5_, p_i49846_6_, p_i49846_7_);
        this.s_956_w = p_i49846_1_;
        if (p_i49846_9_ != null) {
            this.u_2550_I = p_i49846_9_.u_2550_I;
        }
        this.n_1700_B(p_i49846_8_, false);
    }

    public void n_1700_B(Supplier<String> p_212330_1_, boolean p_212330_2_) {
        this.clearEntries();
        b_2971_z saveformat = this.minecraft.t_148_a();
        if (this.u_2550_I == null || p_212330_2_) {
            try {
                this.u_2550_I = saveformat.n_1700_B();
            }
            catch (i_3199_H anvilconverterexception) {
                n_1700_B.error("Couldn't load level list", (Throwable)anvilconverterexception);
                this.minecraft.n_1700_B(new ErrorScreen(new F_2904_S("selectWorld.unable_to_load"), new U_2871_b(anvilconverterexception.getMessage())));
                return;
            }
            Collections.sort(this.u_2550_I);
        }
        if (this.u_2550_I.isEmpty()) {
            this.minecraft.n_1700_B(o_1792_J.n_1700_B(null));
        } else {
            String s = p_212330_1_.get().toLowerCase(Locale.ROOT);
            for (J_2011_a worldsummary : this.u_2550_I) {
                if (!worldsummary.J_1907_R().toLowerCase(Locale.ROOT).contains(s) && !worldsummary.n_1700_B().toLowerCase(Locale.ROOT).contains(s)) continue;
                this.addEntry(new n_1700_B(this, worldsummary));
            }
        }
    }

    @Override
    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 20;
    }

    @Override
    public int getRowWidth() {
        return super.getRowWidth() + 50;
    }

    @Override
    protected boolean isFocused() {
        return this.s_956_w.getListener() == this;
    }

    public void n_1700_B(@Nullable n_1700_B entry) {
        super.setSelected(entry);
        if (entry != null) {
            J_2011_a worldsummary = entry.G_564_y;
            I_1084_e.J_1907_R.n_1700_B(new F_2904_S("narrator.select", new F_2904_S("narrator.select.world", worldsummary.J_1907_R(), new Date(worldsummary.P_1922_E()), worldsummary.v_4262_N() ? new F_2904_S("gameMode.hardcore") : new F_2904_S("gameMode." + worldsummary.u_1723_Y().J_1907_R()), worldsummary.w_1484_f() ? new F_2904_S("selectWorld.cheats") : U_2871_b.R_4764_Y, worldsummary.t_148_a())).getString());
        }
        this.s_956_w.n_1700_B(entry != null && !entry.G_564_y.h_1847_R());
    }

    @Override
    protected void moveSelection(o_2488_o.J_1907_R p_241219_1_) {
        this.func_241572_a_(p_241219_1_, p_241652_0_ -> !p_241652_0_.G_564_y.h_1847_R());
    }

    public Optional<n_1700_B> n_1700_B() {
        return Optional.ofNullable((n_1700_B)this.getSelected());
    }

    public R_4398_I J_1907_R() {
        return this.s_956_w;
    }

    @Override
    public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
        this.n_1700_B((n_1700_B)n_1700_B2);
    }

    public final class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B>
    implements AutoCloseable {
        private final MinecraftClient J_1907_R;
        private final R_4398_I R_4764_Y;
        private final J_2011_a G_564_y;
        private final g_2336_b P_1922_E;
        private File u_1723_Y;
        @Nullable
        private final T_1114_L v_4262_N;
        private long w_1484_f;

        public n_1700_B(u_970_b p_i242066_2_, J_2011_a p_i242066_3_) {
            this.R_4764_Y = p_i242066_2_.J_1907_R();
            this.G_564_y = p_i242066_3_;
            this.J_1907_R = MinecraftClient.A_4115_X();
            String s = p_i242066_3_.n_1700_B();
            this.P_1922_E = new g_2336_b("minecraft", "worlds/" + j_3341_s.n_1700_B(s, g_2336_b::J_1907_R) + "/" + String.valueOf(Hashing.sha1().hashUnencodedChars((CharSequence)s)) + "/icon");
            this.u_1723_Y = p_i242066_3_.R_4764_Y();
            if (!this.u_1723_Y.isFile()) {
                this.u_1723_Y = null;
            }
            this.v_4262_N = this.v_4262_N();
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            Object s = this.G_564_y.J_1907_R();
            String s1 = this.G_564_y.n_1700_B() + " (" + J_1907_R.format(new Date(this.G_564_y.P_1922_E())) + ")";
            if (StringUtils.isEmpty((CharSequence)s)) {
                s = K_1289_S.n_1700_B("selectWorld.world", new Object[0]) + " " + (p_230432_2_ + 1);
            }
            x_282_a itextcomponent = this.G_564_y.Q_4569_t();
            this.J_1907_R.t_148_a.J_1907_R(p_230432_1_, (String)s, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 1), 0xFFFFFF);
            this.J_1907_R.t_148_a.J_1907_R(p_230432_1_, s1, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 9 + 3), 0x808080);
            this.J_1907_R.t_148_a.J_1907_R(p_230432_1_, itextcomponent, (float)(p_230432_4_ + 32 + 3), (float)(p_230432_3_ + 9 + 9 + 3), 0x808080);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.J_1907_R.G_624_v().n_1700_B(this.v_4262_N != null ? this.P_1922_E : R_4764_Y);
            c_4037_x.Y_601_j();
            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 0.0f, 32, 32, 32, 32);
            c_4037_x.Y_259_p();
            if (this.J_1907_R.P_4830_p.c_4037_x || p_230432_9_) {
                int j;
                this.J_1907_R.G_624_v().n_1700_B(G_564_y);
                C_2701_A.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + 32, p_230432_3_ + 32, -1601138544);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                int i = p_230432_7_ - p_230432_4_;
                boolean flag = i < 32;
                int n = j = flag ? 32 : 0;
                if (this.G_564_y.h_1847_R()) {
                    C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, j, 32, 32, 256, 256);
                    if (flag) {
                        this.R_4764_Y.n_1700_B(this.J_1907_R.t_148_a.J_1907_R(t_148_a, 175));
                    }
                } else if (this.G_564_y.u_2550_I()) {
                    C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 32.0f, j, 32, 32, 256, 256);
                    if (this.G_564_y.M_588_G()) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, j, 32, 32, 256, 256);
                        if (flag) {
                            this.R_4764_Y.n_1700_B((List<FormattedCharSequence>)ImmutableList.of((Object)P_1922_E.u_1723_Y(), (Object)u_1723_Y.u_1723_Y()));
                        }
                    } else if (!SharedConstants.n_1700_B().isStable()) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0f, j, 32, 32, 256, 256);
                        if (flag) {
                            this.R_4764_Y.n_1700_B((List<FormattedCharSequence>)ImmutableList.of((Object)v_4262_N.u_1723_Y(), (Object)w_1484_f.u_1723_Y()));
                        }
                    }
                } else {
                    C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, j, 32, 32, 256, 256);
                }
            }
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (this.G_564_y.h_1847_R()) {
                return true;
            }
            u_970_b.this.n_1700_B(this);
            this.R_4764_Y.n_1700_B(u_970_b.this.n_1700_B().isPresent());
            if (mouseX - (double)u_970_b.this.getRowLeft() <= 32.0) {
                this.n_1700_B();
                return true;
            }
            if (j_3341_s.J_1907_R() - this.w_1484_f < 250L) {
                this.n_1700_B();
                return true;
            }
            this.w_1484_f = j_3341_s.J_1907_R();
            return false;
        }

        public void n_1700_B() {
            if (!this.G_564_y.h_1847_R()) {
                if (this.G_564_y.P_4830_p()) {
                    F_2904_S itextcomponent = new F_2904_S("selectWorld.backupQuestion");
                    F_2904_S itextcomponent1 = new F_2904_S("selectWorld.backupWarning", this.G_564_y.t_148_a(), SharedConstants.n_1700_B().getName());
                    this.J_1907_R.n_1700_B(new O_3892_W(this.R_4764_Y, (p_214436_1_, p_214436_2_) -> {
                        if (p_214436_1_) {
                            String s = this.G_564_y.n_1700_B();
                            try (b_2971_z.n_1700_B saveformat$levelsave = this.J_1907_R.t_148_a().R_4764_Y(s);){
                                s_1875_c.n_1700_B(saveformat$levelsave);
                            }
                            catch (IOException ioexception) {
                                SystemToast.n_1700_B(this.J_1907_R, s);
                                n_1700_B.error("Failed to backup level {}", (Object)s, (Object)ioexception);
                            }
                        }
                        this.P_1922_E();
                    }, itextcomponent, itextcomponent1, false));
                } else if (this.G_564_y.M_588_G()) {
                    this.J_1907_R.n_1700_B(new q_3418_t(p_214434_1_ -> {
                        if (p_214434_1_) {
                            try {
                                this.P_1922_E();
                            }
                            catch (Exception exception) {
                                n_1700_B.error("Failure to open 'future world'", (Throwable)exception);
                                this.J_1907_R.n_1700_B(new AlertScreen(() -> this.J_1907_R.n_1700_B(this.R_4764_Y), new F_2904_S("selectWorld.futureworld.error.title"), new F_2904_S("selectWorld.futureworld.error.text")));
                            }
                        } else {
                            this.J_1907_R.n_1700_B(this.R_4764_Y);
                        }
                    }, new F_2904_S("selectWorld.versionQuestion"), new F_2904_S("selectWorld.versionWarning", this.G_564_y.t_148_a(), new F_2904_S("selectWorld.versionJoinButton"), CommonComponents.G_564_y)));
                } else {
                    this.P_1922_E();
                }
            }
        }

        public void J_1907_R() {
            this.J_1907_R.n_1700_B(new q_3418_t(p_214440_1_ -> {
                if (p_214440_1_) {
                    this.J_1907_R.n_1700_B(new u_4650_L());
                    b_2971_z saveformat = this.J_1907_R.t_148_a();
                    String s = this.G_564_y.n_1700_B();
                    try (b_2971_z.n_1700_B saveformat$levelsave = saveformat.R_4764_Y(s);){
                        saveformat$levelsave.v_4262_N();
                    }
                    catch (IOException ioexception) {
                        SystemToast.J_1907_R(this.J_1907_R, s);
                        n_1700_B.error("Failed to delete world {}", (Object)s, (Object)ioexception);
                    }
                    u_970_b.this.n_1700_B(() -> this.R_4764_Y.J_1907_R.getText(), true);
                }
                this.J_1907_R.n_1700_B(this.R_4764_Y);
            }, new F_2904_S("selectWorld.deleteQuestion"), new F_2904_S("selectWorld.deleteWarning", this.G_564_y.J_1907_R()), new F_2904_S("selectWorld.deleteButton"), CommonComponents.G_564_y));
        }

        public void R_4764_Y() {
            String s = this.G_564_y.n_1700_B();
            try {
                b_2971_z.n_1700_B saveformat$levelsave = this.J_1907_R.t_148_a().R_4764_Y(s);
                this.J_1907_R.n_1700_B(new s_1875_c(p_239096_3_ -> {
                    try {
                        saveformat$levelsave.close();
                    }
                    catch (IOException ioexception1) {
                        n_1700_B.error("Failed to unlock level {}", (Object)s, (Object)ioexception1);
                    }
                    if (p_239096_3_) {
                        u_970_b.this.n_1700_B(() -> this.R_4764_Y.J_1907_R.getText(), true);
                    }
                    this.J_1907_R.n_1700_B(this.R_4764_Y);
                }, saveformat$levelsave));
            }
            catch (IOException ioexception) {
                SystemToast.n_1700_B(this.J_1907_R, s);
                n_1700_B.error("Failed to access level {}", (Object)s, (Object)ioexception);
                u_970_b.this.n_1700_B(() -> this.R_4764_Y.J_1907_R.getText(), true);
            }
        }

        public void G_564_y() {
            this.u_1723_Y();
            r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
            try (b_2971_z.n_1700_B saveformat$levelsave = this.J_1907_R.t_148_a().R_4764_Y(this.G_564_y.n_1700_B());
                 MinecraftClient.n_1700_B minecraft$packmanager = this.J_1907_R.n_1700_B(dynamicregistries$impl, MinecraftClient::n_1700_B, (Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData>)((Function4)MinecraftClient::n_1700_B), false, saveformat$levelsave);){
                B_4315_y worldsettings = minecraft$packmanager.R_4764_Y().A_4115_X();
                DataPackConfig datapackcodec = worldsettings.v_4262_N();
                j_419_j dimensiongeneratorsettings = minecraft$packmanager.R_4764_Y().e_4240_b();
                Path path = o_1792_J.n_1700_B(saveformat$levelsave.n_1700_B(H_4757_Q.v_4262_N), this.J_1907_R);
                if (dimensiongeneratorsettings.t_148_a()) {
                    this.J_1907_R.n_1700_B(new q_3418_t(p_239095_6_ -> this.J_1907_R.n_1700_B(p_239095_6_ ? new o_1792_J(this.R_4764_Y, worldsettings, dimensiongeneratorsettings, path, datapackcodec, dynamicregistries$impl) : this.R_4764_Y), new F_2904_S("selectWorld.recreate.customized.title"), new F_2904_S("selectWorld.recreate.customized.text"), CommonComponents.v_4262_N, CommonComponents.G_564_y));
                } else {
                    this.J_1907_R.n_1700_B(new o_1792_J(this.R_4764_Y, worldsettings, dimensiongeneratorsettings, path, datapackcodec, dynamicregistries$impl));
                }
            }
            catch (Exception exception) {
                n_1700_B.error("Unable to recreate world", (Throwable)exception);
                this.J_1907_R.n_1700_B(new AlertScreen(() -> this.J_1907_R.n_1700_B(this.R_4764_Y), new F_2904_S("selectWorld.recreate.error.title"), new F_2904_S("selectWorld.recreate.error.text")));
            }
        }

        private void P_1922_E() {
            this.J_1907_R.Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HayBlock, 1.0f));
            if (this.J_1907_R.t_148_a().J_1907_R(this.G_564_y.n_1700_B())) {
                this.u_1723_Y();
                this.J_1907_R.n_1700_B(this.G_564_y.n_1700_B());
            }
        }

        private void u_1723_Y() {
            this.J_1907_R.R_4764_Y(new GenericDirtMessageScreen(new F_2904_S("selectWorld.data_read")));
        }

        @Nullable
        private T_1114_L v_4262_N() {
            boolean flag;
            boolean bl = flag = this.u_1723_Y != null && this.u_1723_Y.isFile();
            if (flag) {
                T_1114_L t_1114_L;
                FileInputStream inputstream = new FileInputStream(this.u_1723_Y);
                try {
                    i_2518_W nativeimage = i_2518_W.n_1700_B(inputstream);
                    Validate.validState((nativeimage.n_1700_B() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels wide", (Object[])new Object[0]);
                    Validate.validState((nativeimage.J_1907_R() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels high", (Object[])new Object[0]);
                    T_1114_L dynamictexture = new T_1114_L(nativeimage);
                    this.J_1907_R.G_624_v().n_1700_B(this.P_1922_E, (c_4477_a)dynamictexture);
                    t_1114_L = dynamictexture;
                }
                catch (Throwable throwable) {
                    try {
                        try {
                            ((InputStream)inputstream).close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    catch (Throwable throwable3) {
                        n_1700_B.error("Invalid icon for world {}", (Object)this.G_564_y.n_1700_B(), (Object)throwable3);
                        this.u_1723_Y = null;
                        return null;
                    }
                }
                ((InputStream)inputstream).close();
                return t_1114_L;
            }
            this.J_1907_R.G_624_v().R_4764_Y(this.P_1922_E);
            return null;
        }

        @Override
        public void close() {
            if (this.v_4262_N != null) {
                this.v_4262_N.close();
            }
        }
    }
}



