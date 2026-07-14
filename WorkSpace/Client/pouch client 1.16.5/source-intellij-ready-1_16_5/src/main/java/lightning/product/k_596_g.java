/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.Runnables
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.util.concurrent.Runnables;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import lightning.product.B_4756_G;
import lightning.product.C_3240_x;
import lightning.product.RealmsBridge;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.J_2011_a;
import lightning.product.K_1289_S;
import lightning.product.SystemToast;
import lightning.product.R_4398_I;
import lightning.product.S_2919_l;
import lightning.product.T_1088_H;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.ImageButton;
import lightning.product.X_933_l;
import lightning.product.AccessibilityOptionsScreen;
import lightning.product.a_3289_V;
import lightning.product.b_2971_z;
import lightning.product.c_4037_x;
import lightning.product.e_465_j;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.k_2603_m;
import lightning.product.PanoramaRenderer;
import lightning.product.CommonComponents;
import lightning.product.q_3131_N;
import lightning.product.q_3418_t;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_596_g
extends k_2603_m {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final B_4756_G n_1700_B = new B_4756_G(new g_2336_b("textures/gui/title/background/panorama"));
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/gui/title/background/panorama_overlay.png");
    private static final g_2336_b G_564_y = new g_2336_b("textures/gui/accessibility.png");
    private final boolean P_1922_E;
    @Nullable
    private String u_1723_Y;
    private Button v_4262_N;
    private static final g_2336_b w_1484_f = new g_2336_b("textures/gui/title/minecraft.png");
    private static final g_2336_b t_148_a = new g_2336_b("textures/gui/title/edition.png");
    private boolean s_956_w;
    private k_2603_m u_2550_I;
    private int M_588_G;
    private int P_4830_p;
    private final PanoramaRenderer h_1847_R = new PanoramaRenderer(n_1700_B);
    private final boolean Q_4569_t;
    private long M_182_A;
    private k_2603_m t_1786_h;

    public k_596_g() {
        this(false);
    }

    public k_596_g(boolean fadeIn) {
        super(new F_2904_S("narrator.screen.title"));
        this.Q_4569_t = fadeIn;
        this.P_1922_E = (double)new Random().nextFloat() < 1.0E-4;
    }

    private boolean n_1700_B() {
        return this.minecraft.P_4830_p.g_164_R && this.u_2550_I != null;
    }

    @Override
    public void tick() {
        if (this.n_1700_B()) {
            this.u_2550_I.tick();
        }
    }

    public static CompletableFuture<Void> n_1700_B(C_3240_x texMngr, Executor backgroundExecutor) {
        return CompletableFuture.allOf(texMngr.n_1700_B(w_1484_f, backgroundExecutor), texMngr.n_1700_B(t_148_a, backgroundExecutor), texMngr.n_1700_B(R_4764_Y, backgroundExecutor), n_1700_B.n_1700_B(texMngr, backgroundExecutor));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init() {
        if (this.u_1723_Y == null) {
            this.u_1723_Y = this.minecraft.U_1241_n().J_1907_R();
        }
        this.M_588_G = this.font.J_1907_R("Copyright Mojang AB. Do not distribute!");
        this.P_4830_p = this.width - this.M_588_G - 2;
        int i = 24;
        int j = this.height / 4 + 48;
        Button button = null;
        if (this.minecraft.C_2741_M()) {
            this.J_1907_R(j, 24);
        } else {
            this.n_1700_B(j, 24);
            if (Reflector.ModListScreen_Constructor.exists()) {
                button = ReflectorForge.makeButtonMods(this, j, 24);
                this.addButton(button);
            }
        }
        this.addButton(new ImageButton(this.width / 2 - 124, j + 72 + 12, 20, 20, 0, 106, 20, Button.WIDGETS_LOCATION, 256, 256, p_lambda$init$0_1_ -> this.minecraft.n_1700_B(new a_3289_V((k_2603_m)this, this.minecraft.P_4830_p, this.minecraft.e_2887_G())), new F_2904_S("narrator.button.language")));
        this.addButton(new Button(this.width / 2 - 100, j + 72 + 12, 98, 20, new F_2904_S("menu.options"), p_lambda$init$1_1_ -> this.minecraft.n_1700_B(new e_465_j(this, this.minecraft.P_4830_p))));
        this.addButton(new Button(this.width / 2 + 2, j + 72 + 12, 98, 20, new F_2904_S("menu.quit"), p_lambda$init$2_1_ -> this.minecraft.h_1847_R()));
        this.addButton(new ImageButton(this.width / 2 + 104, j + 72 + 12, 20, 20, 0, 0, 20, G_564_y, 32, 64, p_lambda$init$3_1_ -> this.minecraft.n_1700_B(new AccessibilityOptionsScreen(this, this.minecraft.P_4830_p)), new F_2904_S("narrator.button.accessibility")));
        this.minecraft.R_4764_Y(false);
        if (this.minecraft.P_4830_p.g_164_R && !this.s_956_w) {
            RealmsBridge realmsbridgescreen = new RealmsBridge();
            this.u_2550_I = realmsbridgescreen.J_1907_R(this);
            this.s_956_w = true;
        }
        if (this.n_1700_B()) {
            this.u_2550_I.init(this.minecraft, this.width, this.height);
        }
        if (Reflector.NotificationModUpdateScreen_init.exists()) {
            this.t_1786_h = (k_2603_m)Reflector.call(Reflector.NotificationModUpdateScreen_init, this, button);
        }
    }

    private void n_1700_B(int yIn, int rowHeightIn) {
        this.addButton(new Button(this.width / 2 - 100, yIn, 200, 20, new F_2904_S("menu.singleplayer"), p_lambda$addSingleplayerMultiplayerButtons$4_1_ -> this.minecraft.n_1700_B(new R_4398_I(this))));
        boolean flag = this.minecraft.Y_259_p();
        Button.J_1907_R button$itooltip = flag ? Button.field_238486_s_ : (p_lambda$addSingleplayerMultiplayerButtons$5_1_, p_lambda$addSingleplayerMultiplayerButtons$5_2_, p_lambda$addSingleplayerMultiplayerButtons$5_3_, p_lambda$addSingleplayerMultiplayerButtons$5_4_) -> {
            if (!p_lambda$addSingleplayerMultiplayerButtons$5_1_.active) {
                this.renderTooltip(p_lambda$addSingleplayerMultiplayerButtons$5_2_, this.minecraft.t_148_a.J_1907_R(new F_2904_S("title.multiplayer.disabled"), Math.max(this.width / 2 - 43, 170)), p_lambda$addSingleplayerMultiplayerButtons$5_3_, p_lambda$addSingleplayerMultiplayerButtons$5_4_);
            }
        };
        this.addButton(new Button((int)(this.width / 2 - 100), (int)(yIn + rowHeightIn * 1), (int)200, (int)20, (x_282_a)new F_2904_S((String)"menu.multiplayer"), (Button.n_1700_B)(Button.n_1700_B)LambdaMetafactory.metafactory(null, null, null, (Llightning/product/Button;)V, R_4764_Y(lightning.product.Button ), (Llightning/product/Button;)V)((k_596_g)this), (Button.J_1907_R)button$itooltip)).active = flag;
        this.addButton(new Button((int)(this.width / 2 - 100), (int)(yIn + rowHeightIn * 2), (int)200, (int)20, (x_282_a)new F_2904_S((String)"menu.online"), (Button.n_1700_B)(Button.n_1700_B)LambdaMetafactory.metafactory(null, null, null, (Llightning/product/Button;)V, J_1907_R(lightning.product.Button ), (Llightning/product/Button;)V)((k_596_g)this), (Button.J_1907_R)button$itooltip)).active = flag;
        if (Reflector.ModListScreen_Constructor.exists() && this.buttons.size() > 0) {
            V_2511_L widget = (V_2511_L)this.buttons.get(this.buttons.size() - 1);
            widget.x = this.width / 2 + 2;
            widget.setWidth(98);
        }
    }

    private void J_1907_R(int yIn, int rowHeightIn) {
        boolean flag = this.J_1907_R();
        this.addButton(new Button(this.width / 2 - 100, yIn, 200, 20, new F_2904_S("menu.playdemo"), p_lambda$addDemoButtons$8_2_ -> {
            if (flag) {
                this.minecraft.n_1700_B("Demo_World");
            } else {
                r_4097_j.J_1907_R dynamicregistries$impl = r_4097_j.J_1907_R();
                this.minecraft.n_1700_B("Demo_World", net.minecraft.server.G_564_y.J_1907_R, dynamicregistries$impl, j_419_j.n_1700_B(dynamicregistries$impl));
            }
        }));
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 100, yIn + rowHeightIn * 1, 200, 20, new F_2904_S("menu.resetdemo"), p_lambda$addDemoButtons$9_1_ -> {
            b_2971_z saveformat = this.minecraft.t_148_a();
            try (b_2971_z.n_1700_B saveformat$levelsave = saveformat.R_4764_Y("Demo_World");){
                J_2011_a worldsummary = saveformat$levelsave.G_564_y();
                if (worldsummary != null) {
                    this.minecraft.n_1700_B(new q_3418_t(this::n_1700_B, new F_2904_S("selectWorld.deleteQuestion"), new F_2904_S("selectWorld.deleteWarning", worldsummary.J_1907_R()), new F_2904_S("selectWorld.deleteButton"), CommonComponents.G_564_y));
                }
            }
            catch (IOException ioexception1) {
                SystemToast.n_1700_B(this.minecraft, "Demo_World");
                J_1907_R.warn("Failed to access demo world", (Throwable)ioexception1);
            }
        }));
        this.v_4262_N.active = flag;
    }

    private boolean J_1907_R() {
        boolean bl;
        block8: {
            b_2971_z.n_1700_B saveformat$levelsave = this.minecraft.t_148_a().R_4764_Y("Demo_World");
            try {
                boolean bl2 = bl = saveformat$levelsave.G_564_y() != null;
                if (saveformat$levelsave == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (saveformat$levelsave != null) {
                        try {
                            saveformat$levelsave.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (IOException ioexception1) {
                    SystemToast.n_1700_B(this.minecraft, "Demo_World");
                    J_1907_R.warn("Failed to read demo world data", (Throwable)ioexception1);
                    return false;
                }
            }
            saveformat$levelsave.close();
        }
        return bl;
    }

    private void R_4764_Y() {
        RealmsBridge realmsbridgescreen = new RealmsBridge();
        realmsbridgescreen.n_1700_B(this);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.M_182_A == 0L && this.Q_4569_t) {
            this.M_182_A = j_3341_s.J_1907_R();
        }
        float f = this.Q_4569_t ? (float)(j_3341_s.J_1907_R() - this.M_182_A) / 1000.0f : 1.0f;
        X_933_l.M_588_G();
        this.h_1847_R.n_1700_B(partialTicks, 1.0f);
        k_596_g.fill(matrixStack, 0, 0, this.width, this.height, -1873784752);
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        float f1 = this.Q_4569_t ? u_530_F.n_1700_B(f - 1.0f, 0.0f, 1.0f) : 1.0f;
        int l = u_530_F.u_1723_Y(f1 * 255.0f) << 24;
        if ((l & 0xFC000000) != 0) {
            this.minecraft.G_624_v().n_1700_B(w_1484_f);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, f1);
            if (this.P_1922_E) {
                this.blitBlackOutline(j, 30, (p_lambda$render$10_2_, p_lambda$render$10_3_) -> {
                    this.blit(matrixStack, p_lambda$render$10_2_ + 0, (int)p_lambda$render$10_3_, 0, 0, 99, 44);
                    this.blit(matrixStack, p_lambda$render$10_2_ + 99, (int)p_lambda$render$10_3_, 129, 0, 27, 44);
                    this.blit(matrixStack, p_lambda$render$10_2_ + 99 + 26, (int)p_lambda$render$10_3_, 126, 0, 3, 44);
                    this.blit(matrixStack, p_lambda$render$10_2_ + 99 + 26 + 3, (int)p_lambda$render$10_3_, 99, 0, 26, 44);
                    this.blit(matrixStack, p_lambda$render$10_2_ + 155, (int)p_lambda$render$10_3_, 0, 45, 155, 44);
                });
            } else {
                this.blitBlackOutline(j, 30, (p_lambda$render$11_2_, p_lambda$render$11_3_) -> {
                    this.blit(matrixStack, p_lambda$render$11_2_ + 0, (int)p_lambda$render$11_3_, 0, 0, 155, 44);
                    this.blit(matrixStack, p_lambda$render$11_2_ + 155, (int)p_lambda$render$11_3_, 0, 45, 155, 44);
                });
            }
            this.minecraft.G_624_v().n_1700_B(t_148_a);
            k_596_g.blit(matrixStack, j + 88, 67, 0.0f, 0.0f, 98, 14, 128, 16);
            if (Reflector.ForgeHooksClient_renderMainMenu.exists()) {
                Reflector.callVoid(Reflector.ForgeHooksClient_renderMainMenu, this, matrixStack, this.font, this.width, this.height, l);
            }
            if (this.u_1723_Y != null) {
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y((float)(this.width / 2 + 90), 70.0f, 0.0f);
                c_4037_x.R_4764_Y(-20.0f, 0.0f, 0.0f, 1.0f);
                float f2 = 1.8f - u_530_F.P_1922_E(u_530_F.n_1700_B((float)(j_3341_s.J_1907_R() % 1000L) / 1000.0f * ((float)Math.PI * 2)) * 0.1f);
                f2 = f2 * 100.0f / (float)(this.font.J_1907_R(this.u_1723_Y) + 32);
                c_4037_x.J_1907_R(f2, f2, f2);
                k_596_g.drawCenteredString(matrixStack, this.font, this.u_1723_Y, 0, -8, 0xFFFF00 | l);
                c_4037_x.d_2461_k();
            }
            String s = "Minecraft " + SharedConstants.n_1700_B().getName();
            s = this.minecraft.C_2741_M() ? s + " Demo" : s + (String)("release".equalsIgnoreCase(this.minecraft.u_1723_Y()) ? "" : "/" + this.minecraft.u_1723_Y());
            if (this.minecraft.J_1907_R()) {
                s = s + K_1289_S.n_1700_B("menu.modded", new Object[0]);
            }
            if (Reflector.BrandingControl.exists()) {
                if (Reflector.BrandingControl_forEachLine.exists()) {
                    BiConsumer<Integer, String> biconsumer = (p_lambda$render$12_3_, p_lambda$render$12_4_) -> k_596_g.drawString(matrixStack, this.font, p_lambda$render$12_4_, 2, this.height - (10 + p_lambda$render$12_3_ * 10), 0xFFFFFF | l);
                    Reflector.call(Reflector.BrandingControl_forEachLine, true, true, biconsumer);
                }
                if (Reflector.BrandingControl_forEachAboveCopyrightLine.exists()) {
                    BiConsumer<Integer, String> biconsumer1 = (p_lambda$render$13_3_, p_lambda$render$13_4_) -> k_596_g.drawString(matrixStack, this.font, p_lambda$render$13_4_, this.width - this.font.J_1907_R((String)p_lambda$render$13_4_), this.height - (10 + (p_lambda$render$13_3_ + 1) * 10), 0xFFFFFF | l);
                    Reflector.call(Reflector.BrandingControl_forEachAboveCopyrightLine, biconsumer1);
                }
            } else {
                k_596_g.drawString(matrixStack, this.font, s, 2, this.height - 10, 0xFFFFFF | l);
            }
            k_596_g.drawString(matrixStack, this.font, "Copyright Mojang AB. Do not distribute!", this.P_4830_p, this.height - 10, 0xFFFFFF | l);
            if (mouseX > this.P_4830_p && mouseX < this.P_4830_p + this.M_588_G && mouseY > this.height - 10 && mouseY < this.height) {
                k_596_g.fill(matrixStack, this.P_4830_p, this.height - 1, this.P_4830_p + this.M_588_G, this.height, 0xFFFFFF | l);
            }
            for (V_2511_L widget : this.buttons) {
                widget.setAlpha(f1);
            }
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            if (this.n_1700_B() && f1 >= 1.0f) {
                this.u_2550_I.render(matrixStack, mouseX, mouseY, partialTicks);
            }
        }
        if (this.t_1786_h != null) {
            this.t_1786_h.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (this.n_1700_B() && this.u_2550_I.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (mouseX > (double)this.P_4830_p && mouseX < (double)(this.P_4830_p + this.M_588_G) && mouseY > (double)(this.height - 10) && mouseY < (double)this.height) {
            this.minecraft.n_1700_B(new T_1088_H(false, Runnables.doNothing()));
        }
        return false;
    }

    @Override
    public void onClose() {
        if (this.u_2550_I != null) {
            this.u_2550_I.onClose();
        }
    }

    private void n_1700_B(boolean p_213087_1_) {
        if (p_213087_1_) {
            try (b_2971_z.n_1700_B saveformat$levelsave = this.minecraft.t_148_a().R_4764_Y("Demo_World");){
                saveformat$levelsave.v_4262_N();
            }
            catch (IOException ioexception1) {
                SystemToast.J_1907_R(this.minecraft, "Demo_World");
                J_1907_R.warn("Failed to delete demo world", (Throwable)ioexception1);
            }
        }
        this.minecraft.n_1700_B(this);
    }

    private /* synthetic */ void J_1907_R(Button p_lambda$addSingleplayerMultiplayerButtons$7_1_) {
        this.R_4764_Y();
    }

    private /* synthetic */ void R_4764_Y(Button p_lambda$addSingleplayerMultiplayerButtons$6_1_) {
        k_2603_m screen = this.minecraft.P_4830_p.l_1233_K ? new q_3131_N(this) : new S_2919_l(this);
        this.minecraft.n_1700_B(screen);
    }
}


