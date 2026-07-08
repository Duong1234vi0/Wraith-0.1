/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Suppliers
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.UserApiService
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_320
 *  net.minecraft.class_320$class_321
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_5520
 *  net.minecraft.class_7569
 *  net.minecraft.class_7574
 *  net.minecraft.class_7853
 *  org.joml.Matrix4f
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.mainmenu;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Easy.Direction;
import code.alphadlc.common.animation.Easy.EaseBackIn;
import code.alphadlc.mixins.IMinecraftClient;
import code.alphadlc.utils.client.managers.file.impl.AccountFile;
import code.alphadlc.utils.client.managers.file.impl.account.Account;
import code.alphadlc.utils.client.managers.file.impl.account.AccountRepository;
import code.alphadlc.utils.display.atlasfont.msdf.MsdfFont;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.StartupBackgroundRenderer;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.systemrender.builders.Builder;
import code.alphadlc.utils.display.render.systemrender.builders.states.QuadColorState;
import code.alphadlc.utils.display.render.systemrender.builders.states.QuadRadiusState;
import code.alphadlc.utils.display.render.systemrender.builders.states.SizeState;
import code.alphadlc.utils.display.render.systemrender.renderers.impl.BuiltText;
import code.alphadlc.utils.display.render.systemrender.renderers.impl.BuiltTexture;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import com.google.common.base.Suppliers;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_5520;
import net.minecraft.class_7569;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class AccountsScreen
extends class_437
implements QuickImports {
    static final float PANEL_WIDTH = 260.0f;
    static final float PANEL_HEIGHT = 340.0f;
    static final float ACCOUNT_SPACING = 30.0f;
    static final int MIN_LENGTH = 3;
    static final int MAX_LENGTH = 16;
    private static final Supplier<MsdfFont> ICONS_FONT_MAIN = Suppliers.memoize(() -> MsdfFont.builder().atlas("iconmain").data("iconmain").build());
    private final class_437 parent;
    private final AccountRepository accountRepository = AlphaDLC.getInstance().getAccountRepository();
    private final Random rand = new Random();
    private final EaseBackIn openAnimation = new EaseBackIn(400, 1.0, 1.15f);
    private String currentAccount = "None";
    private boolean typing;
    private String typedText = "";
    private int cursorPos;
    private int selStart = -1;
    private int selEnd = -1;
    private long lastClick;
    private float textXOffset;
    private boolean dragging;
    private float scroll;
    private float smoothedScroll;
    private float parallaxX;
    private float parallaxY;
    private final Color bgColor = new Color(24, 24, 24, 238);
    private final Color fillColor = new Color(42, 42, 42, 255);
    private final Color outlineColor = new Color(220, 220, 220, 140);
    private final Color textColor = new Color(232, 232, 232, 255);

    public AccountsScreen(class_437 parent) {
        super(class_2561.method_30163((String)"Accounts"));
        this.parent = parent;
        this.openAnimation.setDirection(Direction.FORWARDS);
        this.openAnimation.reset();
    }

    static float panelX(int screenWidth) {
        return (float)screenWidth / 2.0f - 130.0f;
    }

    static float panelY(int screenHeight) {
        return (float)screenHeight / 2.0f - 170.0f;
    }

    protected void method_25426() {
        super.method_25426();
        if (mc.method_1548() != null) {
            this.currentAccount = mc.method_1548().method_1676();
        }
    }

    public void method_25393() {
        super.method_25393();
        for (Account account : this.accountRepository.accountList) {
            float target = account.starred ? 1.0f : 0.0f;
            account.starAnim += (target - account.starAnim) * 0.2f;
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.renderBackgroundLayer(context, mouseX, mouseY);
        float anim = class_3532.method_15363((float)((float)this.openAnimation.getOutput()), (float)0.0f, (float)1.0f);
        float panelX = AccountsScreen.panelX(this.field_22789);
        float panelY = AccountsScreen.panelY(this.field_22790);
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)anim);
        this.renderPanel(context, panelX, panelY);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        matrices.method_22909();
    }

    private void renderBackgroundLayer(class_332 context, int mouseX, int mouseY) {
        class_4587 matrices = context.method_51448();
        float margin = 30.0f;
        float maxParallax = 16.0f;
        float targetParallaxX = class_3532.method_15363((float)(-((float)mouseX / (float)this.field_22789 - 0.5f) * 2.0f * maxParallax), (float)(-maxParallax), (float)maxParallax);
        float targetParallaxY = class_3532.method_15363((float)(-((float)mouseY / (float)this.field_22790 - 0.5f) * 2.0f * maxParallax), (float)(-maxParallax), (float)maxParallax);
        this.parallaxX += (targetParallaxX - this.parallaxX) * 0.08f;
        this.parallaxY += (targetParallaxY - this.parallaxY) * 0.08f;
        this.parallaxX = class_3532.method_15363((float)this.parallaxX, (float)(-margin), (float)margin);
        this.parallaxY = class_3532.method_15363((float)this.parallaxY, (float)(-margin), (float)margin);
        this.drawFullscreenBackgroundTexture(matrices, StartupBackgroundRenderer.BACKGROUND, -margin + this.parallaxX, -margin + this.parallaxY, (float)this.field_22789 + margin * 2.0f, (float)this.field_22790 + margin * 2.0f, 1.0f);
        rectangle.render(ShapeProperties.create(matrices, -1.0, -1.0, this.field_22789 + 2, this.field_22790 + 2).color(new Color(0, 0, 0, 70).getRGB()).build());
    }

    private void drawFullscreenBackgroundTexture(class_4587 matrices, class_2960 id, float x, float y, float w, float h, float alpha) {
        int glId = mc.method_1531().method_4619(id).method_4624();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)class_3532.method_15363((float)alpha, (float)0.0f, (float)1.0f));
        ((BuiltTexture)Builder.texture().size(new SizeState(w, h)).radius(QuadRadiusState.NO_ROUND).color(QuadColorState.WHITE).smoothness(1.0f).texture(0.0f, 0.0f, 1.0f, 1.0f, glId).build()).render(matrices.method_23760().method_23761(), x, y, 0.0f);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderPanel(class_332 context, float panelX, float panelY) {
        class_4587 matrices = context.method_51448();
        rectangle.render(ShapeProperties.create(matrices, panelX + 130.0f - 30.0f, panelY - 18.0f, 60.0, 16.0).color(this.bgColor.getRGB()).round(3.0f).build());
        Fonts.getSize(18, Fonts.Type.DEFAULT).drawCenteredString(matrices, "Accounts", panelX + 130.0f, panelY - 14.0f, this.textColor.getRGB());
        rectangle.render(ShapeProperties.create(matrices, panelX, panelY, 260.0, 340.0).color(this.bgColor.getRGB()).round(8.0f).build());
        float listY = panelY + 10.0f;
        float listHeight = 248.0f;
        float inputY = panelY + 340.0f - 58.0f;
        float inputX = panelX + 10.0f;
        float inputW = 240.0f;
        float textFieldW = inputW - 34.0f;
        this.solidOutlinedRect(matrices, inputX, inputY, textFieldW, 26.0f, 6.0f);
        this.solidOutlinedRect(matrices, inputX + textFieldW + 6.0f, inputY + 3.0f, 22.0f, 22.0f, 5.0f);
        ((BuiltText)Builder.text().font(ICONS_FONT_MAIN.get()).text("D").size(16.0f).color(this.textColor.getRGB()).build()).render(matrices.method_23760().method_23761(), inputX + textFieldW + 10.0f, inputY + 5.0f);
        this.renderTextField(context, inputX + 4.0f, inputY + 5.0f, textFieldW - 8.0f, 22.0f);
        this.solidOutlinedRect(matrices, panelX + 10.0f, panelY + 340.0f - 24.0f, 240.0f, 18.0f, 5.0f);
        Fonts.getSize(16, Fonts.Type.DEFAULT).drawCenteredString(matrices, "Back", panelX + 130.0f, panelY + 340.0f - 19.0f, this.textColor.getRGB());
        Matrix4f positionMatrix = matrices.method_23760().method_23761();
        ScissorAssist scissor = AlphaDLC.getInstance().getScissorManager();
        scissor.push(positionMatrix, panelX + 8.0f, listY, 244.0f, listHeight);
        float maxScrollClamp = Math.max(0.0f, (float)this.accountRepository.accountList.size() * 30.0f - listHeight);
        this.scroll = class_3532.method_15363((float)this.scroll, (float)0.0f, (float)maxScrollClamp);
        this.smoothedScroll = class_3532.method_16439((float)0.1f, (float)this.smoothedScroll, (float)this.scroll);
        for (int i = 0; i < this.accountRepository.accountList.size(); ++i) {
            Account account = this.accountRepository.accountList.get(i);
            float accY = panelY + 14.0f + (float)i * 30.0f - this.smoothedScroll;
            if (accY + 26.0f < listY || accY > listY + listHeight) continue;
            this.solidOutlinedRect(matrices, panelX + 10.0f, accY, 240.0f, 26.0f, 6.0f);
            this.solidOutlinedRect(matrices, panelX + 14.0f, accY + 3.0f, 20.0f, 20.0f, 10.0f);
            Color starColor = this.interpolateColor(this.textColor, new Color(255, 255, 0, this.textColor.getAlpha()), account.starAnim);
            Fonts.getSize(28, Fonts.Type.ICONS).drawString(matrices, "S", panelX + 260.0f - 36.0f, accY + 8.0f, starColor.getRGB());
            this.drawAccountFace(context, account, panelX + 15.0f, accY + 4.5f);
            Fonts.getSize(16, Fonts.Type.SEMI).drawString(matrices, account.name, panelX + 40.0f, accY + 10.0f, this.textColor.getRGB());
        }
        scissor.pop();
        if (this.accountRepository.accountList.size() > 7) {
            this.renderScrollbar(matrices, panelX, listY, listHeight);
        }
        String currentText = "Current account - " + this.currentAccount;
        FontRenderer font = Fonts.getSize(16, Fonts.Type.SEMI);
        float currentWidth = font.getStringWidth(currentText) + 24.0f;
        float currentX = panelX + 130.0f - currentWidth / 2.0f;
        rectangle.render(ShapeProperties.create(matrices, currentX, panelY + 340.0f + 8.0f, currentWidth, 16.0).color(this.bgColor.getRGB()).round(3.0f).build());
        font.drawCenteredString(matrices, currentText, panelX + 130.0f, panelY + 340.0f + 12.0f, this.textColor.getRGB());
    }

    private void solidOutlinedRect(class_4587 matrices, float x, float y, float w, float h, float round) {
        rectangle.render(ShapeProperties.create(matrices, x, y, w, h).thickness(2.0f).round(round).outlineColor(this.outlineColor.getRGB()).color(this.fillColor.getRGB()).build());
    }

    private void renderTextField(class_332 context, float textFieldX, float textFieldY, float textFieldWidth, float textFieldHeight) {
        FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
        boolean blink = System.currentTimeMillis() % 1000L < 500L;
        context.method_44379((int)textFieldX, (int)textFieldY, (int)(textFieldX + textFieldWidth), (int)(textFieldY + textFieldHeight));
        if (this.typing && this.hasSelection()) {
            int start = Math.min(this.selStart, this.selEnd);
            int end = Math.max(this.selStart, this.selEnd);
            float selXStart = textFieldX + 2.0f - this.textXOffset + font.getStringWidth(this.typedText.substring(0, start));
            float selWidth = font.getStringWidth(this.typedText.substring(start, end));
            rectangle.render(ShapeProperties.create(context.method_51448(), selXStart, textFieldY + 1.0f, selWidth, textFieldHeight - 2.0f).color(-11172376).build());
        }
        if (!this.typedText.isEmpty() || this.typing) {
            font.drawString(context.method_51448(), this.typedText, textFieldX + 2.0f - this.textXOffset, textFieldY + 6.0f, this.textColor.getRGB());
        } else {
            font.drawString(context.method_51448(), "Typing nickname", textFieldX + 2.0f, textFieldY + 6.0f, this.textColor.getRGB());
        }
        if (this.typing && blink && !this.hasSelection()) {
            float cursorX = textFieldX + 2.0f - this.textXOffset + font.getStringWidth(this.typedText.substring(0, this.cursorPos));
            rectangle.render(ShapeProperties.create(context.method_51448(), cursorX, textFieldY + 3.0f, 0.5, textFieldHeight - 6.0f).color(this.textColor.getRGB()).build());
        }
        context.method_44380();
    }

    private void renderScrollbar(class_4587 matrices, float panelX, float listY, float listHeight) {
        float contentHeight = (float)this.accountRepository.accountList.size() * 30.0f;
        float maxScroll = Math.max(0.0f, contentHeight - listHeight);
        this.scroll = class_3532.method_15363((float)this.scroll, (float)0.0f, (float)maxScroll);
        float scrollbarWidth = 3.0f;
        float scrollbarX = panelX + 260.0f - scrollbarWidth - 12.0f;
        float scrollbarY = listY + 1.0f;
        float scrollbarHeight = listHeight - 2.0f;
        rectangle.render(ShapeProperties.create(matrices, scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight).round(1.0f).color(new Color(30, 30, 30, 100).getRGB()).build());
        float handleHeight = Math.max(24.0f, listHeight * (listHeight / (contentHeight + listHeight)));
        float scrollRatio = maxScroll > 0.0f ? this.smoothedScroll / maxScroll : 0.0f;
        float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
        rectangle.render(ShapeProperties.create(matrices, scrollbarX, handleY, scrollbarWidth, handleHeight).round(1.0f).color(new Color(100, 100, 100, 150).getRGB()).build());
    }

    private void drawAccountFace(class_332 context, Account account, float x, float y) {
        GameProfile profile = new GameProfile(UUID.fromString(account.uuid), account.name);
        class_2960 skinTexture = class_310.method_1551().method_1582().method_52862(profile).comp_1626();
        if (skinTexture == null) {
            skinTexture = class_2960.method_60655((String)"minecraft", (String)"textures/entity/steve.png");
        }
        Render2D.drawTexture(context, skinTexture, x, y, 18.0f, 8.0f, 8, 8, 64, ColorAssist.getRect(1.0f), -1);
    }

    private Color interpolateColor(Color start, Color end, float t) {
        int r = (int)((float)start.getRed() + (float)(end.getRed() - start.getRed()) * t);
        int g = (int)((float)start.getGreen() + (float)(end.getGreen() - start.getGreen()) * t);
        int b = (int)((float)start.getBlue() + (float)(end.getBlue() - start.getBlue()) * t);
        int a = (int)((float)start.getAlpha() + (float)(end.getAlpha() - start.getAlpha()) * t);
        return new Color(r, g, b, a);
    }

    private boolean isHover(double mouseX, double mouseY, float x, float y, float w, float h) {
        return mouseX >= (double)x && mouseX <= (double)(x + w) && mouseY >= (double)y && mouseY <= (double)(y + h);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (this.openAnimation.getOutput() < (double)0.95f) {
            return true;
        }
        float panelX = AccountsScreen.panelX(this.field_22789);
        float panelY = AccountsScreen.panelY(this.field_22790);
        float inputY = panelY + 340.0f - 58.0f;
        float inputX = panelX + 10.0f;
        float inputW = 240.0f;
        float textFieldW = inputW - 34.0f;
        if (button == 0 && this.isHover(mouseX, mouseY, panelX + 10.0f, panelY + 340.0f - 24.0f, 240.0f, 18.0f)) {
            mc.method_1507(this.parent);
            return true;
        }
        if (button == 0 && this.isHover(mouseX, mouseY, inputX + 4.0f, inputY + 5.0f, textFieldW - 8.0f, 22.0f)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastClick < 250L) {
                this.selStart = 0;
                this.selEnd = this.typedText.length();
            } else {
                this.typing = true;
                this.selStart = this.cursorPos = this.getCursorIndexAt(mouseX);
                this.selEnd = this.cursorPos;
                this.lastClick = currentTime;
            }
            this.dragging = true;
            return true;
        }
        this.typing = false;
        this.clearSelection();
        if (button == 0 && this.isHover(mouseX, mouseY, inputX + textFieldW + 6.0f, inputY + 3.0f, 22.0f, 22.0f)) {
            this.addRandomAccount();
            return true;
        }
        float listY = panelY + 10.0f;
        float listHeight = 248.0f;
        for (int i = 0; i < this.accountRepository.accountList.size(); ++i) {
            float accY = panelY + 14.0f + (float)i * 30.0f - this.smoothedScroll;
            if (accY + 26.0f < listY || accY > listY + listHeight) continue;
            float starX = panelX + 260.0f - 36.0f;
            if (button == 0 && this.isHover(mouseX, mouseY, starX, accY + 8.0f, 18.0f, 18.0f)) {
                Account account = this.accountRepository.accountList.get(i);
                account.starred = !account.starred;
                this.accountRepository.accountList.sort((a1, a2) -> Boolean.compare(a2.starred, a1.starred));
                this.saveAccounts();
                return true;
            }
            if (!this.isHover(mouseX, mouseY, panelX + 10.0f, accY, 240.0f, 26.0f)) continue;
            if (button == 0) {
                this.selectAccount(this.accountRepository.accountList.get(i));
            } else if (button == 1) {
                this.accountRepository.accountList.remove(i);
                this.saveAccounts();
            }
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontal, double vertical) {
        float panelX = AccountsScreen.panelX(this.field_22789);
        float panelY = AccountsScreen.panelY(this.field_22790);
        float listY = panelY + 10.0f;
        float listHeight = 248.0f;
        float contentHeight = (float)this.accountRepository.accountList.size() * 30.0f;
        float maxScroll = Math.max(0.0f, contentHeight - listHeight);
        if (maxScroll > 0.0f && this.isHover(mouseX, mouseY, panelX + 8.0f, listY, 244.0f, listHeight)) {
            this.scroll = (float)((double)this.scroll - vertical * 24.0);
            this.scroll = class_3532.method_15363((float)this.scroll, (float)0.0f, (float)maxScroll);
            return true;
        }
        return super.method_25401(mouseX, mouseY, horizontal, vertical);
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.dragging && button == 0) {
            this.selEnd = this.cursorPos = this.getCursorIndexAt(mouseX);
            return true;
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        this.dragging = false;
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.typing && this.typedText.length() < 16) {
            this.deleteSelectedText();
            this.typedText = this.typedText.substring(0, this.cursorPos) + chr + this.typedText.substring(this.cursorPos);
            ++this.cursorPos;
            this.clearSelection();
            this.updateTextXOffset();
            return true;
        }
        return super.method_25400(chr, modifiers);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.typing) {
            if (class_437.method_25441()) {
                switch (keyCode) {
                    case 65: {
                        this.selStart = 0;
                        this.selEnd = this.typedText.length();
                        return true;
                    }
                    case 67: {
                        if (this.hasSelection()) {
                            GLFW.glfwSetClipboardString((long)mc.method_22683().method_4490(), (CharSequence)this.getSelectedText());
                        }
                        return true;
                    }
                    case 86: {
                        String clipboard = GLFW.glfwGetClipboardString((long)mc.method_22683().method_4490());
                        if (clipboard != null) {
                            this.deleteSelectedText();
                            this.typedText = this.typedText.substring(0, this.cursorPos) + clipboard + this.typedText.substring(this.cursorPos);
                            this.cursorPos += clipboard.length();
                            this.clearSelection();
                            this.updateTextXOffset();
                        }
                        return true;
                    }
                }
            }
            switch (keyCode) {
                case 259: {
                    if (this.hasSelection()) {
                        this.deleteSelectedText();
                    } else if (this.cursorPos > 0) {
                        this.typedText = this.typedText.substring(0, this.cursorPos - 1) + this.typedText.substring(this.cursorPos);
                        --this.cursorPos;
                    }
                    this.updateTextXOffset();
                    return true;
                }
                case 263: {
                    if (this.cursorPos > 0) {
                        --this.cursorPos;
                    }
                    this.updateSelectionAfterMove();
                    this.updateTextXOffset();
                    return true;
                }
                case 262: {
                    if (this.cursorPos < this.typedText.length()) {
                        ++this.cursorPos;
                    }
                    this.updateSelectionAfterMove();
                    this.updateTextXOffset();
                    return true;
                }
                case 257: {
                    if (this.typedText.length() >= 3 && this.typedText.length() <= 16) {
                        this.addAccount(this.typedText);
                    }
                    return true;
                }
            }
        }
        if (keyCode == 256) {
            mc.method_1507(this.parent);
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private void addAccount(String name) {
        String offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8)).toString();
        this.accountRepository.accountList.add(new Account(name, false, false, null, offlineUuid, "0"));
        this.accountRepository.accountList.sort((a1, a2) -> Boolean.compare(a2.starred, a1.starred));
        this.saveAccounts();
        this.typedText = "";
        this.cursorPos = 0;
        this.typing = false;
        this.clearSelection();
    }

    private void addRandomAccount() {
        String finalUsername = null;
        StringBuilder username = new StringBuilder();
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        char[] consonants = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
        int attempts = 0;
        do {
            username.setLength(0);
            int length = 6 + this.rand.nextInt(5);
            boolean startWithVowel = this.rand.nextBoolean();
            for (int i = 0; i < length; ++i) {
                if (i % 2 == 0) {
                    username.append(startWithVowel ? vowels[this.rand.nextInt(vowels.length)] : consonants[this.rand.nextInt(consonants.length)]);
                    continue;
                }
                username.append(startWithVowel ? consonants[this.rand.nextInt(consonants.length)] : vowels[this.rand.nextInt(vowels.length)]);
            }
            if (this.rand.nextInt(100) < 30) {
                username.append(this.rand.nextInt(100));
            }
            String tempUsername = username.substring(0, 1).toUpperCase() + username.substring(1);
            ++attempts;
            if (!this.accountRepository.accountList.stream().noneMatch(account -> account.name.equals(tempUsername))) continue;
            finalUsername = tempUsername;
            break;
        } while (attempts < 10);
        if (finalUsername == null) {
            finalUsername = username.substring(0, 1).toUpperCase() + username.substring(1) + System.currentTimeMillis() % 1000L;
        }
        this.addAccount(finalUsername);
    }

    private void selectAccount(Account account) {
        for (Account other : this.accountRepository.accountList) {
            other.active = other == account;
        }
        this.currentAccount = account.name;
        this.setSession(account);
        this.saveAccounts();
    }

    private void saveAccounts() {
        try {
            AlphaDLC.getInstance().getFileController().saveFile(AccountFile.class);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void setSession(Account account) {
        class_320 newSession = new class_320(account.name, UUID.fromString(account.uuid), "0", Optional.empty(), Optional.empty(), class_320.class_321.field_1988);
        IMinecraftClient mca = (IMinecraftClient)class_310.method_1551();
        mca.setSessionT(newSession);
        class_310.method_1551().method_53462().getProperties().clear();
        UserApiService apiService = UserApiService.OFFLINE;
        mca.setUserApiService(apiService);
        mca.setSocialInteractionsManagerT(new class_5520(class_310.method_1551(), apiService));
        mca.setProfileKeys(class_7853.method_46532((UserApiService)apiService, (class_320)newSession, (Path)class_310.method_1551().field_1697.toPath()));
        mca.setAbuseReportContextT(class_7574.method_44599((class_7569)class_7569.method_44586(), (UserApiService)apiService));
    }

    private boolean hasSelection() {
        return this.selStart != this.selEnd;
    }

    private String getSelectedText() {
        int start = Math.min(this.selStart, this.selEnd);
        int end = Math.max(this.selStart, this.selEnd);
        return this.typedText.substring(start, end);
    }

    private void deleteSelectedText() {
        if (this.hasSelection()) {
            int start = Math.min(this.selStart, this.selEnd);
            int end = Math.max(this.selStart, this.selEnd);
            this.typedText = this.typedText.substring(0, start) + this.typedText.substring(end);
            this.cursorPos = start;
            this.clearSelection();
        }
    }

    private void clearSelection() {
        this.selStart = this.cursorPos;
        this.selEnd = this.cursorPos;
    }

    private void updateSelectionAfterMove() {
        if (class_437.method_25442()) {
            this.selEnd = this.cursorPos;
        } else {
            this.clearSelection();
        }
    }

    private int getCursorIndexAt(double mouseX) {
        int pos;
        float textFieldX = AccountsScreen.panelX(this.field_22789) + 14.0f;
        FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
        float relX = (float)mouseX - textFieldX - 2.0f + this.textXOffset;
        for (pos = 0; pos < this.typedText.length() && !(font.getStringWidth(this.typedText.substring(0, pos + 1)) > relX); ++pos) {
        }
        return pos;
    }

    private void updateTextXOffset() {
        float textFieldWidth = 190.0f;
        FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
        float cursorX = font.getStringWidth(this.typedText.substring(0, this.cursorPos));
        if (cursorX < this.textXOffset) {
            this.textXOffset = cursorX;
        } else if (cursorX > this.textXOffset + textFieldWidth - 10.0f) {
            this.textXOffset = cursorX - (textFieldWidth - 10.0f);
        }
    }

    public boolean method_25422() {
        return false;
    }

    public boolean method_25421() {
        return false;
    }
}
