/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_1658_r;
import lightning.product.A_229_v;
import lightning.product.A_4115_X;
import lightning.product.C_1269_X;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.GuiEventListener;
import lightning.product.ConfirmLinkScreen;
import lightning.product.H_1873_g;
import lightning.product.H_3330_w;
import lightning.product.J_1907_R;
import lightning.product.Q_4113_P;
import lightning.product.Widget;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_973_a;
import lightning.product.FormattedCharSequence;
import lightning.product.e_1813_Z;
import lightning.product.g_221_o;
import lightning.product.g_3316_o;
import lightning.product.h_3270_j;
import lightning.product.i_2909_p;
import lightning.product.j_3341_s;
import lightning.product.l_3747_P;
import lightning.product.n_1700_B;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_2840_r;
import lightning.product.o_3091_w;
import lightning.product.CrashReportCategory;
import lightning.product.v_143_j;
import lightning.product.x_282_a;
import mods.baritone.utils.accessor.IGuiScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class k_2603_m
extends A_1658_r
implements Widget,
e_1813_Z,
IGuiScreen {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Set<String> ALLOWED_PROTOCOLS = Sets.newHashSet((Object[])new String[]{"http", "https"});
    protected final x_282_a title;
    protected final List<GuiEventListener> children = Lists.newArrayList();
    @Nullable
    protected MinecraftClient minecraft;
    protected H_3330_w itemRenderer;
    public int width;
    public int height;
    protected final List<V_2511_L> buttons = Lists.newArrayList();
    public boolean passEvents;
    protected Y_4083_F font;
    private URI clickedLink;

    protected k_2603_m(x_282_a titleIn) {
        this.title = titleIn;
    }

    public x_282_a getTitle() {
        return this.title;
    }

    public String getNarrationMessage() {
        return this.getTitle().getString();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (int i = 0; i < this.buttons.size(); ++i) {
            this.buttons.get(i).render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256 && this.shouldCloseOnEsc()) {
            this.closeScreen();
            return true;
        }
        if (keyCode == 258) {
            boolean flag;
            boolean bl = flag = !k_2603_m.hasShiftDown();
            if (!this.changeFocus(flag)) {
                this.changeFocus(flag);
            }
            return false;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean shouldCloseOnEsc() {
        return true;
    }

    public void closeScreen() {
        this.minecraft.n_1700_B((k_2603_m)null);
    }

    protected <T extends V_2511_L> T addButton(T button) {
        this.buttons.add(button);
        return this.addListener(button);
    }

    protected <T extends GuiEventListener> T addListener(T listener) {
        this.children.add(listener);
        return listener;
    }

    protected void renderTooltip(g_221_o matrixStack, Z_1993_T itemStack, int mouseX, int mouseY) {
        A_229_v event = new A_229_v(matrixStack, itemStack, mouseX, mouseY);
        A_4115_X.n_1700_B(event);
        if (!event.n_1700_B()) {
            this.func_243308_b(matrixStack, this.getTooltipFromItem(itemStack), mouseX, mouseY);
        }
    }

    public List<x_282_a> getTooltipFromItem(Z_1993_T itemStack) {
        List<x_282_a> tooltip = itemStack.n_1700_B(this.minecraft.Y_259_p, this.minecraft.P_4830_p.M_182_A ? g_3316_o.n_1700_B.J_1907_R : g_3316_o.n_1700_B.n_1700_B);
        ArrayList<x_282_a> fixed = new ArrayList<x_282_a>();
        for (x_282_a line : tooltip) {
            String original = line.getString();
            String fixedStr = v_143_j.n_1700_B(original);
            if (!fixedStr.equals(original)) {
                fixed.add(new U_2871_b(fixedStr));
                continue;
            }
            fixed.add(line);
        }
        return fixed;
    }

    public void renderTooltip(g_221_o matrixStack, x_282_a text, int mouseX, int mouseY) {
        this.renderTooltip(matrixStack, Arrays.asList(text.u_1723_Y()), mouseX, mouseY);
    }

    public void func_243308_b(g_221_o p_243308_1_, List<x_282_a> p_243308_2_, int p_243308_3_, int p_243308_4_) {
        this.renderTooltip(p_243308_1_, Lists.transform(p_243308_2_, x_282_a::u_1723_Y), p_243308_3_, p_243308_4_);
    }

    /*
     * WARNING - void declaration
     */
    public void renderTooltip(g_221_o matrixStack, List<? extends FormattedCharSequence> tooltips, int mouseX, int mouseY) {
        if (!tooltips.isEmpty()) {
            int n;
            int i = 0;
            for (FormattedCharSequence d_3332_t2 : tooltips) {
                int j = this.font.n_1700_B(d_3332_t2);
                if (j <= i) continue;
                i = j;
            }
            int i2 = mouseX + 12;
            int n2 = mouseY - 12;
            int k = 8;
            if (tooltips.size() > 1) {
                k += 2 + (tooltips.size() - 1) * 10;
            }
            if (i2 + i > this.width) {
                i2 -= 28 + i;
            }
            if (n2 + k + 6 > this.height) {
                n = this.height - k - 6;
            }
            matrixStack.n_1700_B();
            int l = -267386864;
            int i1 = 0x505000FF;
            int j1 = 1344798847;
            int k1 = 400;
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
            D_1098_v matrix4f = matrixStack.R_4764_Y().n_1700_B();
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n - 4, i2 + i + 3, n - 3, 400, -267386864, -267386864);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n + k + 3, i2 + i + 3, n + k + 4, 400, -267386864, -267386864);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n - 3, i2 + i + 3, n + k + 3, 400, -267386864, -267386864);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 4, n - 3, i2 - 3, n + k + 3, 400, -267386864, -267386864);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 + i + 3, n - 3, i2 + i + 4, n + k + 3, 400, -267386864, -267386864);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n - 3 + 1, i2 - 3 + 1, n + k + 3 - 1, 400, 0x505000FF, 1344798847);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 + i + 2, n - 3 + 1, i2 + i + 3, n + k + 3 - 1, 400, 0x505000FF, 1344798847);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n - 3, i2 + i + 3, n - 3 + 1, 400, 0x505000FF, 0x505000FF);
            k_2603_m.fillGradient(matrix4f, bufferbuilder, i2 - 3, n + k + 2, i2 + i + 3, n + k + 3, 400, 1344798847, 1344798847);
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.e_4240_b();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.w_1484_f(7425);
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            c_4037_x.w_1484_f(7424);
            c_4037_x.Y_259_p();
            c_4037_x.x_607_J();
            o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
            matrixStack.n_1700_B(0.0, 0.0, 400.0);
            for (int l1 = 0; l1 < tooltips.size(); ++l1) {
                FormattedCharSequence ireorderingprocessor1 = tooltips.get(l1);
                if (ireorderingprocessor1 != null) {
                    void var7_11;
                    this.font.n_1700_B(ireorderingprocessor1, (float)i2, (float)var7_11, -1, true, matrix4f, (o_3091_w)irendertypebuffer$impl, false, 0, 0xF000F0);
                }
                if (l1 == 0) {
                    var7_11 += 2;
                }
                var7_11 += 10;
            }
            irendertypebuffer$impl.J_1907_R();
            matrixStack.J_1907_R();
        }
    }

    protected void renderComponentHoverEffect(g_221_o matrixStack, @Nullable Z_1567_W style, int mouseX, int mouseY) {
        if (style != null && style.t_148_a() != null) {
            c_973_a hoverevent = style.t_148_a();
            c_973_a.R_4764_Y hoverevent$itemhover = hoverevent.n_1700_B(c_973_a.n_1700_B.J_1907_R);
            if (hoverevent$itemhover != null) {
                this.renderTooltip(matrixStack, hoverevent$itemhover.n_1700_B(), mouseX, mouseY);
            } else {
                c_973_a.J_1907_R hoverevent$entityhover = hoverevent.n_1700_B(c_973_a.n_1700_B.R_4764_Y);
                if (hoverevent$entityhover != null) {
                    if (this.minecraft.P_4830_p.M_182_A) {
                        this.func_243308_b(matrixStack, hoverevent$entityhover.J_1907_R(), mouseX, mouseY);
                    }
                } else {
                    x_282_a itextcomponent = hoverevent.n_1700_B(c_973_a.n_1700_B.n_1700_B);
                    if (itextcomponent != null) {
                        this.renderTooltip(matrixStack, this.minecraft.t_148_a.J_1907_R(itextcomponent, Math.max(this.width / 2, 200)), mouseX, mouseY);
                    }
                }
            }
        }
    }

    protected void insertText(String text, boolean overwrite) {
    }

    public boolean handleComponentClicked(@Nullable Z_1567_W style) {
        i_2909_p i_2909_p2 = style.w_1484_f();
        if (i_2909_p2 instanceof H_1873_g) {
            H_1873_g clientClickEvent = (H_1873_g)i_2909_p2;
            String value = clientClickEvent.J_1907_R();
            String prefix = ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B();
            C_1269_X manager = ClientBootstrap.Y_601_j().Q_4569_t();
            try {
                if (value.startsWith(prefix)) {
                    String command = value.substring(prefix.length());
                    if (!manager.R_4764_Y(command)) {
                        return true;
                    }
                    manager.J_1907_R().execute(command, (Object)manager.G_564_y());
                } else {
                    if (!manager.R_4764_Y(value)) {
                        return true;
                    }
                    manager.J_1907_R().execute(value, (Object)manager.G_564_y());
                }
                return true;
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
        }
        if (style == null) {
            return false;
        }
        i_2909_p clickevent = style.w_1484_f();
        if (k_2603_m.hasShiftDown()) {
            if (style.s_956_w() != null) {
                this.insertText(style.s_956_w(), false);
            }
        } else if (clickevent != null) {
            block28: {
                if (clickevent.n_1700_B() == i_2909_p.n_1700_B.n_1700_B) {
                    if (!this.minecraft.P_4830_p.G_624_v) {
                        return false;
                    }
                    try {
                        URI uri = new URI(clickevent.J_1907_R());
                        String s = uri.getScheme();
                        if (s == null) {
                            throw new URISyntaxException(clickevent.J_1907_R(), "Missing protocol");
                        }
                        if (!ALLOWED_PROTOCOLS.contains(s.toLowerCase(Locale.ROOT))) {
                            throw new URISyntaxException(clickevent.J_1907_R(), "Unsupported protocol: " + s.toLowerCase(Locale.ROOT));
                        }
                        if (this.minecraft.P_4830_p.T_2506_i) {
                            this.clickedLink = uri;
                            this.minecraft.n_1700_B(new ConfirmLinkScreen(this::confirmLink, clickevent.J_1907_R(), false));
                            break block28;
                        }
                        this.openLink(uri);
                    }
                    catch (URISyntaxException urisyntaxexception) {
                        LOGGER.error("Can't open url for {}", (Object)clickevent, (Object)urisyntaxexception);
                    }
                } else if (clickevent.n_1700_B() == i_2909_p.n_1700_B.J_1907_R) {
                    URI uri1 = new File(clickevent.J_1907_R()).toURI();
                    this.openLink(uri1);
                } else if (clickevent.n_1700_B() == i_2909_p.n_1700_B.G_564_y) {
                    this.insertText(clickevent.J_1907_R(), true);
                } else if (clickevent.n_1700_B() == i_2909_p.n_1700_B.R_4764_Y) {
                    this.sendMessage(clickevent.J_1907_R(), false);
                } else if (clickevent.n_1700_B() == i_2909_p.n_1700_B.u_1723_Y) {
                    this.minecraft.Q_4569_t.n_1700_B(clickevent.J_1907_R());
                } else {
                    LOGGER.error("Don't know how to handle {}", (Object)clickevent);
                }
            }
            return true;
        }
        return false;
    }

    public void sendMessage(String text) {
        this.sendMessage(text, true);
    }

    public void sendMessage(String text, boolean addToChat) {
        if (addToChat) {
            this.minecraft.M_588_G.R_4764_Y().n_1700_B(text);
        }
        n_1700_B bot1 = null;
        for (n_1700_B bot : J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 == null) {
            this.minecraft.Y_259_p.n_1700_B(text);
        } else {
            bot1.P_1922_E.Q_2552_b.n_1700_B(text);
        }
    }

    public void init(MinecraftClient minecraft, int width, int height) {
        this.minecraft = minecraft;
        this.itemRenderer = minecraft.r_715_M();
        this.font = minecraft.t_148_a;
        this.width = width;
        this.height = height;
        this.buttons.clear();
        this.children.clear();
        this.setListener(null);
        this.init();
    }

    @Override
    public List<? extends GuiEventListener> getEventListeners() {
        return this.children;
    }

    protected void init() {
    }

    @Override
    public void tick() {
    }

    public void onClose() {
    }

    public void renderBackground(g_221_o matrixStack) {
        this.renderBackground(matrixStack, 0);
    }

    public void renderBackground(g_221_o matrixStack, int vOffset) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.Y_601_j);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        if (this.minecraft.Y_601_j != null) {
            k_2603_m.fillGradient(matrixStack, 0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            this.renderDirtBackground(vOffset);
        }
    }

    public void renderDirtBackground(int vOffset) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        this.minecraft.G_624_v().n_1700_B(BACKGROUND_LOCATION);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = 32.0f;
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(0.0, this.height, 0.0).tex(0.0f, (float)this.height / 32.0f + (float)vOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(this.width, this.height, 0.0).tex((float)this.width / 32.0f, (float)this.height / 32.0f + (float)vOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(this.width, 0.0, 0.0).tex((float)this.width / 32.0f, vOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0f, vOffset).color(64, 64, 64, 255).endVertex();
        tessellator.J_1907_R();
    }

    public boolean isPauseScreen() {
        return true;
    }

    private void confirmLink(boolean doOpen) {
        if (doOpen) {
            this.openLink(this.clickedLink);
        }
        this.clickedLink = null;
        this.minecraft.n_1700_B(this);
    }

    private void openLink(URI uri) {
        j_3341_s.t_148_a().n_1700_B(uri);
    }

    @Override
    public void openLinkInvoker(URI uri) {
        this.openLink(uri);
    }

    public static boolean hasControlDown() {
        if (MinecraftClient.n_1700_B) {
            return Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 343) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 347);
        }
        return Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 341) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 345);
    }

    public static boolean hasShiftDown() {
        return Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 340) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 344);
    }

    public static boolean hasAltDown() {
        return Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 342) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 346);
    }

    public static boolean isCut(int keyCode) {
        return keyCode == 88 && k_2603_m.hasControlDown() && !k_2603_m.hasShiftDown() && !k_2603_m.hasAltDown();
    }

    public static boolean isPaste(int keyCode) {
        return keyCode == 86 && k_2603_m.hasControlDown() && !k_2603_m.hasShiftDown() && !k_2603_m.hasAltDown();
    }

    public static boolean isCopy(int keyCode) {
        return keyCode == 67 && k_2603_m.hasControlDown() && !k_2603_m.hasShiftDown() && !k_2603_m.hasAltDown();
    }

    public static boolean isSelectAll(int keyCode) {
        return keyCode == 65 && k_2603_m.hasControlDown() && !k_2603_m.hasShiftDown() && !k_2603_m.hasAltDown();
    }

    public void resize(MinecraftClient minecraft, int width, int height) {
        this.init(minecraft, width, height);
    }

    public static void wrapScreenError(Runnable action, String errorDesc, String screenName) {
        try {
            action.run();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, errorDesc);
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Affected screen");
            crashreportcategory.n_1700_B("Screen name", () -> screenName);
            throw new ReportedException(crashreport);
        }
    }

    protected boolean isValidCharacterForName(String text, char charTyped, int cursorPos) {
        int i = text.indexOf(58);
        int j = text.indexOf(47);
        if (charTyped == ':') {
            return (j == -1 || cursorPos <= j) && i == -1;
        }
        if (charTyped == '/') {
            return cursorPos > i;
        }
        return charTyped == '_' || charTyped == '-' || charTyped >= 'a' && charTyped <= 'z' || charTyped >= '0' && charTyped <= '9' || charTyped == '.';
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return true;
    }

    public void addPacks(List<Path> packs) {
    }
}



